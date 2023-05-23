package org.j2os.domain.View;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.sql.Timestamp;

@Entity
@Immutable
@Subselect(value = "SELECT\n" +
        "    inpatient.inpatient_id,\n" +
        "    inpatient.inpatient_date,\n" +
        "    inpatient.doctor_id,person.first_name DOCTOR_FIRST_NAME,person.surname DOCTOR_SURNAME ,\n" +
        "    inpatient.patient_id,person1.first_name PATIENT_FIRST_NAME,person1.surname PATIENT_SURNAME ,\n" +
        "    inpatient.room_id,room.room_Number , room.room_Cost ,\n" +
        "    inpatient.staff_id,person2.first_name STAFF_FIRST_NAME,person2.surname STAFF_SURNAME \n" +
        "FROM\n" +
        "    (Select * from inpatient Where remove_datetime is null )inpatient\n" +
        "    INNER JOIN  patient \n" +
        "        ON inpatient.patient_id = patient.patient_id\n" +
        "    INNER JOIN (Select * from Person Where remove_datetime is null ) person1 \n" +
        "        ON patient.person_id = person1.person_id\n" +
        "    INNER JOIN (Select * from Room Where remove_datetime is null )room\n" +
        "       ON inpatient.room_id = room.room_id\n" +
        "    INNER JOIN staff\n" +
        " ON inpatient.staff_id = staff.staff_id\n" +
        "    INNER JOIN (Select * from Person Where remove_datetime is null )  person2 ON staff.person_id = person2.person_id\n" +
        "    INNER JOIN  doctor ON inpatient.doctor_id = doctor.doctor_id\n" +
        "    INNER JOIN (Select * from Person Where remove_datetime is null ) person ON doctor.person_id = person.person_id"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InPatientVew {
    @Id
    @Column( name = "INPATIENT_ID")
    private Long inPatientId;
    @Column ( name = "INPATIENT_DATE")
    private Timestamp inPatientDate;
    @Column ( name = "DOCTOR_ID")
    private String doctorId;
    @Column ( name = "DOCTOR_FIRST_NAME")
    private String doctorFirstName;
    @Column ( name = "DOCTOR_SURNAME")
    private String doctorSurname;
    @Column ( name = "PATIENT_ID")
    private String patientId;
    @Column ( name = "PATIENT_FIRST_NAME")
    private String patientFirstName;
    @Column ( name = "PATIENT_SURNAME")
    private String patientSurname;
    @Column ( name = "ROOM_ID")
    private String roomId;
    @Column ( name = "ROOM_NUMBER")
    private String roomNumber;
    @Column ( name = "ROOM_COST")
    private String roomCost;
    @Column ( name = "STAFF_ID")
    private String staffId;
    @Column ( name = "STAFF_FIRST_NAME")
    private String staffFirstName;
    @Column ( name = "STAFF_SURNAME")
    private String staffSurname;
    @Transient
    private String patientName;
    @Transient
    private String staffName;
    @Transient
    private String doctorName;

    public String getPatientName() {
        return this.patientSurname + " " + this.patientFirstName;
    }

    public String getStaffName() {
        return this.staffSurname + " " + this.staffFirstName;
    }

    public String getDoctorName() {
        return this.doctorSurname + " " + this.doctorFirstName;
    }
}
