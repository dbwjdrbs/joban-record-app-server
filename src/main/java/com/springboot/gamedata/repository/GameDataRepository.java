package com.springboot.gamedata.repository;

import com.springboot.gamedata.entity.GameData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameDataRepository extends JpaRepository<GameData, Long> {
}
