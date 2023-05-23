package org.j2os.controller;

import org.j2os.common.ObjectMapper;
import org.j2os.domain.entity.Payment;
import org.j2os.domain.entity.PaymentType;
import org.j2os.service.Logic.PaymentLogic;
import org.j2os.service.modern.PaymentService;
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
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PaymentLogic paymentLogic;
    //*****************************************
    @GetMapping("/payments")
    public ResponseEntity<List<Map<String, Object>>> getAllPayments() {
        try {
            List<Payment> payments = paymentService.findPaymentsByRemoveDateTimeIsNull();
            if (payments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(paymentService.convertPaymentListToMapList(payments), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByPayer")
    public ResponseEntity<List<Map<String, Object>>> findByPayer(@RequestParam("payer") String payer) {
        try {
            List<Payment> payments = paymentService.findPaymentsByPayerAndRemoveDateTimeIsNull(payer);
            if (payments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(paymentService.convertPaymentListToMapList(payments), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByInPatientBillId")
    public ResponseEntity<List<Map<String, Object>>> findByInPatientBillId(@RequestParam("inPatientBillId") String inPatientBillId) {
        try {
            List<Payment> payments = paymentService.findPaymentsByInPatientBillIdAndRemoveDateTimeIsNull(Long.valueOf(inPatientBillId));
            if (payments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(paymentService.convertPaymentListToMapList(payments), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findByPaymentTypeId")
    public ResponseEntity<List<Map<String, Object>>> findByPaymentTypeId(@RequestParam("paymentTypeId") String paymentTypeId) {
        try {
            List<Payment> payments = paymentService.findPaymentsByPaymentType_PaymentTypeIdAndRemoveDateTimeIsNull(Long.valueOf(paymentTypeId));
            if (payments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(paymentService.convertPaymentListToMapList(payments), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findByCashierId")
    public ResponseEntity<List<Map<String, Object>>> findByCashierId(@RequestParam("cashierId") String cashierId) {
        try {
            List<Payment> payments = paymentService.findPaymentsByCashier_CashierIdAndRemoveDateTimeIsNull(Long.valueOf(cashierId));
            if (payments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(paymentService.convertPaymentListToMapList(payments), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByPaymentAmountIsBetween")
    public ResponseEntity<List<Map<String, Object>>> findByPaymentAmountIsBetween(@RequestParam("fromAmount") String fromAmount,
                                                         @RequestParam("toAmount") String toAmount) {
        try {
            List<Payment> payments = paymentService.findPaymentsByPaymentAmountIsBetweenAndRemoveDateTimeIsNull(Long.valueOf(fromAmount) ,Long.valueOf(toAmount));
            if (payments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(paymentService.convertPaymentListToMapList(payments), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/save")
    public ResponseEntity<Map<String, Object>> savePayment(@RequestParam("inPatientBillId") String inPatientBillId,
                                               @RequestParam("paymentAmount") String paymentAmount,
                                               @RequestParam("payer") String payer,
                                               @RequestParam("paymentTypeId") String paymentTypeId,
                                               @RequestParam("cashierId") String cashierId) {
        try {
            Payment payment = ObjectMapper.getInstance().mapToPayment(cashierId,paymentTypeId,paymentAmount,payer,inPatientBillId);
            return paymentLogic.savePayment(payment);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //**********************************************************************************************
    @GetMapping("/update")
    public ResponseEntity<Map<String, Object>> updatePayment(@RequestParam("paymentId") String paymentId,
                                                 @RequestParam("inPatientBillId") String inPatientBillId,
                                                 @RequestParam("paymentAmount") String paymentAmount,
                                                 @RequestParam("payer") String payer,
                                                 @RequestParam("paymentTypeId") String paymentTypeId,
                                                 @RequestParam("paymentVno") String paymentVno) {
        Optional<Payment> paymentData = paymentService.findPaymentByPaymentIdAndRemoveDateTimeIsNull(Long.valueOf(paymentId));
        if (paymentData.isPresent()) {
            PaymentType paymentType = new PaymentType().builder().paymentTypeId(Long.valueOf(paymentTypeId)).build();
            Payment payment1 = paymentData.get();
            payment1.builder().versionNumber(Integer.parseInt(paymentVno)).inPatientBillId(Long.valueOf(inPatientBillId))
                              .paymentType(paymentType).payer(payer).paymentAmount(Long.parseLong(paymentAmount)).build();
            return paymentLogic.updatePayment(payment1);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //***************************************************************
    @GetMapping("/remove")
    public ResponseEntity<HttpStatus> removeLogicalPayment(@RequestParam("paymentId") String paymentId,
                                                           @RequestParam("paymentVno") String paymentVno)  {
        Optional<Payment> paymentData = paymentService.findPaymentByPaymentIdAndRemoveDateTimeIsNull(Long.valueOf(paymentId));
        if (paymentData.isPresent()) {
            Payment payment1 = paymentData.get();
            payment1.setVersionNumber(Integer.parseInt(paymentVno));
            return paymentLogic.removeLogicalPayment(payment1);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
