package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.NoDataFoundException;
import ru.practicum.shareit.item.ItemMapper;
import ru.practicum.shareit.item.dao.ItemDao;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.dao.UserDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;
    private final UserDao userDao;
    private final ItemMapper mapper;

    @Override
    public ItemDto addItem(int userId, Item item) {

        userDao.getUserById(userId);
        item.setOwner(userId);
        return itemDao.addItem(item);
    }

    @Override
    public ItemDto updateItem(ItemDto itemDto, int itemId, int userId) {
        userDao.getUserById(userId);
        itemDao.getItemById(itemId);
        Item item = mapper.returnItem(itemDto);
        item.setId(itemId);
        item.setOwner(userId);
        if (!getAllItemForOwner(userId).stream().anyMatch(i -> i.getId() == itemId)) {
            throw new NoDataFoundException("The item was not found with the user id " + userId);
        }
        return itemDao.updateItem(userId, item);
    }

    @Override
    public ItemDto getItemById(int itemId) {
        return itemDao.getItemById(itemId);
    }

    @Override
    public List<ItemDto> getAllItemForOwner(int ownerId) {
        return itemDao.getAllItemForOwner(ownerId);
    }

    @Override
    public List<ItemDto> searchItem(String request) {
        return itemDao.searchItem(request);
    }

}