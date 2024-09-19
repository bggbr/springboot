package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

//    public List<Order> findAll(OrderSearch orderSearch) {
//        em.createQuery("select o from Order o join o.member m" +
//                " where o.status = :status " +
//                " and m.username like :username "
//                , Order.class)
//                .setParameter("status", orderSearch.getOrderStatus())
//                .setParameter("username", orderSearch.getMemberName())
//                .setMaxResults(1000) // 최대 1000 건
//                .getResultList();
        // 스프링부트, JPA, Querydsl 필수!
//    }

}
