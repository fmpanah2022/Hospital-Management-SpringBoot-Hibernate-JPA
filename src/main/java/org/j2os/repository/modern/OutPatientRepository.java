package org.j2os.repository.modern;

import org.j2os.domain.entity.OutPatient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OutPatientRepository extends JpaRepository<OutPatient, Long> {
    Optional<OutPatient> findOutPatientByOutPatientIdAndRemoveDateTimeIsNull(Long outPatientId);
    OutPatient findOutPatientByInPatient_InPatientIdAndRemoveDateTimeIsNull(Long inPatientId);
    //List<OutPatient> findOutPatientsByOutPatientDate_DateAndRemoveDateTimeIsNull(Date outPatientDate);
    //List<OutPatient> findOutPatientsByInPatient_InPatientDate_DateAndRemoveDateTimeIsNull(Date inPatientDate);

}
