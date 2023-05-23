package org.j2os.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "personTell")
@Table( name = "PERSON_TELL")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonTell implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column( name = "PERSON_TELL_ID", columnDefinition = "NUMBER")
    private Long personTellId;
    @Column ( name = "TELL", columnDefinition = "VARCHAR2(20)")
    private String tell;
    @Column ( name = "Mobile", columnDefinition = "VARCHAR2(20)")
    private String mobile;
    @Column ( name = "REMOVE_DATETIME" )
    private Timestamp removeDateTime;
    @Version
    private int versionNumber;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "PERSON_ID")
    private Person person;
}
