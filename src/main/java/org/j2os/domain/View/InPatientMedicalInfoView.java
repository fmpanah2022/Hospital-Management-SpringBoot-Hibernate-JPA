package org.j2os.domain.View;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;
import java.io.Serializable;

@Entity( name = "inPatientMedicalInfoView")
@Immutable
@Subselect(value = "SELECT INFO.INPATIENT_MEDICAL_INFO_ID , INFO.INPATIENT_ID  , INFO.MEDICINE_ID , INFO.VERSIONNUMBER," +
                   "       M.MEDICINE_CODE, M.MEDICINE_NAME, M.MEDICINE_COST , INFO.COUNT" +
                   " FROM ( SELECT * FROM inPatientMedicalInfo where  removeDateTime is null  )INFO," +
                   "      ( SELECT * FROM MEDICINE where removeDateTime is null ) M " +
                   " where INFO.medicineId = M.medicineId ")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedQueries({
        @NamedQuery(name = "inPatientMedicalInfoV.findInPatientMedicalInfoByInPatientIdWithDetail" , query = "select info from inPatientMedicalInfoView info " +
                " where info.inPatientId = :inPatientId"),
        @NamedQuery(name = "inPatientMedicalInfoV.findInPatientMedicalInfoByIdWithDetail" , query = "select info from inPatientMedicalInfoView info " +
                " where info.inPatientMedicalInfoId = :ID")
})
public class InPatientMedicalInfoView implements Serializable {
    @Id
    @Column( name = "INPATIENT_MEDICAL_INFO_ID")
    private Long inPatientMedicalInfoId;
    @Column ( name = "INPATIENT_ID")
    private Long inPatientId;
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
