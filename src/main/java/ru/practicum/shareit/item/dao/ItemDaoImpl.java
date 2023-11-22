package ru.practicum.shareit.item.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.NoDataFoundException;
import ru.practicum.shareit.item.ItemMapper;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ItemDaoImpl implements ItemDao {

    private int newId = 1;

    private final HashMap<Integer, List<Item>> items = new HashMap<>();

    private final HashMap<Integer, Item> allItems = new HashMap<>();

    private final ItemMapper mapper;

    @Override
    public ItemDto getItemById(int itemId) {
        if (allItems.containsKey(itemId)) {
            return mapper.returnItemDto(allItems.get(itemId));
        } else {
            throw new NoDataFoundException("Item с id: " + itemId + " не найдена.");
        }
    }

    @Override
    public List<Item> getAll() {

        return new ArrayList<>(allItems.values());

    }

    @Override
    public ItemDto addItem(Item item) {
        if (item.getId() == 0) {
            item.setId(newId++);
        }
        allItems.put(item.getId(), item);
        items.compute(item.getOwner(), (userId, userItems) -> {
            if (userItems == null) {
                userItems = new ArrayList<>();
            }
            userItems.add(item);
            return userItems;
        });
        return mapper.returnItemDto(item);
    }

    @Override
    public ItemDto updateItem(int userId, Item item) {

        Item newItem = allItems.get(item.getId());

        if (item.getName() != null) {
            newItem.setName(item.getName());
        }

        if (item.getDescription() != null) {
            newItem.setDescription(item.getDescription());
        }

        if (item.getAvailable() != null) {
            newItem.setAvailable(item.getAvailable());
        }

        List<Item> list = items.get(userId);
        list.remove(item);
        list.add(newItem);
        allItems.put(item.getId(), newItem);
        return mapper.returnItemDto(newItem);
    }



    @Override
    public List<ItemDto> getAllItemForOwner(int userId) {
        List<Item> itemList = items.getOrDefault(userId, Collections.emptyList());
        return itemList.stream()
                .map(mapper::returnItemDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> searchItem(String text) {
        if (text.equals("")) {
            return Collections.emptyList();
        } else {
            final String searchText = text.toLowerCase(Locale.ENGLISH);
            return allItems.values()
                    .stream()
                    .filter(i -> i.getAvailable()
                            && (i.getName().toLowerCase(Locale.ENGLISH).contains(searchText)
                            || i.getDescription().toLowerCase(Locale.ENGLISH).contains(searchText)))
                    .map(mapper::returnItemDto)
                    .collect(Collectors.toList());
        }
    }
}