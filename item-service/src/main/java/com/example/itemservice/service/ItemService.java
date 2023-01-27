package com.example.itemservice.service;

import com.example.itemservice.dto.ItemDto;
import com.example.itemservice.exeption.ItemNotFoundException;
import com.example.itemservice.model.Item;
import com.example.itemservice.repository.ItemRepository;
import com.example.itemservice.util.ItemMapper;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemDto createItem(ItemDto itemDto) {
        Item item = itemRepository.save(itemMapper.toItem(itemDto));
        log.info("{} has saved ",item);
        return itemDto;

    }

    public ItemDto getItemById(Long itemId){
        return itemMapper.fromItem(itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new));
    }

    public List<ItemDto> getAllUsersItems(Long userId) {
        return itemRepository.findAllByUserId(userId).stream()
                .map(itemMapper::fromItem).collect(Collectors.toList());

    }

    @Transactional
    public boolean deleteItem(Long itemId, Principal principal) {
        Long userId = getUserByPrincipal(principal);
        Item item = itemRepository.findAllByUserId(userId).orElseThrow(ItemNotFoundException::new);
        itemRepository.deleteById(itemId);
        log.info("item has benn deleted successfully");
        return true;
    }

    private Long getUserByPrincipal(Principal principal) {
        return 0L;
        //TODO method will be sent request to user-service
    }
}
