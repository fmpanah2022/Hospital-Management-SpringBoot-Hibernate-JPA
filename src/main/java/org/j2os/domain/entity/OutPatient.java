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

@Entity(name = "outInPatient")
@Table(name = "OUT_INPATIENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OutPatient implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column ( name = "OUT_INPATIENT_ID", columnDefinition = "NUMBER")
    private Long outPatientId;

    @Column(name = "OUT_INPATIENT_DATE")
    private Timestamp outPatientDate;

    @Column(name = "REMOVE_DATETIME")
    private Timestamp removeDateTime;
    @Version
    private int versionNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOCTOR_ID")
    private Doctor doctor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "INPATIENT_ID")
    private InPatient inPatient;

    @OneToMany (cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn (name = "OUT_INPATIENT_ID")
    private List<OutPatientMedicalInfo> outPatientMedicalInfos = new ArrayList<>();
}
