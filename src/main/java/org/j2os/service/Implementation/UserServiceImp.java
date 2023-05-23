package org.j2os.service.Implementation;

import org.j2os.domain.entity.Role;
import org.j2os.domain.entity.UserRoles;
import org.j2os.domain.entity.Users;
import org.j2os.repository.Classic.UserClassicRepository;
import org.j2os.service.modern.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserClassicRepository userClassicRepository;
//*********************************************************
    @Override
    @Transactional
    public List<Role> fillUserRoles(List<String> roleNameList) {
        return userClassicRepository.fillUserRoles(roleNameList);
    }

    @Override
    public void login(Users users) throws Exception{
        userClassicRepository.login(users);
    }

    @Override
    public Role findRoleByName(String roleName) {
        return userClassicRepository.findRoleByName(roleName);
    }

    @Override
    public List<Role> findUserRolesByUserId(Users user) {
        return userClassicRepository.findUserRolesByUserId(user);
    }

    @Override
    public List<UserRoles> findUserRolesByRoleName(String roleName) {
        return userClassicRepository.findUserRolesByRoleName(roleName);
    }

    @Override
    public List<Role> findAllRoles() {
        return userClassicRepository.findAllRoles();
    }

    @Override
    public Users findByUserNameAndPassword(Users user) {
        return userClassicRepository.findByUserNameAndPassword(user);
    }
}
