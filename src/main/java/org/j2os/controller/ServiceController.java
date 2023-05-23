package org.j2os.controller;

import org.j2os.domain.entity.ServiceForPatient;
import org.j2os.service.modern.ServiceForPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    private ServiceForPatientService serviceForPatientService;
    //*******************************************************
    @GetMapping("/services")
    public ResponseEntity<List<Map<String, Object>>> getAllServices() {
        try {
            List<ServiceForPatient> services = serviceForPatientService.findServiceForPatientsByRemoveDateTimeIsNull();
            if (services.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(serviceForPatientService.convertServiceForPatientListToMapList(services), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findByServiceName")
    public ResponseEntity<Map<String, Object>> findByServiceName(@RequestParam("serviceName") String serviceName) {
        try {
            ServiceForPatient service =  serviceForPatientService.findServiceForPatientByServiceNameAndRemoveDateTimeIsNull(serviceName);
            if (service.getServiceId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(serviceForPatientService.convertServiceForPatientToMap(service), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByServiceUnitCostIsBetween")
    public ResponseEntity<List<Map<String, Object>>> findByServiceUnitCostIsBetween(@RequestParam("fromCost") String fromCost ,
                                                                              @RequestParam("toCost") String toCost) {
        try {
            List<ServiceForPatient> services = new ArrayList<ServiceForPatient>();
            services = serviceForPatientService.findServiceForPatientsByServiceUnitCostIsBetween(Integer.parseInt(fromCost),Integer.parseInt(toCost));
            if (services.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(serviceForPatientService.convertServiceForPatientListToMapList(services), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //***********************************************************
    @GetMapping("/save")
    public ResponseEntity<Map<String, Object>> saveService(@RequestParam("serviceName") String serviceName ,
                                                           @RequestParam("serviceUnitCost") String serviceUnitCost) {
        try {
            ServiceForPatient service1 = new ServiceForPatient().builder().serviceName(serviceName).serviceUnitCost(Integer.parseInt(serviceUnitCost)).build();
            if (serviceForPatientService.existsService(service1)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            ServiceForPatient savedService = serviceForPatientService.save(service1);
            if (savedService.getServiceId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(serviceForPatientService.convertServiceForPatientToMap(savedService), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //***************************************************************************************
    @GetMapping("/update")
    public ResponseEntity<Map<String, Object>> updateService(@RequestParam("serviceId") String serviceId,
                                              @RequestParam("serviceName") String serviceName ,
                                              @RequestParam("serviceUnitCost") String serviceUnitCost,
                                              @RequestParam("serviceVno") String serviceVno) {

        Optional<ServiceForPatient> serviceData = serviceForPatientService.findServiceForPatientByServiceIdAndRemoveDateTimeIsNull(Long.valueOf(serviceId));
        if (serviceData.isPresent()) {
            ServiceForPatient service1 = serviceData.get();
            service1.builder().serviceName(serviceName).serviceUnitCost(Integer.parseInt(serviceUnitCost)).versionNumber(Integer.parseInt(serviceVno)).build();

            if (serviceForPatientService.conflictService(service1)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            ServiceForPatient savedService = serviceForPatientService.update(service1);
            return new ResponseEntity<>(serviceForPatientService.convertServiceForPatientToMap(savedService), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/remove")
    public ResponseEntity<HttpStatus> removeLogicalService(@RequestParam("serviceId") String serviceId,
                                                           @RequestParam("serviceVno") String serviceVno) {
        Optional<ServiceForPatient> serviceData = serviceForPatientService.findServiceForPatientByServiceIdAndRemoveDateTimeIsNull(Long.valueOf(serviceId));
        if (serviceData.isPresent()) {
            ServiceForPatient service1 = serviceData.get();
            service1.setVersionNumber(Integer.parseInt(serviceVno));
            if (!serviceForPatientService.canRemove(service1)) {  return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }

            serviceForPatientService.removeLogical(service1);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
