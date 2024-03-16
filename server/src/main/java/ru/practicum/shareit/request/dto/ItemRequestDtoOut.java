package ru.practicum.shareit.request.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
<<<<<<<< HEAD:src/main/java/ru/practicum/shareit/request/dto/ItemRequestDto.java

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
========
import ru.practicum.shareit.item.dto.ItemDtoOut;

import java.time.LocalDateTime;
import java.util.List;
>>>>>>>> aa8bb75 (16 Sprint):server/src/main/java/ru/practicum/shareit/request/dto/ItemRequestDtoOut.java

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
<<<<<<<< HEAD:src/main/java/ru/practicum/shareit/request/dto/ItemRequestDto.java
public class ItemRequestDto {
========
public class ItemRequestDtoOut {
>>>>>>>> aa8bb75 (16 Sprint):server/src/main/java/ru/practicum/shareit/request/dto/ItemRequestDtoOut.java

    @NotBlank
    @Size(max = 255)
    private String description;
<<<<<<<< HEAD:src/main/java/ru/practicum/shareit/request/dto/ItemRequestDto.java
========
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime created;
    private List<ItemDtoOut> items;
>>>>>>>> aa8bb75 (16 Sprint):server/src/main/java/ru/practicum/shareit/request/dto/ItemRequestDtoOut.java
}