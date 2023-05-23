package org.j2os.repository.modern;

import org.j2os.domain.entity.InPatientServiceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface InPatientServiceInfoRepository extends JpaRepository<InPatientServiceInfo, Long> {
    List<InPatientServiceInfo> findInPatientServiceInfosByServiceIdAndRemoveDateTimeIsNull(Long serviceId);
    List<InPatientServiceInfo> findInPatientServiceInfoByInPatientIdAndRemoveDateTimeIsNull(Long inPatientId);
    List<InPatientServiceInfo> findInPatientServiceInfoByRemoveDateTimeIsNull();
    Optional<InPatientServiceInfo> findInPatientServiceInfoByInPatientServiceInfoIdAndRemoveDateTimeIsNull(Long inPatientServiceInfoId);
}
