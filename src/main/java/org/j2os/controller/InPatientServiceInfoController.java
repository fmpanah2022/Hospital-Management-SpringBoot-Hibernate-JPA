package org.j2os.controller;

import org.j2os.domain.entity.InPatientServiceInfo;
import org.j2os.service.modern.InPatientServiceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/inPatientService")
public class InPatientServiceInfoController {
    @Autowired
    private InPatientServiceInfoService inServiceInfoService;
    //*****************************************
    @GetMapping("/findByInPatientId")
    public ResponseEntity<List<Map<String, Object>>> findByInPatientId(@RequestParam("inPatientId") String inPatientId) {
        try {
            List<Map<String, Object>> inPatientServiceInfos = inServiceInfoService.findInPatientServiceInfosByInPatientId(Long.valueOf(inPatientId));
            if (inPatientServiceInfos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inPatientServiceInfos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findByServiceId")
    public ResponseEntity<List<Map<String, Object>>> findByServiceId(@RequestParam("serviceId") String serviceId) {
        try {
            List<Map<String, Object>> inPatientServiceInfos = inServiceInfoService.findServiceInfoByServiceId(Long.valueOf(serviceId));
            if (inPatientServiceInfos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inPatientServiceInfos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/saveList")
    public ResponseEntity<List<Map<String, Object>>> saveInPatientServiceInfoList( @RequestParam("inPatientId") String inPatientId,
                                                                                   @RequestBody List<InPatientServiceInfo> inPatientServiceInfos) {
        try {
            List<InPatientServiceInfo> savedList = inServiceInfoService.saveList(Long.valueOf(inPatientId),inPatientServiceInfos);
            if (savedList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inServiceInfoService.findInPatientServiceInfosByInPatientId(Long.valueOf(inPatientId)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //**************************************************************************************************
    @GetMapping("/save")
    public ResponseEntity<Map<String, Object>> saveInPatientServiceInfo( @RequestParam("inPatientId") String inPatientId,
                                                                         @RequestParam("serviceId") String serviceId ,
                                                                         @RequestParam("count") String count,
                                                                         @RequestParam("serviceUnitCost") String serviceUnitCost ) {
        try {
            InPatientServiceInfo inPatientServiceInfo1 = new InPatientServiceInfo().builder().serviceUnitCost(Integer.parseInt(serviceUnitCost)).count(Integer.parseInt(count)).serviceId(Long.valueOf(serviceId)).inPatientId(Long.valueOf(inPatientId)).build();
            InPatientServiceInfo savedData = inServiceInfoService.save(inPatientServiceInfo1);
            if (savedData.getInPatientServiceInfoId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inServiceInfoService.findInPatientServiceInfoById(savedData.getInPatientServiceInfoId()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //**********************************************************************************************
    @GetMapping("/update")
    public ResponseEntity<Map<String, Object>> updateInPatientServiceInfo(@RequestParam("inPatientServiceInfoId") String inPatientServiceInfoId,
                                                                           @RequestParam("serviceId") String serviceId,
                                                                           @RequestParam("count") String count,
                                                                           @RequestParam("serviceUnitCost") String serviceUnitCost,
                                                                           @RequestParam("infoVno") String infoVno)  {
        Optional<InPatientServiceInfo> data = inServiceInfoService.findInPatientServiceInfoByInPatientServiceInfoIdAndRemoveDateTimeIsNull(Long.valueOf(inPatientServiceInfoId));
        if (data.isPresent()) {
            InPatientServiceInfo inPatientServiceInfo1 = data.get();
            inPatientServiceInfo1.builder().versionNumber(Integer.parseInt(infoVno)).serviceId(Long.valueOf(serviceId))
                                           .count(Integer.parseInt(count)).serviceUnitCost(Integer.parseInt(serviceUnitCost)).build();
            InPatientServiceInfo savedData = inServiceInfoService.update(inPatientServiceInfo1);
            return new ResponseEntity<>(inServiceInfoService.findInPatientServiceInfoById(savedData.getInPatientServiceInfoId()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //***************************************************************
    @GetMapping("/remove")
    public ResponseEntity<HttpStatus> removeLogicalInPatientServiceServiceInfo(@RequestParam("inPatientServiceInfoId") String inPatientServiceInfoId,
                                                                               @RequestParam("infoVno") String infoVno)  {
        Optional<InPatientServiceInfo> data = inServiceInfoService.findInPatientServiceInfoByInPatientServiceInfoIdAndRemoveDateTimeIsNull(Long.valueOf(inPatientServiceInfoId));
        if (data.isPresent()) {
            InPatientServiceInfo inPatientServiceInfo1 = data.get();
            inPatientServiceInfo1.setVersionNumber(Integer.parseInt(infoVno));
            if (!inServiceInfoService.canRemove(inPatientServiceInfo1)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            inServiceInfoService.removeLogical(inPatientServiceInfo1);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
