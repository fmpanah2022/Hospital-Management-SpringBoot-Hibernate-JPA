package org.j2os.service.Logic;

import org.j2os.domain.entity.Payment;
import org.j2os.service.modern.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class PaymentLogic {
    @Autowired
    private PaymentService paymentService;
    //---------------------------------------------------
    public ResponseEntity<Map<String, Object>> savePayment(Payment payment) {
            if (paymentService.existsPayment(payment)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Payment savedPayment = paymentService.save(payment);
            if (savedPayment.getPaymentId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(paymentService.convertPaymentToMap(savedPayment), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> updatePayment(Payment payment) {
            if (paymentService.conflictPayment(payment)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Payment savedPayment = paymentService.update(payment);
            return new ResponseEntity<>(paymentService.convertPaymentToMap(savedPayment), HttpStatus.OK);
    }
    public ResponseEntity<HttpStatus> removeLogicalPayment(Payment payment)  {
            if (!paymentService.canRemove(payment)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            paymentService.removeLogical(payment);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
