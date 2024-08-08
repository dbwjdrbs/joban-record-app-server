package com.springboot.helper.event.member_event;

import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.member.entity.Member;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class MemberEventListener {
    @Value("${mail.auth-code-expiration-millis}")
    private long authCodeExpirationMillis;

    @EventListener
    public void sendMessage(MemberEvent memberEvent) {
        Member member = memberEvent.getMember();
        String authCode = createCode();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            try {
                memberEvent.getMailService().sendEmail(member.getEmail(),
                        "조선의 반격 유저 전적 기록 시스템 인증 번호",
                        authCode);
                memberEvent.getRedisService().setValues(member.getEmail(), authCode, Duration.ofMillis(authCodeExpirationMillis));
            } catch (Exception e) {
                memberEvent.getMemberRepository().delete(memberEvent.getMember());
                memberEvent.getRedisService().deleteValues(member.getEmail());
                throw new RuntimeException(e);
            }
        });
    }

   private String createCode() {
        int lenth = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < lenth; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new BusinessLogicException(ExceptionCode.NO_SUCH_ALGORITHM);
        }
    }
}
