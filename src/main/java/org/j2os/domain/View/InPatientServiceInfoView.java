package org.j2os.domain.View;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;
import java.io.Serializable;

@Entity( name = "inPatientServiceInfoView")
@Immutable
@Subselect(value = "SELECT INFO.INPATIENT_SERVICE_INFO_ID , INFO.INPATIENT_ID  , INFO.SERVICE_ID , INFO.VERSIONNUMBER," +
        "       S.SERVICE_UNIT_COST, S.COUNT , S.SERVICE_NAME " +
        " FROM ( SELECT * FROM inPatientServiceInfo where  removeDateTime is null  )INFO," +
        "      ( SELECT * FROM SERVICE_FOR_PATIENT where removeDateTime is null ) S " +
        " where INFO.SERVICE_ID = S.SERVICE_ID ")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedQueries({
        @NamedQuery(name = "inPatientServiceInfoV.findServiceInfoByInPatientIdWithDetail" , query = "select info from  inPatientServiceInfoView info" +
                " where info.inPatientId = :inPatientId"),
        @NamedQuery(name = "inPatientServiceInfoV.findServiceInfoByServiceId" , query = "select info from  inPatientServiceInfoView info" +
                " where info.serviceId = :serviceId"),
        @NamedQuery(name = "inPatientServiceInfoV.findServiceInfoByIdWithDetail" , query = "select info from inPatientServiceInfoView info " +
                " where info.inPatientServiceInfoId = :ID")
})
public class InPatientServiceInfoView implements Serializable {
    @Id
    @Column( name = "INPATIENT_SERVICE_INFO_ID")
    private Long inPatientServiceInfoId;
    @Column ( name = "INPATIENT_ID")
    private Long inPatientId;
    @Column ( name = "SERVICE_ID")
    private Long serviceId;
    @Column ( name = "VERSIONNUMBER")
    private int versionNumber;
    @Column ( name = "SERVICE_UNIT_COST")
    private int serviceUnitCost;
    @Column ( name = "COUNT")
    private int count;
    @Column ( name = "SERVICE_NAME")
    private String serviceName;
    @Transient
    private int totalCost;
//************************************************
    public int getTotalCost() {
        return this.count * this.serviceUnitCost;
    }
}
