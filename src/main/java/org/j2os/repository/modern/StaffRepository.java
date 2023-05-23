package org.j2os.repository.modern;

import org.j2os.domain.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findByStaffIdAndRemoveDateTimeIsNull(Long staffId);
    Staff findStaffByPerson_FirstNameAndPerson_SurnameAndPerson_NationalCodeAndRemoveDateTimeIsNull(String firstName ,String surname , int nationalCode);
    Staff findStaffByPerson_NationalCodeAndRemoveDateTimeIsNull(int nationalCode);
    List<Staff> findStaffByRemoveDateTimeIsNull();
    Staff findStaffByPersonalCodeAndRemoveDateTimeIsNull(String personalCode);
}
