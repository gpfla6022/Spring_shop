package com.yhr.shop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Category {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
        joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = LAZY) // 지연로딩을 위하여 설정
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();


    /*
     하위 카테고리를 위한 연관관계
      */
    public void addChildCategory(Category child) {

        // 해당 인스턴스의 child에 카테고리 child를 추가하고
        this.child.add(child);
        // 해당 child의 부모에 해당 인스턴스를 주입함으로서 계층관계를 나누었음
        child.setParent(this);

    }

}
