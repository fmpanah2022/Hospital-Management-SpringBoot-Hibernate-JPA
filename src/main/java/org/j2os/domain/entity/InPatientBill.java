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

@Entity(name = "inPatientBill")
@Table(name = "INPATIENT_BILL")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InPatientBill implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column ( name = "INPATIENT_BILL_ID", columnDefinition = "NUMBER")
    private Long inPatientBillId;

    @Column(name = "INPATIENT_BILL_DATE")
    private Timestamp inPatientBillDate;

    @Column(name = "REMOVE_DATETIME")
    private Timestamp removeDateTime;

    @Column ( name = "TOTAL_AMOUNT" , columnDefinition = "INT")
    private int totalAmount;
    @Version
    private int versionNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STAFF_ID")
    private Staff staff;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "INPATIENT_ID")
    private InPatient inPatient;

    @OneToMany (cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn (name = "INPATIENT_BILL_ID")
    private List<Payment> payments = new ArrayList<>();

    @OneToMany (cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn (name = "INPATIENT_BILL_ID")
    private List<InPatientBillDetail> inPatientBillDetails = new ArrayList<>();
}
