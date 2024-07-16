package com.springboot.member.entity;

import com.springboot.audit.Auditable;
import com.springboot.gamedata.entity.GameData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @Column(nullable = false)
    private int win = 0;

    @Column(nullable = false)
    private int lose = 0;

    @OneToMany(mappedBy = "member")
    private List<GameData> gameDatas = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

    public Member(String email) {
        this.email = email;
    }

    public Member(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public void setGameData(GameData gameData) {
        gameDatas.add(gameData);
        if (gameData.getMember() != this) {
            gameData.setMember(this);
        }
    }

    public void addWin() {
        this.win += 1;
    }

    public void addLose() {
        this.lose += 1;
    }

    public enum MemberStatus {
        MEMBER_ACTIVE("활동중"),
        MEMBER_SLEEP("휴면 상태"),
        MEMBER_QUIT("탈퇴 상태");

        @Getter
        private String status;

        MemberStatus(String status) {
           this.status = status;
        }
    }
}
