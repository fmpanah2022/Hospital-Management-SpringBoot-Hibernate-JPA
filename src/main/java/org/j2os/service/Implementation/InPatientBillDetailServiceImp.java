package org.j2os.service.Implementation;

import org.j2os.domain.entity.InPatientBillDetail;
import org.j2os.domain.View.InPatientBillDetailView;
import org.j2os.repository.Classic.InPatientBillDetailClassicRepository;
import org.j2os.repository.modern.InPatientBillDetailRepository;
import org.j2os.service.modern.InPatientBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class InPatientBillDetailServiceImp implements InPatientBillDetailService {
    @Autowired
    private InPatientBillDetailRepository inPatientBillDetailRepository;
    @Autowired
    private InPatientBillDetailClassicRepository inPatientBillDetailClassicRepository;
    //-----------------------------------------------------------------------
    @Override
    public boolean existsInPatientBillDetail(InPatientBillDetail inPatientBillDetail) {
        return false;
    }

    @Override
    public boolean conflictInPatientBillDetail(InPatientBillDetail inPatientBillDetail) {
        return false;
    }

    @Override
    public boolean canRemove(InPatientBillDetail inPatientBillDetail) {
        return true;
    }

    @Override
    public Map<String, Object> convertBillDetailToMap(InPatientBillDetailView detailView) {
        Map<String, Object> data = new HashMap<>();
        data.put("detailId",detailView.getDetailId());
        data.put("serviceId", detailView.getServiceId());
        data.put("serviceName",detailView.getServiceName());
        data.put("serviceCount", detailView.getServiceCount());
        data.put("serviceUnitCost", detailView.getServiceUnitCost());
        data.put("totalCost",detailView.getTotalCost());
        data.put("billId", detailView.getBillId());
        data.put("detailVno",detailView.getDetailVno());
        return data;
    }

    @Override
    public List<Map<String, Object>> convertBillDetailListToMapList(List<InPatientBillDetailView> detailViews) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (InPatientBillDetailView detailView : detailViews) {
            Map<String, Object> data = new HashMap<>();
            data.put("detailId",detailView.getDetailId());
            data.put("serviceId", detailView.getServiceId());
            data.put("serviceName",detailView.getServiceName());
            data.put("serviceCount", detailView.getServiceCount());
            data.put("serviceUnitCost", detailView.getServiceUnitCost());
            data.put("totalCost",detailView.getTotalCost());
            data.put("billId", detailView.getBillId());
            data.put("detailVno",detailView.getDetailVno());
            dataList.add(data);
        }
        return dataList;
    }

    @Override
    public List<InPatientBillDetailView> findBillDetailsByBillId(Long billId) {
        return inPatientBillDetailClassicRepository.findBillDetailsByBillId(billId);
    }

    @Override
    public InPatientBillDetailView findBillDetailByDetailId(Long detailId) {
        return inPatientBillDetailClassicRepository.findBillDetailByDetailId(detailId);
    }

    @Override
    public Optional<InPatientBillDetail> findInPatientBillDetailByInPatientBillDetailIdAndRemoveDateTimeIsNull(Long inPatientBillDetailId) {
        return inPatientBillDetailRepository.findInPatientBillDetailByInPatientBillDetailIdAndRemoveDateTimeIsNull(inPatientBillDetailId);
    }

    @Override
    @Transactional
    public InPatientBillDetail save(InPatientBillDetail inPatientBillDetail) {
        return inPatientBillDetailClassicRepository.save(inPatientBillDetail);
    }

    @Override
    @Transactional
    public InPatientBillDetail update(InPatientBillDetail inPatientBillDetail) {
        return inPatientBillDetailClassicRepository.update(inPatientBillDetail);
    }

    @Override
    @Transactional
    public void removeLogical(InPatientBillDetail inPatientBillDetail) {
        inPatientBillDetailClassicRepository.removeLogical(inPatientBillDetail);
    }
}
