package org.j2os.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "outInPatientMedicalInfo")
@Table(name = "OUT_INPATIENT_MEDICAL_INFO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OutPatientMedicalInfo implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column ( name = "OUT_INPATIENT_MEDICAL_INFO_ID", columnDefinition = "NUMBER")
    private Long outPatientMedicalInfoId;
    @Column ( name = "COUNT" , columnDefinition = "INT")
    private int count;
    @Column(name = "OUT_INPATIENT_ID")
    private Long outPatientId;
    @Column(name = "MEDICINE_ID")
    private Long medicineId;
    @Column(name = "REMOVE_DATETIME")
    private Timestamp removeDateTime;
    @Version
    private int versionNumber;
}
