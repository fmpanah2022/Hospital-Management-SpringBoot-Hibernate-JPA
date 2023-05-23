package org.j2os.service.Implementation;

import org.j2os.domain.entity.Payment;
import org.j2os.repository.Classic.PaymentClassicRepository;
import org.j2os.repository.modern.PaymentRepository;
import org.j2os.service.modern.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PaymentServiceImp implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentClassicRepository paymentClassicRepository;
    //------------------------------------------------------------------------------
    @Override
    public Map<String, Object> convertPaymentToMap(Payment payment) {
        Map<String, Object> data = new HashMap<>();
        data.put("paymentId",payment.getPaymentId());
        data.put("inPatientBillId", payment.getInPatientBillId());
        data.put("paymentDate", payment.getPaymentDate());
        data.put("paymentAmount", payment.getPaymentAmount());
        data.put("payer", payment.getPayer());
        data.put("cashierId" , payment.getCashier().getCashierId());
        data.put("cashierFirstName" , payment.getCashier().getPerson().getFirstName());
        data.put("cashierSurname" , payment.getCashier().getPerson().getSurname());
        data.put("cashierPersonalCode" , payment.getCashier().getPersonalCode());
        data.put("paymentTypeId", payment.getPaymentType().getPaymentTypeId());
        data.put("paymentTypeDesc", payment.getPaymentType().getPaymentTypeDesc());
        data.put("versionNumber", payment.getVersionNumber());
        return data;
    }

    @Override
    public List<Map<String, Object>> convertPaymentListToMapList(List<Payment> payments) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (Payment payment : payments) {
            Map<String, Object> data = new HashMap<>();
            data.put("paymentId",payment.getPaymentId());
            data.put("inPatientBillId", payment.getInPatientBillId());
            data.put("paymentDate", payment.getPaymentDate());
            data.put("paymentAmount", payment.getPaymentAmount());
            data.put("payer", payment.getPayer());
            data.put("cashierId" , payment.getCashier().getCashierId());
            data.put("cashierFirstName" , payment.getCashier().getPerson().getFirstName());
            data.put("cashierSurname" , payment.getCashier().getPerson().getSurname());
            data.put("cashierPersonalCode" , payment.getCashier().getPersonalCode());
            data.put("paymentTypeId", payment.getPaymentType().getPaymentTypeId());
            data.put("paymentTypeDesc", payment.getPaymentType().getPaymentTypeDesc());
            data.put("versionNumber", payment.getVersionNumber());
            dataList.add(data);
        }
        return dataList;
    }
    @Override
    public List<Payment> findPaymentsByRemoveDateTimeIsNull() {
        return paymentRepository.findPaymentsByRemoveDateTimeIsNull();
    }
    @Override
    public List<Payment> findPaymentsByPayerAndRemoveDateTimeIsNull(String payer) {
        return paymentRepository.findPaymentsByPayerAndRemoveDateTimeIsNull(payer);
    }

    @Override
    public List<Payment> findPaymentsByInPatientBillIdAndRemoveDateTimeIsNull(Long inPatientBillId) {
        return paymentRepository.findPaymentsByInPatientBillIdAndRemoveDateTimeIsNull(inPatientBillId);
    }

    @Override
    public List<Payment> findPaymentsByPaymentType_PaymentTypeIdAndRemoveDateTimeIsNull(Long paymentTypeId) {
        return paymentRepository.findPaymentsByPaymentType_PaymentTypeIdAndRemoveDateTimeIsNull(paymentTypeId);
    }

    @Override
    public List<Payment> findPaymentsByCashier_CashierIdAndRemoveDateTimeIsNull(Long cashierId) {
        return paymentRepository.findPaymentsByCashier_CashierIdAndRemoveDateTimeIsNull(cashierId);
    }

    @Override
    public Optional<Payment> findPaymentByPaymentIdAndRemoveDateTimeIsNull(Long paymentId) {
        return paymentRepository.findPaymentByPaymentIdAndRemoveDateTimeIsNull(paymentId);
    }

    @Override
    public List<Payment> findPaymentsByPaymentAmountIsBetweenAndRemoveDateTimeIsNull(long fromAmount, long toAmount) {
        return paymentRepository.findPaymentsByPaymentAmountIsBetweenAndRemoveDateTimeIsNull(fromAmount, toAmount);
    }

    @Override
    @Transactional
    public Payment save(Payment payment) {
        return paymentClassicRepository.save(payment);
    }

    @Override
    @Transactional
    public Payment update(Payment payment) {
        return paymentClassicRepository.update(payment);
    }

    @Override
    @Transactional
    public void removeLogical(Payment payment) {
        paymentClassicRepository.removeLogical(payment);
    }

    @Override
    public boolean existsPayment(Payment payment) {
        return false;
    }

    @Override
    public boolean conflictPayment(Payment payment) {
        return false;
    }

    @Override
    public boolean canRemove(Payment payment) {
        return true;
    }
}
