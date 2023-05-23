package org.j2os.repository.modern;

import org.j2os.domain.entity.PersonTell;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PersonTellRepository extends JpaRepository<PersonTell , Long> {
    PersonTell findPersonTellByPerson_NationalCodeAndRemoveDateTimeIsNull(int nationalCode);
    PersonTell findPersonTellByPerson_PersonIdAndRemoveDateTimeIsNull(Long personId);
    List<PersonTell> findPersonTellsByMobileAndRemoveDateTimeIsNull(String mobile);
    List<PersonTell> findPersonTellsByTellAndRemoveDateTimeIsNull(String tell);
    Optional<PersonTell> findPersonTellByPersonTellIdAndRemoveDateTimeIsNull(Long personTellId);
}
