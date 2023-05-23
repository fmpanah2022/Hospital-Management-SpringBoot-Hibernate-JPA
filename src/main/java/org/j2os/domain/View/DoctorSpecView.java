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
import java.io.Serializable;
import java.sql.Date;
@Entity( name = "doctorSpecView")
@Immutable
@Subselect(value = "Select " +
        "        DS.DOCTOR_SPEC_ID , D.DOCTOR_ID , D.PERSONAL_CODE, D.PERSON_ID, P.SURNAME , P.FIRST_NAME , P.BIRTHDATE," +
        "        P.NATIONAL_CODE , DS.SPECIALIZATION_ID , S.SPECIALIZATION ,  DS.VERSIONNUMBER DOCTOR_SPEC_VNO  " +
        "        FROM ( Select * From DOCTOR_SPEC Where REMOVE_DATETIME IS NULL ) DS , " +
        "             ( Select * From DOCTOR Where REMOVE_DATETIME IS NULL )  D ," +
        "             ( Select * From SPECIALIZATION Where REMOVE_DATETIME IS NULL )  S," +
        "             ( Select * From PERSON Where REMOVE_DATETIME IS NULL )  P ,   " +
        "        WHERE D.PERSON_ID = P.PERSON_ID AND D.DOCTOR_ID = DS.DOCTOR_ID AND S.SPECIALIZATION_ID = DS.SPECIALIZATION_ID ")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorSpecView implements Serializable {
    @Id
    @Column( name = "DOCTOR_SPEC_ID")
    private Long doctorSpecId;
    @Column( name = "DOCTOR_ID")
    private Long doctorId;
    @Column ( name = "PERSONAL_CODE")
    private String personalCode;
    @Column ( name = "PERSON_ID")
    private Long personId;
    @Column ( name = "SURNAME")
    private String surname;
    @Column ( name = "FIRST_NAME")
    private String firstName;
    @Column ( name = "BIRTHDATE")
    private Date birthdate;
    @Column ( name = "NATIONAL_CODE")
    private int nationalCode;
    @Column ( name = "SPECIALIZATION_ID")
    private Long specializationId;
    @Column ( name = "SPECIALIZATION")
    private String specialization;
    @Column ( name = "DOCTOR_SPEC_VNO")
    private int doctorSpecVno;
}
