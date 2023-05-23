package org.j2os.service.modern;

import org.j2os.domain.entity.Doctor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DoctorService {
   //  String createPersonalCode(int nationalCode , String employeeRoleName);
     Map<String, Object> convertDoctorToMap(Doctor doctor);
     List<Map<String, Object>> convertDoctorListToMapList(List<Doctor> doctors);
     Doctor findDoctorByPersonalCodeAndRemoveDateTimeIsNull(String personalCode);
     Optional<Doctor> findByDoctorIdAndRemoveDateTimeIsNull(Long doctorId);
     Doctor findDoctorByPerson_FirstNameAndPerson_SurnameAndPerson_NationalCodeAndRemoveDateTimeIsNull(String firstName , String surname ,int nationalCode);
     Doctor findDoctorByDoctorIdAndRemoveDateTimeIsNull(Long doctorId);
     Doctor save(Doctor doctor) ;
     Doctor saveWithSpecList(Doctor doctor, List<String> specList);
     Doctor update(Doctor doctor) throws Exception;
     void removeLogical(Doctor doctor ) throws Exception;
     Optional<Doctor> findById(Long id);
     boolean existsDoctor ( Doctor doctor);
     boolean conflictDoctor ( Doctor doctor) ;
     boolean canRemove( Doctor doctor) ;
     List<Doctor> findDoctorsByRemoveDateTimeIsNull();
     Doctor findDoctorByPerson_NationalCodeAndRemoveDateTimeIsNull(int nationalCode);
}
