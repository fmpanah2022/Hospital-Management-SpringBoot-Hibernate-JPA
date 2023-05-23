package org.j2os.service.Implementation;

import org.j2os.domain.entity.Role;
import org.j2os.repository.Classic.RoleClassicRepository;
import org.j2os.repository.modern.RoleRepository;
import org.j2os.service.modern.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleClassicRepository roleClassicRepository;
//*************************************************************************
    @Override
    public List<Role> findRolesByUserId(Long userId) {
        return roleClassicRepository.findRolesByUserId(userId);
    }

    @Override
    public Role findRoleByRoleNameAndRemoveDateTimeIsNull(String roleName) {
        return roleRepository.findRoleByRoleNameAndRemoveDateTimeIsNull( roleName);
    }

    @Override
    public List<Role> findRoleByRemoveDateTimeIsNull() {
        return roleRepository.findRoleByRemoveDateTimeIsNull();
    }
}
