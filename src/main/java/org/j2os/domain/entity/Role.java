package org.j2os.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "role")
@Table(name = "ROLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedQueries({
        @NamedQuery( name = "role.findRoleByName" , query = " select r from role r where r.removeDateTime is null and r.roleName = :N"),
        @NamedQuery( name = "role.findAllRoles" , query = " select r from role r where r.removeDateTime is null"),
        @NamedQuery( name = "role.findUserRolesByUserId" , query = " select r from role r inner join userRoles ur  " +
                " ON ur.role.roleId = r.roleId inner join users u on  ur.user.userId = u.userId " +
                " where ur.removeDateTime is null  and r.removeDateTime is null and u.userId = :UID " )
})
public class Role implements Serializable {
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO)
    @Column ( name = "ROLE_ID" , columnDefinition = "NUMBER")
    private Long roleId;
    @Column ( name = "ROLE_NAME" , columnDefinition = "VARCHAR2(20)")
    private String roleName;
    @Column ( name = "REMOVE_DATETIME" )
    private Timestamp removeDateTime;
    @Version
    private int versionNumber;
}
