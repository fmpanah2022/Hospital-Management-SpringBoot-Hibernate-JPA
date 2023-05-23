package org.j2os.service.modern;

import org.j2os.domain.entity.Role;
import org.j2os.domain.entity.UserRoles;
import org.j2os.domain.entity.Users;

import java.util.List;

public interface UserService {
    List<Role> fillUserRoles(List<String> roleNameList);
    void login (Users users ) throws Exception;
    Role findRoleByName (String roleName);
    List<Role> findUserRolesByUserId (Users user);
    List<UserRoles> findUserRolesByRoleName (String roleName);
    List<Role> findAllRoles ();
    Users findByUserNameAndPassword (Users user);
}
