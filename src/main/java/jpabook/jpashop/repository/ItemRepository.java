package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        // item을 저장하기 까지 id 값이 없다. 이것의 의미는 완전히 새로 생성하는 객체라는 것
        // 신규로 등록하는 것이다. id 값이 있는 것은 DB에 있는 것이기 때문 업데이트한다고 보면 된다.
        // merge는 업데이트는 아니지만 비슷한 것..
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }
}
