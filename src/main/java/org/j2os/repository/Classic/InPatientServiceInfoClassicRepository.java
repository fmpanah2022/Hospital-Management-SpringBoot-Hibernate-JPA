package org.j2os.repository.Classic;

import org.j2os.domain.entity.InPatientServiceInfo;
import org.j2os.domain.View.InPatientServiceInfoView;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class InPatientServiceInfoClassicRepository {
    @PersistenceContext
    private EntityManager entityManager;
    //***********************************************************
    public List<InPatientServiceInfoView> findServiceInfoByServiceId(Long serviceId){
        Query query = entityManager.createNamedQuery("inPatientServiceInfoV.findServiceInfoByServiceId");
        query.setParameter("serviceId" ,serviceId);
        List<InPatientServiceInfoView> list = query.getResultList();
        return list;
    }
    public List<InPatientServiceInfoView> findServiceInfoByInPatientIdWithDetail(Long inPatientId){
        Query query = entityManager.createNamedQuery("inPatientServiceInfoV.findServiceInfoByInPatientIdWithDetail");
        query.setParameter("inPatientId" ,inPatientId);
        List<InPatientServiceInfoView> list = query.getResultList();
        return list;
    }
    public InPatientServiceInfoView findServiceInfoByIdWithDetail(Long inPatientServiceInfoId){
        Query query = entityManager.createNamedQuery("inPatientServiceInfoV.findServiceInfoByIdWithDetail");
        query.setParameter("ID" ,inPatientServiceInfoId);
        InPatientServiceInfoView info = new InPatientServiceInfoView();
        if ( !query.getResultList().isEmpty()) {
            info = (InPatientServiceInfoView) query.getResultList().get(0);
        }
        return info;
    }
    public InPatientServiceInfo save(InPatientServiceInfo inPatientServiceInfo)
    {
        entityManager.persist(inPatientServiceInfo);
        return inPatientServiceInfo;
    }
    public List<InPatientServiceInfo> saveList(Long inPatientId, List<InPatientServiceInfo> inPatientServiceInfos)
    {
        List<InPatientServiceInfo> inPatientServiceInfoList = new ArrayList<>();
        for (InPatientServiceInfo info : inPatientServiceInfos) {
            InPatientServiceInfo inPatientServiceInfo = new InPatientServiceInfo().builder().count(info.getCount()).serviceId(info.getServiceId()).inPatientId(inPatientId).build();
            entityManager.persist(inPatientServiceInfo);
            inPatientServiceInfoList.add(inPatientServiceInfo);
        }
        return inPatientServiceInfoList;
    }
    // Just Service ID and Count
    public InPatientServiceInfo update(InPatientServiceInfo inPatientServiceInfo)
    {
        return entityManager.merge(inPatientServiceInfo);
    }

    public void removeLogical(InPatientServiceInfo inPatientServiceInfo)
    {
        inPatientServiceInfo.setRemoveDateTime(new java.sql.Timestamp(new Date().getTime()));
        entityManager.merge(inPatientServiceInfo);
    }
}
