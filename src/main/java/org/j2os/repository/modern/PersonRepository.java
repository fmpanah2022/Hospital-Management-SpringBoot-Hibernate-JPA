package org.j2os.repository.modern;

import org.j2os.domain.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findPersonByGender_GenderIdAndRemoveDateTimeIsNull(Long genderId);
}
