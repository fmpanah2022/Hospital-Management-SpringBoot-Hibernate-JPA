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

@Entity(name = "serviceForPatient")
@Table(name = "SERVICE_FOR_PATIENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceForPatient implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column ( name = "SERVICE_ID", columnDefinition = "NUMBER")
    private Long serviceId;
    @Column(name = "SERVICE_NAME", columnDefinition = "VARCHAR2(30)")
    private String serviceName;

    @Column(name = "SERVICE_UNIT_COST", columnDefinition = "INT")
    private int serviceUnitCost;
    @Column(name = "REMOVE_DATETIME")
    private Timestamp removeDateTime;
    @Version
    private int versionNumber;
    @OneToMany (cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn (name = "SERVICE_ID")
    private List<InPatientServiceInfo> inPatientServiceInfos = new ArrayList<>();

    @OneToMany (cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn (name = "SERVICE_ID")
    private List<InPatientBillDetail> inPatientBillDetails = new ArrayList<>();
}
