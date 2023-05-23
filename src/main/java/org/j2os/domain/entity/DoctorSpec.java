package org.j2os.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "doctorSpec")
@Table ( name = "DOCTOR_SPEC")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorSpec implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column ( name = "DOCTOR_SPEC_ID" , columnDefinition = "NUMBER")
    private Long doctorSpecId;
    @Version
    private int versionNumber;
    @Column ( name = "REMOVE_DATETIME" )
    private Timestamp removeDateTime;
    @Column ( name = "SPECIALIZATION_ID" ,columnDefinition = "NUMBER")
    private Long specializationId;
    @Column ( name = "DOCTOR_ID", columnDefinition = "NUMBER")
    private Long doctorId;
}
