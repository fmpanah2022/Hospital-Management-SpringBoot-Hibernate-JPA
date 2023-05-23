package org.j2os.repository.modern;

import org.j2os.domain.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface GenderRepository extends JpaRepository<Gender,Long> {
    Optional<Gender> findByGenderIdAndRemoveDateTimeIsNull(Long genderId);
    Gender findGenderByGenderNameAndRemoveDateTimeIsNull(String genderName);
    List<Gender> findGendersByRemoveDateTimeIsNull();
}
