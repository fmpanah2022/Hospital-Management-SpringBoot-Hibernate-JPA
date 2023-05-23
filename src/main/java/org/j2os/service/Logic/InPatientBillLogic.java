package org.j2os.service.Logic;

import org.j2os.domain.entity.InPatientBill;
import org.j2os.domain.entity.InPatientBillDetail;
import org.j2os.service.modern.InPatientBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InPatientBillLogic {
    @Autowired
    private InPatientBillService inPatientBillService;
    //------------------------------------------------------
    public ResponseEntity<Map<String, Object>> saveInPatientBillWithDetail(InPatientBill inPatientBill, List<InPatientBillDetail> inPatientBillDetails) {
            if (inPatientBillService.existsInPatientBill(inPatientBill)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            InPatientBill savedData = inPatientBillService.save(inPatientBill,inPatientBillDetails);
            if (savedData.getInPatientBillId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inPatientBillService.convertBillToMap(inPatientBillService.findInPatientBillsByInPatientBillId(savedData.getInPatientBillId())), HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> removeLogicalInPatientBill(InPatientBill inPatientBill)  {
            if (!inPatientBillService.canRemove(inPatientBill)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            inPatientBillService.removeLogical(inPatientBill);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
