package org.j2os.repository.modern;

import org.j2os.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByRoleNameAndRemoveDateTimeIsNull( String roleName);
    List<Role> findRoleByRemoveDateTimeIsNull();
}
