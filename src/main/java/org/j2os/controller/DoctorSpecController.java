package org.j2os.controller;

import org.j2os.domain.entity.DoctorSpec;
import org.j2os.service.Implementation.DoctorSpecServiceImp;
import org.j2os.service.Implementation.DoctorSpecViewServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/doctorSpec")
public class DoctorSpecController {
    @Autowired
    private DoctorSpecViewServiceImp doctorSpecViewService;
    @Autowired
    private DoctorSpecServiceImp doctorSpecService;
    //*****************************************
    @GetMapping("/doctorSpecs")
    public ResponseEntity<List<Map<String, Object>>> getAllDoctors() {
        try {
            List<Map<String, Object>> doctorSpecs = doctorSpecViewService.findAllDoctorSpecViews();
            if (doctorSpecs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(doctorSpecs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findBySpecializationId")
    public ResponseEntity<List<Map<String, Object>>> findBySpecializationId(@RequestParam("specializationId") String specializationId) {
        try {
            List<Map<String, Object>> doctorSpec = doctorSpecViewService.findDoctorSpecViewsBySpecializationId(Long.valueOf(specializationId));
            if (doctorSpec.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(doctorSpec, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByDoctorId")
    public ResponseEntity<List<Map<String, Object>>> findByDoctorId(@RequestParam("doctorId") String doctorId) {
        try {
            List<Map<String, Object>> doctorSpec = doctorSpecViewService.findDoctorSpecViewsByDoctorId(Long.valueOf(doctorId));
            if (doctorSpec.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(doctorSpec, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/saveDoctorSpecList")
    public ResponseEntity<List<Map<String, Object>>> saveDoctorSpecListOfOneDoctor(@RequestParam("doctorId") String doctorId,
                                                                                   @RequestParam("specNameList") List<String> specNameList) {
        try {
            List<DoctorSpec> savedData = doctorSpecService.saveDoctorSpecListOfOneDoctor(Long.valueOf(doctorId),specNameList);
            if (savedData.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(doctorSpecViewService.findDoctorSpecViewsByDoctorId(Long.valueOf(doctorId)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //**************************************************************************************************
    @GetMapping("/save")
    public ResponseEntity<Map<String, Object>> saveDoctorSpec ( @RequestParam("doctorId") String doctorId,
                                                                @RequestParam("specId") String specId) {
        try {
            DoctorSpec doctorSpec = new DoctorSpec().builder().specializationId(Long.valueOf(specId)).doctorId(Long.valueOf(doctorId)).build();
            if (doctorSpecService.existsDoctorSpec(doctorSpec)){return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
            DoctorSpec savedData = doctorSpecService.save(doctorSpec);
            if (savedData.getDoctorSpecId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(doctorSpecViewService.findDoctorSpecViewByDoctorSpecId(savedData.getDoctorSpecId()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //**********************************************************************************************
    @GetMapping("/update")
    public ResponseEntity<Map<String, Object>> updateDoctorSpec( @RequestParam("doctorSpecId") String doctorSpecId,
                                                                 @RequestParam("doctorId") String doctorId,
                                                                 @RequestParam("specId") String specId,
                                                                 @RequestParam("doctorSpecVno") String doctorSpecVno) {
        Optional<DoctorSpec> data = doctorSpecService.findDoctorSpecByDoctorSpecIdAndRemoveDateTimeIsNull(Long.valueOf(doctorSpecId));
        if (data.isPresent()) {
            DoctorSpec doctorSpec1 = data.get();
            doctorSpec1.builder().doctorId(Long.valueOf(doctorId)).specializationId(Long.valueOf(specId)).versionNumber(Integer.parseInt(doctorSpecVno)).build();
            if (doctorSpecService.conflictDoctorSpec(doctorSpec1)){return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
            DoctorSpec savedData = doctorSpecService.update(doctorSpec1);
            if (savedData.getDoctorSpecId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(doctorSpecViewService.findDoctorSpecViewByDoctorSpecId(savedData.getDoctorSpecId()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //***************************************************************
    @GetMapping("/remove")
    public ResponseEntity<HttpStatus> removeLogicalDoctorSpec(@RequestParam("doctorSpecId") String doctorSpecId,
                                                              @RequestParam("doctorSpecVno") String doctorSpecVno) {
        Optional<DoctorSpec> data = doctorSpecService.findDoctorSpecByDoctorSpecIdAndRemoveDateTimeIsNull(Long.valueOf(doctorSpecId));
        if (data.isPresent()) {
            DoctorSpec doctorSpec1 = data.get();
            doctorSpec1.setVersionNumber(Integer.parseInt(doctorSpecVno));
            doctorSpecService.removeLogical(doctorSpec1);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
