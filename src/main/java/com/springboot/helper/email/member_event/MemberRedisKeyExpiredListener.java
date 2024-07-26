package com.springboot.helper.email.member_event;

import com.springboot.member.entity.Member;
import com.springboot.member.service.MemberService;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
public class MemberRedisKeyExpiredListener extends KeyExpirationEventMessageListener {
    private final MemberService memberService;

    public MemberRedisKeyExpiredListener(RedisMessageListenerContainer listenerContainer, MemberService memberService) {
        super(listenerContainer);
        this.memberService = memberService;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) { // message -> key 값
        // 1. 멤버 가져오기
        Member member = memberService.findVerifiedMember(message.toString());
        // 2. 롤 체크
        if (member.getRoles().contains("TEMP")) {
            // 3. 롤이 USER면 삭제
            memberService.deleteMember(member.getMemberId());
        }
    }
}
