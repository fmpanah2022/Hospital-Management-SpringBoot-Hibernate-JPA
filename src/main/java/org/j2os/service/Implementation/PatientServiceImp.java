package org.j2os.service.Implementation;

import org.j2os.domain.entity.Patient;
import org.j2os.repository.modern.InPatientRepository;
import org.j2os.repository.Classic.PatientClassicRepository;
import org.j2os.repository.modern.PatientRepository;
import org.j2os.service.modern.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PatientServiceImp implements PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientClassicRepository patientClassicRepository;
    @Autowired
    private InPatientRepository inPatientRepository;
//*****************************************************************************
   @Override
   public boolean existsPatient(Patient patient) {
       return  (  patientRepository.findPatientByPerson_NationalCodeAndRemoveDateTimeIsNull(patient.getPerson().getNationalCode()) != null  ) ;
     }

    @Override
    public boolean conflictPatient(Patient patient) {
        Patient patient1 = patientRepository.findPatientByPerson_NationalCodeAndRemoveDateTimeIsNull(patient.getPerson().getNationalCode());
        if  (  patient1 != null && patient1.getPatientId() != null)
            return( patient1.getPatientId() != patient.getPatientId()) ;
        else return false;
    }

    @Override
    public boolean canRemove(Patient patient) {
        return ( inPatientRepository.findInPatientsByPatient_PatientIdAndRemoveDateTimeIsNull(patient.getPatientId()).isEmpty());
    }

    @Override
    public Map<String, Object> convertPatientToMap(Patient patient) {
        Map<String, Object> data = new HashMap<>();
        data.put("patientId",patient.getPatientId());
        data.put("firstName", patient.getPerson().getFirstName());
        data.put("surname", patient.getPerson().getSurname());
        data.put("nationalCode", patient.getPerson().getNationalCode());
        data.put("personId", patient.getPerson().getPersonId());
        data.put("genderId" , patient.getPerson().getGender().getGenderId());
        data.put("genderName", patient.getPerson().getGender().getGenderName());
        data.put("birthdate", patient.getPerson().getBirthdate());
        data.put("personVno", patient.getPerson().getVersionNumber());
        data.put("patientVno", patient.getVersionNumber());
        return data;
    }

    @Override
    public List<Map<String, Object>> convertPatientListToMapList(List<Patient> patients) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (Patient patient:patients) {
            Map<String, Object> data = new HashMap<>();
            data.put("patientId",patient.getPatientId());
            data.put("firstName", patient.getPerson().getFirstName());
            data.put("surname", patient.getPerson().getSurname());
            data.put("nationalCode", patient.getPerson().getNationalCode());
            data.put("personId", patient.getPerson().getPersonId());
            data.put("genderId" , patient.getPerson().getGender().getGenderId());
            data.put("genderName", patient.getPerson().getGender().getGenderName());
            data.put("birthdate", patient.getPerson().getBirthdate());
            data.put("personVno", patient.getPerson().getVersionNumber());
            data.put("patientVno", patient.getVersionNumber());
            dataList.add(data);
        }
        return dataList;
    }

    @Override
    public Optional<Patient> findByPatientIdAndRemoveDateTimeIsNull(Long patientId) {
        return patientRepository.findByPatientIdAndRemoveDateTimeIsNull(patientId);
    }

    @Override
    public Patient findPatientByPerson_NationalCodeAndRemoveDateTimeIsNull(int nationalCode) {
        return patientRepository.findPatientByPerson_NationalCodeAndRemoveDateTimeIsNull(nationalCode);
    }

    @Override
    public List<Patient> findPatientsByRemoveDateTimeIsNull() {
        return patientRepository.findPatientsByRemoveDateTimeIsNull();
    }

    @Override
    public Patient findPatientByPerson_FirstNameAndPerson_SurnameAndPerson_NationalCodeAndRemoveDateTimeIsNull(String firstName, String surname, int nationalCode) {
        return patientRepository.findPatientByPerson_FirstNameAndPerson_SurnameAndPerson_NationalCodeAndRemoveDateTimeIsNull(firstName, surname, nationalCode);
    }
    @Transactional
    @Override
    public Patient save(Patient patient) {
        return patientClassicRepository.save(patient);
    }

    @Transactional
    @Override
    public Patient update(Patient patient) {
        return patientClassicRepository.update(patient);
    }

    @Transactional
    @Override
    public void removeLogical(Patient patient) {
         patientClassicRepository.removeLogical(patient);
    }

    @Override
    public Optional<Patient> findById(Long id) {
       return patientRepository.findById(id);
    }
}
