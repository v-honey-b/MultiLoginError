package com.final_project.Store.repository;

import com.final_project.Store.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemberId(String memberId);
}
