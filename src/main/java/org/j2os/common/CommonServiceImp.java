package org.j2os.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CommonServiceImp implements CommonService {
    @Autowired
    private CommonRepository commonRepository;
    //**********************************************************
    @Override
    public String createPersonalCode(int nationalCode, String employeeRoleName) {
        return employeeRoleName + nationalCode;
      /*  BigDecimal id = commonRepository.getNextValPERSONALCODE_SEQ();
        String pc = String.valueOf(nationalCode);
        pc = pc.substring(0,4) + employeeRoleName + id;
        return pc;*/
    }
}
