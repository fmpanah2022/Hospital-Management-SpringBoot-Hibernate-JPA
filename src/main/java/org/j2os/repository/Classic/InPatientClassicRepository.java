package org.j2os.repository.Classic;

import org.j2os.domain.View.InPatientVew;
import org.j2os.domain.entity.InPatient;
import org.j2os.domain.entity.InPatientMedicalInfo;
import org.j2os.domain.entity.Medicine;
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
public class InPatientClassicRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private RoomClassicRepository roomClassicRepository;
    //*******************************************
    public List<InPatientVew> findAllInPatients() {
        Query query = entityManager.createNamedQuery("inPatient.findAllInPatients",InPatientVew.class);
        List<InPatientVew> list = query.getResultList();
        return list;
    }
    // room patient staff doctor List<InPatientMedical> have to check
    public InPatient saveWithMedicalInfo(InPatient inPatient , List<String> medicineCodeList)
    {
        entityManager.persist(inPatient);
        entityManager.flush();
        InPatient savedInPatient = entityManager.find(InPatient.class , inPatient.getInPatientId());
        List<InPatientMedicalInfo> inPatientMedicalInfoList = new ArrayList<>();

        for (String medicineCode: medicineCodeList) {
            InPatientMedicalInfo inPatientMedicalInfo = new InPatientMedicalInfo();
            Medicine savedMedicine = medicineRepository.findMedicineByMedicineCodeAndRemoveDateTimeIsNull(medicineCode);

            inPatientMedicalInfo.setInPatientId(savedInPatient.getInPatientId());
            inPatientMedicalInfo.setMedicineId(savedMedicine.getMedicineId());

            inPatientMedicalInfoList.add(inPatientMedicalInfo);
        }
        inPatient.setInPatientMedicalInfos(inPatientMedicalInfoList);
        entityManager.persist(inPatient);
        return inPatient;
    }
    public InPatient save (InPatient inPatient )
    {
        entityManager.persist(inPatient);
        return inPatient;
    }

    public InPatient update(InPatient inPatient)
    {
        return entityManager.merge(inPatient);
    }
// have to check relation to set remove flag
    public void removeLogical(InPatient inPatient)
    {
        Timestamp rDateTime = new java.sql.Timestamp(new Date().getTime());
        inPatient.setRemoveDateTime(rDateTime);
        List<InPatientMedicalInfo>  inPatientMedicalInfoList = inPatient.getInPatientMedicalInfos();
        for(int  i = 0; i <= inPatientMedicalInfoList.size()-1  ; i++){
            inPatient.getInPatientMedicalInfos().get(i).setRemoveDateTime(rDateTime);
        }
       // inPatient.getInPatientBill().setRemoveDateTime(rDateTime);
        inPatient.getRoom().setAvailable('1');
        entityManager.merge(inPatient);
    }
}
