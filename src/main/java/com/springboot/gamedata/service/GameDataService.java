package com.springboot.gamedata.service;

import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.gamedata.entity.GameData;
import com.springboot.gamedata.repository.GameDataRepository;
import com.springboot.helper.event.gamemode_event.GameModeEvent;
import com.springboot.helper.redis.service.RedisService;
import com.springboot.member.entity.Member;
import com.springboot.member.service.MemberService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameDataService {
    private final GameDataRepository gameDataRepository;
    private final MemberService memberService;
    private final RedisService redisService;
    private final ApplicationEventPublisher publisher;
    private final String GAME_MODE = "gamemode:";

    public GameDataService(GameDataRepository gameDataRepository, MemberService memberService, RedisService redisService, ApplicationEventPublisher publisher) {
        this.gameDataRepository = gameDataRepository;
        this.memberService = memberService;
        this.redisService = redisService;
        this.publisher = publisher;
    }

    public GameData createGameData(GameData gameData, Authentication authentication) {
        memberService.findVerifiedMember(getPrincipal(authentication));
        GameData savedGameData = gameDataRepository.save(gameData);

        publisher.publishEvent(new GameModeEvent(savedGameData, gameDataRepository, redisService));

        return savedGameData;
    }

    public GameData updateGameData(GameData gameData, Authentication authentication) {
        memberService.findVerifiedMember(getPrincipal(authentication));
        GameData findGameData = verifyGameData(gameData.getGameDataId());

        Optional.ofNullable(gameData.getGameResultDatas())
                .ifPresent(gameResultData -> findGameData.setGameResultDatas(gameResultData));
        Optional.ofNullable(gameData.getPlayTime())
                .ifPresent(playTime -> findGameData.setPlayTime(playTime));
        Optional.ofNullable(gameData.getGameResultDatas())
                .ifPresent(gameResultData -> findGameData.setGameResultDatas(gameResultData));

        redisService.deleteValues(getPrincipal(authentication));

        return gameDataRepository.save(findGameData);
    }

    public GameData findGameData(Authentication authentication) {
        String username = getPrincipal(authentication);
        Member member = memberService.findVerifiedMember(username);
        String gameDataId = redisService.getValues(GAME_MODE + member.getName());

        GameData gameData = new GameData();
        gameData.setGameDataId(Long.parseLong(gameDataId));

        return verifyGameData(gameData.getGameDataId());
    }

    public GameData verifyGameData(long gameDataId) {
        Optional<GameData> optionalGameData = gameDataRepository.findById(gameDataId);
        return optionalGameData.orElseThrow(() -> new BusinessLogicException(ExceptionCode.DATA_NOT_FOUND));
    }

    private String getPrincipal(Authentication authentication) {
        return String.valueOf(authentication.getPrincipal());
    }
}