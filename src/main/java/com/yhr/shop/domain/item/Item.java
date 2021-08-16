package com.yhr.shop.domain.item;

import com.yhr.shop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)// 싱글테이블 전략을 사용하였다.
@DiscriminatorColumn(name = "dtype") // 상속받은 클래스들을 구분하기 위한 구분자 컬럼을 지정 함
@Getter
@Setter // Setter는 사용을 지양하는 것이 바람직하다. 만일 내부의 데이터를 변경해야할 일이 생긴다면
        // *비즈니스로직 처럼 해당 데이터를 다루는 메소드를 따로 추가하여 다루는 것이 안전하고 바람직하다.
public abstract class Item {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    /*
    비즈니스 로직
     */
    // 데이터를 가지고 있는 쪽에서 데이터를 다룰 수 있는 메소드를 갖고 있는 것이 바람직하다.
    // 객체지향 패러다임에 맞추어 관련도가 밀접한 내용의 비즈니스 로직은 엔티티 클래스 내부에 녹이는 것이 바람직하다

    /*
    stock 증가
     */
    public void addStock(int quantity){
        //재고 수량을 더해주기 위한 메소드
        this.stockQuantity += quantity;
    }

    /*
    stock감소
     */
    public void minusStock(int quantity){
        int remainedStock = this.stockQuantity - quantity;
        if(remainedStock < 0){
            throw new NotEnoughStockException("잔여재고가 부족합니다.");
        }
        this.stockQuantity = remainedStock;
    }
}
