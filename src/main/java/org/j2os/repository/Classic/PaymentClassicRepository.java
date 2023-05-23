package org.j2os.repository.Classic;

import org.j2os.domain.entity.Payment;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Repository
public class PaymentClassicRepository {
    @PersistenceContext
    private EntityManager entityManager;
    //***********************************************************
    public Payment save(Payment payment)
    {
        entityManager.persist(payment);
        return payment;
    }

    public Payment update(Payment payment)
    {
        return entityManager.merge(payment);
    }

    public void removeLogical(Payment payment)
    {
        payment.setRemoveDateTime(new java.sql.Timestamp(new Date().getTime()));
        entityManager.merge(payment);
    }
}
