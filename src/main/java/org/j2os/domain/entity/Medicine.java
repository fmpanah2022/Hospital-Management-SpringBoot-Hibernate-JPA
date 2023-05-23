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

@Entity( name = "medicine")
@Table( name = "MEDICINE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medicine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column ( name = "MEDICINE_ID" , columnDefinition = "NUMBER")
    private Long medicineId;
    @Column ( name = "MEDICINE_CODE" , columnDefinition = "VARCHAR2(30)")
    private String medicineCode;

    @Column ( name = "MEDICINE_NAME" , columnDefinition = "VARCHAR2(50)")
    private String medicineName;

    @Column ( name = "MEDICINE_COST" , columnDefinition = "INT")
    private int medicineCost;
    @Column( name = "REMOVE_DATETIME" )
    private Timestamp removeDateTime;
    @Version
    private int versionNumber;

    @OneToMany (cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn (name = "MEDICINE_ID")
    private List<InPatientMedicalInfo> inPatientMedicalInfos = new ArrayList<>();

    @OneToMany (cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn (name = "MEDICINE_ID")
    private List<OutPatientMedicalInfo> outPatientMedicalInfos = new ArrayList<>();
}
