package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemService {
    ItemDto addItem(int owner, Item item);

    ItemDto updateItem(ItemDto itemDto, int itemId, int userId);

    ItemDto getItemById(int itemId);

    List<ItemDto> getAllItemForOwner(int userId);

    List<ItemDto> searchItem(String text);
}