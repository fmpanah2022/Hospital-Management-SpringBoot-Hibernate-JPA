package org.j2os.service.Logic;

import org.j2os.domain.entity.Staff;
import org.j2os.common.CommonService;
import org.j2os.service.modern.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class StaffLogic {
    @Autowired
    private StaffService staffService;
    @Autowired
    private CommonService commonService;
    //**********************************************************
    public ResponseEntity<Map<String, Object>> saveStaff (Staff staff) {
            if (staffService.existsStaff(staff)) {
                return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            staff.setPersonalCode(commonService.createPersonalCode(staff.getPerson().getNationalCode(),"Staff"));
            Staff savedStaff = staffService.save(staff);
            if (savedStaff.getStaffId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(staffService.convertStaffToMap(savedStaff), HttpStatus.OK);
    }
    public ResponseEntity<Map<String, Object>> updateStaff(Staff staff) {
            if (staffService.conflictStaff(staff)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Staff savedStaff = staffService.update(staff);
            return new ResponseEntity<>(staffService.convertStaffToMap(savedStaff), HttpStatus.OK);

    }
    public ResponseEntity<HttpStatus> removeLogicalStaff(Staff staff)  {
            if (!staffService.canRemove(staff)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            staffService.removeLogical(staff);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
