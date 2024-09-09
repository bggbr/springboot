package jpabook.jpashop.service;

import jpabook.jpashop.domain.member.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 생성자가 하나만 있는 경우 '@Autowired'가 없어도 스프링이 자동으로 주입을 해준다.
//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     * 회원 가입
     */
    @Transactional(readOnly = false)
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // EXCEPTION
        // 만약 같은 회원의 이름으로 동시에 가입(INSERT)을 진행하게 되면 '동시성' 문제가 발생할 수 있다.
        // 멀티스레딩을 고려하여 데이터베이스에 멤버의 이름을 유니크 제약 조건을 거는 게 안전하다.
        List<Member> findMembers = memberRepository.findByName(member.getUsername());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     * 'readOnly = true' 옵션을 주게 되면, 성능 최적화 (영속성 컨텍스트를 플러시 안 함 등)
     * 위 옵션을 넣으면 데이터 변경이 되지 않는다.
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 단건 조회
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
