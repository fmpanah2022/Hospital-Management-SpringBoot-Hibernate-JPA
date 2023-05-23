package org.j2os.service.modern;

import org.j2os.domain.entity.Role;
import java.util.List;

public interface RoleService {
    List<Role> findRolesByUserId (Long userId);
    Role findRoleByRoleNameAndRemoveDateTimeIsNull(String roleName);
    List<Role> findRoleByRemoveDateTimeIsNull();
}
