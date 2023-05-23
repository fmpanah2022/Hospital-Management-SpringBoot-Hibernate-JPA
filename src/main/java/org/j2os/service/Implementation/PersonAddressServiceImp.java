package org.j2os.service.Implementation;

import org.j2os.domain.entity.PersonAddress;
import org.j2os.repository.Classic.PersonAddressClassicRepository;
import org.j2os.repository.modern.PersonAddressRepository;
import org.j2os.service.modern.PersonAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PersonAddressServiceImp implements PersonAddressService {
    @Autowired
    private PersonAddressRepository personAddressRepository;
    @Autowired
    private PersonAddressClassicRepository personAddressClassicRepository;
    //-------------------------------------------------------------------
    @Override
    public Map<String, Object> convertPersonAddressToMap(PersonAddress address) {
        Map<String, Object> data = new HashMap<>();
        data.put("personAddressId", address.getPersonAddressId());
        data.put("personId", address.getPerson().getPersonId());
        data.put("personFirstName", address.getPerson().getFirstName());
        data.put("personSurname", address.getPerson().getSurname());
        data.put("personNationalCode", address.getPerson().getNationalCode());
        data.put("country", address.getCountry());
        data.put("city", address.getCity());
        data.put("state", address.getState());
        data.put("street", address.getStreet());
        data.put("postalCode", address.getPostalCode());
        data.put("versionNumber", address.getVersionNumber());
        return data;
    }
    @Override
    public List<Map<String, Object>> convertPersonAddressListToMapList(List<PersonAddress> addresses) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (PersonAddress address : addresses) {
            Map<String, Object> data = new HashMap<>();
            data.put("personAddressId", address.getPersonAddressId());
            data.put("personId", address.getPerson().getPersonId());
            data.put("personFirstName", address.getPerson().getFirstName());
            data.put("personSurname", address.getPerson().getSurname());
            data.put("personNationalCode", address.getPerson().getNationalCode());
            data.put("country", address.getCountry());
            data.put("city", address.getCity());
            data.put("state", address.getState());
            data.put("street", address.getStreet());
            data.put("postalCode", address.getPostalCode());
            data.put("versionNumber", address.getVersionNumber());
            dataList.add(data);
        }
        return dataList;
    }
    @Override
    public PersonAddress findPersonAddressByPerson_NationalCodeAndRemoveDateTimeIsNull(int nationalCode) {
        return personAddressRepository.findPersonAddressByPerson_NationalCodeAndRemoveDateTimeIsNull(nationalCode);
    }
    @Override
    public PersonAddress findPersonAddressByPerson_PersonIdAndRemoveDateTimeIsNull(Long personId) {
        return personAddressRepository.findPersonAddressByPerson_PersonIdAndRemoveDateTimeIsNull(personId);
    }

    @Override
    public List<PersonAddress> findPersonAddressesByCountryAndRemoveDateTimeIsNull(String country) {
        return personAddressRepository.findPersonAddressesByCountryAndRemoveDateTimeIsNull(country);
    }

    @Override
    public List<PersonAddress> findPersonAddressesByCityAndRemoveDateTimeIsNull(String city) {
        return personAddressRepository.findPersonAddressesByCityAndRemoveDateTimeIsNull(city);
    }

    @Override
    public List<PersonAddress> findPersonAddressesByStateAndRemoveDateTimeIsNull(String state) {
        return personAddressRepository.findPersonAddressesByStateAndRemoveDateTimeIsNull(state);
    }

    @Override
    public List<PersonAddress> findPersonAddressesByPostalCodeAndRemoveDateTimeIsNull(String postalCode) {
        return personAddressRepository.findPersonAddressesByPostalCodeAndRemoveDateTimeIsNull(postalCode);
    }

    @Override
    public Optional<PersonAddress> findPersonAddressByPersonAddressIdAndRemoveDateTimeIsNull(Long personAddressId) {
        return personAddressRepository.findPersonAddressByPersonAddressIdAndRemoveDateTimeIsNull(personAddressId);
    }

    @Override
    @Transactional
    public PersonAddress save(PersonAddress personAddress) {
        return personAddressClassicRepository.save(personAddress);
    }

    @Override
    @Transactional
    public PersonAddress update(PersonAddress personAddress) {
        return personAddressClassicRepository.update(personAddress);
    }

    @Override
    @Transactional
    public void removeLogical(PersonAddress personAddress) {
        personAddressClassicRepository.removeLogical(personAddress);
    }
}
