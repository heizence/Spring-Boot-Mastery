package com.example.todoListApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Use "abstract" to prevent this class from being generated as an instance.
@Schema(description = "Container for all request DTOs")
public abstract class RequestDto {

    // Use "private" so that external environment cannot call new RequestDto().
    private RequestDto() {
    }

    // Define each DTOs for each requests as public static classes.
    // @Getter: Generates getter methods automatically by Lombok
    // @NoArgsConstructor: Generates basic generator automatically by Lombok. Necessary for deserialization.
    @Getter
    @NoArgsConstructor
    @Schema(description = "Request DTO for creating a new Todo")
    public static class Add {
        private String title;
        private String description;
    }

    @Getter
    @NoArgsConstructor
    @Schema(description = "Request DTO for editing an existing Todo")
    public static class Edit {
        private Long id;
        private String title;
        private String description;
    }
}