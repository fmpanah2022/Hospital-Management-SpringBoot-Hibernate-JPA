package org.j2os.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "paymentType")
@Table(name = "PAYMENT_TYPE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentType implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column ( name = "PAYMENT_TYPE_ID", columnDefinition = "NUMBER")
    private Long paymentTypeId;
    @Column ( name = "PAYMENT_TYPE_DESC" , columnDefinition = "VARCHAR2(30)")
    private String paymentTypeDesc;
    @Column(name = "REMOVE_DATETIME")
    private Timestamp removeDateTime;
    @Version
    private int versionNumber;
}
