package org.j2os.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity( name = "InPatientServiceInfo")
@Table( name = "INPATIENT_SERVICE_INFO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InPatientServiceInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column ( name = "INPATIENT_SERVICE_INFO_ID" , columnDefinition = "NUMBER")
    private Long inPatientServiceInfoId;
    @Column ( name = "COUNT" , columnDefinition = "INT")
    private int count;
    @Column(name = "SERVICE_UNIT_COST", columnDefinition = "VARCHAR2(30)")
    private int serviceUnitCost;
    @Column( name = "REMOVE_DATETIME" )
    private Timestamp removeDateTime;
    @Version
    private int versionNumber;
    @Column(name = "INPATIENT_ID")
    private Long inPatientId;
    @Column(name = "SERVICE_ID")
    private Long serviceId;
}
