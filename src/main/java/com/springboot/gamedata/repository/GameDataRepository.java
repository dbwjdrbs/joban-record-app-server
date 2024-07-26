package com.springboot.gamedata.repository;

import com.springboot.gamedata.entity.GameData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameDataRepository extends JpaRepository<GameData, Long> {
}
