package org.j2os.controller;

import org.j2os.domain.entity.Role;
import org.j2os.service.modern.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private UserService userService;
    //*******************************************************
    @GetMapping("/fillRoles")
    public ResponseEntity<List<Role>> fillRoles(@RequestParam("roleNameList") List<String> roleNameList) {
        try {
            List<Role> list = userService.fillUserRoles(roleNameList);
            if ( list.isEmpty() ) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
