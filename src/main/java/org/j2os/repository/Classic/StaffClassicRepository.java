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
public class StaffClassicRepository  {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private PersonTellRepository personTellRepository;
    @Autowired
    private PersonAddressRepository personAddressRepository;
    @Autowired
    private RoleRepository roleRepository;
    //********************************************
    public Staff save (Staff staff) {
       entityManager.persist(staff);
        entityManager.flush();

        Role role = roleRepository.findRoleByRoleNameAndRemoveDateTimeIsNull("employeeStaff");
        UserRoles userRole = new UserRoles().builder().user(staff.getPerson().getUser()).role(role).build();
        entityManager.persist(userRole);
        return staff;
    }
    public Staff update (Staff staff) {
        return entityManager.merge(staff);
    }

    public void removeLogical(Staff staff)
    {
        Timestamp rDateTime = new java.sql.Timestamp(new Date().getTime());
        staff.setRemoveDateTime(rDateTime);
        staff.getPerson().setRemoveDateTime(rDateTime);

        PersonTell personTell =  personTellRepository.findPersonTellByPerson_PersonIdAndRemoveDateTimeIsNull(staff.getPerson().getPersonId());
        if (personTell != null) {
            personTell.setRemoveDateTime(rDateTime);
            entityManager.merge(personTell);
        }

        PersonAddress personAddress =  personAddressRepository.findPersonAddressByPerson_PersonIdAndRemoveDateTimeIsNull(staff.getPerson().getPersonId());
        if (personAddress != null) {
            personAddress.setRemoveDateTime(rDateTime);
            entityManager.merge(personAddress);
        }

        if ( staff.getPerson().getUser() != null) {
            staff.getPerson().getUser().setRemoveDateTime(rDateTime);
        }
        entityManager.merge(staff);
    }
}
