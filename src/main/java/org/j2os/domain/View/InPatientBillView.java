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

@Entity( name = "inPatientBillView")
@Immutable
@Subselect(value = "SELECT BILL.INPATIENT_BILL_ID ,BILL.INPATIENT_BILL_DATE ,BILL.TOTAL_AMOUNT ,BILL.STAFF_ID," +
        " BILL.INPATIENT_ID ,BILL.VersionNumber BILL_VNO, INPA.INPATIENT_DATE ," +
        " P.FIRST_NAME PA_FIRST_NAME , P.SURNAME PA_SURNAME , P.NATIONAL_CODE PA_NATIONAL_CODE ," +
        " StaffP.FIRST_NAME ST_FIRST_NAME , StaffP.SURNAME ST_SURNAME , S.PERSONAL_CODE ST_PERSONAL_CODE "+
        "FROM " +
        "    (Select * from INPATIENT_BILL Where remove_datetime is null )BILL ," +
        "    (Select * from INPATIENT Where remove_datetime is null ) INPA," +
        "    (Select * from PATIENT Where remove_datetime is null ) PA, "+
        "    (Select * from PERSON Where remove_datetime is null ) P, "+
        "    (Select * from PERSON Where remove_datetime is null ) StaffP, "+
        "    (Select * from STAFF Where remove_datetime is null ) S "+
        "    WHERE BILL.INPATIENT_ID = INPA.INPATIENT_ID and INPA.PATIENT_ID = PA.PATIENT_ID "+
        "      and PA.PERSON_ID = P.PERSON_ID and BILL.STAFF_ID = S.STAFF_ID and S.PERSON_ID = StaffP.PERSON_ID "
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedQueries({
   @NamedQuery(name = "inPatientBillV.findInPatientBills" , query = "select billV from inPatientBillView billV "),
   @NamedQuery(name = "inPatientBillV.findByInPatientBillId" , query = "select billV from inPatientBillView billV where billV.inPatientBillId = :inPatientBillId"),
   @NamedQuery(name = "inPatientBillV.findByInPatientBillDate" , query = "select billV from inPatientBillView billV where billV.inPatientBillDate = :DATE "),
   @NamedQuery(name = "inPatientBillV.findByStaffId" , query = "select billV from inPatientBillView billV where billV.staffId = :staffId "),
   @NamedQuery(name = "inPatientBillV.findByInPatientId" , query = "select billV from inPatientBillView billV where billV.inPatientId = :inPatientId "),
   @NamedQuery(name = "inPatientBillV.findByTotalAmountIsBetween" , query = "select billV from inPatientBillView billV " +
           " where billV.totalAmount >= :fromAmount and billV.totalAmount <= :toAmount ")   ,
   @NamedQuery(name = "inPatientBillV.findByInPatientBillDateIsBetween" , query = "select billV from inPatientBillView billV " +
                " where billV.inPatientBillDate between :fromDate and :endDate")
})
public class InPatientBillView implements Serializable {
    @Id
    @Column( name = "INPATIENT_BILL_ID")
    private Long inPatientBillId;
    @Column ( name = "INPATIENT_BILL_DATE")
    private Timestamp inPatientBillDate;
    @Column ( name = "TOTAL_AMOUNT")
    private int totalAmount;
    @Column ( name = "STAFF_ID")
    private Long staffId;
    @Column ( name = "INPATIENT_ID")
    private Long inPatientId;
    @Column ( name = "PATIENT_ID")
    private Long patientId;
    @Column ( name = "BILL_VNO")
    private int billVno;
    @Column ( name = "INPATIENT_DATE")
    private Timestamp inPatientDate;
    @Column ( name = "PA_FIRST_NAME")
    private String patientFirstName;
    @Column ( name = "PA_SURNAME")
    private String patientSurname;
    @Column ( name = "PA_NATIONAL_CODE")
    private String patientNationalCode;
    @Column ( name = "ST_FIRST_NAME")
    private String staffFirstName;
    @Column ( name = "ST_SURNAME")
    private String StaffSurname;
    @Column ( name = "ST_PERSONAL_CODE")
    private String staffPersonalCode;

    @Transient
    private String patientName;
    @Transient
    private String staffName;

    public String getPatientName() {
        return this.patientFirstName + " " + this.patientSurname;
    }

    public String getStaffName() {
        return this.staffFirstName + " " + this.getStaffSurname();
    }
}
