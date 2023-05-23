package org.j2os.service.Implementation;

import org.j2os.domain.entity.Person;
import org.j2os.repository.modern.PersonRepository;
import org.j2os.service.modern.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonServiceImp implements PersonService {
    @Autowired
    private PersonRepository personRepository;
    //****************************************************************
    @Override
    public List<Person> findPersonByGender_GenderIdAndRemoveDateTimeIsNull(Long genderId) {
        return personRepository.findPersonByGender_GenderIdAndRemoveDateTimeIsNull(genderId) ;
    }
}
