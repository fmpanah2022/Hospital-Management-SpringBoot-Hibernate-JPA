package org.j2os.service.modern;

import org.j2os.domain.View.InPatientVew;
import org.j2os.domain.entity.InPatient;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface InPatientService {
     Map<String, Object> convertInPatientToMap(InPatient inPatient);
     List<Map<String, Object>> convertInPatientListToMapList(List<InPatient> inPatients);
     Optional<InPatient> findByInPatientIdAndRemoveDateTimeIsNull(Long patientId);
     List<InPatient> findInPatientsByRemoveDateTimeIsNull();
     List<InPatient> findInPatientsByDoctor_DoctorIdAndRemoveDateTimeIsNull(Long doctorId);
     List<InPatient> findInPatientsByPatient_PatientIdAndRemoveDateTimeIsNull(Long patientId);
     List<InPatient> findInPatientsByRoom_RoomIdAndRemoveDateTimeIsNull(Long roomId);
     List<InPatient> findInPatientsByStaff_StaffIdAndRemoveDateTimeIsNull(Long staffId);
     List<InPatientVew> findAllInPatients();
     InPatient save(InPatient inPatient);
     InPatient saveWithMedicalInfo(InPatient inPatient ,List<String> medicineCodeList);
     InPatient update(InPatient inPatient);
     void removeLogical(InPatient inPatient);
     Optional<InPatient> findById(Long id);
     boolean existsInPatient(InPatient inPatient) ;
     boolean conflictInPatient(InPatient inPatient) ;
     boolean canRemove(InPatient inPatient) ;
   //  InPatient findInPatientByPatient_Person_NationalCodeAndInPatientDate_DateAndRemoveDateTimeIsNull(int nationalCode , Date inPatientDate);
     List<InPatient> findInPatientsByPatient_Person_NationalCodeAndRemoveDateTimeIsNull(int nationalCode);
}
