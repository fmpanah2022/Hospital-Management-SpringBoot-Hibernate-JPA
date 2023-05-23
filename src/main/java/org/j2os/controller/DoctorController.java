package org.j2os.controller;

import org.j2os.common.ObjectMapper;
import org.j2os.domain.entity.Doctor;
import org.j2os.domain.entity.Gender;
import org.j2os.domain.entity.Person;
import org.j2os.domain.entity.Users;
import org.j2os.service.modern.DoctorService;
import org.j2os.service.Logic.DoctorLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private DoctorLogic doctorLogic;
//*****************************************
    @GetMapping("/doctors")
    public ResponseEntity<List<Map<String, Object>>> getAllDoctors() {
        try {
            List<Doctor> doctors = doctorService.findDoctorsByRemoveDateTimeIsNull();
            if (doctors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(doctorService.convertDoctorListToMapList(doctors), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByFirstNameAndSurnameAndNationalCode")
    public ResponseEntity<Map<String, Object>> findDoctorByPerson_FirstNameAndPerson_SurnameAndPerson_NationalCode(
            @RequestParam("firstName") String firstName,
            @RequestParam("surname") String surname,
            @RequestParam("nationalCode") String nationalCode) {
        try {
            Doctor doctor1 = doctorService.findDoctorByPerson_FirstNameAndPerson_SurnameAndPerson_NationalCodeAndRemoveDateTimeIsNull(firstName, surname, Integer.parseInt(nationalCode));
            if (doctor1.getDoctorId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(doctorService.convertDoctorToMap(doctor1), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cause description here");
    @GetMapping("/saveWithSpecList")
    public ResponseEntity<Map<String, Object>> saveDoctorWithSpecList(@RequestParam("firstName") String firstName,
                                             @RequestParam("surname") String surname,
                                             @RequestParam("nationalCode") String nationalCode,
                                             @RequestParam("genderId") String genderId,
                                             @RequestParam("userName") String userName,
                                             @RequestParam("password") String password,
                                             @RequestParam("specList") List<String> specList) {
        try {
            Doctor doctor = ObjectMapper.getInstance().mapToDoctor(firstName,surname,nationalCode,genderId,userName,password);
            return doctorLogic.saveDoctorWithSpecList(doctor,specList);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//**************************************************************************************************
    @GetMapping("/save")
    public ResponseEntity<Map<String, Object>> saveDoctor(@RequestParam("firstName") String firstName,
                                             @RequestParam("surname") String surname,
                                             @RequestParam("nationalCode") String nationalCode,
                                             @RequestParam("genderId") String genderId,
                                             @RequestParam("userName") String userName,
                                             @RequestParam("password") String password) {
        try {
            Doctor doctor = ObjectMapper.getInstance().mapToDoctor(firstName,surname,nationalCode,genderId,userName,password);
            return doctorLogic.saveDoctor(doctor);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //**********************************************************************************************
    @GetMapping("/update")
    public ResponseEntity<Map<String, Object>> update(@RequestParam("doctorId") String doctorId,
                                         @RequestParam("firstName") String firstName,
                                         @RequestParam("surname") String surname,
                                         @RequestParam("nationalCode") String nationalCode,
                                         @RequestParam("genderId") String genderId,
                                         @RequestParam("userId") String userId,
                                         @RequestParam("password") String password,
                                         @RequestParam("doctorVno") String doctorVno,
                                         @RequestParam("userVno") String userVno,
                                         @RequestParam("personVno") String personVno) throws Exception {
        Optional<Doctor> doctorData = doctorService.findByDoctorIdAndRemoveDateTimeIsNull(Long.valueOf(doctorId));
        if (doctorData.isPresent()) {
            Gender gender = new Gender().builder().genderId(Long.valueOf(genderId)).build();
            Users user = new Users().builder().versionNumber(Integer.parseInt(userVno)).userId(Long.parseLong(userId)).password(password).build();
            Person person = new Person().builder().versionNumber(Integer.parseInt(personVno)).user(user).firstName(firstName).surname(surname).nationalCode(Integer.parseInt(nationalCode)).gender(gender).build();
            Doctor doctor1 = doctorData.get();
            doctor1.builder().person(person).versionNumber(Integer.parseInt(doctorVno)).build();
            return doctorLogic.updateDoctor(doctor1);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //***************************************************************
    @GetMapping("/remove")
    public ResponseEntity<HttpStatus> removeLogicalDoctor(@RequestParam("doctorId") String doctorId,
                                                          @RequestParam("doctorVno") String doctorVno) throws Exception {
        Optional<Doctor> doctorData = doctorService.findByDoctorIdAndRemoveDateTimeIsNull(Long.valueOf(doctorId));
        if (doctorData.isPresent()) {
            Doctor doctor1 = doctorData.get();
            doctor1.setVersionNumber(Integer.parseInt(doctorVno));
            return doctorLogic.removeLogicalDoctor(doctor1);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //*********************************************************************
    @GetMapping("/findByNationalCode")
    public ResponseEntity<Map<String, Object>> findByNationalCode(@RequestParam("nationalCode") String nationalCode) {
        try {
            Doctor doctor1 = doctorService.findDoctorByPerson_NationalCodeAndRemoveDateTimeIsNull(Integer.parseInt(nationalCode));
            if (doctor1.getDoctorId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(doctorService.convertDoctorToMap(doctor1), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByPersonalCode")
    public ResponseEntity<Map<String, Object>> findByPersonalCode(@RequestParam("personalCode") String personalCode) {
        try {
            Doctor doctor1 = doctorService.findDoctorByPersonalCodeAndRemoveDateTimeIsNull(personalCode);
            if (doctor1.getDoctorId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(doctorService.convertDoctorToMap(doctor1), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
