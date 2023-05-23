package org.j2os.repository.Classic;

import org.j2os.common.exception.InvalidUsernameOrPassword;
import org.j2os.domain.entity.Role;
import org.j2os.domain.entity.UserRoles;
import org.j2os.domain.entity.Users;
import org.j2os.repository.modern.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserClassicRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private RoleRepository roleRepository;
    //********************************************
    public List<Role> fillUserRoles(List<String> roleNameList) {
        List<Role> roleList = new ArrayList<>();
        for (String roleName: roleNameList) {
            Role role = new Role().builder().roleName(roleName).build();
            roleList.add(role);
            entityManager.persist(role);
        }
        return roleList;
    }
    public void login (Users users ) throws Exception{
        Query query = entityManager.createNamedQuery("users.findOneByUserNameAndPassword" , Users.class);
        query.setParameter("U" , users.getUserName());
        query.setParameter("P" , users.getPassword());

        Users users1 = (Users) query.getResultList().get(0);
        if (users1.getUserId() != 0 ) {
            users.setUserId(users1.getUserId());
            users.setUserName(users1.getUserName());
        } else throw  new InvalidUsernameOrPassword();
    }
    public Role findRoleByName (String roleName) {
        Query query = entityManager.createNamedQuery("role.findRoleByName" , Role.class) ;
        query.setParameter("N" ,roleName);
        Role role = new Role();
        List<Role> roleList = query.getResultList();
        if (!roleList.isEmpty())  role = roleList.get(0);
        return role;
    }

    public List<Role> findUserRolesByUserId (Users user) {
        Query query = entityManager.createNamedQuery("role.findUserRolesByUserId", Role.class );
        query.setParameter("UID" ,user.getUserId());
        List<Role> roleList = query.getResultList();
        return roleList;
    }

    public List<UserRoles> findUserRolesByRoleName (String roleName) {
        Query query = entityManager.createNamedQuery("userRoles.findUserRolesByRoleName" , UserRoles.class) ;
        query.setParameter("RN" ,roleName);
        List<UserRoles> userRolesList =  query.getResultList();
        return userRolesList;
    }
    public List<Role> findAllRoles () {
        Query query = entityManager.createNamedQuery("role.findAllRoles" , Role.class) ;
        List<Role> list =  query.getResultList();
        return list;
    }
    public Users findUserByEmployeeId(long employeeId)  {
        Query query = entityManager.createNativeQuery("SELECT\n" +
                "    U.user_id,\n" +
                "    U.password,\n" +
                "    U.remove_datetime,\n" +
                "    U.role_name_login,\n" +
                "    U.user_name,\n" +
                "    U.versionnumber\n" +
                "FROM\n" +
                "   (SELECT user_id FROM EMPLOYEE  WHERE employee_id = :EID )  E ,users U\n" +
                "    WHERE  E.user_id = U.user_id  ", Users.class) ;
        query.setParameter("EID" ,employeeId);
        Users user = (Users) query.getResultList().get(0);
        return user;
    }

    public Users findByUserNameAndPassword (Users user ) {
        Query query = entityManager.createNativeQuery("SELECT\n" +
                "    U.user_id,\n" +
                "    U.password,\n" +
                "    U.remove_datetime,\n" +
                "    U.role_name_login,\n" +
                "    U.user_name,\n" +
                "    U.versionnumber\n" +
                "FROM\n" +
                "   (SELECT * FROM users  WHERE  user_name= :UN AND password = :P)  U" ,Users.class) ;
        query.setParameter("UN" ,user.getUserName());
        query.setParameter("P" ,user.getPassword());
        Users user1 = (Users) query.getResultList().get(0);
        return user1;
    }
}
