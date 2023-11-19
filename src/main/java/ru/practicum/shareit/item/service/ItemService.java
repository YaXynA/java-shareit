package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemService {
    Item addItem(int owner, Item item);

    Item updateItem(Item item, int itemId, int userId);

    Item getItemById(int itemId);

    List<Item> getAllItemForOwner(int userId);

    List<Item> searchItem(String text);
}