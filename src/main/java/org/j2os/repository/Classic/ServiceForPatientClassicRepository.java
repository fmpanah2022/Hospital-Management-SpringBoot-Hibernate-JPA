package org.j2os.repository.Classic;

import org.j2os.domain.entity.ServiceForPatient;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Repository
public class ServiceForPatientClassicRepository {
    @PersistenceContext
    private EntityManager entityManager;
    //------------------------------------------------------
    public ServiceForPatient save (ServiceForPatient service) {
        entityManager.persist(service);
        return service;
    }
    public ServiceForPatient update (ServiceForPatient service) {
        return entityManager.merge(service);
    }
    public void removeLogical(ServiceForPatient service)
    {
        service.setRemoveDateTime(new java.sql.Timestamp(new Date().getTime()));
        entityManager.merge(service);
    }
}
