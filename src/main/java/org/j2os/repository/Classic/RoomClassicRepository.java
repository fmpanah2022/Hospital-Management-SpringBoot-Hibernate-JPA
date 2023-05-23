package org.j2os.repository.Classic;

import org.j2os.domain.entity.Room;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Repository
public class RoomClassicRepository {
    @PersistenceContext
    private EntityManager entityManager;
    //------------------------------------------------------
    public Room save (Room room) {
        room.setAvailable('1');
        entityManager.persist(room);
        return room;
    }
    public Room update (Room room) {
        return entityManager.merge(room);
    }
    public void removeLogical(Room room)
    {
        room.setRemoveDateTime(new java.sql.Timestamp(new Date().getTime()));
        entityManager.merge(room);
    }
}
