package org.j2os.service.Implementation;

import org.j2os.domain.entity.ServiceForPatient;
import org.j2os.repository.Classic.ServiceForPatientClassicRepository;
import org.j2os.repository.modern.InPatientServiceInfoRepository;
import org.j2os.repository.modern.ServiceForPatientRepository;
import org.j2os.service.modern.ServiceForPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceForPatientServiceImp implements ServiceForPatientService {
    @Autowired
    private ServiceForPatientRepository serviceRepository;
    @Autowired
    private ServiceForPatientClassicRepository serviceClassicRepository;
    @Autowired
    private InPatientServiceInfoRepository inPatientServiceInfoRepository;
    //-----------------------------------------------------------------------------------
    @Override
    public boolean existsService(ServiceForPatient service) {
        return (serviceRepository.findServiceForPatientByServiceNameAndRemoveDateTimeIsNull(service.getServiceName()) != null);
    }

    @Override
    public boolean conflictService(ServiceForPatient service) {
        ServiceForPatient service1 = serviceRepository.findServiceForPatientByServiceNameAndRemoveDateTimeIsNull(service.getServiceName());
        if  (  service1 != null && service1.getServiceId() != null)
            return( service1.getServiceId() != service.getServiceId()) ;
        else return false;
    }

    @Override
    public boolean canRemove(ServiceForPatient service) {
        return ( inPatientServiceInfoRepository.findInPatientServiceInfosByServiceIdAndRemoveDateTimeIsNull(service.getServiceId()).isEmpty());
    }

    @Override
    public Map<String, Object> convertServiceForPatientToMap(ServiceForPatient service) {
        Map<String, Object> data = new HashMap<>();
        data.put("serviceId",service.getServiceId());
        data.put("serviceName", service.getServiceName());
        data.put("serviceUnitCost", service.getServiceUnitCost());
        data.put("versionNumber", service.getVersionNumber());
        return data;
    }
    @Override
    public List<Map<String, Object>> convertServiceForPatientListToMapList(List<ServiceForPatient> services) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (ServiceForPatient service : services) {
            Map<String, Object> data = new HashMap<>();
            data.put("serviceId",service.getServiceId());
            data.put("serviceName", service.getServiceName());
            data.put("serviceUnitCost", service.getServiceUnitCost());
            data.put("versionNumber", service.getVersionNumber());
            dataList.add(data);
        }
        return dataList;
    }
    @Override
    public Optional<ServiceForPatient> findServiceForPatientByServiceIdAndRemoveDateTimeIsNull(Long serviceId) {
        return serviceRepository.findServiceForPatientByServiceIdAndRemoveDateTimeIsNull(serviceId);
    }

    @Override
    public List<ServiceForPatient> findServiceForPatientsByRemoveDateTimeIsNull() {
        return serviceRepository.findServiceForPatientsByRemoveDateTimeIsNull();
    }

    @Override
    public ServiceForPatient findServiceForPatientByServiceNameAndRemoveDateTimeIsNull(String serviceName) {
        return serviceRepository.findServiceForPatientByServiceNameAndRemoveDateTimeIsNull(serviceName);
    }

    @Override
    public List<ServiceForPatient> findServiceForPatientsByServiceUnitCostIsBetween(int fromCost, int toCost) {
        return serviceRepository.findServiceForPatientsByServiceUnitCostIsBetween(fromCost, toCost);
    }

    @Override
    public ServiceForPatient save(ServiceForPatient service) {
        return serviceClassicRepository.save(service);
    }

    @Override
    public ServiceForPatient update(ServiceForPatient service) {
        return serviceClassicRepository.update(service);
    }

    @Override
    public void removeLogical(ServiceForPatient service) {
        serviceClassicRepository.removeLogical(service);
    }
}
