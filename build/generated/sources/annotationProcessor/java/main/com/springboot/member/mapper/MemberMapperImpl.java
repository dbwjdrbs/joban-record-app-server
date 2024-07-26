package com.springboot.member.mapper;

import com.springboot.member.dto.MemberDto;
import com.springboot.member.entity.Member;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-25T16:54:39+0900",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.22 (Azul Systems, Inc.)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberPostToMember(MemberDto.Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Member member = new Member();

        member.setEmail( requestBody.getEmail() );
        member.setName( requestBody.getName() );
        member.setPassword( requestBody.getPassword() );

        return member;
    }

    @Override
    public Member memberPatchToMember(MemberDto.Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Member member = new Member();

        member.setMemberId( requestBody.getMemberId() );
        member.setName( requestBody.getName() );
        member.setWin( requestBody.getWin() );
        member.setLose( requestBody.getLose() );
        member.setMemberStatus( requestBody.getMemberStatus() );

        return member;
    }

    @Override
    public MemberDto.Response memberToMemberResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        long memberId = 0L;
        String email = null;
        String name = null;
        int win = 0;
        int lose = 0;
        Member.MemberStatus memberStatus = null;

        if ( member.getMemberId() != null ) {
            memberId = member.getMemberId();
        }
        email = member.getEmail();
        name = member.getName();
        win = member.getWin();
        lose = member.getLose();
        memberStatus = member.getMemberStatus();

        MemberDto.Response response = new MemberDto.Response( memberId, email, name, win, lose, memberStatus );

        return response;
    }
}
