package com.springboot.gamedata.service;

import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.gamedata.entity.GameData;
import com.springboot.gamedata.repository.GameDataRepository;
import com.springboot.member.entity.Member;
import com.springboot.member.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameDataService {
    private final GameDataRepository gameDataRepository;

    public GameDataService(GameDataRepository gameDataRepository) {
        this.gameDataRepository = gameDataRepository;
    }

    public GameData createGameData(GameData gameData) {
        return gameDataRepository.save(gameData);
    }

    public void verifyGame(String gameId) {
        Optional<GameData> optionalGameData = gameDataRepository.findByGameId(gameId);
        if (optionalGameData.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.DATA_NOT_FOUND);
        }
    }
}