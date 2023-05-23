package org.j2os.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity (name = "person")
@Table ( name = "PERSON")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person implements Serializable {
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO)
    @Column ( name = "PERSON_ID", columnDefinition = "NUMBER")
    private Long personId;
    @Column ( name = "FIRST_NAME", columnDefinition = "VARCHAR2(20)")
    private String firstName;
    @Column ( name = "SURNAME", columnDefinition = "VARCHAR2(20)")
    private String surname;

    @Column ( name = "NATIONAL_CODE", columnDefinition = "INT")
    @NotNull(message = "National Code cannot be null")
    private int nationalCode;

    @Column ( name = "BIRTHDATE", columnDefinition = "DATE")
    private Date birthdate;
    @Transient
    private int age;
    @Column ( name = "REMOVE_DATETIME" )
    private Timestamp removeDateTime;
    @Version
    private int versionNumber;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GENDER_ID")
    private Gender gender;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "USER_ID")
    private Users user;
}
