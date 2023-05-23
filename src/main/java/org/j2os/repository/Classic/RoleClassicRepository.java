package org.j2os.repository.Classic;

import org.j2os.domain.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
public class RoleClassicRepository {
    @Autowired
    private EntityManager entityManager;
    //-----------------------------------------
    public Role save(Role role)
    {
        entityManager.persist(role);
        return role;
    }

    public Role update(Role role)
    {
        return entityManager.merge(role);
    }

    public void removeLogical(Role role)
    {
        role.setRemoveDateTime(new java.sql.Timestamp(new Date().getTime()));
        entityManager.merge(role);
    }
    public List<Role> findRolesByUserId (Long userId) {
        Query query = entityManager.createNamedQuery("role.findRolesByUserId" ,Role.class);
        query.setParameter("UID" , userId);
        return query.getResultList();
    }

}
