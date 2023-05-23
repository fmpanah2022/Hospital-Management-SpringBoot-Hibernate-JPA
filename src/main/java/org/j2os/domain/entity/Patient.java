package org.j2os.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity (name = "patient")
@Table (name = "PATIENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient   implements Serializable {
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO)
    @Column ( name = "PATIENT_ID", columnDefinition = "NUMBER")
    private Long patientId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "PERSON_ID")
    private Person person;
    @Column(name = "REMOVE_DATETIME")
    private Timestamp removeDateTime;
    @Version
    private int versionNumber;
}
