package org.j2os.service.Implementation;

import org.j2os.domain.entity.Cashier;
import org.j2os.repository.modern.CashierRepository;
import org.j2os.repository.Classic.CashierClassicRepository;
import org.j2os.repository.modern.PaymentRepository;
import org.j2os.service.modern.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class CashierServiceImp implements CashierService {
    @Autowired
    private CashierClassicRepository cashierClassicRepository;
    @Autowired
    private CashierRepository cashierRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    //--------------------------------------------------
    @Override
    public Map<String, Object> convertCashierToMap(Cashier cashier) {
        Map<String, Object> data = new HashMap<>();
        data.put("cashierId",cashier.getCashierId());
        data.put("firstName", cashier.getPerson().getFirstName());
        data.put("surname", cashier.getPerson().getSurname());
        data.put("nationalCode", cashier.getPerson().getNationalCode());
        data.put("personId", cashier.getPerson().getPersonId());
        data.put("personalCode", cashier.getPersonalCode());
        data.put("genderId" , cashier.getPerson().getGender().getGenderId());
        data.put("genderName", cashier.getPerson().getGender().getGenderName());
        data.put("birthdate", cashier.getPerson().getBirthdate());
        data.put("userId" , cashier.getPerson().getUser().getUserId());
        data.put("userName", cashier.getPerson().getUser().getUserName());
        data.put("password", cashier.getPerson().getUser().getPassword());
        data.put("userVno", cashier.getPerson().getUser().getVersionNumber());
        data.put("personVno", cashier.getPerson().getVersionNumber());
        data.put("cashierVno", cashier.getVersionNumber());
        Map<String, Object> synMapData = Collections.synchronizedMap(data);
        return synMapData;
    }
    @Override
    public List<Map<String, Object>> convertCashierListToMapList(List<Cashier> cashiers) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (Cashier cashier:cashiers) {
            Map<String,Object> data = new ConcurrentHashMap<>();
         //   Map<String, Object> data = new HashMap<>();
            data.put("cashierId",cashier.getCashierId());
            data.put("firstName", cashier.getPerson().getFirstName());
            data.put("surname", cashier.getPerson().getSurname());
            data.put("nationalCode", cashier.getPerson().getNationalCode());
            data.put("personId", cashier.getPerson().getPersonId());
            data.put("personalCode", cashier.getPersonalCode());
            data.put("genderId" , cashier.getPerson().getGender().getGenderId());
            data.put("genderName", cashier.getPerson().getGender().getGenderName());
            data.put("birthdate", cashier.getPerson().getBirthdate());
            data.put("userId" , cashier.getPerson().getUser().getUserId());
            data.put("userName", cashier.getPerson().getUser().getUserName());
            data.put("password", cashier.getPerson().getUser().getPassword());
            data.put("userVno", cashier.getPerson().getUser().getVersionNumber());
            data.put("personVno", cashier.getPerson().getVersionNumber());
            data.put("cashierVno", cashier.getVersionNumber());
          //  Map<String, Object> synMapData = Collections.synchronizedMap(data);
            dataList.add(data);
        }

        return dataList;
    }
    @Override
    public Cashier findCashierByPersonalCodeAndRemoveDateTimeIsNull(String personalCode) {
        return cashierRepository.findCashierByPersonalCodeAndRemoveDateTimeIsNull(personalCode);
    }
    @Override
    public Optional<Cashier> findCashierByCashierIdAndRemoveDateTimeIsNull(Long cashierId) {
        return cashierRepository.findCashierByCashierIdAndRemoveDateTimeIsNull(cashierId);
    }

    @Override
    public List<Cashier> findCashiersByRemoveDateTimeIsNotNull() {
        return cashierRepository.findCashiersByRemoveDateTimeIsNotNull();
    }

    @Override
    public Cashier findCashierByPerson_FirstNameAndPerson_SurnameAndPerson_NationalCodeAndRemoveDateTimeIsNull(String firstName, String surname, int nationalCode) {
        return cashierRepository.findCashierByPerson_FirstNameAndPerson_SurnameAndPerson_NationalCodeAndRemoveDateTimeIsNull(firstName, surname, nationalCode);
    }

    @Override
    public Cashier findCashierByPerson_NationalCodeAndRemoveDateTimeIsNull(int nationalCode) {
        return cashierRepository.findCashierByPerson_NationalCodeAndRemoveDateTimeIsNull(nationalCode);
    }

    @Override
    @Transactional
    public Cashier save(Cashier cashier) {
        return cashierClassicRepository.save(cashier);
    }

    @Override
    @Transactional
    public Cashier update(Cashier cashier) {
        return cashierClassicRepository.update(cashier);
    }

    @Override
    @Transactional
    public void removeLogical(Cashier cashier) {
        cashierClassicRepository.removeLogical(cashier);
    }

    @Override
    public boolean existsCashier(Cashier cashier) {
        return  (  cashierRepository.findCashierByPerson_NationalCodeAndRemoveDateTimeIsNull(cashier.getPerson().getNationalCode()) != null  ) ;
    }

    @Override
    public boolean conflictCashier(Cashier cashier) {
        Cashier cashier1 = cashierRepository.findCashierByPerson_NationalCodeAndRemoveDateTimeIsNull(cashier.getPerson().getNationalCode());
        if  (  cashier1 != null && cashier1.getCashierId() != null)
            return( cashier1.getCashierId() != cashier.getCashierId()) ;
        else return false;
    }

    @Override
    public boolean canRemove(Cashier cashier) {
        return ( paymentRepository.findPaymentsByCashier_CashierIdAndRemoveDateTimeIsNull(cashier.getCashierId()).isEmpty() );
    }
}
