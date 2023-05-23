package org.j2os.service.modern;

import org.j2os.domain.entity.Staff;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StaffService {
    Map<String, Object> convertStaffToMap(Staff staff);
    List<Map<String, Object>> convertStaffListToMapList(List<Staff> staffs);
    Staff findStaffByPersonalCodeAndRemoveDateTimeIsNull(String personalCode);
    Optional<Staff> findByStaffIdAndRemoveDateTimeIsNull(Long staffId);
    List<Staff> findStaffByRemoveDateTimeIsNull();
    Staff findStaffByPerson_FirstNameAndPerson_SurnameAndPerson_NationalCodeAndRemoveDateTimeIsNull(String firstName ,String surname , int nationalCode);
    Staff findStaffByPerson_NationalCodeAndRemoveDateTimeIsNull(int nationalCode);
    Staff save (Staff staff) ;
    Staff update (Staff staff) ;
    void removeLogical(Staff staff);
    Optional<Staff> findById(Long id);
    boolean existsStaff ( Staff staff);
    boolean conflictStaff ( Staff staff) ;
    boolean canRemove( Staff staff)  ;
}
