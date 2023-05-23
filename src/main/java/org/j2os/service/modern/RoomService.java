package org.j2os.service.modern;

import org.j2os.domain.entity.Room;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RoomService {
    Map<String, Object> convertRoomToMap(Room room);
    List<Map<String, Object>> convertRoomListToMapList(List<Room> rooms);
    Optional<Room> findByRoomIdAndRemoveDateTimeIsNull(Long roomId);
    boolean existsRoom ( Room room);
    boolean conflictRoom ( Room room);
    boolean canRemove( Room room) ;
    public Room save (Room room) ;
    public Room update (Room room) ;
    public void removeLogical(Room room);
    List<Room> findRoomByRemoveDateTimeIsNull();
    Room findRoomByRoomNumberAndRemoveDateTimeIsNull(int roomNumber);
    List<Room> findRoomsByAvailableAndRemoveDateTimeIsNull(char available);
    Optional<Room> findById(Long id);
}
