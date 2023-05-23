package org.j2os.service.Implementation;

import org.j2os.domain.entity.PaymentType;
import org.j2os.repository.Classic.PaymentTypeClassicRepository;
import org.j2os.repository.modern.PaymentRepository;
import org.j2os.repository.modern.PaymentTypeRepository;
import org.j2os.service.modern.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentTypeServiceImp implements PaymentTypeService {
    @Autowired
    private PaymentTypeRepository paymentTypeRepository;
    @Autowired
    private PaymentTypeClassicRepository paymentTypeClassicRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    //-------------------------------------------------------------------
    @Override
    public Map<String, Object> convertPaymentTypeToMap(PaymentType paymentType) {
        Map<String, Object> data = new HashMap<>();
        data.put("paymentTypeId", paymentType.getPaymentTypeId());
        data.put("paymentTypeDesc", paymentType.getPaymentTypeDesc());
        data.put("versionNumber", paymentType.getVersionNumber());
        return data;
    }
    @Override
    public List<Map<String, Object>> convertPaymentTypeListToMapList(List<PaymentType> paymentTypes) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (PaymentType paymentType : paymentTypes) {
            Map<String, Object> data = new HashMap<>();
            data.put("paymentTypeId", paymentType.getPaymentTypeId());
            data.put("paymentTypeDesc", paymentType.getPaymentTypeDesc());
            data.put("versionNumber", paymentType.getVersionNumber());
            dataList.add(data);
        }
        return dataList;
    }
    @Override
    public PaymentType findPaymentTypeByPaymentTypeDescAndRemoveDateTimeIsNull(String paymentTypeDesc) {
        return paymentTypeRepository.findPaymentTypeByPaymentTypeDescAndRemoveDateTimeIsNull(paymentTypeDesc);
    }

    @Override
    public List<PaymentType> findPaymentTypesByRemoveDateTimeIsNull() {
        return paymentTypeRepository.findPaymentTypesByRemoveDateTimeIsNull();
    }

    @Override
    public Optional<PaymentType> findPaymentTypeByPaymentTypeIdAndRemoveDateTimeIsNull(Long paymentTypeId) {
        return paymentTypeRepository.findPaymentTypeByPaymentTypeIdAndRemoveDateTimeIsNull(paymentTypeId);
    }

    @Override
    public PaymentType save(PaymentType paymentType) {
        return paymentTypeClassicRepository.save(paymentType);
    }

    @Override
    public PaymentType update(PaymentType paymentType) {
        return paymentTypeClassicRepository.update(paymentType);
    }

    @Override
    public void removeLogical(PaymentType paymentType) {
        paymentTypeClassicRepository.removeLogical(paymentType);
    }

    @Override
    public boolean existsPaymentType(PaymentType paymentType)  {
        return paymentTypeRepository.findPaymentTypeByPaymentTypeDescAndRemoveDateTimeIsNull(paymentType.getPaymentTypeDesc()) != null;
    }

    @Override
    public boolean conflictPaymentType(PaymentType paymentType)  {
        PaymentType paymentType1 = paymentTypeRepository.findPaymentTypeByPaymentTypeDescAndRemoveDateTimeIsNull(paymentType.getPaymentTypeDesc());
        if  (  paymentType1 != null && paymentType1.getPaymentTypeId() != null)
            return( paymentType1.getPaymentTypeId() != paymentType.getPaymentTypeId()) ;
        else return false;
    }

    @Override
    public boolean canRemove(PaymentType paymentType) {
        return paymentRepository.findPaymentsByPaymentType_PaymentTypeIdAndRemoveDateTimeIsNull(paymentType.getPaymentTypeId()).isEmpty();
    }
}
