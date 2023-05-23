package org.j2os.repository.Classic;

import org.j2os.domain.entity.Patient;
import org.j2os.repository.modern.InPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.Date;

@Repository
public class PatientClassicRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private InPatientRepository inPatientRepository;
    //-------------------------------------------------------
    public Patient save(Patient patient)
    {
        entityManager.persist(patient);
        return patient;
    }

    public Patient update(Patient patient)
    {
        return entityManager.merge(patient);
    }

    public void removeLogical(Patient patient)
    {
        Timestamp rDateTime = new java.sql.Timestamp(new Date().getTime());
        patient.setRemoveDateTime(rDateTime);
        patient.getPerson().setRemoveDateTime(rDateTime);
       /* if ( patient.getPerson().getPersonAddress() != null) {
            patient.getPerson().getPersonAddress().setRemoveDateTime(rDateTime);
        }
        if ( patient.getPerson().getPersonTell() != null) {
            patient.getPerson().getPersonTell().setRemoveDateTime(rDateTime);
        }*/
        entityManager.merge(patient);
    }

}
