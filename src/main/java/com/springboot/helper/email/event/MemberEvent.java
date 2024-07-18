package com.springboot.helper.email.event;

import com.springboot.helper.email.service.MailService;
import com.springboot.helper.redis.service.RedisService;
import com.springboot.member.entity.Member;
import com.springboot.member.repository.MemberRepository;
import lombok.Getter;

@Getter
public class MemberEvent {
    private final Member member;
    private final MailService mailService;
    private final MemberRepository memberRepository;
    private final RedisService redisService;

    public MemberEvent(Member member, MailService mailService, MemberRepository memberRepository, RedisService redisService) {
        this.member = member;
        this.mailService = mailService;
        this.memberRepository = memberRepository;
        this.redisService = redisService;
    }
}
