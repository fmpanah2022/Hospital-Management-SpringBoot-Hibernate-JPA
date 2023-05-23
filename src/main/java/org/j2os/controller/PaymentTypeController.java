package org.j2os.controller;

import org.j2os.domain.entity.PaymentType;
import org.j2os.service.modern.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/paymentType")
public class PaymentTypeController {
    @Autowired
    private PaymentTypeService paymentTypeService;
    //*******************************************************
    @PostMapping("/savePost")
    public ResponseEntity<Map<String, Object>> createPaymentType(@RequestBody PaymentType paymentType) {
        try {
            PaymentType paymentType1 = paymentTypeService.save(paymentType);
            return new ResponseEntity<>(paymentTypeService.convertPaymentTypeToMap(paymentType1), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/paymentTypes")
    public ResponseEntity<List<Map<String, Object>>> getAllPaymentTypes() {
        try {
            List<PaymentType> paymentTypes = paymentTypeService.findPaymentTypesByRemoveDateTimeIsNull();
            if (paymentTypes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(paymentTypeService.convertPaymentTypeListToMapList(paymentTypes), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByPaymentTypeDesc")
    public ResponseEntity<Map<String, Object>> findByPaymentTypeDesc(@RequestParam("paymentTypeDesc") String paymentTypeDesc) {
        try {
            PaymentType paymentType = paymentTypeService.findPaymentTypeByPaymentTypeDescAndRemoveDateTimeIsNull(paymentTypeDesc);
            if (paymentType.getPaymentTypeId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(paymentTypeService.convertPaymentTypeToMap(paymentType), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/save")
    public ResponseEntity<Map<String, Object>>  savePaymentType(@RequestParam("paymentTypeDesc") String paymentTypeDesc ) {
        try {
            PaymentType paymentType1 = new PaymentType().builder().paymentTypeDesc(paymentTypeDesc).build();
            if (paymentTypeService.existsPaymentType(paymentType1)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            PaymentType savedPaymentType = paymentTypeService.save(paymentType1);
            if (savedPaymentType.getPaymentTypeId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(paymentTypeService.convertPaymentTypeToMap(savedPaymentType), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/update")
    public ResponseEntity<Map<String, Object>> updatePaymentType(@RequestParam("paymentTypeId") String paymentTypeId,
                                                                 @RequestParam("paymentTypeDesc") String paymentTypeDesc,
                                                                 @RequestParam("typeVno") String typeVno) {
        Optional<PaymentType> paymentTypeData = paymentTypeService.findPaymentTypeByPaymentTypeIdAndRemoveDateTimeIsNull(Long.valueOf(paymentTypeId));
        if (paymentTypeData.isPresent()) {
            PaymentType paymentType1= paymentTypeData.get();
            paymentType1.builder().paymentTypeDesc(paymentTypeDesc).versionNumber(Integer.parseInt(typeVno)).build();
            if (paymentTypeService.conflictPaymentType(paymentType1)) {  return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }

            PaymentType savedPaymentType = paymentTypeService.update(paymentType1);
            return new ResponseEntity<>(paymentTypeService.convertPaymentTypeToMap(savedPaymentType), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/remove")
    public ResponseEntity<HttpStatus> removeLogicalPaymentType(@RequestParam("paymentTypeId") String paymentTypeId,
                                                               @RequestParam("typeVno") String typeVno) {
        Optional<PaymentType> paymentTypeData = paymentTypeService.findPaymentTypeByPaymentTypeIdAndRemoveDateTimeIsNull(Long.valueOf(paymentTypeId));
        if (paymentTypeData.isPresent()) {
            PaymentType paymentType1 = paymentTypeData.get();
            paymentType1.setVersionNumber(Integer.parseInt(typeVno));
            if (! paymentTypeService.canRemove(paymentType1)) {  return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
            paymentTypeService.removeLogical(paymentType1);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
