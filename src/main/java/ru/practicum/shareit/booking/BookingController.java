package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingDtoOut;
import ru.practicum.shareit.booking.service.BookingService;
import ru.practicum.shareit.constants.Constants;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
@Slf4j
@Validated
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public BookingDtoOut create(@RequestHeader(Constants.USER_HEADER) Long userId,
                                @Valid @RequestBody BookingDto bookingDto) {
        log.info("POST запрос на создание нового бронирования вещи от пользователя c id: {} ", userId);
        return bookingService.add(userId, bookingDto);
    }

    @PatchMapping("/{bookingId}")
    public BookingDtoOut updateStatus(@RequestHeader(Constants.USER_HEADER) Long userId,
                                      @PathVariable("bookingId")
                                      Long bookingId,
                                      @RequestParam(name = "approved") Boolean approved) {
        log.info("PATCH запрос на обновление статуса бронирования вещи от владельца с id: {}", userId);
        return bookingService.update(userId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    public BookingDtoOut findBookingById(@RequestHeader(Constants.USER_HEADER) Long userId,
                                         @PathVariable("bookingId")
                                         Long bookingId) {
        log.info("GET запрос на получение данных о  бронировании от пользователя с id: {}", userId);
        return bookingService.findBookingByUserId(userId, bookingId);
    }

    @GetMapping
    public List<BookingDtoOut> findAll(@RequestHeader(Constants.USER_HEADER) Long userId,
                                       @RequestParam(value = "state", defaultValue = "ALL") String bookingState,
                                       @RequestParam(value = "from", defaultValue = "0") @PositiveOrZero Integer from,
                                       @RequestParam(value = "size", defaultValue = "10") @Positive Integer size) {
        log.info("GET запрос на получение списка всех бронирований текущего пользователя с id: {} и статусом {}", userId, bookingState);
        return bookingService.findAll(userId, bookingState, from, size);
    }

    @GetMapping("/owner")
    public List<BookingDtoOut> getAllOwner(@RequestHeader(Constants.USER_HEADER) Long ownerId,
                                           @RequestParam(value = "state", defaultValue = "ALL") String bookingState,
                                           @RequestParam(value = "from", defaultValue = "0") @PositiveOrZero Integer from,
                                           @RequestParam(value = "size", defaultValue = "10") @Positive Integer size) {
        log.info("GET запрос на получение списка всех бронирований текущего владельца с id: {} и статусом {}", ownerId, bookingState);
        return bookingService.findAllOwner(ownerId, bookingState, from, size);
    }
}