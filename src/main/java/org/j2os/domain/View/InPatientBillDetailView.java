package org.j2os.domain.View;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;
import java.io.Serializable;

@Entity( name = "inPatientBillDetailView")
@Immutable
@Subselect(value = "SELECT DETAIL.INPATIENT_BILL_DETAIL_ID DETAIL_ID,DETAIL.SERVICE_ID ,DETAIL.SERVICE_COUNT," +
        " DETAIL.SERVICE_UNIT_COST,DETAIL.versionNumber DETAIL_VNO, " +
        " DETAIL.INPATIENT_BILL_ID BILL_ID ,S.SERVICE_NAME "+
        "FROM " +
        "    (Select * from INPATIENT_BILL_DETAIL Where remove_datetime is null )DETAIL ," +
        "    (Select * from SERVICE_FOR_PATIENT Where remove_datetime is null ) S "+
        "    WHERE  DETAIL.SERVICE_ID = S.SERVICE_ID "
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedQueries({
        @NamedQuery(name = "billDetailV.findBillDetailByDetailId" , query = "select dv from inPatientBillDetailView dv where dv.detailId = :detailId"),
        @NamedQuery(name = "billDetailV.findBillDetailsByBillId" , query = "select dv from inPatientBillDetailView dv where dv.billId = :billId")
})
public class InPatientBillDetailView implements Serializable {
    @Id
    @Column( name = "DETAIL_ID")
    private Long detailId;
    @Column ( name = "SERVICE_ID")
    private Long serviceId;
    @Column ( name = "SERVICE_COUNT")
    private int serviceCount;
    @Column ( name = "SERVICE_UNIT_COST")
    private int serviceUnitCost;
    @Column ( name = "BILL_ID")
    private Long billId;
    @Column ( name = "SERVICE_NAME")
    private String serviceName;
    @Column ( name = "DETAIL_VNO")
    private int detailVno;

    @Transient
    private int totalCost;
    public int getTotalCost() {
        return this.serviceUnitCost * this.serviceCount;
    }
}
