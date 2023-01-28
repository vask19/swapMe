package com.example.itemservice.controller;
import com.example.itemservice.dto.ItemDto;
import com.example.itemservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/v1/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;


    @PostMapping
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto itemDto){
        itemService.createItem(itemDto);
        return ResponseEntity.ok(itemDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItem(@PathVariable("id") Long id){
        return ResponseEntity.ok(null);

    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ItemDto>> getAllUsersItems(@PathVariable("userId") String  userId){
        List<ItemDto> items = itemService.getAllUsersItems(userId);
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable("id") Long itemId, Principal principal){
        itemService.deleteItem(itemId,principal);
        return ResponseEntity.noContent().build();
    }




}
