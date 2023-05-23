package org.j2os.repository.Classic;

import org.j2os.domain.entity.OutPatientMedicalInfo;
import org.j2os.domain.View.OutPatientMedicalInfoView;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
public class OutPatientMedicalInfoClassicRepository {
    @PersistenceContext
    private EntityManager entityManager;
    //***********************************************************
    public List<OutPatientMedicalInfoView> findOutPatientMedicalInfosByOutPatientId(Long outPatientId){
        Query query = entityManager.createNamedQuery("outPatientMedicalInfoV.findOutPatientMedicalInfoByOutPatientIdWithDetail");
        query.setParameter("outPatientId" ,outPatientId);
        List<OutPatientMedicalInfoView> list = query.getResultList();
        return list;
    }
    public OutPatientMedicalInfoView findOutPatientMedicalInfoById(Long outPatientMedicalInfoId){
        Query query = entityManager.createNamedQuery("outPatientMedicalInfoV.findOutPatientMedicalInfoByIdWithDetail");
        query.setParameter("ID" ,outPatientMedicalInfoId);
        OutPatientMedicalInfoView info = new OutPatientMedicalInfoView();
        if ( !query.getResultList().isEmpty()) {
            info = (OutPatientMedicalInfoView) query.getResultList().get(0);
        }
        return info;
    }
    public OutPatientMedicalInfo save(OutPatientMedicalInfo outPatientMedicalInfo)
    {
        entityManager.persist(outPatientMedicalInfo);
        return outPatientMedicalInfo;
    }

    public OutPatientMedicalInfo update(OutPatientMedicalInfo outPatientMedicalInfo)
    {
        return entityManager.merge(outPatientMedicalInfo);
    }

    public void removeLogical(OutPatientMedicalInfo outPatientMedicalInfo)
    {
        outPatientMedicalInfo.setRemoveDateTime(new java.sql.Timestamp(new Date().getTime()));
        entityManager.merge(outPatientMedicalInfo);
    }
}
