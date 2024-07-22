package com.springboot.gameresultdata.service;

import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.gamedata.entity.GameData;
import com.springboot.gamedata.service.GameDataService;
import com.springboot.gameresultdata.dto.GameResultDataDto;
import com.springboot.gameresultdata.entity.GameResultData;
import com.springboot.gameresultdata.repostitory.GameResultDataRepository;
import com.springboot.member.entity.Member;
import com.springboot.member.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class GameResultDataService {
    private final GameResultDataRepository gameResultDataRepository;
    private final MemberService memberService;
    private final GameDataService gameDataService;

    public GameResultDataService(GameResultDataRepository gameResultDataRepository, MemberService memberService, GameDataService gameDataService) {
        this.gameResultDataRepository = gameResultDataRepository;
        this.memberService = memberService;
        this.gameDataService = gameDataService;
    }

    public GameResultData createGameResultData(GameResultData requestBody) {
        // 제약
        Member member = memberService.findVerifiedMember(requestBody.getMember().getMemberId());
        gameDataService.verifyGame(requestBody.getGameId());
        // 승률 올리기
        setRecord(member, requestBody.getIsWin());

        // 저장
        return gameResultDataRepository.save(requestBody);
    }

    private void setRecord(Member member, String isWin) {
        Runnable action = isWin.equals("승리") ? member::addWin : member::addLose;
        action.run();
    }
}
