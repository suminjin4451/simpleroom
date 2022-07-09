package com.suminjin.simpleroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suminjin.simpleroom.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
