package org.j2os.repository.Classic;

import org.j2os.domain.entity.InPatientMedicalInfo;
import org.j2os.domain.View.InPatientMedicalInfoView;
import org.j2os.repository.modern.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class InPatientMedicalInfoClassicRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private MedicineRepository medicineRepository;
    //***********************************************************
    public List<InPatientMedicalInfoView> findInPatientMedicalInfosByInPatientId(Long inPatientId){
        Query query = entityManager.createNamedQuery("inPatientMedicalInfoV.findInPatientMedicalInfoByInPatientIdWithDetail");
        query.setParameter("inPatientId" ,inPatientId);
        List<InPatientMedicalInfoView> list = query.getResultList();
        return list;
    }
    public InPatientMedicalInfoView findInPatientMedicalInfoById(Long inPatientMedicalInfoId){
        Query query = entityManager.createNamedQuery("inPatientMedicalInfoV.findInPatientMedicalInfoByIdWithDetail");
        query.setParameter("ID" ,inPatientMedicalInfoId);
        InPatientMedicalInfoView info = new InPatientMedicalInfoView();
        if ( !query.getResultList().isEmpty()) {
            info = (InPatientMedicalInfoView) query.getResultList().get(0);
        }
        return info;
    }
    public InPatientMedicalInfo save(InPatientMedicalInfo inPatientMedicalInfo)
    {
        entityManager.persist(inPatientMedicalInfo);
        return inPatientMedicalInfo;
    }
    public List<InPatientMedicalInfo> saveList(Long inPatientId, List<InPatientMedicalInfo> medicineDetailList)
    {
      List<InPatientMedicalInfo> medicalInfoList = new ArrayList<>();
        for (InPatientMedicalInfo info : medicineDetailList) {
            InPatientMedicalInfo inPatientMedicalInfo = new InPatientMedicalInfo().builder().count(info.getCount()).medicineId(info.getMedicineId()).inPatientId(inPatientId).build();
            entityManager.persist(inPatientMedicalInfo);
            medicalInfoList.add(inPatientMedicalInfo);
        }
        return medicalInfoList;
    }
// Just Medicine ID AND COUNT
    public InPatientMedicalInfo update(InPatientMedicalInfo inPatientMedicalInfo)
    {
        return entityManager.merge(inPatientMedicalInfo);
    }

    public void removeLogical(InPatientMedicalInfo inPatientMedicalInfo)
    {
        inPatientMedicalInfo.setRemoveDateTime(new java.sql.Timestamp(new Date().getTime()));
        entityManager.merge(inPatientMedicalInfo);
    }
}
