package org.j2os.service.modern;

import org.j2os.domain.entity.Gender;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GenderService {
    Map<String, Object> convertGenderToMap(Gender gender);
    List<Map<String, Object>> convertGenderListToMapList(List<Gender> genders);
    Optional<Gender> findByGenderIdAndRemoveDateTimeIsNull(Long genderId);
    boolean existsGender ( Gender gender) throws Exception;
    boolean conflictGender ( Gender gender) throws Exception ;
    boolean canRemove( Gender gender) throws Exception ;
    Gender save (Gender gender) throws Exception ;
    Gender update (Gender gender) throws Exception;
    void removeLogical (Gender gender) throws Exception ;
    Gender findGenderByGenderNameAndRemoveDateTimeIsNull(String genderName);
    Optional<Gender> findById(Long id);
    List<Gender> findGendersByRemoveDateTimeIsNull();
}
