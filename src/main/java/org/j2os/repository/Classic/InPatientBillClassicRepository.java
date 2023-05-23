package org.j2os.repository.Classic;

import org.j2os.domain.View.InPatientBillView;
import org.j2os.domain.entity.InPatientBill;
import org.j2os.domain.entity.InPatientBillDetail;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class InPatientBillClassicRepository {
    @PersistenceContext
    private EntityManager entityManager;
    //*******************************************
    public InPatientBill save (InPatientBill inPatientBill , List<InPatientBillDetail> inPatientBillDetails )
    {
        entityManager.persist(inPatientBill);
        entityManager.flush();
        InPatientBill savedInPatientBill = entityManager.find(InPatientBill.class , inPatientBill.getInPatientBillId());
        List<InPatientBillDetail> detailList = new ArrayList<>();
        for (InPatientBillDetail detail: inPatientBillDetails) {
            InPatientBillDetail inPatientBillDetail = new InPatientBillDetail();

            inPatientBillDetail.setServiceId(detail.getServiceId());
            inPatientBillDetail.setInPatientBillId(savedInPatientBill.getInPatientBillId());
            inPatientBillDetail.setServiceCount(detail.getServiceCount());
            inPatientBillDetail.setServiceUnitCost(detail.getServiceUnitCost());

            detailList.add(inPatientBillDetail);
        }
        savedInPatientBill.setInPatientBillDetails(detailList);
        entityManager.persist(inPatientBill);
        return inPatientBill;
    }
    public InPatientBill update(InPatientBill inPatientBill)
    {
        return entityManager.merge(inPatientBill);
    }
    public void removeLogical(InPatientBill inPatientBill)
    {
        Timestamp rDateTime = new java.sql.Timestamp(new Date().getTime());
        inPatientBill.setRemoveDateTime(rDateTime);

        List<InPatientBillDetail>  inPatientBillDetailList = inPatientBill.getInPatientBillDetails();
        for(int i=0; i<=inPatientBillDetailList.size()-1  ; i++){
            inPatientBill.getInPatientBillDetails().get(i).setRemoveDateTime(rDateTime);
        }
        entityManager.merge(inPatientBill);
    }

    public List<InPatientBillView> findInPatientBills(){
        Query query = entityManager.createNamedQuery("inPatientBillV.findInPatientBills");
        List<InPatientBillView> list = query.getResultList();
        return list;
    }
    public InPatientBillView findInPatientBillByInPatientId(Long inPatientId){
        Query query = entityManager.createNamedQuery("inPatientBillV.findByInPatientId");
        query.setParameter("inPatientId" ,inPatientId);
        InPatientBillView inPatientBillView = new InPatientBillView();
        if (!query.getResultList().isEmpty()){
            inPatientBillView = (InPatientBillView) query.getResultList().get(0);
        }
        return inPatientBillView;
    }
    public List<InPatientBillView> findInPatientBillsByStaffId(Long staffId){
        Query query = entityManager.createNamedQuery("inPatientBillV.findByStaffId");
        query.setParameter("staffId" ,staffId);
        List<InPatientBillView> list = query.getResultList();
        return list;
    }
    public List<InPatientBillView> findInPatientBillsByTotalAmountIsBetween(int fromAmount , int toAmount){
        Query query = entityManager.createNamedQuery("inPatientBillV.findByTotalAmountIsBetween");
        query.setParameter("fromAmount" ,fromAmount);
        query.setParameter("toAmount" ,toAmount);
        List<InPatientBillView> list = query.getResultList();
        return list;
    }

    public InPatientBillView findInPatientBillsByInPatientBillId(Long inPatientBillId){
        Query query = entityManager.createNamedQuery("inPatientBillV.findByInPatientBillId");
        query.setParameter("inPatientBillId" ,inPatientBillId);
        InPatientBillView inPatientBillView = new InPatientBillView();
        if (!query.getResultList().isEmpty()){
            inPatientBillView = (InPatientBillView) query.getResultList().get(0);
        }
        return inPatientBillView;
    }
}
