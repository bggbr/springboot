package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<Category>();

    /**
     * 우리가 보통 개발할 때 Item의 서비스를 즉, 비즈니스 로직을 처리하는
     * ItemService를 만들고 stock의 값을 처리하는 로직을 만들었을 것이다.
     * ex) Item.setStockQuantity와 같은 메소드를 사용
     * 그런데 객체지향적으로 생각해보면 데이터를 가지고 있는 쪽에서
     * 비즈니스 로직을 처리하는 게 가장 좋다. 그래야 응집력이 있다.
     * 현재 편의를 위해 lombok의 `@Setter`를 넣었는데 Setter를 넣는 게 아닌
     * stockQuantity를 변경해야 할 일이 있으면, 핵심 비즈니스 메서드를 가지고
     * 변경해야 한다. 밖에서 setStock 메서드 등을 해야 하는 것이 아닌..
     */

    /**
     * stock 증가
     * @param quantity
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     * @param quantity
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity -= restStock;
    }
}
