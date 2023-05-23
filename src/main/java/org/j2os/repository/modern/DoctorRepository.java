package org.j2os.repository.modern;

import org.j2os.domain.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor , Long> {
  Doctor findDoctorByPersonalCodeAndRemoveDateTimeIsNull(String personalCode);
  List<Doctor> findDoctorsByRemoveDateTimeIsNull();
  Doctor findDoctorByPerson_FirstNameAndPerson_SurnameAndPerson_NationalCodeAndRemoveDateTimeIsNull(String firstName , String surname ,int nationalCode);
  Doctor findDoctorByDoctorIdAndRemoveDateTimeIsNull(Long doctorId);
  Doctor findDoctorByPerson_NationalCodeAndRemoveDateTimeIsNull(int nationalCode);
  Optional<Doctor> findByDoctorIdAndRemoveDateTimeIsNull(Long doctorId);
}
