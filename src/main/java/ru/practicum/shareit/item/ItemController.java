package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemService;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final ItemMapper mapper;


    @PostMapping
    public ItemDto addItem(@RequestHeader("X-Sharer-User-Id") int userId,
                       @RequestBody @Valid ItemDto itemDto) {

        Item item = mapper.returnItem(itemDto);
        itemService.addItem(userId, item);
        log.info("User {}, add new item {}", userId, item.getName());
        return mapper.returnItemDto(item);
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable(name = "id") int itemId) {
        return itemService.getItemById(itemId);
    }

    @GetMapping
    public List<Item> getAllItemForOwner(@RequestHeader("X-Sharer-User-Id") int userId) {
        return itemService.getAllItemForOwner(userId);
    }

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@RequestHeader("X-Sharer-User-Id") int userId,
                              @RequestBody ItemDto itemDto,
                              @PathVariable Integer itemId) {

        Item item = mapper.returnItem(itemDto);
        Item updateItem = itemService.updateItem(item, itemId, userId);
        return mapper.returnItemDto(updateItem);
    }

    @GetMapping("/search")
    public List<Item> searchItem(@RequestParam(name = "text") String request) {
        return itemService.searchItem(request);
    }
}
