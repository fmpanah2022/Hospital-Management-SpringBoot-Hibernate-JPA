package org.j2os.controller;

import org.j2os.common.ObjectMapper;
import org.j2os.domain.View.InPatientBillView;
import org.j2os.domain.entity.InPatient;
import org.j2os.domain.entity.InPatientBill;
import org.j2os.domain.entity.InPatientBillDetail;
import org.j2os.domain.entity.Staff;
import org.j2os.service.modern.InPatientBillService;
import org.j2os.service.Logic.InPatientBillLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/inPatientBill")
public class InPatientBillController {
    @Autowired
    private InPatientBillService inPatientBillService;
    @Autowired
    private InPatientBillLogic inPatientBillLogic;
    //*****************************************
    @GetMapping("/inPatientBills")
    public ResponseEntity<List<Map<String, Object>>> getAllPatientBills() {
        try {
            List<InPatientBillView> inPatientBills = inPatientBillService.findInPatientBills();
            if (inPatientBills.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inPatientBillService.convertBillListToMapList(inPatientBills), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByInPatientId")
    public ResponseEntity<Map<String, Object>> findByInPatientId(@RequestParam("inPatientId") String inPatientId) {
        try {
            InPatientBillView inPatientBill = inPatientBillService.findInPatientBillByInPatientId(Long.valueOf(inPatientId));
            if (inPatientBill.getInPatientBillId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inPatientBillService.convertBillToMap(inPatientBill), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByStaffId")
    public ResponseEntity<List<Map<String, Object>>> findByStaffId(@RequestParam("staffId") String staffId) {
        try {
            List<InPatientBillView> inPatientBills = inPatientBillService.findInPatientBillsByStaffId(Long.valueOf(staffId));
            if (inPatientBills.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inPatientBillService.convertBillListToMapList(inPatientBills), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByTotalAmountIsBetween")
    public ResponseEntity<List<Map<String, Object>>> findByTotalAmountIsBetween(@RequestParam("fromAmount") String fromAmount,
                                                                          @RequestParam("toAmount") String toAmount) {
        try {
            List<InPatientBillView> inPatientBills = inPatientBillService.findInPatientBillsByTotalAmountIsBetween(Integer.parseInt(fromAmount) , Integer.parseInt(toAmount));
            if (inPatientBills.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inPatientBillService.convertBillListToMapList(inPatientBills), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/save")
    public ResponseEntity<Map<String, Object>> saveInPatientBillWithDetail( @RequestParam("inPatientId") String inPatientId,
                                                                      @RequestParam("staffId") String staffId,
                                                                      @RequestParam List<InPatientBillDetail> inPatientBillDetails) {
        try {
            // date sets from system date
            InPatientBill inPatientBill = ObjectMapper.getInstance().mapToInPatientBill(staffId,inPatientId);
            return inPatientBillLogic.saveInPatientBillWithDetail(inPatientBill , inPatientBillDetails);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/remove")
    public ResponseEntity<HttpStatus> removeLogicalInPatientBill(@RequestParam("inPatientBillId") String inPatientBillId,
                                                                 @RequestParam("inPatientBillVno") String inPatientBillVno)  {
        Optional<InPatientBill> data = inPatientBillService.findInPatientBillByInPatientBillIdAndRemoveDateTimeIsNull(Long.valueOf(inPatientBillId));
        if (data.isPresent()) {
            InPatientBill inPatientBill1 = data.get();
            inPatientBill1.setVersionNumber(Integer.parseInt(inPatientBillVno));
            return inPatientBillLogic.removeLogicalInPatientBill(inPatientBill1);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
