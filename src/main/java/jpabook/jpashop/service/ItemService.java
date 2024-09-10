package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    // 상품 서비스(ItemService)는 단순히 상품 레포지토리(ItemRepository)에게
    // 위임만 하는 클래스이다. 단순히 위임만하는 걸 만들 필요가 있을까? 의심해 볼 필요가 있다.
    // 컨트롤러에서 ItemRepository에 바로 접근하여 사용해도 문제가 없다고 생각한다.
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
