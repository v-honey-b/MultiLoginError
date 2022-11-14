package com.final_project.Store.entity;

import com.final_project.Store.constant.Role;
import com.final_project.Store.dto.MemberRegisterFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "member", uniqueConstraints = {@UniqueConstraint(name = "memberIdNameUnique", columnNames = {"memberId", "memberName"})})
@Getter
@Setter
@ToString
public class Member {

    @Id
    @SequenceGenerator(name = "MEMBER_SEQUENCE_GEN", sequenceName = "member_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "memberNum")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQUENCE_GEN")
    private Long memberNum;

    @Column(unique = true)
    private String memberId;

    @Column(unique = true)
    private String memberName;

    private String memberPwd;
    private String memberEmail;
    private String memberZipCode;
    private String memberAddress;
    private String memberPhone;

    @CreationTimestamp
    private LocalDateTime memberRegDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberRegisterFormDto memberRegisterFormDto, PasswordEncoder passwordEncoder) {
        Member member = new Member();

        member.setMemberId(memberRegisterFormDto.getMemberId());

        String memberPwd = passwordEncoder.encode(memberRegisterFormDto.getMemberPwd());
        member.setMemberPwd(memberPwd);

        member.setMemberName(memberRegisterFormDto.getMemberName());

        member.setMemberEmail(memberRegisterFormDto.getMemberEmail());

        member.setMemberZipCode(memberRegisterFormDto.getMemberZipCode());

        member.setMemberAddress(memberRegisterFormDto.getMemberAddress());

        member.setMemberPhone(memberRegisterFormDto.getMemberPhone());

        member.setRole(Role.MEMBER);

        return member;
    }
}
