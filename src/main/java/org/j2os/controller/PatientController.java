package org.j2os.controller;

import org.j2os.common.ObjectMapper;
import org.j2os.domain.entity.Gender;
import org.j2os.domain.entity.Patient;
import org.j2os.domain.entity.Person;
import org.j2os.service.Logic.PatientLogic;
import org.j2os.service.modern.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientLogic patientLogic;
    //*****************************************
    @GetMapping("/patients")
    public ResponseEntity<List<Map<String, Object>>> getAll() {
        try {
            List<Patient> patients = patientService.findPatientsByRemoveDateTimeIsNull();
            if (patients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(patientService.convertPatientListToMapList(patients), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findByFirstNameAndSurnameAndNationalCode")
    public ResponseEntity<Map<String, Object>> findByFirstNameAndSurnameAndNationalCode(
            @RequestParam("firstName") String firstName,
            @RequestParam("surname") String surname,
            @RequestParam("nationalCode") String nationalCode) {
        try {
            Patient patient1 = patientService.findPatientByPerson_FirstNameAndPerson_SurnameAndPerson_NationalCodeAndRemoveDateTimeIsNull(firstName, surname, Integer.parseInt(nationalCode));
            if (patient1.getPatientId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(patientService.convertPatientToMap(patient1), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByNationalCode")
    public ResponseEntity<Map<String, Object>> findByNationalCode( @RequestParam("nationalCode") String nationalCode) {
        try {
            Patient patient1 = patientService.findPatientByPerson_NationalCodeAndRemoveDateTimeIsNull(Integer.parseInt(nationalCode));
            if (patient1.getPatientId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(patientService.convertPatientToMap(patient1), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/save")
    public ResponseEntity<Map<String, Object>> savePatient(@RequestParam("firstName") String firstName,
                                             @RequestParam("surname") String surname,
                                             @RequestParam("nationalCode") String nationalCode,
                                             @RequestParam("genderId") String genderId) {
        try {
            Patient patient = ObjectMapper.getInstance().mapToPatient(firstName, surname, nationalCode, genderId);
            return patientLogic.savePatient(patient);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //**********************************************************************************************
    @GetMapping("/update")
    public ResponseEntity<Map<String, Object>> updatePatient(@RequestParam("patientId") String patientId,
                                         @RequestParam("firstName") String firstName,
                                         @RequestParam("surname") String surname,
                                         @RequestParam("nationalCode") String nationalCode,
                                         @RequestParam("genderId") String genderId,
                                         @RequestParam("patientVno") String patientVno,
                                         @RequestParam("personVno") String personVno) {
        Optional<Patient> patientData = patientService.findByPatientIdAndRemoveDateTimeIsNull(Long.valueOf(patientId));
        if (patientData.isPresent()) {
            Gender gender = new Gender().builder().genderId(Long.valueOf(genderId)).build();
            Person person = new Person().builder().versionNumber(Integer.parseInt(personVno)).firstName(firstName).surname(surname).nationalCode(Integer.parseInt(nationalCode)).gender(gender).build();
            Patient patient1 = patientData.get();
            patient1.builder().person(person).versionNumber(Integer.parseInt(patientVno)).build();
            return patientLogic.updatePatient(patient1);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //***************************************************************
    @GetMapping("/remove")
    public ResponseEntity<HttpStatus> removeLogicalPatient(@RequestParam("patientId") String patientId,
                                                           @RequestParam("patientVno") String patientVno)  {
        Optional<Patient> patientData = patientService.findByPatientIdAndRemoveDateTimeIsNull(Long.valueOf(patientId));
        if (patientData.isPresent()) {
            Patient patient1 = patientData.get();
            patient1.setVersionNumber(Integer.parseInt(patientVno));
            return patientLogic.removeLogicalPatient(patient1);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
