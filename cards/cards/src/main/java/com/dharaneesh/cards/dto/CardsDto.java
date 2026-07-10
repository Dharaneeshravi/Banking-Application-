package com.dharaneesh.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(
        name = "CardsDto",
        description = "Data Transfer Object for Card details"
)
public class CardsDto {

    @Schema(
            description = "Mobile number associated with the card",
            example = "9876543210"
    )
    @NotEmpty(message = "Mobile number cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private String mobileNumber;


    @Schema(
            description = "Card number associated with the card",
            example = "123456789012"
    )
    @NotEmpty(message = "Card number cannot be empty")
    @Pattern(regexp = "^[0-9]{12}$", message = "Card number must be 12 digits")
    private String cardNumber;


    @Schema(
            description = "Type of the card",
            example = "Credit"
    )
    @NotEmpty(message = "Card type cannot be empty")
    private String cardType;


    @Schema(
            description = "Total limit for the card",
            example = "50000"
    )
    @Positive(message = "Total limit must be a positive number")
    private Integer totalLimit;


    @Schema(
            description = "Amount used for the card",
            example = "25000"
    )
    @PositiveOrZero(message = "Amount used must be zero or a positive number")
    private Integer amountUsed;


    @Schema(
            description = "Available amount for the card",
            example = "25000"
    )
    @PositiveOrZero(message = "Available amount must be zero or a positive number")
    private Integer availableAmount;


}
