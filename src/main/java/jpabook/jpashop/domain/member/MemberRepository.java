package jpabook.jpashop.domain.member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;


@Repository("domainMemberRepository")
public class MemberRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }
}
