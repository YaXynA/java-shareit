package ru.practicum.shareit.exception;

import lombok.Data;
import lombok.AllArgsConstructor;


@Data
@AllArgsConstructor
public class ErrorResponse {
    private String error;
    private String description;
}