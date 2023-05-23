package org.j2os.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity (name = "users") //JPQL
@Table (name = "USERS") //table Name in oracle
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedQueries({
        @NamedQuery( name = "users.findAll" , query = "select u from users u where u.removeDateTime is null ") ,
        @NamedQuery( name = "users.findOneByUserNameAndPassword" , query = "select u from users u where lower(u.userName)=lower(:U) and u.password= :P  " +
                " AND u.removeDateTime is null"),
        @NamedQuery( name = "users.findOneByUserName" , query = "select u from users u  where lower(u.userName)= lower(:U)  " +
                " AND u.removeDateTime is null")
})
public class Users implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column (name ="USER_ID" ,columnDefinition = "NUMBER")
    private long userId;
    @Column (name = "USER_NAME" ,columnDefinition = "VARCHAR2(20)" )
    private String userName;
    @Column (name = "PASSWORD" , columnDefinition = "VARCHAR2(20)")
    private String password;
    @Column ( name = "REMOVE_DATETIME" )
    private Timestamp removeDateTime;
    @Version
    private  int versionNumber;
    @Column ( name = "ROLE_NAME_LOGIN" )
    private String roleNameLogin;
}
