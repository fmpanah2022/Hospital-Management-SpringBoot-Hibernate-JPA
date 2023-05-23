package org.j2os.controller;

import org.j2os.common.ObjectMapper;
import org.j2os.domain.entity.Gender;
import org.j2os.domain.entity.Person;
import org.j2os.domain.entity.Staff;
import org.j2os.domain.entity.Users;
import org.j2os.service.Logic.StaffLogic;
import org.j2os.service.modern.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private StaffLogic staffLogic;
    //*****************************************
    @GetMapping("/staffs")
    public ResponseEntity<List<Map<String, Object>>> getAllStaffs() {
        try {
            List<Staff> staffs = staffService.findStaffByRemoveDateTimeIsNull();
            if (staffs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(staffService.convertStaffListToMapList(staffs), HttpStatus.OK);
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
            Staff staff1 = staffService.findStaffByPerson_FirstNameAndPerson_SurnameAndPerson_NationalCodeAndRemoveDateTimeIsNull(firstName, surname, Integer.parseInt(nationalCode));
            if (staff1.getStaffId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(staffService.convertStaffToMap(staff1), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findByNationalCode")
    public ResponseEntity<Map<String, Object>> findByNationalCode(@RequestParam("nationalCode") String nationalCode) {
        try {
            Staff staff1 = staffService.findStaffByPerson_NationalCodeAndRemoveDateTimeIsNull(Integer.parseInt(nationalCode));
            if (staff1.getStaffId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(staffService.convertStaffToMap(staff1), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findByPersonalCode")
    public ResponseEntity<Map<String, Object>> findByPersonalCode(@RequestParam("personalCode") String personalCode) {
        try {
            Staff staff1 = staffService.findStaffByPersonalCodeAndRemoveDateTimeIsNull(personalCode);
            if (staff1.getStaffId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(staffService.convertStaffToMap(staff1), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/save")
    public ResponseEntity<Map<String, Object>> saveStaff ( @RequestParam("firstName") String firstName,
                                             @RequestParam("surname") String surname,
                                             @RequestParam("nationalCode") String nationalCode,
                                             @RequestParam("genderId") String genderId,
                                             @RequestParam("userName") String userName,
                                             @RequestParam("password") String password) {
        try {
            Staff staff = ObjectMapper.OBJECT_MAPPER.mapToPaymentStaff(firstName, surname, nationalCode, genderId, userName, password);
            return staffLogic.saveStaff(staff);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //**********************************************************************************************
    @GetMapping("/update")
    public ResponseEntity<Map<String, Object>> updateStaff(@RequestParam("staffId") String staffId,
                                         @RequestParam("firstName") String firstName,
                                         @RequestParam("surname") String surname,
                                         @RequestParam("nationalCode") String nationalCode,
                                         @RequestParam("genderId") String genderId,
                                         @RequestParam("userId") String userId,
                                         @RequestParam("password") String password,
                                         @RequestParam("staffVno") String staffVno,
                                         @RequestParam("userVno") String userVno,
                                         @RequestParam("personVno") String personVno) {
        Optional<Staff> staffData = staffService.findByStaffIdAndRemoveDateTimeIsNull(Long.valueOf(staffId));
        if (staffData.isPresent()) {
            Gender gender = new Gender().builder().genderId(Long.valueOf(genderId)).build();
            Users user = new Users().builder().versionNumber(Integer.parseInt(userVno)).userId(Long.parseLong(userId)).password(password).build();
            Person person = new Person().builder().user(user).versionNumber(Integer.parseInt(personVno)).firstName(firstName).surname(surname).nationalCode(Integer.parseInt(nationalCode)).gender(gender).build();
            Staff staff1 = staffData.get();
            staff1.builder().person(person).versionNumber(Integer.parseInt(staffVno)).build();
            return staffLogic.updateStaff(staff1);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //***************************************************************
    @GetMapping("/remove")
    public ResponseEntity<HttpStatus> removeLogicalStaff(@RequestParam("staffId") String staffId,
                                                         @RequestParam("staffVno") String staffVno)  {
        Optional<Staff> staffData = staffService.findByStaffIdAndRemoveDateTimeIsNull(Long.valueOf(staffId));
        if (staffData.isPresent()) {
            Staff staff1 = staffData.get();
            staff1.setVersionNumber(Integer.parseInt(staffVno));
            return staffLogic.removeLogicalStaff(staff1);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
