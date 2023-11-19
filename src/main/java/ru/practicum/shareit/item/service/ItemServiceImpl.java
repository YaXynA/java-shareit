package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.NoDataFoundException;
import ru.practicum.shareit.item.dao.ItemDao;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.dao.UserDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;
    private final UserDao userDao;

    @Override
    public Item addItem(int userId, Item item) {

        userDao.getUserById(userId);
        item.setOwner(userId);
        return itemDao.addItem(item);
    }

    @Override
    public Item updateItem(Item item, int itemId, int userId) {

        userDao.getUserById(userId);
        itemDao.getItemById(itemId);

        item.setId(itemId);
        item.setOwner(userId);

        if (!getAllItemForOwner(userId).contains(item)) {
            throw new NoDataFoundException("the item was not found with the user id " + userId);
        }

        return itemDao.updateItem(userId, item);
    }

    @Override
    public Item getItemById(int itemId) {
        return itemDao.getItemById(itemId);
    }

    @Override
    public List<Item> getAllItemForOwner(int ownerId) {
        return itemDao.getAllItemForOwner(ownerId);
    }

    @Override
    public List<Item> searchItem(String request) {
        return itemDao.searchItem(request);
    }

}