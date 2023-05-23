package org.j2os.service.Implementation;

import org.j2os.domain.entity.Room;
import org.j2os.repository.modern.InPatientRepository;
import org.j2os.repository.Classic.RoomClassicRepository;
import org.j2os.repository.modern.RoomRepository;
import org.j2os.service.modern.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class RoomServiceImp implements RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomClassicRepository roomClassicRepository;
    @Autowired
    private InPatientRepository inPatientRepository;
    //********************************************************************
    @Override
    public Map<String, Object> convertRoomToMap(Room room) {
        Map<String, Object> data = new HashMap<>();
        data.put("roomId",room.getRoomId());
        data.put("roomNumber", room.getRoomNumber());
        data.put("roomCost", room.getRoomCost());
        data.put("available",room.getAvailable());
        data.put("versionNumber", room.getVersionNumber());
        return data;
    }

    @Override
    public List<Map<String, Object>> convertRoomListToMapList(List<Room> rooms) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (Room room : rooms) {
            Map<String, Object> data = new HashMap<>();
            data.put("roomId",room.getRoomId());
            data.put("roomNumber", room.getRoomNumber());
            data.put("roomCost", room.getRoomCost());
            data.put("available",room.getAvailable());
            data.put("versionNumber", room.getVersionNumber());
            dataList.add(data);
        }
        return dataList;
    }

    @Override
    public Optional<Room> findByRoomIdAndRemoveDateTimeIsNull(Long roomId) {
        return roomRepository.findByRoomIdAndRemoveDateTimeIsNull(roomId);
    }
    @Override
    public boolean existsRoom(Room room) {
        return  (  roomRepository.findRoomByRoomNumberAndRemoveDateTimeIsNull(room.getRoomNumber()) != null  ) ;
    }
    @Override
    public boolean conflictRoom(Room room) {
        Room room1 = roomRepository.findRoomByRoomNumberAndRemoveDateTimeIsNull(room.getRoomNumber());
        if  (  room1 != null && room1.getRoomId() != null)
            return( room1.getRoomId() != room.getRoomId()) ;
        else return false;
    }

    @Override
    public boolean canRemove(Room room) {
        return ( inPatientRepository.findInPatientsByRoom_RoomIdAndRemoveDateTimeIsNull(room.getRoomId()).isEmpty());
    }
    //--------------------------------------------------------------
    @Override
    @Transactional
    public Room save(Room room) {
        return roomClassicRepository.save(room);
    }

    @Override
    @Transactional
    public Room update(Room room) {
        return roomClassicRepository.update(room);
    }

    @Override
    @Transactional
    public void removeLogical(Room room) {
        roomClassicRepository.removeLogical(room);
    }

    @Override
    public List<Room> findRoomByRemoveDateTimeIsNull() {
        return roomRepository.findRoomByRemoveDateTimeIsNull();
    }

    @Override
    public Room findRoomByRoomNumberAndRemoveDateTimeIsNull(int roomNumber) {
        return roomRepository.findRoomByRoomNumberAndRemoveDateTimeIsNull(roomNumber);
    }

    @Override
    public List<Room> findRoomsByAvailableAndRemoveDateTimeIsNull(char available) {
        return roomRepository.findRoomsByAvailableAndRemoveDateTimeIsNull(available);
    }

    @Override
    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }
}
