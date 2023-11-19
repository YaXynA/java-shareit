package ru.practicum.shareit.item.dao;

import ru.practicum.shareit.item.model.Item;

import java.util.List;


public interface ItemDao {
    Item getItemById(int itemId);

    List<Item> getAll();

    Item addItem(Item item);

    Item updateItem(int userId, Item item);

    List<Item> getAllItemForOwner(int userId);

    List<Item> searchItem(String text);
}