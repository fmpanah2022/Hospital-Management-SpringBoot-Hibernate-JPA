package org.j2os.service.modern;

import org.j2os.domain.entity.ServiceForPatient;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ServiceForPatientService {
    Map<String, Object> convertServiceForPatientToMap(ServiceForPatient service);
    List<Map<String, Object>> convertServiceForPatientListToMapList(List<ServiceForPatient> services);
    Optional<ServiceForPatient> findServiceForPatientByServiceIdAndRemoveDateTimeIsNull(Long serviceId);
    List<ServiceForPatient> findServiceForPatientsByRemoveDateTimeIsNull();
    ServiceForPatient findServiceForPatientByServiceNameAndRemoveDateTimeIsNull(String serviceName);
    List<ServiceForPatient> findServiceForPatientsByServiceUnitCostIsBetween(int fromCost , int toCost);
    boolean existsService ( ServiceForPatient service);
    boolean conflictService ( ServiceForPatient service);
    boolean canRemove( ServiceForPatient service) ;
    ServiceForPatient save (ServiceForPatient service);
    ServiceForPatient update (ServiceForPatient service);
    void removeLogical(ServiceForPatient service);
}
