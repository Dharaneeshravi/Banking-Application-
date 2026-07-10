package com.dharaneesh.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Data @AllArgsConstructor
@Schema(
        name = "ErrorResponseDto",
        description = "Data Transfer Object for Error Response"
)
public class ErrorResponseDto {

    @Schema(
            description = "API path where the error occurred",
            example = "/api/create"
    )
    private String apiPath;


    @Schema(
            description = "HTTP status code for the error",
            example = "400"
    )
    private HttpStatus errorCode;


    @Schema(
            description = "Error message for the response",
            example = "Invalid request body"
    )
    private String errorMessage;


    @Schema(
            description = "Timestamp of the error",
            example = "2023-01-01T12:00:00"
    )
    private LocalDateTime timestamp;
}
