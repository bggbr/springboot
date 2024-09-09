package jpabook.jpashop.member;

import jakarta.transaction.Transactional;
import jpabook.jpashop.domain.member.Member;
import jpabook.jpashop.domain.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;


@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testMember() {
        Member member = new Member();
        member.setUsername("memberA");
        Long memberId = memberRepository.save(member);

        Member findMember = memberRepository.findById(memberId);

        org.assertj.core.api.Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());

    }

}