package org.j2os.domain.View;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity( name = "outPatientView")
@Immutable
@Subselect(value = "SELECT OUT.OUT_INPATIENT_ID ,OUT.OUT_INPATIENT_DATE ,OUT.DOCTOR_ID ,OUT.INPATIENT_ID," +
        " OUT.VersionNumber OUT_VNO, INPA.INPATIENT_DATE ," +
        " PA.PATIENT_ID ,P.FIRST_NAME PA_FIRST_NAME , P.SURNAME PA_SURNAME , P.NATIONAL_CODE PA_NATIONAL_CODE ," +
        " DoctorP.FIRST_NAME D_FIRST_NAME , DoctorP.SURNAME D_SURNAME , D.PERSONAL_CODE D_PERSONAL_CODE "+
        "FROM " +
        "    (Select * from OUT_INPATIENT Where remove_datetime is null )OUT ," +
        "    (Select * from INPATIENT Where remove_datetime is null ) INPA," +
        "    (Select * from PATIENT Where remove_datetime is null ) PA, "+
        "    (Select * from PERSON Where remove_datetime is null ) P, "+
        "    (Select * from PERSON Where remove_datetime is null ) DoctorP, "+
        "    (Select * from DOCTOR Where remove_datetime is null ) D "+
        "    WHERE OUT.INPATIENT_ID = INPA.INPATIENT_ID and INPA.PATIENT_ID = PA.PATIENT_ID "+
        "      and PA.PERSON_ID = P.PERSON_ID and OUT.DOCTOR_ID = D.DOCTOR_ID and D.PERSON_ID = DoctorP.PERSON_ID "
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedQueries({
        @NamedQuery(name = "outPatientV.findOutPatients" , query = "select outV from outPatientView outV "),
        @NamedQuery(name = "outPatientV.findByOutPatientId" , query = "select outV from outPatientView outV where outV.outPatientId = :outPatientId"),
        @NamedQuery(name = "outPatientV.findByPatientId" , query = "select outV from outPatientView outV where outV.patientId = :patientId"),
        @NamedQuery(name = "outPatientV.findByDoctorId" , query = "select outV from outPatientView outV where outV.doctorId = :doctorId"),
        @NamedQuery(name = "outPatientV.findByInPatientId" , query = "select outV from outPatientView outV where outV.inPatientId = :inPatientId "),
        @NamedQuery(name = "outPatientV.findByPatient_NationalCode" , query = "select outV from outPatientView outV where outV.patientNationalCode = :patientNationalCode ")
})
public class OutPatientView implements Serializable {
    @Id
    @Column( name = "OUT_INPATIENT_ID")
    private Long outPatientId;
    @Column ( name = "OUT_INPATIENT_DATE")
    private Timestamp outPatientDate;
    @Column ( name = "DOCTOR_ID")
    private Long doctorId;
    @Column ( name = "INPATIENT_ID")
    private Long inPatientId;
    @Column ( name = "PATIENT_ID")
    private Long patientId;
    @Column ( name = "OUT_VNO")
    private int outVno;
    @Column ( name = "INPATIENT_DATE")
    private Timestamp inPatientDate;
    @Column ( name = "PA_FIRST_NAME")
    private String patientFirstName;
    @Column ( name = "PA_SURNAME")
    private String patientSurname;
    @Column ( name = "PA_NATIONAL_CODE")
    private String patientNationalCode;
    @Column ( name = "D_FIRST_NAME")
    private String doctorFirstName;
    @Column ( name = "D_SURNAME")
    private String doctorSurname;
    @Column ( name = "D_PERSONAL_CODE")
    private String doctorPersonalCode;

    @Transient
    private String patientName;
    @Transient
    private String doctorName;

    public String getPatientName() {
        return this.patientFirstName + " " + patientSurname;
    }

    public String getDoctorName() {
        return this.doctorFirstName + " " + doctorSurname;
    }
}
