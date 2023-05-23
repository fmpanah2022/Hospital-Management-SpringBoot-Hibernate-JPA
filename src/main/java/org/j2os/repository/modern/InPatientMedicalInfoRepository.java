package org.j2os.repository.modern;

import org.j2os.domain.entity.InPatientMedicalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface InPatientMedicalInfoRepository extends JpaRepository<InPatientMedicalInfo, Long> {
    List<InPatientMedicalInfo> findInPatientMedicalInfoByInPatientIdAndRemoveDateTimeIsNull(Long inPatientId);
    List<InPatientMedicalInfo> findInPatientMedicalInfoByRemoveDateTimeIsNull();
    Optional<InPatientMedicalInfo> findInPatientMedicalInfoByInPatientMedicalInfoIdAndRemoveDateTimeIsNull(Long inPatientMedicalInfoId);
 }
