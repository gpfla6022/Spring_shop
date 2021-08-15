package com.yhr.shop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B") // 싱글테이블 전략이기 때문에 DB입장에서 구분할 수 있는 수단이 필요한데 해당 어노테이션으로 지정할 수 있다.
@Getter @Setter
public class Book extends Item {

    private String author;
    private String isbn;

}
