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
    private final MemberService memberService;

    public GameDataService(GameDataRepository gameDataRepository, MemberService memberService) {
        this.gameDataRepository = gameDataRepository;
        this.memberService = memberService;
    }

    public GameData createGameData(GameData gameData) {
        Member member = memberService.findMember(gameData.getMember().getMemberId());

        if (gameData.getIsWin().equals("승리")) {
            member.addWin();
            memberService.updateMember(member);
        } else if (gameData.getIsWin().equals("패배")) {
            member.addLose();
            memberService.updateMember(member);
        }
        return gameDataRepository.save(gameData);
    }
}
