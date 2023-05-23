package org.j2os.repository.Classic;

import org.j2os.domain.entity.*;
import org.j2os.repository.modern.PersonAddressRepository;
import org.j2os.repository.modern.PersonTellRepository;
import org.j2os.repository.modern.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.Date;

@Repository
public class CashierClassicRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private PersonTellRepository personTellRepository;
    @Autowired
    private PersonAddressRepository personAddressRepository;
    @Autowired
    private RoleRepository roleRepository;
    //********************************************
    public Cashier save (Cashier cashier) {
        entityManager.persist(cashier);
        entityManager.flush();
        //employeeCashier
        Role role = roleRepository.findRoleByRoleNameAndRemoveDateTimeIsNull("employeeCashier");
        UserRoles userRole = new UserRoles().builder().user(cashier.getPerson().getUser()).role(role).build();
        entityManager.persist(userRole);
        return cashier;
    }
    public Cashier update (Cashier cashier) {
        return entityManager.merge(cashier);
    }

    public void removeLogical(Cashier cashier)
    {
        Timestamp rDateTime = new java.sql.Timestamp(new Date().getTime());
        cashier.setRemoveDateTime(rDateTime);
        cashier.getPerson().setRemoveDateTime(rDateTime);

        PersonTell personTell =  personTellRepository.findPersonTellByPerson_PersonIdAndRemoveDateTimeIsNull(cashier.getPerson().getPersonId());
        if (personTell != null) {
            personTell.setRemoveDateTime(rDateTime);
            entityManager.merge(personTell);
        }

        PersonAddress personAddress =  personAddressRepository.findPersonAddressByPerson_PersonIdAndRemoveDateTimeIsNull(cashier.getPerson().getPersonId());
        if (personAddress != null) {
            personAddress.setRemoveDateTime(rDateTime);
            entityManager.merge(personAddress);
        }

        if ( cashier.getPerson().getUser() != null) {
            cashier.getPerson().getUser().setRemoveDateTime(rDateTime);
        }
        entityManager.merge(cashier);
    }
}
