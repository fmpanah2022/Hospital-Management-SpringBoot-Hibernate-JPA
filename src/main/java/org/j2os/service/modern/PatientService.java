package org.j2os.service.modern;

import org.j2os.domain.entity.Patient;

import java.util.List;
import java.util.Map;
import java.util.Optional;
public interface PatientService {
    //-------------------------------------------------------
    Map<String, Object> convertPatientToMap(Patient patient);
    List<Map<String, Object>> convertPatientListToMapList(List<Patient> patients);
    Optional<Patient> findByPatientIdAndRemoveDateTimeIsNull(Long patientId);
    Patient findPatientByPerson_FirstNameAndPerson_SurnameAndPerson_NationalCodeAndRemoveDateTimeIsNull(String firstName , String surname , int nationalCode);
    Patient findPatientByPerson_NationalCodeAndRemoveDateTimeIsNull(int nationalCode);
    List<Patient> findPatientsByRemoveDateTimeIsNull();
    Patient save(Patient patient);
    Patient update(Patient patient);
    void removeLogical(Patient patient);
    Optional<Patient> findById(Long patientId);
    boolean existsPatient ( Patient patient);
    boolean conflictPatient ( Patient patient) ;
    boolean canRemove( Patient patient)  ;
}
