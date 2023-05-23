package org.j2os.controller;

import org.j2os.domain.entity.OutPatientMedicalInfo;
import org.j2os.service.modern.OutPatientMedicalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/outPatientMedical")
public class OutPatientMedicalInfoController {
    @Autowired
    private OutPatientMedicalInfoService outMedicalService;
    //*****************************************
    @GetMapping("/findByOutPatientId")
    public ResponseEntity<List<Map<String, Object>>> findByOutPatientId(@RequestParam("outPatientId") String outPatientId) {
        try {
            List<Map<String, Object>> outPatientMedicalInfos = outMedicalService.findOutPatientMedicalInfosByOutPatientId(Long.valueOf(outPatientId));
            if (outPatientMedicalInfos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(outPatientMedicalInfos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //**************************************************************************************************
    @GetMapping("/save")
    public ResponseEntity<Map<String, Object>> saveOutPatientMedicalInfo(  @RequestParam("outPatientId") String outPatientId,
                                                                           @RequestParam("medicineId") String medicineId ,
                                                                           @RequestParam("count") String count) {
        try {
            OutPatientMedicalInfo outPatientMedicalInfo1 = new OutPatientMedicalInfo().builder().count(Integer.parseInt(count)).medicineId(Long.valueOf(medicineId)).outPatientId(Long.valueOf(outPatientId)).build();
            OutPatientMedicalInfo savedData = outMedicalService.save(outPatientMedicalInfo1);
            if (savedData.getOutPatientMedicalInfoId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(outMedicalService.findOutPatientMedicalInfoById(savedData.getOutPatientMedicalInfoId()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //**********************************************************************************************
    @GetMapping("/update")
    public ResponseEntity<Map<String, Object>> updateOutPatientMedicalInfo(  @RequestParam("outPatientMedicalInfoId") String outPatientMedicalInfoId,
                                                                             @RequestParam("medicineId") String medicineId,
                                                                             @RequestParam("count") String count,
                                                                             @RequestParam("infoVno") String infoVno)  {
        Optional<OutPatientMedicalInfo> data = outMedicalService.findOutPatientMedicalInfosByOutPatientMedicalInfoIdAndRemoveDateTimeIsNull(Long.valueOf(outPatientMedicalInfoId));
        if (data.isPresent()) {
            OutPatientMedicalInfo outPatientMedicalInfo1 = data.get();
            outPatientMedicalInfo1.builder().versionNumber(Integer.parseInt(infoVno)).medicineId(Long.valueOf(medicineId))
                                            .count(Integer.parseInt(count)).build();

            OutPatientMedicalInfo savedData = outMedicalService.update(outPatientMedicalInfo1);
            return new ResponseEntity<>(outMedicalService.findOutPatientMedicalInfoById(savedData.getOutPatientMedicalInfoId()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //***************************************************************
    @GetMapping("/remove")
    public ResponseEntity<HttpStatus> removeLogicalOutPatientMedicalInfo(@RequestParam("outPatientMedicalInfoId") String outPatientMedicalInfoId,
                                                                         @RequestParam("infoVno") String infoVno)  {
        Optional<OutPatientMedicalInfo> data = outMedicalService.findOutPatientMedicalInfosByOutPatientMedicalInfoIdAndRemoveDateTimeIsNull(Long.valueOf(outPatientMedicalInfoId));
        if (data.isPresent()) {
            OutPatientMedicalInfo outPatientMedicalInfo1 = data.get();
            outPatientMedicalInfo1.setVersionNumber(Integer.parseInt(infoVno));
            outMedicalService.removeLogical(outPatientMedicalInfo1);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
