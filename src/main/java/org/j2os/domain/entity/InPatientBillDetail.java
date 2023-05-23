package org.j2os.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "inPatientBillDetail")
@Table(name = "INPATIENT_BILL_DETAIL")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InPatientBillDetail implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column ( name = "INPATIENT_BILL_DETAIL_ID", columnDefinition = "NUMBER")
    private Long inPatientBillDetailId;

    @Column ( name = "INPATIENT_BILL_ID", columnDefinition = "NUMBER")
    private Long inPatientBillId;

    @Column(name = "SERVICE_ID")
    private Long serviceId;
    @Column ( name = "SERVICE_COUNT" , columnDefinition = "INT")
    private int serviceCount;

    @Column(name = "SERVICE_UNIT_COST", columnDefinition = "VARCHAR2(30)")
    private int serviceUnitCost;
    @Column(name = "REMOVE_DATETIME")
    private Timestamp removeDateTime;
    @Version
    private int versionNumber;
}
