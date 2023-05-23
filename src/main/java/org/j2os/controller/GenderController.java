package org.j2os.controller;

import org.j2os.domain.entity.Gender;
import org.j2os.service.modern.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/gender")
public class GenderController {
    @Autowired
    private GenderService genderService;
    //*******************************************************
    @PostMapping("/savePost")
    public ResponseEntity<Gender> createGender(@RequestBody Gender gender) {
        try {
            Gender gender1 = genderService.save(gender);
            return new ResponseEntity<>(gender1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/genders")
    public ResponseEntity<List<Map<String, Object>>> getAllGenders() {
        try {
            List<Gender> genders = genderService.findGendersByRemoveDateTimeIsNull();
            if (genders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(genderService.convertGenderListToMapList(genders), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByGenderName")
    public ResponseEntity<Map<String, Object>> findByGenderName(@RequestParam("genderName") String genderName) {
        try {
            Gender gender = genderService.findGenderByGenderNameAndRemoveDateTimeIsNull(genderName);
            if (gender.getGenderId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(genderService.convertGenderToMap(gender), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/save")
    public ResponseEntity<Map<String, Object>>  saveGender(@RequestParam("genderName") String genderName ) {
        try {
            Gender gender = new Gender().builder().genderName(genderName).build();
            if (genderService.existsGender(gender)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Gender savedGender = genderService.save(gender);
            if (savedGender.getGenderId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(genderService.convertGenderToMap(savedGender), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/update")
    public ResponseEntity<Map<String, Object>> updateGender(@RequestParam("genderName") String genderName,
                                                            @RequestParam("genderId") String genderId,
                                                            @RequestParam("genderVno") String genderVno) throws Exception {
        Optional<Gender> genderData = genderService.findByGenderIdAndRemoveDateTimeIsNull(Long.valueOf(genderId));

        if (genderData.isPresent()) {
            Gender gender= genderData.get();
            gender.builder().versionNumber(Integer.parseInt(genderVno)).genderName(genderName).build();
            if (genderService.conflictGender(gender)) {  return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
            Gender savedGender = genderService.update(gender);
            return new ResponseEntity<>(genderService.convertGenderToMap(savedGender), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/remove")
    public ResponseEntity<HttpStatus> removeLogicalGender(@RequestParam("genderId") String genderId,
                                                          @RequestParam("genderVno") String genderVno) throws Exception {
        Optional<Gender> genderData = genderService.findByGenderIdAndRemoveDateTimeIsNull(Long.valueOf(genderId));

        if (genderData.isPresent()) {
            Gender gender = genderData.get();
            gender.setVersionNumber(Integer.parseInt(genderVno));
            if (! genderService.canRemove(gender)) {  return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
            genderService.removeLogical(gender);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
