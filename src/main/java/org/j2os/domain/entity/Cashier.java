package org.j2os.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "cashier")
@Table(name = "CASHIER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cashier implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column ( name = "CASHIER_ID", columnDefinition = "NUMBER")
    private Long cashierId;
    @Column ( name = "PERSONAL_CODE" , columnDefinition = "VARCHAR2(10)")
    private String personalCode;
    @Column(name = "REMOVE_DATETIME")
    private Timestamp removeDateTime;
    @Version
    private int versionNumber;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "PERSON_ID")
    private Person person;
}
