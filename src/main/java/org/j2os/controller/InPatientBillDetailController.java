package org.j2os.controller;

import org.j2os.common.ObjectMapper;
import org.j2os.domain.entity.InPatientBillDetail;
import org.j2os.domain.View.InPatientBillDetailView;
import org.j2os.service.modern.InPatientBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/billDetail")
public class InPatientBillDetailController {
    @Autowired
    private InPatientBillDetailService detailService;
    //*****************************************
    @GetMapping("/findByInPatientBillId")
    public ResponseEntity<List<Map<String, Object>>> findByInPatientBillId(@RequestParam("inPatientBillId") String inPatientBillId) {
        try {
            List<InPatientBillDetailView> billDetails = detailService.findBillDetailsByBillId(Long.valueOf(inPatientBillId));
            if (billDetails.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(detailService.convertBillDetailListToMapList(billDetails), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //**************************************************************************************************
    @GetMapping("/save")
    public ResponseEntity<Map<String, Object>> saveInPatientBillDetail(  @RequestParam("inPatientBillId") String inPatientBillId,
                                                                         @RequestParam("serviceId") String serviceId ,
                                                                         @RequestParam("serviceCount") String serviceCount,
                                                                         @RequestParam("serviceUnitCost") String serviceUnitCost) {
        try {
            InPatientBillDetail detail = ObjectMapper.getInstance().mapToInPatientBillDetail(inPatientBillId, serviceId, serviceCount, serviceUnitCost);
            InPatientBillDetail savedData = detailService.save(detail);
            if (savedData.getInPatientBillDetailId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(detailService.convertBillDetailToMap(detailService.findBillDetailByDetailId(savedData.getInPatientBillDetailId())), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //**********************************************************************************************
    @GetMapping("/update")
    public ResponseEntity<Map<String, Object>> updateInPatientBillDetail(  @RequestParam("inPatientBillDetailId") String inPatientBillDetailId,
                                                                           @RequestParam("serviceId") String serviceId ,
                                                                           @RequestParam("serviceCount") String serviceCount,
                                                                           @RequestParam("serviceUnitCost") String serviceUnitCost,
                                                                           @RequestParam("detailVno") String detailVno)  {
        Optional<InPatientBillDetail> data = detailService.findInPatientBillDetailByInPatientBillDetailIdAndRemoveDateTimeIsNull(Long.valueOf(inPatientBillDetailId));
        if (data.isPresent()) {
            InPatientBillDetail detail1 = data.get();
            detail1.builder().versionNumber(Integer.parseInt(detailVno)).serviceId(Long.valueOf(serviceId)).serviceCount(Integer.parseInt(serviceCount)).
                                                                         serviceUnitCost(Integer.parseInt(serviceUnitCost)).build();
            InPatientBillDetail savedData = detailService.update(detail1);
            return new ResponseEntity<>(detailService.convertBillDetailToMap(detailService.findBillDetailByDetailId(savedData.getInPatientBillDetailId())), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //***************************************************************
    @GetMapping("/remove")
    public ResponseEntity<HttpStatus> removeLogicalInPatientBillDetail(@RequestParam("inPatientBillDetailId") String inPatientBillDetailId,
                                                                       @RequestParam("detailVno") String detailVno)  {
        Optional<InPatientBillDetail> data = detailService.findInPatientBillDetailByInPatientBillDetailIdAndRemoveDateTimeIsNull(Long.valueOf(inPatientBillDetailId));
        if (data.isPresent()) {
            InPatientBillDetail detail1 = data.get();
            detail1.setVersionNumber(Integer.parseInt(detailVno));
            if (!detailService.canRemove(detail1)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            detailService.removeLogical(detail1);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
