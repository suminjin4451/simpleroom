package com.suminjin.simpleroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suminjin.simpleroom.model.Room;
import com.suminjin.simpleroom.model.RoomDTO;
import com.suminjin.simpleroom.repository.RoomRepository;
import com.suminjin.simpleroom.security.UserDetailsImpl;
import com.suminjin.simpleroom.service.RoomListService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/roomlist")
@Log4j2
public class RoomlistController {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private RoomListService roomListService;

	@GetMapping()
	public java.util.List<Room> getRoomlist() {
		return roomRepository.findAll();
	}

	@PostMapping("/create")
	public void createRoom(@RequestBody RoomDTO room) {
		try {
			log.info(room);
			roomListService.createRoom(room);
		} catch (Exception e) {
			throw e;
		}
	}

	@GetMapping("/join")
	public void createRoom(@RequestParam(required = true, value = "roomID") Long roomID,
			@RequestParam(required = true, value = "job") String job, @AuthenticationPrincipal UserDetailsImpl user) {
		try {
			roomListService.joinRoom(roomID, user.getUsername(), job);
		} catch (Exception e) {
			throw e;
		}
	}

	@GetMapping("/exit")
	public void createRoom(@RequestParam(required = true, value = "roomID") Long roomID,
			@AuthenticationPrincipal UserDetailsImpl user) {
		try {
			roomListService.exitRoom(roomID, user.getUsername());
		} catch (Exception e) {
			throw e;
		}
	}

	@GetMapping("/{roomID}")
	public Room getRoomInformatin(@PathVariable("roomID") Long roomID, @AuthenticationPrincipal UserDetailsImpl user) {
		try {
			return roomListService.getRoomInformation(roomID, user.getUsername());
		} catch (Exception e) {
			throw e;
		}
	}

}
