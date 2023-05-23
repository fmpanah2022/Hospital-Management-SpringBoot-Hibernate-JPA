package org.j2os.controller;

import org.j2os.domain.entity.Person;
import org.j2os.domain.entity.PersonTell;
import org.j2os.service.modern.PersonTellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/personTell")
public class PersonTellController {
    @Autowired
    private PersonTellService personTellService;
    //*******************************************************
    @PostMapping("/savePost")
    public ResponseEntity<Map<String, Object>> createPersonTell(@RequestBody PersonTell personTell) {
        try {
            PersonTell personTell1 = personTellService.save(personTell);
            return new ResponseEntity<>(personTellService.convertPersonTellToMap(personTell1), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findByPersonId")
    public ResponseEntity<Map<String, Object>> findByPersonId(@RequestParam("personId") String personId) {
        try {
            PersonTell personTell = personTellService.findPersonTellByPerson_PersonIdAndRemoveDateTimeIsNull(Long.valueOf(personId));
            if (personTell.getPersonTellId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(personTellService.convertPersonTellToMap(personTell), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByNationalCode")
    public ResponseEntity<Map<String, Object>> findByNationalCode(@RequestParam("nationalCode") String nationalCode) {
        try {
            PersonTell personTell = personTellService.findPersonTellByPerson_NationalCodeAndRemoveDateTimeIsNull(Integer.parseInt(nationalCode));
            if (personTell.getPersonTellId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(personTellService.convertPersonTellToMap(personTell), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByMobile")
    public ResponseEntity<List<Map<String, Object>>> findByMobile(@RequestParam("mobile") String mobile) {
        try {
            List<PersonTell> personTells = personTellService.findPersonTellsByMobileAndRemoveDateTimeIsNull(mobile);
            if (personTells.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(personTellService.convertPersonTellListToMapList(personTells), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByTell")
    public ResponseEntity<List<Map<String, Object>>> findByTell( @RequestParam("tell") String tell ) {
        try {
            List<PersonTell> personTells = personTellService.findPersonTellsByTellAndRemoveDateTimeIsNull(tell);
            if (personTells.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(personTellService.convertPersonTellListToMapList(personTells), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/save")
    public ResponseEntity<Map<String, Object>>  savePersonTell( @RequestParam("personId") String personId ,
                                                       @RequestParam("mobile") String mobile,
                                                       @RequestParam("tell") String tell) {
        try {
            Person person = new Person().builder().personId(Long.valueOf(personId)).build();
            PersonTell personTell1 = new PersonTell().builder().person(person).mobile(mobile).tell(tell).build();
            PersonTell savedPersonTell = personTellService.save(personTell1);
            if (savedPersonTell.getPersonTellId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(personTellService.convertPersonTellToMap(savedPersonTell), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/update")
    public ResponseEntity<Map<String, Object>> updatePersonTell(  @RequestParam("personTellId") String personTellId,
                                                         @RequestParam("mobile") String mobile,
                                                         @RequestParam("tell") String tell,
                                                         @RequestParam("tellVno") String tellVno){
        Optional<PersonTell> data = personTellService.findPersonTellByPersonTellIdAndRemoveDateTimeIsNull(Long.valueOf(personTellId));
        if (data.isPresent()) {
            PersonTell personTell1= data.get();
            personTell1.builder().mobile(mobile).tell(tell).versionNumber(Integer.parseInt(tellVno)).build();
            PersonTell savedPersonTell = personTellService.update(personTell1);
            return new ResponseEntity<>(personTellService.convertPersonTellToMap(savedPersonTell), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/remove")
    public ResponseEntity<HttpStatus> removeLogicalPersonTell( @RequestParam("personTellId") String personTellId ,
                                                               @RequestParam("tellVno") String tellVno){
        Optional<PersonTell> data = personTellService.findPersonTellByPersonTellIdAndRemoveDateTimeIsNull(Long.valueOf(personTellId));
        if (data.isPresent()) {
            PersonTell personTell1 = data.get();
            personTell1.setVersionNumber(Integer.parseInt(tellVno));
            personTellService.removeLogical(personTell1);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
