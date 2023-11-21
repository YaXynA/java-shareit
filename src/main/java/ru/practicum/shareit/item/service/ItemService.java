package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemService {
    ItemDto addItem(int owner, Item item);

    ItemDto updateItem(Item item, int itemId, int userId);

    Item getItemById(int itemId);

    List<Item> getAllItemForOwner(int userId);

    List<Item> searchItem(String text);
}