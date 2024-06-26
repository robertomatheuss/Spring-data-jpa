package com.bookstore.jpa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

public record BookRecordDTO(

        @NotNull(message = "Title is mandatory")
        @NotBlank(message = "Title is mandatory")
        String title,

        UUID publisherId,

        Set<UUID> authorIds,

        String reviewComment) {
}
