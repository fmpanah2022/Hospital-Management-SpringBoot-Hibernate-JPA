package org.j2os.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity( name = "inPatientMedicalInfo")
@Table( name = "INPATIENT_MEDICAL_INFO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InPatientMedicalInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column ( name = "INPATIENT_MEDICAL_INFO_ID" , columnDefinition = "NUMBER")
    private Long inPatientMedicalInfoId;
    @Column ( name = "COUNT" , columnDefinition = "INT")
    private int count;
    @Column( name = "REMOVE_DATETIME" )
    private Timestamp removeDateTime;
    @Version
    private int versionNumber;
    @Column(name = "INPATIENT_ID")
    private Long inPatientId;
    @Column(name = "MEDICINE_ID")
    private Long medicineId;
}
