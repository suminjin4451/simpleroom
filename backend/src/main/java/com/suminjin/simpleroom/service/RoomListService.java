package com.suminjin.simpleroom.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suminjin.simpleroom.model.Room;
import com.suminjin.simpleroom.model.RoomDTO;
import com.suminjin.simpleroom.repository.RoomRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class RoomListService {

	@Autowired
	private RoomRepository roomRepository;

	/**
	 * DB상에서 방을 만드는 Method 입니다.
	 * 
	 */
	public void createRoom(RoomDTO room) {
		Room newRoom = new Room();

		// Initialize Room Setting
		newRoom.setRoomName(room.getRoomName());
		newRoom.setRoomPassword(room.getRoomPassword());
		newRoom.setPlayerNumber(0);
		HashMap<String, Object> newPlayerList = new HashMap<String, Object>();
		newRoom.setPlayerList(newPlayerList);

		roomRepository.save(newRoom);

	}

	/**
	 * DB상에서 방에 입장하는 Method 입니다.
	 * 
	 */
	public void joinRoom(Long roomID, String userID, String job) {
		Room room = roomRepository.findById(roomID).orElse(null);

		room.setPlayerNumber(room.getPlayerNumber() + 1);
		HashMap<String, Object> newPlayerList = room.getPlayerList();
		String[] object = { userID, job };
		newPlayerList.put(userID, object);
		room.setPlayerList(newPlayerList);

		roomRepository.save(room);
	}

	/**
	 * DB상에서 방에서 퇴장하는 Method 입니다.
	 * 
	 */
	public void exitRoom(Long roomID, String userID) {
		Room room = roomRepository.findById(roomID).orElse(null);

		log.info(room.toString());

		if (room.getPlayerNumber() == 1) {
			roomRepository.delete(room);
			return;
		}

		room.setPlayerNumber(room.getPlayerNumber() - 1);
		HashMap<String, Object> newPlayerList = room.getPlayerList();
		newPlayerList.remove(userID);
		room.setPlayerList(newPlayerList);

		roomRepository.save(room);
	}

	public Room getRoomInformation(Long roomID, String userID) {
		Room room = roomRepository.findById(roomID).orElse(null);

		return room;
	}

}
