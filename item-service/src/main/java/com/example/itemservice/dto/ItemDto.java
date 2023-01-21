package com.example.itemservice.dto;

import com.example.itemservice.enums.ItemCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDto {
    private Long itemId;
    private Long userId;
    private String name;
    private String description;
    private List<ItemCategory> categories;
}
