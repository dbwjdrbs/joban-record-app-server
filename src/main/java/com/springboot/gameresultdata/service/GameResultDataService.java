package com.springboot.gameresultdata.service;

import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.gamedata.service.GameDataService;
import com.springboot.gameresultdata.entity.GameResultData;
import com.springboot.gameresultdata.repository.GameResultDataRepository;
import com.springboot.helper.redis.service.RedisService;
import com.springboot.member.entity.Member;
import com.springboot.member.service.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class GameResultDataService {
    private final GameResultDataRepository gameResultDataRepository;
    private final MemberService memberService;
    private final GameDataService gameDataService;
    private final RedisService redisService;
    private final String GAME_MODE = "gamemode:";

    public GameResultDataService(GameResultDataRepository gameResultDataRepository, MemberService memberService, GameDataService gameDataService, RedisService redisService) {
        this.gameResultDataRepository = gameResultDataRepository;
        this.memberService = memberService;
        this.gameDataService = gameDataService;
        this.redisService = redisService;
    }

    public GameResultData createGameResultData(GameResultData requestBody, Authentication authentication) {
        // 제약
        String email = getPrincipal(authentication);
        Member member = memberService.findVerifiedMember(email);
        verifyMember(member, email);
        gameDataService.verifyGameData(requestBody.getGameData().getGameDataId());
        
        // 승률 올리기
        setRecord(member, String.valueOf(requestBody.getIsWin()));
        redisService.deleteValues(GAME_MODE + member.getName());

        // 저장
        return gameResultDataRepository.save(requestBody);
    }

    private void setRecord(Member member, String isWin) {
        Runnable action = isWin.equals("승리") ? member::addWin : member::addLose;
        action.run();
    }

    private String getPrincipal(Authentication authentication) {
        return String.valueOf(authentication.getPrincipal());
    }

    private void verifyMember(Member member, String email) {
        if (!member.getEmail().equals(email)) {
        throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }
    }
}
