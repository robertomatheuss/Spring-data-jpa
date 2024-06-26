package com.bookstore.jpa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PublisherRecordDTO(
        @NotBlank
        @NotNull
        String publisherName
) {
}
