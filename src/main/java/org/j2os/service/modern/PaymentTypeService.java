package org.j2os.service.modern;

import org.j2os.domain.entity.PaymentType;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PaymentTypeService {
    Map<String, Object> convertPaymentTypeToMap(PaymentType paymentType);
    List<Map<String, Object>> convertPaymentTypeListToMapList(List<PaymentType> paymentTypes);
    PaymentType findPaymentTypeByPaymentTypeDescAndRemoveDateTimeIsNull(String paymentTypeDesc);
    List<PaymentType> findPaymentTypesByRemoveDateTimeIsNull();
    Optional<PaymentType> findPaymentTypeByPaymentTypeIdAndRemoveDateTimeIsNull(Long paymentTypeId);
    PaymentType save(PaymentType paymentType);
    PaymentType update(PaymentType paymentType);
    void removeLogical(PaymentType paymentType);
    boolean existsPaymentType ( PaymentType paymentType) ;
    boolean conflictPaymentType ( PaymentType paymentType);
    boolean canRemove( PaymentType paymentType) ;
}
