package org.j2os.service.Logic;

import org.j2os.domain.entity.OutPatient;
import org.j2os.domain.entity.OutPatientMedicalInfo;
import org.j2os.service.modern.OutPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class OutPatientLogic {
    @Autowired
    private OutPatientService outPatientService;
    //------------------------------------------------------------------------
    public ResponseEntity<Map<String, Object>> saveWithMedicalInfo(OutPatient outPatient, List<OutPatientMedicalInfo> outPatientMedicalInfos) {
            if (outPatientService.existsOutPatient(outPatient)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            OutPatient savedData = outPatientService.saveWithMedicalInfo(outPatient,outPatientMedicalInfos);
            if (savedData.getOutPatientId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(outPatientService.convertOutPatientToMap(outPatientService.findByOutPatientId(savedData.getOutPatientId())), HttpStatus.OK);
    }
    public ResponseEntity<Map<String, Object>> saveOutPatient(OutPatient outPatient) {
            if (outPatientService.existsOutPatient(outPatient)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            OutPatient savedData = outPatientService.save(outPatient);
            if (savedData.getOutPatientId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(outPatientService.convertOutPatientToMap(outPatientService.findByOutPatientId(savedData.getOutPatientId())), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> updateOutPatient(OutPatient outPatient) {
            if (outPatientService.conflictOutPatient(outPatient)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            OutPatient savedData = outPatientService.update(outPatient);
            return new ResponseEntity<>(outPatientService.convertOutPatientToMap(outPatientService.findByOutPatientId(savedData.getOutPatientId())), HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> removeLogicalOutPatient(OutPatient outPatient){
            if (!outPatientService.canRemove(outPatient)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            outPatientService.removeLogical(outPatient);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
