package org.j2os.repository.Classic;

import org.j2os.domain.entity.Medicine;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Repository
public class MedicineClassicRepository {
    @PersistenceContext
    private EntityManager entityManager;
    //****************************************
    public Medicine save (Medicine medicine) {
        entityManager.persist(medicine);
        return medicine;
    }
    public Medicine update (Medicine medicine) {
        return entityManager.merge(medicine);
    }
    public void removeLogical(Medicine medicine)
    {
        medicine.setRemoveDateTime(new java.sql.Timestamp(new Date().getTime()));
        entityManager.merge(medicine);
    }
}
