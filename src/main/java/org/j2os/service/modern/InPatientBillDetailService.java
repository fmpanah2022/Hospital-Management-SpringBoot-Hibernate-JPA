package org.j2os.service.modern;

import org.j2os.domain.entity.InPatientBillDetail;
import org.j2os.domain.View.InPatientBillDetailView;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface InPatientBillDetailService {
    Map<String, Object> convertBillDetailToMap(InPatientBillDetailView detailView);
    List<Map<String, Object>> convertBillDetailListToMapList(List<InPatientBillDetailView> detailViews);
    List<InPatientBillDetailView> findBillDetailsByBillId(Long billId);
    InPatientBillDetailView findBillDetailByDetailId(Long detailId);
    Optional<InPatientBillDetail> findInPatientBillDetailByInPatientBillDetailIdAndRemoveDateTimeIsNull(Long inPatientBillDetailId);
    InPatientBillDetail save(InPatientBillDetail inPatientBillDetail);
    InPatientBillDetail update(InPatientBillDetail inPatientBillDetail);
    void removeLogical(InPatientBillDetail inPatientBillDetail);
    boolean existsInPatientBillDetail(InPatientBillDetail inPatientBillDetail) ;
    boolean conflictInPatientBillDetail(InPatientBillDetail inPatientBillDetail) ;
    boolean canRemove(InPatientBillDetail inPatientBillDetail) ;
}
