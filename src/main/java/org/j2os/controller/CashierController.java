package org.j2os.controller;

import org.j2os.common.ObjectMapper;
import org.j2os.domain.entity.Cashier;
import org.j2os.service.Logic.CashierLogic;
import org.j2os.service.modern.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cashier")
public class CashierController {
    @Autowired
    private CashierService cashierService;
    @Autowired
    private CashierLogic cashierLogic;
    //throw new ResponseStatusException(HttpStatus.BAD_REQUEST,String.format("%s does not exist ",name));
    //*****************************************
    @GetMapping("/cashiers")
    public ResponseEntity<List<Map<String, Object>>> getAllCashiers() {
        try {
            List<Cashier> cashiers = cashierService.findCashiersByRemoveDateTimeIsNotNull();
            if (cashiers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cashierService.convertCashierListToMapList(cashiers), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByFirstNameAndSurnameAndNationalCode")
    public ResponseEntity<Map<String, Object>> findByFirstNameAndSurnameAndNationalCode(
            @RequestParam("firstName") String firstName,
            @RequestParam("surname") String surname,
            @RequestParam("nationalCode") String nationalCode) {
        try {
            Cashier cashier1 = cashierService.findCashierByPerson_FirstNameAndPerson_SurnameAndPerson_NationalCodeAndRemoveDateTimeIsNull(firstName, surname, Integer.parseInt(nationalCode));
            if (cashier1.getCashierId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cashierService.convertCashierToMap(cashier1), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByNationalCode")
    public ResponseEntity<Map<String, Object>> findByNationalCode(@RequestParam("nationalCode") String nationalCode) {
        try {
            Cashier cashier1 = cashierService.findCashierByPerson_NationalCodeAndRemoveDateTimeIsNull(Integer.parseInt(nationalCode));
            if (cashier1.getCashierId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cashierService.convertCashierToMap(cashier1), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findByPersonalCode")
    public ResponseEntity<Map<String, Object>> findByPersonalCode(@RequestParam("personalCode") String personalCode) {
        try {
            Cashier cashier1 = cashierService.findCashierByPersonalCodeAndRemoveDateTimeIsNull(personalCode);
            if (cashier1.getCashierId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cashierService.convertCashierToMap(cashier1), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //**********************************************************************************************
    @PostMapping("/savepost")
    public ResponseEntity<Map<String, Object>> savePostCashier ( @RequestBody
            @RequestParam("firstName") String firstName,
                                                             @RequestParam("surname") String surname,
                                                             @RequestParam("nationalCode") String nationalCode,
                                                             @RequestParam("genderId") String genderId,
                                                             @RequestParam("userName") String userName,
                                                             @RequestParam("password") String password) {
        try {
            Cashier cashier = ObjectMapper.getInstance().mapToCashier(firstName,surname,nationalCode,genderId,userName,password);
            return  cashierLogic.saveCashier(cashier);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/save")
    public ResponseEntity<Map<String, Object>> saveCashier ( @RequestParam("firstName") String firstName,
                                                 @RequestParam("surname") String surname,
                                                 @RequestParam("nationalCode") String nationalCode,
                                                 @RequestParam("genderId") String genderId,
                                                 @RequestParam("userName") String userName,
                                                 @RequestParam("password") String password) {
        try {
            Cashier cashier = ObjectMapper.getInstance().mapToCashier(firstName,surname,nationalCode,genderId,userName,password);
            return  cashierLogic.saveCashier(cashier);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //**********************************************************************************************
    @GetMapping("/update")
    public ResponseEntity<Map<String, Object>> updateCashier(@RequestParam("cashierId") String cashierId,
                                                 @RequestParam("firstName") String firstName,
                                                 @RequestParam("surname") String surname,
                                                 @RequestParam("nationalCode") String nationalCode,
                                                 @RequestParam("genderId") String genderId,
                                                 @RequestParam("userId") String userId,
                                                 @RequestParam("password") String password,
                                                 @RequestParam("cashierVno") String cashierVno,
                                                 @RequestParam("userVno") String userVno,
                                                 @RequestParam("personVno") String personVno) {
        Optional<Cashier> cashierData = cashierService.findCashierByCashierIdAndRemoveDateTimeIsNull(Long.valueOf(cashierId));
        if (cashierData.isPresent()) {
           /* Gender gender = new Gender().builder().genderId(Long.valueOf(genderId)).build();
            Users user = new Users().builder().versionNumber(Integer.parseInt(userVno)).userId(Long.parseLong(userId)).password(password).build();
            Person person = new Person().builder().versionNumber(Integer.parseInt(personVno)).user(user).firstName(firstName).surname(surname).nationalCode(Integer.parseInt(nationalCode)).gender(gender).build();*/
            Cashier cashier = ObjectMapper.getInstance().mapToCashier(cashierId ,firstName,surname,nationalCode,genderId,userId,password,userVno,personVno,cashierVno);
            Cashier cashier1 = cashierData.get();
            cashier1.builder().person(cashier.getPerson()).versionNumber(cashier.getVersionNumber()).build();

            return  cashierLogic.updateCashier(cashier1);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //***************************************************************
    @GetMapping("/remove")
    public ResponseEntity<HttpStatus> removeLogicalCashier(@RequestParam("cashierId") String cashierId,
                                                           @RequestParam("cashierVno") String cashierVno)  {
        Optional<Cashier> cashierData = cashierService.findCashierByCashierIdAndRemoveDateTimeIsNull(Long.valueOf(cashierId));
        if (cashierData.isPresent()) {
            Cashier cashier1 = cashierData.get();
            cashier1.setVersionNumber(Integer.parseInt(cashierVno));
            return  cashierLogic.removeLogicalCashier(cashier1);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
