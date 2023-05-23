package org.j2os.service.Implementation;

import org.j2os.domain.entity.Doctor;
import org.j2os.repository.Classic.DoctorClassicRepository;
import org.j2os.repository.modern.DoctorRepository;
import org.j2os.repository.modern.DoctorSpecRepository;
import org.j2os.repository.modern.InPatientRepository;
import org.j2os.service.modern.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class DoctorServiceImp implements DoctorService {
    @Autowired
    private DoctorClassicRepository doctorClassicRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private InPatientRepository inPatientRepository;

    @Autowired
    private DoctorSpecRepository doctorSpecRepository;
//*****************************************************************************
    @Override
    public boolean existsDoctor ( Doctor doctor)  {
        return  (  doctorRepository.findDoctorByPerson_NationalCodeAndRemoveDateTimeIsNull(doctor.getPerson().getNationalCode()) != null  ) ;
    }
    @Override
    public boolean conflictDoctor ( Doctor doctor)  {
        Doctor doctor1 = doctorRepository.findDoctorByPerson_NationalCodeAndRemoveDateTimeIsNull(doctor.getPerson().getNationalCode());
        if  (  doctor1 != null && doctor1.getDoctorId() != null)
            return( doctor1.getDoctorId() != doctor.getDoctorId()) ;
        else return false;
    }
    @Override
    public boolean canRemove( Doctor doctor)  {
        return ( inPatientRepository.findInPatientsByDoctor_DoctorIdAndRemoveDateTimeIsNull(doctor.getDoctorId()).isEmpty());
           //     &&  doctorSpecRepository.findDoctorSpecsByDoctorIdAndRemoveDateTimeIsNull(doctor.getDoctorId()).isEmpty());
    }

    @Override
    public List<Doctor> findDoctorsByRemoveDateTimeIsNull() {
        return doctorRepository.findDoctorsByRemoveDateTimeIsNull();
    }

    @Override
    public Doctor findDoctorByPerson_NationalCodeAndRemoveDateTimeIsNull(int nationalCode) {
        return doctorRepository.findDoctorByPerson_NationalCodeAndRemoveDateTimeIsNull(nationalCode);
    }

   /* @Override
    public String createPersonalCode(int nationalCode, String employeeRoleName) {
        BigDecimal id = doctorRepository.getNextValPERSONALCODE_SEQ();
        String pc = String.valueOf(nationalCode);
        pc = pc.substring(0,4) + employeeRoleName + id;
        return pc;
    }*/

    @Override
    public Map<String, Object> convertDoctorToMap(Doctor doctor) {
        Map<String, Object> data = new HashMap<>();
        data.put("doctorId",doctor.getDoctorId());
        data.put("firstName", doctor.getPerson().getFirstName());
        data.put("surname", doctor.getPerson().getSurname());
        data.put("nationalCode", doctor.getPerson().getNationalCode());
        data.put("personId", doctor.getPerson().getPersonId());
        data.put("personalCode", doctor.getPersonalCode());
        data.put("genderId" , doctor.getPerson().getGender().getGenderId());
        data.put("genderName", doctor.getPerson().getGender().getGenderName());
        data.put("birthdate", doctor.getPerson().getBirthdate());
        data.put("userId" , doctor.getPerson().getUser().getUserId());
        data.put("userName", doctor.getPerson().getUser().getUserName());
        data.put("password", doctor.getPerson().getUser().getPassword());
        data.put("userVno", doctor.getPerson().getUser().getVersionNumber());
        data.put("personVno", doctor.getPerson().getVersionNumber());
        data.put("doctorVno", doctor.getVersionNumber());
        return data;
    }

    @Override
    public List<Map<String, Object>> convertDoctorListToMapList(List<Doctor> doctors) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (Doctor doctor:doctors) {
            Map<String, Object> data = new HashMap<>();
            data.put("doctorId",doctor.getDoctorId());
            data.put("firstName", doctor.getPerson().getFirstName());
            data.put("surname", doctor.getPerson().getSurname());
            data.put("nationalCode", doctor.getPerson().getNationalCode());
            data.put("personId", doctor.getPerson().getPersonId());
            data.put("personalCode", doctor.getPersonalCode());
            data.put("genderId" , doctor.getPerson().getGender().getGenderId());
            data.put("genderName", doctor.getPerson().getGender().getGenderName());
            data.put("birthdate", doctor.getPerson().getBirthdate());
            data.put("userId" , doctor.getPerson().getUser().getUserId());
            data.put("userName", doctor.getPerson().getUser().getUserName());
            data.put("password", doctor.getPerson().getUser().getPassword());
            data.put("userVno", doctor.getPerson().getUser().getVersionNumber());
            data.put("personVno", doctor.getPerson().getVersionNumber());
            data.put("doctorVno", doctor.getVersionNumber());
            dataList.add(data);
        }
        return dataList;
    }

    @Override
    public Doctor findDoctorByPersonalCodeAndRemoveDateTimeIsNull(String personalCode) {
        return doctorRepository.findDoctorByPersonalCodeAndRemoveDateTimeIsNull(personalCode);
    }

    @Override
    public Optional<Doctor> findByDoctorIdAndRemoveDateTimeIsNull(Long doctorId) {
        return doctorRepository.findByDoctorIdAndRemoveDateTimeIsNull(doctorId);
    }

    @Override
    public Doctor findDoctorByPerson_FirstNameAndPerson_SurnameAndPerson_NationalCodeAndRemoveDateTimeIsNull(String firstName, String surname, int nationalCode) {
        return doctorRepository.findDoctorByPerson_FirstNameAndPerson_SurnameAndPerson_NationalCodeAndRemoveDateTimeIsNull(firstName, surname, nationalCode);
    }

    @Override
    public Doctor findDoctorByDoctorIdAndRemoveDateTimeIsNull(Long doctorId) {
        return doctorRepository.findDoctorByDoctorIdAndRemoveDateTimeIsNull(doctorId);
    }

    @Override
    @Transactional
    public Doctor save(Doctor doctor) {
        return doctorClassicRepository.save(doctor);
    }
    @Override
    @Transactional
    public Doctor saveWithSpecList(Doctor doctor, List<String> specList) {
        return doctorClassicRepository.saveWithSpecList(doctor,specList);
    }

    @Override
    @Transactional
    public Doctor update(Doctor doctor) throws Exception {
        return  doctorClassicRepository.update(doctor);
    }

    @Override
    @Transactional
    public void removeLogical(Doctor doctor) throws Exception {
         doctorClassicRepository.removeLogical(doctor);
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        return doctorRepository.findById(id);
    }
}
