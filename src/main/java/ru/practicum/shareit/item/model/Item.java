package ru.practicum.shareit.item.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Item {

    @EqualsAndHashCode.Include
    private int id;

    @NotBlank(message = "Name cannot be empty or contain spaces.")
    private String name;

    @NotBlank(message = "Description cannot be empty or contain spaces.")
    private String description;

    @NotNull(message = "Available cannot be empty.")
    private Boolean available;

    private int owner;

}