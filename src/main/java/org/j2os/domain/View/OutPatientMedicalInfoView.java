package org.j2os.domain.View;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;
import java.io.Serializable;

@Entity( name = "outPatientMedicalInfoView")
@Immutable
@Subselect(value = "SELECT INFO.OUTPATIENT_MEDICAL_INFO_ID , INFO.OUTPATIENT_ID  , INFO.MEDICINE_ID , INFO.VERSIONNUMBER," +
        "       M.MEDICINE_CODE, M.MEDICINE_NAME, M.MEDICINE_COST , INFO.COUNT" +
        " FROM ( SELECT * FROM outPatientMedicalInfo where  removeDateTime is null  )INFO," +
        "      ( SELECT * FROM MEDICINE where removeDateTime is null ) M " +
        " where INFO.medicineId = M.medicineId ")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedQueries({
        @NamedQuery(name = "outPatientMedicalInfoV.findOutPatientMedicalInfoByOutPatientIdWithDetail" , query = "select info from outPatientMedicalInfoView info " +
                " where info.outPatientId = :outPatientId"),
        @NamedQuery(name = "outPatientMedicalInfoV.findOutPatientMedicalInfoByIdWithDetail" , query = "select info from outPatientMedicalInfoView info " +
                " where info.outPatientMedicalInfoId = :ID")
})
public class OutPatientMedicalInfoView implements Serializable {
    @Id
    @Column( name = "OutPATIENT_MEDICAL_INFO_ID")
    private Long outPatientMedicalInfoId;
    @Column ( name = "OUTPATIENT_ID")
    private Long outPatientId;
    @Column ( name = "MEDICINE_ID")
    private Long medicineId;
    @Column ( name = "VERSIONNUMBER")
    private int versionNumber;
    @Column ( name = "MEDICINE_CODE")
    private String medicineCode;
    @Column ( name = "MEDICINE_NAME")
    private String medicineName;
    @Column ( name = "MEDICINE_COST")
    private int medicineCost;
    @Column ( name = "COUNT")
    private int count;
}
