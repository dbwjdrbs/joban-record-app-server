package com.springboot.gameresultdata.repostitory;

import com.springboot.gameresultdata.entity.GameResultData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameResultDataRepository extends JpaRepository<GameResultData, Long> {
}
