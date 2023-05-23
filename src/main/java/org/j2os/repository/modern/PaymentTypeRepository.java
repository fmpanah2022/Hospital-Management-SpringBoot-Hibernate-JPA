package org.j2os.repository.modern;

import org.j2os.domain.entity.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentTypeRepository extends JpaRepository<PaymentType , Long> {
    PaymentType findPaymentTypeByPaymentTypeDescAndRemoveDateTimeIsNull(String paymentTypeDesc);
    List<PaymentType> findPaymentTypesByRemoveDateTimeIsNull();
    Optional<PaymentType> findPaymentTypeByPaymentTypeIdAndRemoveDateTimeIsNull(Long paymentTypeId);
}
