package org.j2os.repository.Classic;

import org.j2os.domain.entity.PersonTell;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Repository
public class PersonTellClassicRepository {
    @PersistenceContext
    private EntityManager entityManager;
    //------------------------------------------------------
    public PersonTell save (PersonTell personTell) {
        entityManager.persist(personTell);
        return personTell;
    }
    public PersonTell update (PersonTell personTell) {
        return entityManager.merge(personTell);
    }
    public void removeLogical(PersonTell personTell)
    {
        personTell.setRemoveDateTime(new java.sql.Timestamp(new Date().getTime()));
        entityManager.merge(personTell);
    }
}
