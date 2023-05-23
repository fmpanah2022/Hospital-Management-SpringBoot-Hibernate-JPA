package org.j2os.repository.Classic;

import org.j2os.domain.entity.Gender;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Repository
public class GenderClassicRepository {
    @PersistenceContext
    private EntityManager entityManager;
    //***********************************************************
    public Gender save(Gender gender)
    {
         entityManager.persist(gender);
         return gender;
    }

    public Gender update(Gender gender)
    {
        return entityManager.merge(gender);
    }

    public void removeLogical(Gender gender)
    {
        gender.setRemoveDateTime(new java.sql.Timestamp(new Date().getTime()));
        entityManager.merge(gender);
    }
}
