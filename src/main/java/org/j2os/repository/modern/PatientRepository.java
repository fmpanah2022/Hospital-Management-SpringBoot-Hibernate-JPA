package org.j2os.repository.modern;

import org.j2os.domain.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByPatientIdAndRemoveDateTimeIsNull(Long patientId);
    Patient findPatientByPerson_FirstNameAndPerson_SurnameAndPerson_NationalCodeAndRemoveDateTimeIsNull(String firstName , String surname , int nationalCode);
    Patient findPatientByPerson_NationalCodeAndRemoveDateTimeIsNull(int nationalCode);
    List<Patient> findPatientsByRemoveDateTimeIsNull();
}
