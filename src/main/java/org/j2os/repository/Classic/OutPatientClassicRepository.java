package org.j2os.repository.Classic;

import org.j2os.domain.View.OutPatientView;
import org.j2os.domain.entity.OutPatient;
import org.j2os.domain.entity.OutPatientMedicalInfo;
import org.j2os.domain.entity.Room;
import org.j2os.repository.modern.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class OutPatientClassicRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private RoomClassicRepository roomClassicRepository;
    //*******************************************
    // in controller have to set Inpatient for OutPatient
    public OutPatient saveWithMedicalInfo(OutPatient outPatient , List<OutPatientMedicalInfo> outPatientMedicalInfos)
    {
        entityManager.persist(outPatient);
        entityManager.flush();
        OutPatient savedOutPatient = entityManager.find(OutPatient.class , outPatient.getOutPatientId());
        List<OutPatientMedicalInfo> outPatientMedicalInfoList = new ArrayList<>();

        for (OutPatientMedicalInfo info: outPatientMedicalInfos) {
            OutPatientMedicalInfo outPatientMedicalInfo = new OutPatientMedicalInfo();
            outPatientMedicalInfo.setOutPatientId(savedOutPatient.getOutPatientId());
            outPatientMedicalInfo.setMedicineId(info.getMedicineId());
            outPatientMedicalInfo.setCount(info.getCount());

            outPatientMedicalInfoList.add(outPatientMedicalInfo);
        }
        outPatient.setOutPatientMedicalInfos(outPatientMedicalInfoList);
        entityManager.persist(outPatient);
        Room room = new Room().builder().roomId(outPatient.getInPatient().getRoom().getRoomId()).available('1').build();
        entityManager.merge(room);
        return outPatient;
    }
    public OutPatient save (OutPatient outPatient )
    {
        entityManager.persist(outPatient);
        Room room = new Room().builder().roomId(outPatient.getInPatient().getRoom().getRoomId()).available('1').build();
        entityManager.merge(room);
        return outPatient;
    }

    public OutPatient update(OutPatient outPatient)
    {
        return entityManager.merge(outPatient);
    }
    // have to check relation to set remove flag
    public void removeLogical(OutPatient outPatient)
    {
        Timestamp rDateTime = new java.sql.Timestamp(new Date().getTime());
        outPatient.setRemoveDateTime(rDateTime);
        List<OutPatientMedicalInfo>  outPatientMedicalInfoList = outPatient.getOutPatientMedicalInfos();
        for(int  i = 0; i <= outPatientMedicalInfoList.size()-1  ; i++){
            outPatient.getOutPatientMedicalInfos().get(i).setRemoveDateTime(rDateTime);
        }
    //    outPatient.getInPatient().getRoom().setAvailable('0');
        entityManager.merge(outPatient);
        Room room = new Room().builder().roomId(outPatient.getInPatient().getRoom().getRoomId()).available('0').build();
        entityManager.merge(room);
    }
    public List<OutPatientView> findOutPatients(){
        Query query = entityManager.createNamedQuery("outPatientV.findOutPatients");
        List<OutPatientView> list = query.getResultList();
        return list;
    }
    public List<OutPatientView> findByDoctorId(Long doctorId){
        Query query = entityManager.createNamedQuery("outPatientV.findByDoctorId");
        query.setParameter("doctorId",doctorId);
        List<OutPatientView> list = query.getResultList();
        return list;

    }
    public OutPatientView findByInPatientId(Long inPatientId){
        Query query = entityManager.createNamedQuery("outPatientV.findByInPatientId");
        query.setParameter("inPatientId",inPatientId);
        OutPatientView outPatientView = new OutPatientView();
        if (!query.getResultList().isEmpty()){
            outPatientView = (OutPatientView) query.getResultList().get(0);
        }
        return outPatientView;
    }

    public OutPatientView findByOutPatientId(Long outPatientId){
        Query query = entityManager.createNamedQuery("outPatientV.findByOutPatientId");
        query.setParameter("outPatientId",outPatientId);
        OutPatientView outPatientView = new OutPatientView();
        if (!query.getResultList().isEmpty()){
            outPatientView = (OutPatientView) query.getResultList().get(0);
        }
        return outPatientView;
    }
    public List<OutPatientView> findByPatient_NationalCode(int patientNationalCode){
        Query query = entityManager.createNamedQuery("outPatientV.findByPatient_NationalCode");
        query.setParameter("patientNationalCode",patientNationalCode);
        List<OutPatientView> list = query.getResultList();
        return list;

    }
    public List<OutPatientView> findByPatientId(Long patientId){
        Query query = entityManager.createNamedQuery("outPatientV.findByPatientId");
        query.setParameter("patientId",patientId);
        List<OutPatientView> list = query.getResultList();
        return list;
    }
}

