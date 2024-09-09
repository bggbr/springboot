package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.member.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

// @Repository로 설정 시, 스프링에서 빈으로 자동으로 등록해준다.
@Repository
public class MemberRepository {

    // 스프링이 엔티티 매니저를 만들어서 자동으로 주입해준다.
    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        // JPA의 영속성 컨텍스트에 멤버 객체를 넣는다.
        // 추후 트랜잭션이 커밋되는 시점에 데이터베이스에 반영된다.
        // 데이터베이스에 INSERT 쿼리가 날리가는 것이다.
        em.persist(member);
    }

    public Member findOne(Long id) {
        // 단건 조회
        // 첫 번째 인자는 타입, 두 번째 인자는 pk를 넣어주면 된다.
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        // jpql을 사용
        // sql은 테이블을 대상으로 쿼리
        // jpql은 엔티티 객체를 대상으로 쿼리 (FROM의 대상)
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.username = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
