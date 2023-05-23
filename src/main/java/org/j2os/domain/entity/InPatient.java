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

@Entity( name = "inPatient")
@Table ( name = "INPATIENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedQueries({
        @NamedQuery(name = "inPatient.findAllInPatients" , query = "select  inv from InPatientVew inv")
})
public class InPatient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column ( name = "INPATIENT_ID" , columnDefinition = "NUMBER")
    private Long inPatientId;
    @Column ( name = "INPATIENT_DATE" , columnDefinition = "TIMESTAMP")
    private Timestamp inPatientDate;
    @Column ( name = "REMOVE_DATETIME" )
    private Timestamp removeDateTime;
    @Version
    private int versionNumber;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STAFF_ID")
    private Staff staff;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOCTOR_ID")
    private Doctor doctor;

    @OneToMany (cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn (name = "INPATIENT_ID")
    private List<InPatientMedicalInfo> inPatientMedicalInfos = new ArrayList<>();

    @OneToMany (cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn (name = "INPATIENT_ID")
    private List<InPatientServiceInfo> inPatientServiceInfos = new ArrayList<>();
}
