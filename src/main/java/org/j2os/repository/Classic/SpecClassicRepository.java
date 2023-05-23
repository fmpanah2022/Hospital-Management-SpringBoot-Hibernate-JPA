package org.j2os.repository.Classic;

import org.j2os.domain.entity.Specialization;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
@Repository
public class SpecClassicRepository {
    @PersistenceContext
    public EntityManager entityManager;
    //******************************************
    public Specialization save(Specialization specialization)
    {
        entityManager.persist(specialization);
        return specialization;
    }

    public Specialization update(Specialization specialization)
    {
        return entityManager.merge(specialization);
    }

    public void removeLogical(Specialization specialization)
    {
        specialization.setRemoveDateTime(new java.sql.Timestamp(new Date().getTime()));
        entityManager.merge(specialization);
    }
}
