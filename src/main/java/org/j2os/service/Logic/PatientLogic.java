package org.j2os.service.Logic;

import org.j2os.domain.entity.Patient;
import org.j2os.service.modern.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PatientLogic {
    @Autowired
    private PatientService patientService;
    //*****************************************
    public ResponseEntity<Map<String, Object>> savePatient(Patient patient) {
            if (patientService.existsPatient(patient)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Patient savedPatient = patientService.save(patient);
            if (savedPatient.getPatientId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(patientService.convertPatientToMap(savedPatient), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> updatePatient(Patient patient) {
            if (patientService.conflictPatient(patient)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Patient savedPatient = patientService.update(patient);
            return new ResponseEntity<>(patientService.convertPatientToMap(savedPatient), HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> removeLogicalPatient(Patient patient)  {
            if (!patientService.canRemove(patient)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            patientService.removeLogical(patient);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
