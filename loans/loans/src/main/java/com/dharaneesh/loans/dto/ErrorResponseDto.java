package com.dharaneesh.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Schema(
        name = "ErrorResponse",
        description = "Schema to hold Error Response information"
)
@Data @AllArgsConstructor
public class ErrorResponseDto {

    @Schema(
            description = "Path of the endpoint where the error occurred"
    )
    private String errorPath;
    @Schema(
            description = "HTTP status code of the error"
    )
    private HttpStatus errorCode;
    @Schema(
            description = "Description of the error"
    )
    private String errorMessage;
    @Schema(
            description = "Timestamp of when the error occurred"
    )
    private LocalDateTime errorTime;
}
