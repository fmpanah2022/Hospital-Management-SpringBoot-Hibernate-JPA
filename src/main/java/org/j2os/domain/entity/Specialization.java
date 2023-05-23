package org.j2os.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity ( name = "specialization")
@Table ( name = "SPECIALIZATION")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Specialization implements Serializable {
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO)
    @Column ( name = "SPECIALIZATION_ID" ,columnDefinition = "NUMBER")
    private Long specializationId;
    @Column( name = "SPECIALIZATION" , columnDefinition = "VARCHAR2(50)")
    private String specialization;
    @Column ( name = "REMOVE_DATETIME" )
    private Timestamp removeDateTime;
    @Version
    private int versionNumber;

    @OneToMany (cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn (name = "SPECIALIZATION_ID")
    private List<DoctorSpec> doctorSpecs = new ArrayList<>();
}
