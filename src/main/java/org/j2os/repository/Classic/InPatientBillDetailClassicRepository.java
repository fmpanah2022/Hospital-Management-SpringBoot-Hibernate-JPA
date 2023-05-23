package org.j2os.repository.Classic;

import org.j2os.domain.entity.InPatientBillDetail;
import org.j2os.domain.View.InPatientBillDetailView;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
public class InPatientBillDetailClassicRepository {
    @PersistenceContext
    private EntityManager entityManager;
    //***********************************************************
    public InPatientBillDetail save(InPatientBillDetail inPatientBillDetail)
    {
        entityManager.persist(inPatientBillDetail);
        return inPatientBillDetail;
    }

    public InPatientBillDetail update(InPatientBillDetail inPatientBillDetail)
    {
        return entityManager.merge(inPatientBillDetail);
    }

    public void removeLogical(InPatientBillDetail inPatientBillDetail)
    {
        inPatientBillDetail.setRemoveDateTime(new java.sql.Timestamp(new Date().getTime()));
        entityManager.merge(inPatientBillDetail);
    }
    public List<InPatientBillDetailView> findBillDetailsByBillId(Long billId){
        Query query = entityManager.createNamedQuery("billDetailV.findBillDetailsByBillId");
        query.setParameter("billId" , billId);
        List<InPatientBillDetailView> list = query.getResultList();
        return list;
    }

    public InPatientBillDetailView findBillDetailByDetailId(Long detailId){
        Query query = entityManager.createNamedQuery("billDetailV.findBillDetailByDetailId");
        query.setParameter("detailId" , detailId);
        InPatientBillDetailView detail = new InPatientBillDetailView();
        if (!query.getResultList().isEmpty()){
            detail = (InPatientBillDetailView) query.getResultList().get(0);
        }
        return detail;
    }

}
