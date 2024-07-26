package com.springboot.gameresultdata.repository;


import com.springboot.gameresultdata.entity.GameResultData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameResultDataRepository extends JpaRepository<GameResultData, Long> {
}
