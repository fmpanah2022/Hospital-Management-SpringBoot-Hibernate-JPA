package org.j2os.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity (name = "userRoles")
@Table (name = "USER_ROLES")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedQueries({
        @NamedQuery( name = "userRoles.findUserRolesByRoleName" , query ="select ur from userRoles ur join fetch ur.user u join fetch  ur.role r " +
                " where u.removeDateTime is null " +
                " and ur.removeDateTime is null  and r.removeDateTime is null and r.roleName = :RN " )
})
public class UserRoles implements Serializable {
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO)
    @Column ( name = "USER_ROLE_ID" , columnDefinition = "NUMBER")
    private long userRoleId;
    @Column ( name = "REMOVE_DATETIME" )
    private Timestamp removeDateTime;
    @Version
    private int versionNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private Users user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    private Role role;
}
