package org.j2os.repository.modern;

import org.j2os.domain.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByRoomIdAndRemoveDateTimeIsNull(Long roomId);
    List<Room> findRoomByRemoveDateTimeIsNull();
    Room findRoomByRoomNumberAndRemoveDateTimeIsNull(int roomNumber);
    List<Room> findRoomsByAvailableAndRemoveDateTimeIsNull(char available);
}
