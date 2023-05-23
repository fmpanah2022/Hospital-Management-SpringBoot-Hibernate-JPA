package org.j2os.service.modern;

import org.j2os.domain.entity.PersonTell;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PersonTellService {
    Map<String, Object> convertPersonTellToMap(PersonTell tell);
    List<Map<String, Object>> convertPersonTellListToMapList(List<PersonTell> tells);
    PersonTell findPersonTellByPerson_NationalCodeAndRemoveDateTimeIsNull(int nationalCode);
    PersonTell findPersonTellByPerson_PersonIdAndRemoveDateTimeIsNull(Long personId);
    List<PersonTell> findPersonTellsByMobileAndRemoveDateTimeIsNull(String mobile);
    List<PersonTell> findPersonTellsByTellAndRemoveDateTimeIsNull(String tell);
    Optional<PersonTell> findPersonTellByPersonTellIdAndRemoveDateTimeIsNull(Long personTellId);
    PersonTell save (PersonTell personTell);
    PersonTell update (PersonTell personTell) ;
    void removeLogical (PersonTell personTell) ;
}
