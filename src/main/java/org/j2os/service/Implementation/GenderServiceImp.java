package org.j2os.service.Implementation;

import org.j2os.domain.entity.Gender;
import org.j2os.repository.Classic.GenderClassicRepository;
import org.j2os.repository.modern.GenderRepository;
import org.j2os.repository.modern.PersonRepository;
import org.j2os.service.modern.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class GenderServiceImp implements GenderService {
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private GenderClassicRepository genderClassicRepository;
    @Autowired
    private PersonRepository personRepository;
    //***************************************************************
    @Override
    public Map<String, Object> convertGenderToMap(Gender gender) {
        Map<String, Object> data = new HashMap<>();
        data.put("genderId",gender.getGenderId());
        data.put("genderName", gender.getGenderName());
        data.put("versionNumber", gender.getVersionNumber());
        return data;
    }
    @Override
    public List<Map<String, Object>> convertGenderListToMapList(List<Gender> genders) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (Gender gender : genders) {
            Map<String, Object> data = new HashMap<>();
            data.put("genderId", gender.getGenderId());
            data.put("genderName", gender.getGenderName());
            data.put("versionNumber", gender.getVersionNumber());
            dataList.add(data);
        }
        return dataList;
    }
    @Override
    public Optional<Gender> findByGenderIdAndRemoveDateTimeIsNull(Long genderId) {
        return genderRepository.findByGenderIdAndRemoveDateTimeIsNull(genderId);
    }
    @Override
    public boolean existsGender(Gender gender)  {
        return  (  genderRepository.findGenderByGenderNameAndRemoveDateTimeIsNull(gender.getGenderName()) != null  ) ;
    }
    @Override
    public boolean conflictGender(Gender gender)  {
        Gender gender1 = genderRepository.findGenderByGenderNameAndRemoveDateTimeIsNull(gender.getGenderName());
        if  (  gender1 != null && gender1.getGenderId() != null)
            return( gender1.getGenderId() != gender.getGenderId()) ;
        else return false;
    }

    @Override
    public boolean canRemove(Gender gender)  {
        return ( personRepository.findPersonByGender_GenderIdAndRemoveDateTimeIsNull(gender.getGenderId()).isEmpty());
    }

    @Override
    @Transactional
    public Gender save(Gender gender) throws Exception {
        return  genderClassicRepository.save(gender);
    }

    @Override
    @Transactional
    public Gender update(Gender gender)  {
        return genderClassicRepository.update(gender);
    }

    @Override
    @Transactional
    public void removeLogical(Gender gender) throws Exception {
       genderClassicRepository.removeLogical(gender);
    }

    @Override
    public Gender findGenderByGenderNameAndRemoveDateTimeIsNull(String genderName) {
        return genderRepository.findGenderByGenderNameAndRemoveDateTimeIsNull(genderName);
    }

    @Override
    public Optional<Gender> findById(Long id) {
        return genderRepository.findById(id);
    }

    @Override
    public List<Gender> findGendersByRemoveDateTimeIsNull() {
        return genderRepository.findGendersByRemoveDateTimeIsNull();
    }
}
