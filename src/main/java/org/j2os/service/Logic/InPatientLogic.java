package org.j2os.service.Logic;

import org.j2os.domain.entity.InPatient;
import org.j2os.service.modern.InPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Service
public class InPatientLogic {
    @Autowired
    private InPatientService inPatientService;
    //------------------------------------------------
    public ResponseEntity<Map<String, Object>> saveInPatient(InPatient inPatient) {
            if (inPatientService.existsInPatient(inPatient)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            InPatient savedInPatient = inPatientService.save(inPatient);
            if (savedInPatient.getInPatientId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inPatientService.convertInPatientToMap(savedInPatient), HttpStatus.OK);
    }
    @GetMapping("/saveWithMedicalInfo")
    public ResponseEntity<Map<String, Object>> saveInPatientWithMedicalInfo(InPatient inPatient, List<String> medicineCodeList) {
            if (inPatientService.existsInPatient(inPatient)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            InPatient savedInPatient = inPatientService.saveWithMedicalInfo(inPatient ,medicineCodeList);
            if (savedInPatient.getInPatientId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inPatientService.convertInPatientToMap(savedInPatient), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> updateInPatient(InPatient iPatient) {
            if (inPatientService.conflictInPatient(iPatient)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            InPatient savedInPatient = inPatientService.update(iPatient);
            return new ResponseEntity<>(inPatientService.convertInPatientToMap(savedInPatient), HttpStatus.OK);
    }
    public ResponseEntity<HttpStatus> removeLogicalDoctor(InPatient inPatient)  {
            if (!inPatientService.canRemove(inPatient)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            inPatientService.removeLogical(inPatient);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
