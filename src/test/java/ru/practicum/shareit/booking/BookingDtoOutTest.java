package ru.practicum.shareit.booking;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import ru.practicum.shareit.booking.dto.BookingDtoOut;
import ru.practicum.shareit.booking.model.BookingStatus;
import ru.practicum.shareit.item.dto.ItemDtoOut;
import ru.practicum.shareit.user.dto.UserDto;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class BookingDtoOutTest {

    @Autowired
    private JacksonTester<BookingDtoOut> json;

    private static final String DATE_TIME = "2023-07-23T07:33:00";

    private BookingDtoOut bookingDtoOut = null;

    @BeforeEach
    public void init() {
        ItemDtoOut itemDtoOut = ItemDtoOut.builder()
                .id(1L)
                .name("Test Item")
                .build();

        UserDto userDto = UserDto.builder()
                .id(1L)
                .name("Test User")
                .build();

        bookingDtoOut = BookingDtoOut.builder()
                .id(1L)
                .item(itemDtoOut)
                .start(LocalDateTime.parse("2023-07-23T07:33:00"))
                .end(LocalDateTime.parse("2023-07-23T07:33:00"))
                .booker(userDto)
                .status(BookingStatus.APPROVED)
                .build();
    }

    @Test
    @SneakyThrows
    public void startSerializes() {
        assertThat(json.write(bookingDtoOut)).extractingJsonPathStringValue("$.start")
                .isEqualTo(DATE_TIME);
    }

    @Test
    @SneakyThrows
    public void endSerializes() {
        assertThat(json.write(bookingDtoOut)).extractingJsonPathStringValue("$.end")
                .isEqualTo(DATE_TIME);
    }
}