package com.yhr.shop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)// 싱글테이블 전략을 사용하였다.
@DiscriminatorColumn(name = "dtype") // 상속받은 클래스들을 구분하기 위한 구분자 컬럼을 지정 함
@Getter@Setter
public abstract class Item {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

}
