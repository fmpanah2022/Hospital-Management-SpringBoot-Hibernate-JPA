package org.j2os.service.Logic;

import org.j2os.domain.entity.Doctor;
import org.j2os.common.CommonService;
import org.j2os.service.modern.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class DoctorLogic {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private CommonService commonService;
    //*********************************************************************************
    public ResponseEntity<Map<String, Object>> saveDoctorWithSpecList(Doctor doctor, List<String> specList) {
            if (doctorService.existsDoctor(doctor)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            doctor.setPersonalCode(commonService.createPersonalCode(doctor.getPerson().getNationalCode(),"Doctor"));
            Doctor savedDoctor = doctorService.saveWithSpecList(doctor,specList);
            if (savedDoctor.getDoctorId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(doctorService.convertDoctorToMap(savedDoctor), HttpStatus.OK);
    }
    public ResponseEntity<Map<String, Object>> saveDoctor(Doctor doctor) {
            if (doctorService.existsDoctor(doctor)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            doctor.setPersonalCode(commonService.createPersonalCode(doctor.getPerson().getNationalCode(),"Doctor"));
            Doctor savedDoctor = doctorService.save(doctor);
            if (savedDoctor.getDoctorId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(doctorService.convertDoctorToMap(savedDoctor), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> updateDoctor(Doctor doctor) throws Exception {
            if (doctorService.conflictDoctor(doctor)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Doctor savedDoctor = doctorService.update(doctor);
            return new ResponseEntity<>(doctorService.convertDoctorToMap(savedDoctor), HttpStatus.OK);
    }
    public ResponseEntity<HttpStatus> removeLogicalDoctor(Doctor doctor) throws Exception {
        if (!doctorService.canRemove(doctor)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        doctorService.removeLogical(doctor);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
