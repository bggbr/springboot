package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.member.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

// 실제 데이터베이스의 트랜잭션 테스트를 위해
@SpringBootTest
// 기본적으로 ROLLBACK을 해버린다.
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    @Rollback(false)
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setUsername("bang");

        // when
        Long saveId = memberService.join(member);

        // then
        em.flush();
        assertEquals(member, memberRepository.findOne(saveId));
    }

//    @Test(expected = IllegalStateException.class)
    @Test
    public void 중복_회원_예외() throws Exception {
        // given
        Member member = new Member();
        member.setUsername("bang2");

        Member member2 = new Member();
        member2.setUsername("bang2");

        // when
        memberService.join(member);
        try {
            memberService.join(member2); // 예외가 발생해야 한다.
        } catch (IllegalStateException e) {
            return;
        }


        // then
        fail("예외가 발생해야 한다.");
    }
}