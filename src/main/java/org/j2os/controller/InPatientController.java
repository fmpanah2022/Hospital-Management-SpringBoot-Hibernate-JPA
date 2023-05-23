package org.j2os.controller;

import org.j2os.common.ObjectMapper;
import org.j2os.domain.entity.*;
import org.j2os.service.modern.InPatientService;
import org.j2os.service.Logic.InPatientLogic;
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
@RequestMapping("/inPatient")
public class InPatientController {
    @Autowired
    private InPatientService inPatientService;
    @Autowired
    private InPatientLogic inPatientLogic;
    //*****************************************
    @GetMapping("/inPatients")
    public ResponseEntity<List<Map<String, Object>>> getAllPatients() {
        try {
            List<InPatient> inPatients = inPatientService.findInPatientsByRemoveDateTimeIsNull();
            if (inPatients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inPatientService.convertInPatientListToMapList(inPatients), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByRoomId")
    public ResponseEntity<List<Map<String, Object>>> findByRoomId(@RequestParam("roomId") String roomId) {
        try {
            List<InPatient> inPatients = inPatientService.findInPatientsByRoom_RoomIdAndRemoveDateTimeIsNull(Long.valueOf(roomId));
            if (inPatients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inPatientService.convertInPatientListToMapList(inPatients), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByDoctorId")
    public ResponseEntity<List<Map<String, Object>>> findByDoctorId(@RequestParam("doctorId") String doctorId) {
        try {
            List<InPatient> inPatients = inPatientService.findInPatientsByDoctor_DoctorIdAndRemoveDateTimeIsNull(Long.valueOf(doctorId));
            if (inPatients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inPatientService.convertInPatientListToMapList(inPatients), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByPatientId")
    public ResponseEntity<List<Map<String, Object>>> findByPatientId(@RequestParam("patientId") String patientId) {
        try {
            List<InPatient> inPatients = inPatientService.findInPatientsByPatient_PatientIdAndRemoveDateTimeIsNull(Long.valueOf(patientId));
            if (inPatients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inPatientService.convertInPatientListToMapList(inPatients), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByStaffId")
    public ResponseEntity<List<Map<String, Object>>> findByStaffId(@RequestParam("staffId") String staffId) {
        try {
            List<InPatient> inPatients = inPatientService.findInPatientsByStaff_StaffIdAndRemoveDateTimeIsNull(Long.valueOf(staffId));
            if (inPatients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inPatientService.convertInPatientListToMapList(inPatients), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByNationalCode")
    public ResponseEntity<List<Map<String, Object>>> findByNationalCode(@RequestParam("nationalCode") String nationalCode) {
        try {
            List<InPatient> inPatients = inPatientService.findInPatientsByPatient_Person_NationalCodeAndRemoveDateTimeIsNull(Integer.parseInt(nationalCode));
            if (inPatients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inPatientService.convertInPatientListToMapList(inPatients), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cause description here");
    @GetMapping("/save")
    public ResponseEntity<Map<String, Object>> saveInPatient(@RequestParam("patientId") String patientId,
                                             @RequestParam("doctorId") String doctorId,
                                             @RequestParam("staffId") String staffId,
                                             @RequestParam("roomId") String roomId) {
        try {
            // date sets from system date and set available room to '0'
            InPatient inPatient = ObjectMapper.getInstance().mapToInPatient(staffId,doctorId,roomId,patientId);
            return inPatientLogic.saveInPatient(inPatient);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
/// Check
    @GetMapping("/saveWithMedicalInfo")
    public ResponseEntity<Map<String, Object>> saveInPatientWithMedicalInfo(@RequestParam("patientId") String patientId,
                                                   @RequestParam("doctorId") String doctorId,
                                                   @RequestParam("staffId") String staffId,
                                                   @RequestParam("roomId") String roomId,
                                                   @RequestParam List<String> medicineCodeList) {
        try {
            // date sets from system date and set available room to '0'
            InPatient inPatient = ObjectMapper.getInstance().mapToInPatient(staffId,doctorId,roomId,patientId);
            return inPatientLogic.saveInPatientWithMedicalInfo(inPatient,medicineCodeList);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //**********************************************************************************************
    @GetMapping("/update")
    public ResponseEntity<Map<String, Object>> updateInPatient(@RequestParam("inPatientId") String inPatientId,
                                                     @RequestParam("patientId") String patientId,
                                                     @RequestParam("doctorId") String doctorId,
                                                     @RequestParam("staffId") String staffId,
                                                     @RequestParam("roomId") String roomId,
                                                     @RequestParam("inPatientVno") String inPatientVno) {
        Optional<InPatient> inPatientData = inPatientService.findByInPatientIdAndRemoveDateTimeIsNull(Long.valueOf(inPatientId));
        if (inPatientData.isPresent()) {
            InPatient inPatient1 = inPatientData.get();
            Staff staff = new Staff().builder().staffId(Long.valueOf(staffId)).build();
            Doctor doctor = new Doctor().builder().doctorId(Long.valueOf(doctorId)).build();
            Room room = new Room().builder().roomId(Long.valueOf(roomId)).build();
            Patient patient = new Patient().builder().patientId(Long.valueOf(patientId)).build();

            inPatient1.builder().patient(patient).inPatientId(Long.valueOf(patientId)).versionNumber(Integer.parseInt(inPatientVno))
                               .doctor(doctor).room(room).staff(staff).build();
            return inPatientLogic.updateInPatient(inPatient1);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //***************************************************************
    @GetMapping("/remove")
    public ResponseEntity<HttpStatus> removeLogicalDoctor(@RequestParam("inPatientId") String inPatientId,
                                                          @RequestParam("inPatientVno") String inPatientVno)  {
        Optional<InPatient> inPatientData = inPatientService.findByInPatientIdAndRemoveDateTimeIsNull(Long.valueOf(inPatientId));
        if (inPatientData.isPresent()) {
            InPatient inPatient1 = inPatientData.get();
            inPatient1.setVersionNumber(Integer.parseInt(inPatientVno));
            return inPatientLogic.removeLogicalDoctor(inPatient1);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
