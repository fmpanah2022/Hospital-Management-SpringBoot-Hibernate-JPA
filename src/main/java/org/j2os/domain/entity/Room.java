package org.j2os.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity( name = "room")
@Table( name = "ROOM")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column ( name = "ROOM_ID" , columnDefinition = "NUMBER")
    private Long roomId;
    @Column ( name = "ROOM_NUMBER" , columnDefinition = "INT")
    private int roomNumber;
    @Column ( name = "ROOM_COST" , columnDefinition = "NUMBER")
    private long roomCost;
    @Column ( name = "AVAILABLE" , columnDefinition = "CHAR" )
    private char available;
    @Column ( name = "REMOVE_DATETIME" )
    private Timestamp removeDateTime;
    @Version
    private int versionNumber;
}
