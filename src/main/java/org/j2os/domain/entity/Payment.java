package org.j2os.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "payment")
@Table(name = "PAYMENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column ( name = "PAYMENT_ID", columnDefinition = "NUMBER")
    private Long paymentId;
    @Column ( name = "INPATIENT_BILL_ID", columnDefinition = "NUMBER")
    private Long inPatientBillId;
    @Column(name = "PAYMENT_DATE")
    private Timestamp paymentDate;
    @Column ( name = "PAYMENT_AMOUNT" , columnDefinition = "NUMBER")
    private long paymentAmount;
    @Column(name = "PAYER", columnDefinition = "VARCHAR2(30)")
    private String payer;
    @Column(name = "REMOVE_DATETIME")
    private Timestamp removeDateTime;
    @Version
    private int versionNumber;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASHIER_ID")
    private Cashier cashier;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAYMENT_TYPE")
    private PaymentType paymentType;
}
