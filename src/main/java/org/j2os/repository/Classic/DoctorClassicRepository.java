package org.j2os.repository.Classic;

import org.j2os.domain.entity.*;
import org.j2os.repository.modern.PersonAddressRepository;
import org.j2os.repository.modern.PersonTellRepository;
import org.j2os.repository.modern.RoleRepository;
import org.j2os.repository.modern.SpecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class DoctorClassicRepository {
    @PersistenceContext
    public EntityManager entityManager;
    @Autowired
    private SpecRepository specRepository;
    @Autowired
    private PersonTellRepository personTellRepository;
    @Autowired
    private PersonAddressRepository personAddressRepository;
    @Autowired
    private RoleRepository roleRepository;
    //******************************************
    public Doctor save(Doctor doctor)
    {
        entityManager.persist(doctor);
        entityManager.flush();
        //employeeDoctor
        Role role = roleRepository.findRoleByRoleNameAndRemoveDateTimeIsNull("employeeDoctor");
        UserRoles userRole = new UserRoles().builder().user(doctor.getPerson().getUser()).role(role).build();
        entityManager.persist(userRole);
        return doctor;
    }

    public Doctor saveWithSpecList(Doctor doctor, List<String> specList)
    {
        entityManager.persist(doctor);
        entityManager.flush();
        Doctor savedDoctor = entityManager.find(Doctor.class , doctor.getDoctorId());
        List<DoctorSpec> doctorSpecList = new ArrayList<> ();

        for (String spec: specList) {
            DoctorSpec doctorSpec = new DoctorSpec();
            Specialization savedSpec = specRepository.findSpecializationBySpecializationAndRemoveDateTimeIsNull(spec);
            doctorSpec.setDoctorId(savedDoctor.getDoctorId());
            doctorSpec.setSpecializationId(savedSpec.getSpecializationId());
            doctorSpecList.add(doctorSpec);
        }
        savedDoctor.setDoctorSpecs(doctorSpecList);
        entityManager.persist(doctor);

        Role role = roleRepository.findRoleByRoleNameAndRemoveDateTimeIsNull("employeeDoctor");
        UserRoles userRole = new UserRoles().builder().user(doctor.getPerson().getUser()).role(role).build();
        entityManager.persist(userRole);
        return doctor;
    }

    public Doctor update(Doctor doctor)
    {
        return entityManager.merge(doctor);
    }

    public void removeLogical(Doctor doctor)
    {
        Timestamp rDateTime = new java.sql.Timestamp(new Date().getTime());
        doctor.setRemoveDateTime(rDateTime);
        doctor.getPerson().setRemoveDateTime(rDateTime);
        List<DoctorSpec>  doctorSpecList = doctor.getDoctorSpecs();
        for(int i=0; i<=doctorSpecList.size()-1  ; i++){
            doctor.getDoctorSpecs().get(i).setRemoveDateTime(rDateTime);
        }
        PersonTell personTell =  personTellRepository.findPersonTellByPerson_PersonIdAndRemoveDateTimeIsNull(doctor.getPerson().getPersonId());
        if (personTell != null) {
            personTell.setRemoveDateTime(rDateTime);
            entityManager.merge(personTell);
        }
        PersonAddress personAddress =  personAddressRepository.findPersonAddressByPerson_PersonIdAndRemoveDateTimeIsNull(doctor.getPerson().getPersonId());
        if (personAddress != null) {
            personAddress.setRemoveDateTime(rDateTime);
            entityManager.merge(personAddress);
        }
        if ( doctor.getPerson().getUser() != null) {
            doctor.getPerson().getUser().setRemoveDateTime(rDateTime);
        }
        entityManager.merge(doctor);
    }
}
