package org.j2os.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity ( name = "gender")
@Table ( name = "GENDER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Gender implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GENDER_ID", columnDefinition = "NUMBER")
    private Long genderId;
    @Column(name = "GENDER_NAME", columnDefinition = "VARCHAR2(30)")
    private String genderName;
    @Column(name = "REMOVE_DATETIME")
    private Timestamp removeDateTime;
    @Version
    private int versionNumber;
}
