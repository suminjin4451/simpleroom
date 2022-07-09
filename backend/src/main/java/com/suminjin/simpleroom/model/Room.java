package com.suminjin.simpleroom.model;

import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "RoomList")
@Data
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long roomID;

	private String roomName;

	private String roomPassword;

	private Integer playerNumber;

	private HashMap<String, Object> playerList;

}
