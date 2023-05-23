package org.j2os.service.modern;

import org.j2os.domain.entity.InPatientBill;
import org.j2os.domain.entity.InPatientBillDetail;
import org.j2os.domain.View.InPatientBillView;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface InPatientBillService {
    Map<String, Object> convertBillToMap(InPatientBillView inPatientBillView);
    List<Map<String, Object>> convertBillListToMapList(List<InPatientBillView> inPatientBills);
    List<InPatientBillView> findInPatientBills();
    InPatientBillView findInPatientBillByInPatientId(Long inPatientId);
    List<InPatientBillView> findInPatientBillsByStaffId(Long staffId);
    List<InPatientBillView> findInPatientBillsByTotalAmountIsBetween(int fromAmount , int toAmount);
    InPatientBillView findInPatientBillsByInPatientBillId(Long inPatientBillId);
    Optional<InPatientBill> findInPatientBillByInPatientBillIdAndRemoveDateTimeIsNull(Long inPatientBillId);
    InPatientBill save (InPatientBill inPatientBill , List<InPatientBillDetail> inPatientBillDetails );
    InPatientBill update(InPatientBill inPatientBill);
    void removeLogical(InPatientBill inPatientBill);
    boolean existsInPatientBill(InPatientBill inPatientBill) ;
    boolean conflictInPatientBill(InPatientBill inPatientBill) ;
    boolean canRemove(InPatientBill inPatientBill) ;
}
