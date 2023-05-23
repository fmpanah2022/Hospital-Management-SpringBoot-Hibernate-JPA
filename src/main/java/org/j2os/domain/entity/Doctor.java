package org.j2os.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity (name = "doctor")
@Table (name = "DOCTOR")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor implements Serializable{
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO)
    @Column ( name = "DOCTOR_ID", columnDefinition = "NUMBER")
    private Long doctorId;
    @Column ( name = "PERSONAL_CODE" , columnDefinition = "VARCHAR2(10)")
    private String personalCode;
    @Column(name = "REMOVE_DATETIME")
    private Timestamp removeDateTime;
    @Version
    private int versionNumber;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "PERSON_ID")
    private Person person;

    @OneToMany (cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn (name = "DOCTOR_ID")
    private List<DoctorSpec> doctorSpecs = new ArrayList<>();
}
