package org.j2os.service.Implementation;

import org.j2os.domain.entity.InPatientBill;
import org.j2os.domain.entity.InPatientBillDetail;
import org.j2os.domain.View.InPatientBillView;
import org.j2os.repository.Classic.InPatientBillClassicRepository;
import org.j2os.repository.modern.InPatientBillDetailRepository;
import org.j2os.repository.modern.InPatientBillRepository;
import org.j2os.service.modern.InPatientBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class InPatientBillServiceImp implements InPatientBillService {
    @Autowired
    private InPatientBillRepository inPatientBillRepository;
    @Autowired
    private InPatientBillClassicRepository inPatientBillClassicRepository;
    @Autowired
    private InPatientBillDetailRepository inPatientBillDetailRepository;
    //--------------------------------------------------------------------------------
    @Override
    public Map<String, Object> convertBillToMap(InPatientBillView inPatientBillView) {
        Map<String, Object> data = new HashMap<>();
        data.put("inPatientBillId",inPatientBillView.getInPatientBillId());
        data.put("inPatientBillDate", inPatientBillView.getInPatientBillDate());
        data.put("totalAmount", inPatientBillView.getTotalAmount());
        data.put("inPatientId", inPatientBillView.getInPatientId());
        data.put("inPatientDate",inPatientBillView.getInPatientDate());
        data.put("patientId",inPatientBillView.getPatientId());
        data.put("patientName",inPatientBillView.getPatientName());
        data.put("patientNationalCode", inPatientBillView.getPatientNationalCode());
        data.put("staffId", inPatientBillView.getStaffId());
        data.put("staffName", inPatientBillView.getStaffName());
        data.put("staffPersonalCode",inPatientBillView.getStaffPersonalCode());
        data.put("billVno", inPatientBillView.getBillVno());
        return data;
    }

    @Override
    public List<Map<String, Object>> convertBillListToMapList(List<InPatientBillView> inPatientBills) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (InPatientBillView inPatientBillView : inPatientBills) {
            Map<String, Object> data = new HashMap<>();
            data.put("inPatientBillId",inPatientBillView.getInPatientBillId());
            data.put("inPatientBillDate", inPatientBillView.getInPatientBillDate());
            data.put("totalAmount", inPatientBillView.getTotalAmount());
            data.put("inPatientId", inPatientBillView.getInPatientId());
            data.put("inPatientDate",inPatientBillView.getInPatientDate());
            data.put("patientId",inPatientBillView.getPatientId());
            data.put("patientName",inPatientBillView.getPatientName());
            data.put("patientNationalCode", inPatientBillView.getPatientNationalCode());
            data.put("staffId", inPatientBillView.getStaffId());
            data.put("staffName", inPatientBillView.getStaffName());
            data.put("staffPersonalCode",inPatientBillView.getStaffPersonalCode());
            data.put("billVno", inPatientBillView.getBillVno());
            dataList.add(data);
        }
        return dataList;
    }

    @Override
    public List<InPatientBillView> findInPatientBills() {
        return inPatientBillClassicRepository.findInPatientBills();
    }

    @Override
    public InPatientBillView findInPatientBillByInPatientId(Long inPatientId) {
        return inPatientBillClassicRepository.findInPatientBillByInPatientId(inPatientId);
    }

    @Override
    public List<InPatientBillView> findInPatientBillsByStaffId(Long staffId) {
        return inPatientBillClassicRepository.findInPatientBillsByStaffId(staffId);
    }

    @Override
    public List<InPatientBillView> findInPatientBillsByTotalAmountIsBetween(int fromAmount, int toAmount) {
        return inPatientBillClassicRepository.findInPatientBillsByTotalAmountIsBetween(fromAmount, toAmount);
    }

    @Override
    public InPatientBillView findInPatientBillsByInPatientBillId(Long inPatientBillId) {
        return inPatientBillClassicRepository.findInPatientBillsByInPatientBillId(inPatientBillId);
    }
    @Override
    public Optional<InPatientBill> findInPatientBillByInPatientBillIdAndRemoveDateTimeIsNull(Long inPatientBillId) {
        return inPatientBillRepository.findInPatientBillByInPatientBillIdAndRemoveDateTimeIsNull(inPatientBillId);
    }

    @Override
    @Transactional
    public InPatientBill save(InPatientBill inPatientBill , List<InPatientBillDetail> inPatientBillDetails) {
        return inPatientBillClassicRepository.save(inPatientBill, inPatientBillDetails);
    }

    @Override
    @Transactional
    public InPatientBill update(InPatientBill inPatientBill) {
        return inPatientBillClassicRepository.update(inPatientBill);
    }

    @Override
    @Transactional
    public void removeLogical(InPatientBill inPatientBill) {
        inPatientBillClassicRepository.removeLogical(inPatientBill);
    }

    @Override
    public boolean existsInPatientBill(InPatientBill inPatientBill) {
        return (findInPatientBillByInPatientId(inPatientBill.getInPatient().getInPatientId()) != null);
    }

    @Override
    public boolean conflictInPatientBill(InPatientBill inPatientBill) {
        InPatientBillView inPatientBill1 = findInPatientBillByInPatientId(inPatientBill.getInPatient().getInPatientId());
        if  (  inPatientBill1 != null && inPatientBill1.getInPatientBillId() != null)
            return( inPatientBill1.getInPatientBillId() != inPatientBill.getInPatientBillId()) ;
        else return false;
    }

    @Override
    public boolean canRemove(InPatientBill inPatientBill) {
        return true;
       // return findInPatientBillsByInPatientBillIdAndRemoveDateTimeIsNullAndPaymentsIsNull(inPatientBill.getInPatientBillId()).isEmpty();
    }
}
