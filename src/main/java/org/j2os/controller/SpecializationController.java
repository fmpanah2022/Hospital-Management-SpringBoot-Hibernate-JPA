package org.j2os.controller;

import org.j2os.domain.entity.Specialization;
import org.j2os.service.modern.SpecService;
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
@RequestMapping("/spec")
public class SpecializationController {
    @Autowired
    private SpecService specService;
    //*******************************************************
    @GetMapping("/specs")
    public ResponseEntity<List<Map<String, Object>>> getAllSpecs() {
        try {
            List<Specialization> specs = specService.findSpecializationsByRemoveDateTimeIsNull();
            if (specs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(specService.convertSpecListToMapList(specs), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findBySpecialization")
    public ResponseEntity<Map<String, Object>> findBySpecialization(@RequestParam("spec") String spec) {
        try {
            Specialization spec1 = specService.findSpecializationBySpecializationAndRemoveDateTimeIsNull(spec);
            if (spec1.getSpecializationId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(specService.convertSpecToMap(spec1), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/save")
    public ResponseEntity<Map<String, Object>> saveSpec(@RequestParam("spec") String spec ) {
        try {
            Specialization Specialization = new Specialization().builder().specialization(spec).build();

            if (specService.existsSpecialization(Specialization)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Specialization savedSpec = specService.save(Specialization);
            if (savedSpec.getSpecializationId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(specService.convertSpecToMap(savedSpec), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/update")
    public ResponseEntity<Map<String, Object>> updateSpec(@RequestParam("spec") String spec,
                                                          @RequestParam("specId") String specId,
                                                          @RequestParam("specVno") String specVno) throws Exception {
        Optional<Specialization> specData = specService.findBySpecializationIdAndRemoveDateTimeIsNull(Long.valueOf(specId));
        if (specData.isPresent()) {
            Specialization specialization= specData.get();
            specialization.builder().specialization(spec).versionNumber(Integer.parseInt(specVno)).build();

            if (specService.conflictSpecialization(specialization)) {  return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
            Specialization savedSpec = specService.update(specialization);
            return new ResponseEntity<>(specService.convertSpecToMap(savedSpec), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/remove")
    public ResponseEntity<HttpStatus> removeLogicalSpec(@RequestParam("specId") String specId,
                                                        @RequestParam("specVno") String specVno) {
        Optional<Specialization> specData = specService.findBySpecializationIdAndRemoveDateTimeIsNull(Long.valueOf(specId));
        if (specData.isPresent()) {
            Specialization specialization = specData.get();
            specialization.setVersionNumber(Integer.parseInt(specVno));
            if (! specService.canRemove(specialization)) {  return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
            specService.removeLogical(specialization);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
