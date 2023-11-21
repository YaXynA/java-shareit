package ru.practicum.shareit.item.dao;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;


public interface ItemDao {
    Item getItemById(int itemId);

    List<Item> getAll();

    ItemDto addItem(Item item);

    ItemDto updateItem(int userId, Item item);

    List<Item> getAllItemForOwner(int userId);

    List<Item> searchItem(String text);
}