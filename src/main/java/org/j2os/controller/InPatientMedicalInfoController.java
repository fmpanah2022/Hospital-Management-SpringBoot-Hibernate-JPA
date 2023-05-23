package org.j2os.controller;

import org.j2os.domain.entity.InPatientMedicalInfo;
import org.j2os.service.modern.InPatientMedicalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/inPatientMedical")
public class InPatientMedicalInfoController {
    @Autowired
    private InPatientMedicalInfoService inMedicalService;
    //*****************************************
    @GetMapping("/inPatientMedicals")
    public ResponseEntity<List<InPatientMedicalInfo>> getAll() {
        try {
            List<InPatientMedicalInfo> InPatientMedicalInfos = inMedicalService.findInPatientMedicalInfoByRemoveDateTimeIsNull();
            if (InPatientMedicalInfos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(InPatientMedicalInfos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByInPatientId")
    public ResponseEntity<List<Map<String, Object>>> findByInPatientId(@RequestParam("inPatientId") String inPatientId) {
        try {
            List<Map<String, Object>> inPatientMedicalInfos = inMedicalService.findInPatientMedicalInfosByInPatientId(Long.valueOf(inPatientId));
            if (inPatientMedicalInfos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inPatientMedicalInfos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/saveList")
    public ResponseEntity<List<Map<String, Object>>> saveInPatientMedicalInfoList( @RequestParam("inPatientId") String inPatientId,
                                                                                   @RequestBody List<InPatientMedicalInfo> inPatientMedicalInfos) {
        try {
            List<InPatientMedicalInfo> savedList = inMedicalService.saveList(Long.valueOf(inPatientId),inPatientMedicalInfos);
            if (savedList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inMedicalService.findInPatientMedicalInfosByInPatientId(Long.valueOf(inPatientId)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //**************************************************************************************************
    @GetMapping("/save")
    public ResponseEntity<Map<String, Object>> saveInPatientMedicalInfo( @RequestParam("inPatientId") String inPatientId,
                                                                         @RequestParam("medicineId") String medicineId ,
                                                                         @RequestParam("count") String count) {
        try {
            InPatientMedicalInfo inPatientMedicalInfo1 = new InPatientMedicalInfo().builder().count(Integer.parseInt(count)).medicineId(Long.valueOf(medicineId)).inPatientId(Long.valueOf(inPatientId)).build();
            InPatientMedicalInfo savedInPatientMedicalInfo = inMedicalService.save(inPatientMedicalInfo1);
            if (savedInPatientMedicalInfo.getInPatientMedicalInfoId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inMedicalService.findInPatientMedicalInfoById(savedInPatientMedicalInfo.getInPatientMedicalInfoId()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //**********************************************************************************************
    @GetMapping("/update")
    public ResponseEntity<Map<String, Object>> updateInPatientMedicalInfo( @RequestParam("inPatientMedicalInfoId") String inPatientMedicalInfoId,
                                                                           @RequestParam("medicineId") String medicineId ,
                                                                           @RequestParam("count") String count,
                                                                           @RequestParam("infoVno") String infoVno)  {
        Optional<InPatientMedicalInfo> data = inMedicalService.findInPatientMedicalInfoByInPatientMedicalInfoIdAndRemoveDateTimeIsNull(Long.valueOf(inPatientMedicalInfoId));
        if (data.isPresent()) {
            InPatientMedicalInfo inPatientMedicalInfo1 = data.get();
            inPatientMedicalInfo1.builder().versionNumber(Integer.parseInt(infoVno)).medicineId(Long.valueOf(medicineId)).count(Integer.parseInt(count)).build();
            InPatientMedicalInfo savedInPatientMedicalInfo = inMedicalService.update(inPatientMedicalInfo1);
            return new ResponseEntity<>(inMedicalService.findInPatientMedicalInfoById(savedInPatientMedicalInfo.getInPatientMedicalInfoId()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //***************************************************************
    @GetMapping("/remove")
    public ResponseEntity<HttpStatus> removeLogicalInPatientMedicalInfo(@RequestParam("inPatientMedicalInfoId") String inPatientMedicalInfoId,
                                                                        @RequestParam("infoVno") String infoVno)  {
        Optional<InPatientMedicalInfo> data = inMedicalService.findInPatientMedicalInfoByInPatientMedicalInfoIdAndRemoveDateTimeIsNull(Long.valueOf(inPatientMedicalInfoId));
        if (data.isPresent()) {
            InPatientMedicalInfo inPatientMedicalInfo1 = data.get();
            inPatientMedicalInfo1.setVersionNumber(Integer.parseInt(infoVno));
            inMedicalService.removeLogical(inPatientMedicalInfo1);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
