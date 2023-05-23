package org.j2os.repository.modern;

import org.j2os.domain.entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolesRepository extends JpaRepository<UserRoles , Long> {
   // List<Role> findUserRolesByUser_UserId
}
