package org.j2os.repository.modern;

import org.j2os.domain.entity.ServiceForPatient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ServiceForPatientRepository extends JpaRepository<ServiceForPatient , Long> {
    Optional<ServiceForPatient> findServiceForPatientByServiceIdAndRemoveDateTimeIsNull(Long serviceId);
    List<ServiceForPatient> findServiceForPatientsByRemoveDateTimeIsNull();
    ServiceForPatient findServiceForPatientByServiceNameAndRemoveDateTimeIsNull(String serviceName);
    List<ServiceForPatient> findServiceForPatientsByServiceUnitCostIsBetween(int fromCost , int toCost);
}
