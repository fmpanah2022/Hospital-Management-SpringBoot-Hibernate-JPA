package org.j2os.service.Implementation;

import org.j2os.domain.entity.PersonTell;
import org.j2os.repository.Classic.PersonTellClassicRepository;
import org.j2os.repository.modern.PersonTellRepository;
import org.j2os.service.modern.PersonTellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PersonTellServiceImp implements PersonTellService {
    @Autowired
    private PersonTellRepository personTellRepository;
    @Autowired
    private PersonTellClassicRepository personTellClassicRepository;
    //************************************************************
    @Override
    public Map<String, Object> convertPersonTellToMap(PersonTell tell) {
        Map<String, Object> data = new HashMap<>();
        data.put("personTellId", tell.getPersonTellId());
        data.put("personId", tell.getPerson().getPersonId());
        data.put("personFirstName", tell.getPerson().getFirstName());
        data.put("personSurname", tell.getPerson().getSurname());
        data.put("personNationalCode", tell.getPerson().getNationalCode());
        data.put("tell", tell.getTell());
        data.put("mobile", tell.getMobile());
        data.put("versionNumber", tell.getVersionNumber());
        return data;
    }
    @Override
    public List<Map<String, Object>> convertPersonTellListToMapList(List<PersonTell> tells) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (PersonTell tell : tells) {
            Map<String, Object> data = new HashMap<>();
            data.put("personTellId", tell.getPersonTellId());
            data.put("personId", tell.getPerson().getPersonId());
            data.put("personFirstName", tell.getPerson().getFirstName());
            data.put("personSurname", tell.getPerson().getSurname());
            data.put("personNationalCode", tell.getPerson().getNationalCode());
            data.put("tell", tell.getTell());
            data.put("mobile", tell.getMobile());
            data.put("versionNumber", tell.getVersionNumber());
            dataList.add(data);
        }
        return dataList;
    }
    @Override
    public PersonTell findPersonTellByPerson_NationalCodeAndRemoveDateTimeIsNull(int nationalCode) {
        return personTellRepository.findPersonTellByPerson_NationalCodeAndRemoveDateTimeIsNull(nationalCode);
    }

    @Override
    public PersonTell findPersonTellByPerson_PersonIdAndRemoveDateTimeIsNull(Long personId) {
        return personTellRepository.findPersonTellByPerson_PersonIdAndRemoveDateTimeIsNull(personId);
    }

    @Override
    public List<PersonTell> findPersonTellsByMobileAndRemoveDateTimeIsNull(String mobile) {
        return personTellRepository.findPersonTellsByMobileAndRemoveDateTimeIsNull(mobile);
    }

    @Override
    public List<PersonTell> findPersonTellsByTellAndRemoveDateTimeIsNull(String tell) {
        return personTellRepository.findPersonTellsByTellAndRemoveDateTimeIsNull(tell);
    }

    @Override
    public Optional<PersonTell> findPersonTellByPersonTellIdAndRemoveDateTimeIsNull(Long personTellId) {
        return personTellRepository.findPersonTellByPersonTellIdAndRemoveDateTimeIsNull(personTellId);
    }

    @Override
    @Transactional
    public PersonTell save(PersonTell personTell) {
        return personTellClassicRepository.save(personTell);
    }

    @Override
    @Transactional
    public PersonTell update(PersonTell personTell) {
        return personTellClassicRepository.update(personTell);
    }

    @Override
    @Transactional
    public void removeLogical(PersonTell personTell) {
        personTellClassicRepository.removeLogical(personTell);
    }
}
