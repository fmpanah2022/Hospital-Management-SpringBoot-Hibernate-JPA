package org.j2os.controller;

import org.j2os.domain.entity.Room;
import org.j2os.service.modern.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;
    //*******************************************************
    @GetMapping("/rooms")
    public ResponseEntity<List<Map<String, Object>>> getAllRooms() {
        try {
            List<Room> rooms = roomService.findRoomByRemoveDateTimeIsNull();
            if (rooms.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(roomService.convertRoomListToMapList(rooms), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findByRoomNumber")
    public ResponseEntity<Map<String, Object>> findByRoomNumber(@RequestParam("roomNumber") String roomNumber) {
        try {
            Room room =  roomService.findRoomByRoomNumberAndRemoveDateTimeIsNull(Integer.parseInt(roomNumber));
            if (room.getRoomId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(roomService.convertRoomToMap(room), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findAllByAvailable")
    public ResponseEntity<List<Map<String, Object>>> findAllByAvailable() {
        try {
            List<Room> rooms = new ArrayList<Room>();
            rooms = roomService.findRoomsByAvailableAndRemoveDateTimeIsNull('1');
            if (rooms.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(roomService.convertRoomListToMapList(rooms), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //***********************************************************
    @GetMapping("/save")
    public ResponseEntity<Map<String, Object>> saveRoom(@RequestParam("roomNumber") String roomNumber ,
                                          @RequestParam("roomCost") String roomCost) {
        try {
            Room room1 = new Room().builder().roomNumber(Integer.parseInt(roomNumber)).roomCost(Long.parseLong(roomCost)).build();
            if (roomService.existsRoom(room1)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Room savedRoom = roomService.save(room1);
            if (savedRoom.getRoomId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(roomService.convertRoomToMap(savedRoom), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //***************************************************************************************
    @GetMapping("/update")
    public ResponseEntity<Map<String, Object>> updateRoom(@RequestParam("roomId") String roomId,
                                           @RequestParam("roomNumber") String roomNumber ,
                                           @RequestParam("roomCost") String roomCost,
                                           @RequestParam("roomVno") String roomVno) {

        Optional<Room> roomData = roomService.findByRoomIdAndRemoveDateTimeIsNull(Long.valueOf(roomId));
        if (roomData.isPresent()) {
            Room room1 = roomData.get();
            room1.builder().roomNumber(Integer.parseInt(roomNumber)).roomCost(Long.parseLong(roomCost)).versionNumber(Integer.parseInt(roomVno)).build();
            if (roomService.conflictRoom(room1)) {  return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
            Room savedRoom = roomService.update(room1);
            return new ResponseEntity<>(roomService.convertRoomToMap(savedRoom), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/remove")
    public ResponseEntity<HttpStatus> removeLogicalRoom(@RequestParam("roomId") String roomId,
                                                        @RequestParam("roomVno") String roomVno) {
        Optional<Room> productData = roomService.findByRoomIdAndRemoveDateTimeIsNull(Long.valueOf(roomId));
        if (productData.isPresent()) {
            Room room1 = productData.get();
            room1.setVersionNumber(Integer.parseInt(roomVno));
            if (!roomService.canRemove(room1)) {  return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }

            room1.setRemoveDateTime(new java.sql.Timestamp(new Date().getTime()));
            roomService.removeLogical(room1);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
