package com.yhr.shop.repository;

import com.yhr.shop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void addItem(Item item){
        if ( item.getId() == null ) {
            // item의 번호가 존재하지 않는다면 ( 새로 생성된 객체 혹은 데이터라면 )
            // item 인스턴스 영속화
            em.persist(item);
        } else {
            // 그렇지 않고 item의 번호가 존재한다면 ( 기존에 있는 데이터를 불러왔다면 )
            // merge
            em.merge(item);
        }

    }

    public Item getItemByItemId(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> getItems(){
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }

}
