package org.j2os.repository.modern;

import org.j2os.domain.entity.InPatient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface InPatientRepository extends JpaRepository<InPatient, Long> {
    Optional<InPatient> findByInPatientIdAndRemoveDateTimeIsNull(Long patientId);

    List<InPatient> findInPatientsByRemoveDateTimeIsNull();

    List<InPatient> findInPatientsByPatient_Person_NationalCodeAndRemoveDateTimeIsNull(int nationalCode);

    List<InPatient> findInPatientsByDoctor_DoctorIdAndRemoveDateTimeIsNull(Long doctorId);

    List<InPatient> findInPatientsByPatient_PatientIdAndRemoveDateTimeIsNull(Long patientId);

    List<InPatient> findInPatientsByRoom_RoomIdAndRemoveDateTimeIsNull(Long roomId);

    List<InPatient> findInPatientsByStaff_StaffIdAndRemoveDateTimeIsNull(Long staffId);
}