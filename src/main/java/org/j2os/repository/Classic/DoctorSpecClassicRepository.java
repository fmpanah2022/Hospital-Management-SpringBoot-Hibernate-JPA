package org.j2os.repository.Classic;

import org.j2os.domain.entity.DoctorSpec;
import org.j2os.domain.entity.Specialization;
import org.j2os.repository.modern.SpecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class DoctorSpecClassicRepository {
    @PersistenceContext
    public EntityManager entityManager;
    @Autowired
    private SpecRepository specRepository;
    //******************************************
    public DoctorSpec save(DoctorSpec doctorSpec)
    {
        entityManager.persist(doctorSpec);
        return doctorSpec;
    }

    public List<DoctorSpec> saveDoctorSpecListOfOneDoctor(Long doctorId, List<String> specNameList)
    {
        List<DoctorSpec> doctorSpecList = new ArrayList<>();
        for (String spec: specNameList) {
            DoctorSpec doctorSpec = new DoctorSpec();
            Specialization savedSpec = specRepository.findSpecializationBySpecializationAndRemoveDateTimeIsNull(spec);
            doctorSpec.setDoctorId(doctorId);
            doctorSpec.setSpecializationId(savedSpec.getSpecializationId());
            entityManager.persist(doctorSpec);
            doctorSpecList.add(doctorSpec);
        }
        return doctorSpecList;
    }

    public DoctorSpec update(DoctorSpec doctorSpec)
    {
        return entityManager.merge(doctorSpec);
    }

    public void removeLogical(DoctorSpec doctorSpec)
    {
        Timestamp rDateTime = new java.sql.Timestamp(new Date().getTime());
        doctorSpec.setRemoveDateTime(rDateTime);
        entityManager.merge(doctorSpec);
    }
}
