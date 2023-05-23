package org.j2os.controller;

import org.j2os.common.ObjectMapper;
import org.j2os.domain.View.OutPatientView;
import org.j2os.domain.entity.Doctor;
import org.j2os.domain.entity.InPatient;
import org.j2os.domain.entity.OutPatient;
import org.j2os.domain.entity.OutPatientMedicalInfo;
import org.j2os.service.Logic.OutPatientLogic;
import org.j2os.service.modern.OutPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/outPatient")
public class OutPatientController {
    @Autowired
    private OutPatientService outPatientService;
    @Autowired
    private OutPatientLogic outPatientLogic;
//*****************************************
    @GetMapping("/outPatients")
    public ResponseEntity<List<Map<String, Object>>> getAllOutPatients() {
        try {
            List<OutPatientView> outPatients = outPatientService.findOutPatients();
            if (outPatients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(outPatientService.convertOutPatientListToMapList(outPatients), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findByDoctorId")
    public ResponseEntity<List<Map<String, Object>>> findByDoctorId(@RequestParam("doctorId") String doctorId) {
        try {
            List<OutPatientView> outPatients =  outPatientService.findByDoctorId(Long.valueOf(doctorId));
            if ( outPatients.isEmpty() ) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(outPatientService.convertOutPatientListToMapList(outPatients), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findByInPatientId")
    public ResponseEntity<Map<String, Object>> findByInPatientId(@RequestParam("inPatientId") String inPatientId) {
        try {
            OutPatientView outPatient1 =  outPatientService.findByInPatientId(Long.valueOf(inPatientId));
            if ( outPatient1.getOutPatientId() == null ) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(outPatientService.convertOutPatientToMap(outPatient1), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByPatientNationalCode")
    public ResponseEntity<List<Map<String, Object>>> findByPatientNationalCode(@RequestParam("patientNationalCode") String patientNationalCode) {
        try {
            List<OutPatientView> outPatients =  outPatientService.findByPatient_NationalCode(Integer.parseInt(patientNationalCode));
            if ( outPatients.isEmpty() ) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(outPatientService.convertOutPatientListToMapList(outPatients), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByPatientId")
    public ResponseEntity<List<Map<String, Object>>> findByPatientId(@RequestParam("patientId") String patientId) {
        try {
            List<OutPatientView> outPatients =  outPatientService.findByPatientId(Long.valueOf(patientId));
            if ( outPatients.isEmpty() ) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(outPatientService.convertOutPatientListToMapList(outPatients), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/saveWithMedicalInfo")
    public ResponseEntity<Map<String, Object>> saveOutPatientWithMedicalInfo(@RequestParam("inPatientId") String inPatientId,
                                                                             @RequestParam("doctorId") String doctorId,
                                                                             @RequestBody List<OutPatientMedicalInfo> outPatientMedicalInfos) {
        try {
            OutPatient outPatient = ObjectMapper.getInstance().mapToOutPatient(inPatientId,doctorId);
            return outPatientLogic.saveWithMedicalInfo(outPatient,outPatientMedicalInfos);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //**************************************************************************************************
    @GetMapping("/save")
    public ResponseEntity<Map<String, Object>> saveOutPatient(@RequestParam("inPatientId") String inPatientId,
                                                              @RequestParam("doctorId") String doctorId) {
        try {
            OutPatient outPatient = ObjectMapper.getInstance().mapToOutPatient(inPatientId,doctorId);
            return outPatientLogic.saveOutPatient(outPatient);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //**********************************************************************************************
    @GetMapping("/update")
    public ResponseEntity<Map<String, Object>> updateOutPatient(@RequestParam("outPatientId") String outPatientId,
                                                       @RequestParam("doctorId") String doctorId,
                                                       @RequestParam("outVno") String outVno) {
        Optional<OutPatient> outPatientData = outPatientService.findOutPatientByOutPatientIdAndRemoveDateTimeIsNull(Long.valueOf(outPatientId));
        if (outPatientData.isPresent()) {
            Doctor doctor = new Doctor().builder().doctorId(Long.valueOf(doctorId)).build();
            OutPatient outPatient1 = outPatientData.get();
            outPatient1.builder().versionNumber(Integer.parseInt(outVno)).doctor(doctor).build();
            return outPatientLogic.updateOutPatient(outPatient1);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //***************************************************************
    @GetMapping("/remove")
    public ResponseEntity<HttpStatus> removeLogicalOutPatient(@RequestParam("outPatientId") String outPatientId,
                                                              @RequestParam("outVno") String outVno){
        Optional<OutPatient> outPatientData = outPatientService.findOutPatientByOutPatientIdAndRemoveDateTimeIsNull(Long.valueOf(outPatientId));
        if (outPatientData.isPresent()) {
            OutPatient outPatient1 = outPatientData.get();
            outPatient1.setVersionNumber(Integer.parseInt(outVno));
            return outPatientLogic.removeLogicalOutPatient(outPatient1);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


