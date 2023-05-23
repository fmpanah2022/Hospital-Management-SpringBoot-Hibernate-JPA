package org.j2os.repository.modern;

import org.j2os.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findPaymentsByPayerAndRemoveDateTimeIsNull(String payer);
    List<Payment> findPaymentsByRemoveDateTimeIsNull();
    List<Payment> findPaymentsByInPatientBillIdAndRemoveDateTimeIsNull(Long inPatientBillId);
    List<Payment> findPaymentsByPaymentType_PaymentTypeIdAndRemoveDateTimeIsNull(Long paymentTypeId);
    List<Payment> findPaymentsByCashier_CashierIdAndRemoveDateTimeIsNull(Long cashierId);
    Optional<Payment> findPaymentByPaymentIdAndRemoveDateTimeIsNull(Long paymentId);
    List<Payment> findPaymentsByPaymentAmountIsBetweenAndRemoveDateTimeIsNull(long fromAmount , long toAmount);
}
