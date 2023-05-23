package org.j2os.service.modern;

import org.j2os.domain.entity.Payment;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PaymentService {
    Map<String, Object> convertPaymentToMap(Payment payment);
    List<Map<String, Object>> convertPaymentListToMapList(List<Payment> payments);
    List<Payment> findPaymentsByRemoveDateTimeIsNull();
    List<Payment> findPaymentsByPayerAndRemoveDateTimeIsNull(String payer);
    List<Payment> findPaymentsByInPatientBillIdAndRemoveDateTimeIsNull(Long inPatientBillId);
    List<Payment> findPaymentsByPaymentType_PaymentTypeIdAndRemoveDateTimeIsNull(Long paymentTypeId);
    List<Payment> findPaymentsByCashier_CashierIdAndRemoveDateTimeIsNull(Long cashierId);
    Optional<Payment> findPaymentByPaymentIdAndRemoveDateTimeIsNull(Long paymentId);
    List<Payment> findPaymentsByPaymentAmountIsBetweenAndRemoveDateTimeIsNull(long fromAmount , long toAmount);
    Payment save(Payment payment);
    Payment update(Payment payment);
    void removeLogical(Payment payment);
    boolean existsPayment ( Payment payment);
    boolean conflictPayment ( Payment payment);
    boolean canRemove( Payment payment);
}
