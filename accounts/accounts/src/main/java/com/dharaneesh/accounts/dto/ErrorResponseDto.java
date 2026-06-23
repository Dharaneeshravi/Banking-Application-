package com.dharaneesh.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Data @AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to Hold error response information"
)
public class ErrorResponseDto {

    @Schema(
            description = "API path invoked  by client"
    )
    private String apiPath;
    @Schema(
            description = "Error code responding the error happened"
    )
    private HttpStatus errorCode;
    @Schema(
            description = "Error Message responding the error happened"
    )
    private String errorMessage;
    @Schema(
            description = "Error Time responding the error happened"
    )
    private LocalDateTime errorTime;
}
