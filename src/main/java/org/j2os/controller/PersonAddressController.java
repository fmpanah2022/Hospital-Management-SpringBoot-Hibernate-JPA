package org.j2os.controller;

import org.j2os.domain.entity.Person;
import org.j2os.domain.entity.PersonAddress;
import org.j2os.service.modern.PersonAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/personAdd")
public class PersonAddressController {
    @Autowired
    private PersonAddressService personAddressService;
    //*******************************************************
    @PostMapping("/savePost")
    public ResponseEntity<Map<String, Object>> createPersonAddress(@RequestBody PersonAddress personAddress) {
        try {
            PersonAddress personAdd1 = personAddressService.save(personAddress);
            return new ResponseEntity<>(personAddressService.convertPersonAddressToMap(personAdd1), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findByPersonId")
    public ResponseEntity<Map<String, Object>> findByPersonId(@RequestParam("personId") String personId) {
        try {
            PersonAddress personAdd = personAddressService.findPersonAddressByPerson_PersonIdAndRemoveDateTimeIsNull(Long.valueOf(personId));
            if (personAdd.getPerson() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(personAddressService.convertPersonAddressToMap(personAdd), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByNationalCode")
    public ResponseEntity<Map<String, Object>> findByNationalCode(@RequestParam("nationalCode") String nationalCode) {
        try {
            PersonAddress personAdd = personAddressService.findPersonAddressByPerson_NationalCodeAndRemoveDateTimeIsNull(Integer.parseInt(nationalCode));
            if (personAdd.getPersonAddressId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(personAddressService.convertPersonAddressToMap(personAdd), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByCountry")
    public ResponseEntity<List<Map<String, Object>>> findByCountry(@RequestParam("country") String country) {
        try {
            List<PersonAddress> personAdds = personAddressService.findPersonAddressesByCountryAndRemoveDateTimeIsNull(country);
            if (personAdds.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(personAddressService.convertPersonAddressListToMapList(personAdds), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByCity")
    public ResponseEntity<List<Map<String, Object>>> findByCity(@RequestParam("city") String city) {
        try {
            List<PersonAddress> personAdds = personAddressService.findPersonAddressesByCityAndRemoveDateTimeIsNull(city);
            if (personAdds.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(personAddressService.convertPersonAddressListToMapList(personAdds), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByState")
    public ResponseEntity<List<Map<String, Object>>> findByState(@RequestParam("state") String state) {
        try {
            List<PersonAddress> personAdds = personAddressService.findPersonAddressesByStateAndRemoveDateTimeIsNull(state);
            if (personAdds.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(personAddressService.convertPersonAddressListToMapList(personAdds), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findByPostalCode")
    public ResponseEntity<List<Map<String, Object>>> findByPostalCode(@RequestParam("postalCode") String postalCode) {
        try {
            List<PersonAddress> personAdds = personAddressService.findPersonAddressesByPostalCodeAndRemoveDateTimeIsNull(postalCode);
            if (personAdds.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(personAddressService.convertPersonAddressListToMapList(personAdds), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/save")
    public ResponseEntity<Map<String, Object>>  savePersonAdd(@RequestParam("personId") String personId ,
                                                     @RequestParam("country") String country,
                                                     @RequestParam("city") String city,
                                                     @RequestParam("state") String state,
                                                     @RequestParam("street") String street,
                                                     @RequestParam("postalCode") String postalCode) {
        try {
            Person person = new Person().builder().personId(Long.valueOf(personId)).build();
            PersonAddress personAdd1 = new PersonAddress().builder().person(person).country(country).city(city).state(state).street(street).postalCode(postalCode).build();
            PersonAddress savedPersonAdd = personAddressService.save(personAdd1);
            if (savedPersonAdd.getPersonAddressId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(personAddressService.convertPersonAddressToMap(savedPersonAdd), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/update")
    public ResponseEntity<Map<String, Object>> updatePersonAdd(@RequestParam("personAddressId") String personAddressId,
                                                  @RequestParam("country") String country,
                                                  @RequestParam("city") String city,
                                                  @RequestParam("state") String state,
                                                  @RequestParam("street") String street,
                                                  @RequestParam("postalCode") String postalCode,
                                                  @RequestParam("addVno") String addVno){
        Optional<PersonAddress> data = personAddressService.findPersonAddressByPersonAddressIdAndRemoveDateTimeIsNull(Long.valueOf(personAddressId));
        if (data.isPresent()) {
            PersonAddress personAdd1= data.get();
            personAdd1.builder().country(country).city(city).state(state).street(street).postalCode(postalCode).versionNumber(Integer.parseInt(addVno)).build();
            PersonAddress savedPersonAdd = personAddressService.update(personAdd1);
            return new ResponseEntity<>(personAddressService.convertPersonAddressToMap(savedPersonAdd), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/remove")
    public ResponseEntity<HttpStatus> removeLogicalPersonAdd(@RequestParam("personAddressId") String personAddressId,
                                                             @RequestParam("addVno") String addVno){
        Optional<PersonAddress> data = personAddressService.findPersonAddressByPersonAddressIdAndRemoveDateTimeIsNull(Long.valueOf(personAddressId));
        if (data.isPresent()) {
            PersonAddress personAdd1 = data.get();
            personAdd1.setVersionNumber(Integer.parseInt(addVno));
            personAddressService.removeLogical(personAdd1);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
