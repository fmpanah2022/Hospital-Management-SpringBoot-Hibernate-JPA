package org.j2os.repository.Classic;

import org.j2os.domain.entity.PersonAddress;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Repository
public class PersonAddressClassicRepository {
    @PersistenceContext
    private EntityManager entityManager;
    //------------------------------------------------------
    public PersonAddress save (PersonAddress personAddress) {
        entityManager.persist(personAddress);
        return personAddress;
    }
    public PersonAddress update (PersonAddress personAddress) {
        return entityManager.merge(personAddress);
    }
    public void removeLogical(PersonAddress personAddress)
    {
        personAddress.setRemoveDateTime(new java.sql.Timestamp(new Date().getTime()));
        entityManager.merge(personAddress);
    }
}
