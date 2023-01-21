package com.example.itemservice.service;

import com.example.itemservice.dto.ItemDto;
import com.example.itemservice.model.Item;
import com.example.itemservice.repository.ItemRepository;
import com.example.itemservice.util.ItemMapper;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemDto createItem(ItemDto itemDto) {
        itemRepository.save(itemMapper.toItem(itemDto));
        return itemDto;

    }

    public List<ItemDto> getAllUsersItems(Long userId) {
        return itemRepository.findAllByUserId(userId).stream()
                .map(itemMapper::fromItem).collect(Collectors.toList());

    }
}
