package org.j2os.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity (name = "personAddress")
@Table ( name = "PERSON_ADDRESS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonAddress implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column( name = "PERSON_ADDRESS_ID", columnDefinition = "NUMBER")
    private Long personAddressId;
    @Column ( name = "COUNTRY", columnDefinition = "VARCHAR2(20)")
    private String country;
    @Column ( name = "CITY", columnDefinition = "VARCHAR2(20)")
    private String city;
    @Column ( name = "STATE", columnDefinition = "VARCHAR2(20)")
    private String state;
    @Column ( name = "STREET", columnDefinition = "VARCHAR2(20)")
    private String street;
    @Column ( name = "POSTAL_CODE", columnDefinition = "VARCHAR2(16)")
    private String postalCode;
    @Version
    private int versionNumber;
    @Column ( name = "REMOVE_DATETIME" )
    private Timestamp removeDateTime;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "PERSON_ID")
    private Person person;
}
