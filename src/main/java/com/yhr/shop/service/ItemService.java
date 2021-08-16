package com.yhr.shop.service;

import com.yhr.shop.domain.item.Item;
import com.yhr.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void addItem(Item item){
        itemRepository.addItem(item);
    }

    public List<Item> getItems(){
        return itemRepository.getItems();
    }

    public Item getItemById(Long id){
        return itemRepository.getItemByItemId(id);
    }


}
