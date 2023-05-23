package org.j2os.service.Implementation;

import org.j2os.domain.entity.Staff;
import org.j2os.repository.modern.InPatientRepository;
import org.j2os.repository.Classic.StaffClassicRepository;
import org.j2os.repository.modern.StaffRepository;
import org.j2os.service.modern.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class StaffServiceImp implements StaffService {
    @Autowired
    private StaffClassicRepository staffClassicRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private InPatientRepository inPatientRepository;
///*********************************************************************************************************
    @Override
    public boolean existsStaff(Staff staff) {
       return  (  staffRepository.findStaffByPerson_NationalCodeAndRemoveDateTimeIsNull(staff.getPerson().getNationalCode()) != null  ) ;
    }
    @Override
    public boolean conflictStaff(Staff staff) {
        Staff staff1 = staffRepository.findStaffByPerson_NationalCodeAndRemoveDateTimeIsNull(staff.getPerson().getNationalCode());
        if  (  staff1 != null && staff1.getStaffId() != null)
            return( staff1.getStaffId() != staff.getStaffId()) ;
        else return false;
    }

    @Override
    public boolean canRemove(Staff staff) {
        return ( inPatientRepository.findInPatientsByStaff_StaffIdAndRemoveDateTimeIsNull(staff.getStaffId()).isEmpty());
    }

    @Override
    public Map<String, Object> convertStaffToMap(Staff staff) {
        Map<String, Object> data = new HashMap<>();
        data.put("staffId",staff.getStaffId());
        data.put("firstName", staff.getPerson().getFirstName());
        data.put("surname", staff.getPerson().getSurname());
        data.put("nationalCode", staff.getPerson().getNationalCode());
        data.put("personId", staff.getPerson().getPersonId());
        data.put("personalCode", staff.getPersonalCode());
        data.put("genderId" , staff.getPerson().getGender().getGenderId());
        data.put("genderName", staff.getPerson().getGender().getGenderName());
        data.put("birthdate", staff.getPerson().getBirthdate());
        data.put("userId" , staff.getPerson().getUser().getUserId());
        data.put("userName", staff.getPerson().getUser().getUserName());
        data.put("password", staff.getPerson().getUser().getPassword());
        data.put("userVno", staff.getPerson().getUser().getVersionNumber());
        data.put("personVno", staff.getPerson().getVersionNumber());
        data.put("staffVno", staff.getVersionNumber());
        return data;
    }

    @Override
    public List<Map<String, Object>> convertStaffListToMapList(List<Staff> staffs) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (Staff staff:staffs) {
            Map<String, Object> data = new HashMap<>();
            data.put("staffId",staff.getStaffId());
            data.put("firstName", staff.getPerson().getFirstName());
            data.put("surname", staff.getPerson().getSurname());
            data.put("nationalCode", staff.getPerson().getNationalCode());
            data.put("personId", staff.getPerson().getPersonId());
            data.put("personalCode", staff.getPersonalCode());
            data.put("genderId" , staff.getPerson().getGender().getGenderId());
            data.put("genderName", staff.getPerson().getGender().getGenderName());
            data.put("birthdate", staff.getPerson().getBirthdate());
            data.put("userId" , staff.getPerson().getUser().getUserId());
            data.put("userName", staff.getPerson().getUser().getUserName());
            data.put("password", staff.getPerson().getUser().getPassword());
            data.put("userVno", staff.getPerson().getUser().getVersionNumber());
            data.put("personVno", staff.getPerson().getVersionNumber());
            data.put("staffVno", staff.getVersionNumber());
            dataList.add(data);
        }
        return dataList;
    }
    @Override
    public Staff findStaffByPersonalCodeAndRemoveDateTimeIsNull(String personalCode) {
        return staffRepository.findStaffByPersonalCodeAndRemoveDateTimeIsNull(personalCode);
    }

    @Override
    public Optional<Staff> findByStaffIdAndRemoveDateTimeIsNull(Long staffId) {
        return staffRepository.findByStaffIdAndRemoveDateTimeIsNull(staffId);
    }
    @Override
    public List<Staff> findStaffByRemoveDateTimeIsNull() {
        return staffRepository.findStaffByRemoveDateTimeIsNull();
    }

    @Override
    public Staff findStaffByPerson_FirstNameAndPerson_SurnameAndPerson_NationalCodeAndRemoveDateTimeIsNull(String firstName, String surname, int nationalCode) {
        return staffRepository.findStaffByPerson_FirstNameAndPerson_SurnameAndPerson_NationalCodeAndRemoveDateTimeIsNull(firstName, surname, nationalCode);
    }

    @Override
    public Staff findStaffByPerson_NationalCodeAndRemoveDateTimeIsNull(int nationalCode) {
        return staffRepository.findStaffByPerson_NationalCodeAndRemoveDateTimeIsNull(nationalCode);
    }
    @Override
    @Transactional
    public Staff save(Staff staff) {
        return staffClassicRepository.save(staff);
    }

    @Override
    @Transactional
    public Staff update(Staff staff) {
        return staffClassicRepository.update(staff);
    }

    @Override
    @Transactional
    public void removeLogical(Staff staff) {
        staffClassicRepository.removeLogical(staff);
    }

    @Override
    public Optional<Staff> findById(Long id) {
        return findById(id);
    }
}
