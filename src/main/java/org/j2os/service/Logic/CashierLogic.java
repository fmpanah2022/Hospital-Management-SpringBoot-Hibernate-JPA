package org.j2os.service.Logic;

import org.j2os.domain.entity.Cashier;
import org.j2os.service.modern.CashierService;
import org.j2os.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class CashierLogic {
    @Autowired
    private CashierService cashierService;
    @Autowired
    private CommonService commonService;
    //*****************************************************
    public ResponseEntity<Map<String, Object>> saveCashier (Cashier cashier) {
            if (cashierService.existsCashier(cashier)) {
                return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            cashier.setPersonalCode(commonService.createPersonalCode(cashier.getPerson().getNationalCode(),"Cashier"));
            Cashier savedCashier = cashierService.save(cashier);
            if (savedCashier.getCashierId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cashierService.convertCashierToMap(savedCashier), HttpStatus.OK);
    }
    //**********************************************************************************************
    public ResponseEntity<Map<String, Object>> updateCashier(Cashier cashier) {
            if (cashierService.conflictCashier(cashier)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Cashier savedCashier = cashierService.update(cashier);
            return new ResponseEntity<>(cashierService.convertCashierToMap(savedCashier), HttpStatus.OK);
    }
    //***************************************************************
    public ResponseEntity<HttpStatus> removeLogicalCashier(Cashier cashier)  {
            if (!cashierService.canRemove(cashier)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            cashierService.removeLogical(cashier);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
