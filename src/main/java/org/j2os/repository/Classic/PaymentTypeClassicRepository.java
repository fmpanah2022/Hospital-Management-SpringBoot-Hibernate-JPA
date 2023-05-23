package org.j2os.repository.Classic;

import org.j2os.domain.entity.PaymentType;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Repository
public class PaymentTypeClassicRepository {
    @PersistenceContext
    private EntityManager entityManager;
    //***********************************************************
    public PaymentType save(PaymentType paymentType)
    {
        entityManager.persist(paymentType);
        return paymentType;
    }

    public PaymentType update(PaymentType paymentType)
    {
        return entityManager.merge(paymentType);
    }

    public void removeLogical(PaymentType paymentType)
    {
        paymentType.setRemoveDateTime(new java.sql.Timestamp(new Date().getTime()));
        entityManager.merge(paymentType);
    }
}
