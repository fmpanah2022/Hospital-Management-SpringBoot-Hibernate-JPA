package org.j2os.service.modern;

import org.j2os.domain.entity.Person;
import java.util.List;

public interface PersonService {
    List<Person> findPersonByGender_GenderIdAndRemoveDateTimeIsNull(Long genderId);
}
