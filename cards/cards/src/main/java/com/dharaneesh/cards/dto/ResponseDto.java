package com.dharaneesh.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
@Schema(
        name = "ResponseDto",
        description = "Data Transfer Object for Response"
)
public class ResponseDto {

    @Schema(
            description = "Status code for the response",
            example = "201"
    )
    private String statusCode;


    @Schema(
            description = "Status message for the response",
            example = "Card created successfully"
    )
    private String statusMessage;
}
