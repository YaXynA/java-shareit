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

    @PostMapping
    public ItemDto addItem(@RequestHeader("X-Sharer-User-Id") int userId,
                           @RequestBody @Valid Item item) {
        log.info("User {}, add new item {}", userId, item.getName());
        return itemService.addItem(userId, item);
    }

    @GetMapping("/{id}")
    public ItemDto getItemById(@PathVariable(name = "id") int itemId) {
        log.info("You search item by id {} ", itemId);
        return itemService.getItemById(itemId);
    }

    @GetMapping
    public List<ItemDto> getAllItemForOwner(@RequestHeader("X-Sharer-User-Id") int userId) {
        log.info("You search all items for userId {} ", userId);
        return itemService.getAllItemForOwner(userId);
    }

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@RequestHeader("X-Sharer-User-Id") int userId,
                              @RequestBody ItemDto itemDto,
                              @PathVariable Integer itemId) {
        log.info("User {},update itemId {} , item {}", userId,itemId, itemDto.getName());
        return itemService.updateItem(itemDto, itemId, userId);

    }

    @GetMapping("/search")
    public List<ItemDto> searchItem(@RequestParam(name = "text") String request) {
        log.info("You search item by {} ", request);
        return itemService.searchItem(request);
    }
}
