package com.todoapi.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

// Use "abstract" to prevent this class from being generated as an instance.
public abstract class RequestDto {

    // Use "private" so that external environment cannot call new RequestDto().
    private RequestDto() {}

    // Define each DTOs for each requests as public static classes.
    // @Getter: Generates getter methods automatically by Lombok
    // @NoArgsConstructor: Generates basic generator automatically by Lombok. Necessary for deserialization.
    @Getter
    @NoArgsConstructor
    public static class Add {
        private String title;
        private String description;
    }

    @Getter
    @NoArgsConstructor
    public static class Edit {
        private Long id;
        private String title;
        private String description;
    }

    @Getter
    @NoArgsConstructor
    public static class Toggle {
        private Long id;
    }

    @Getter
    @NoArgsConstructor
    public static class Delete {
        private Long id;
    }
}