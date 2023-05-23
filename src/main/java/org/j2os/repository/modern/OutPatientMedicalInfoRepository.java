package org.j2os.repository.modern;

import org.j2os.domain.entity.OutPatientMedicalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OutPatientMedicalInfoRepository extends JpaRepository<OutPatientMedicalInfo , Long> {
    List<OutPatientMedicalInfo> findOutPatientMedicalInfosByOutPatientIdAndRemoveDateTimeIsNull(Long outPatientId);
    Optional<OutPatientMedicalInfo> findOutPatientMedicalInfosByOutPatientMedicalInfoIdAndRemoveDateTimeIsNull (Long outPatientMedicalInfoId);
}
