package com.example.itemservice.util;

import com.example.itemservice.dto.ItemDto;
import com.example.itemservice.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {


    public Item toItem(ItemDto itemDto){
        return Item.builder()
                .name(itemDto.getName())
                .description(itemDto.getDescription())
                .userId(itemDto.getUserId())
                .categories(itemDto.getCategories()).build();
    }


    public ItemDto fromItem(Item item) {
        return ItemDto.builder()
                .name(item.getName())
                .description(item.getDescription())
                .userId(item.getUserId())
                .categories(item.getCategories()).build();
    }
}
