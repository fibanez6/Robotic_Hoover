package com.fibanez.repository;

import com.fibanez.domain.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roomoRepository")
public interface RoomRepository extends JpaRepository<Room, Long> {
}
