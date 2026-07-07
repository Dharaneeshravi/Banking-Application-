package com.dharaneesh.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "Loans",
        description = "Schema to hold Loan information"
)
@Data @AllArgsConstructor @NoArgsConstructor
public class LoansDto {

    @Schema(
            description = "Mobile Number of Customer",
            example = "4365327698"
    )
    @NotEmpty(message = "Mobile number cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private String mobileNumber;


    @Schema(
            description = "Loan Number of the customer",
            example = "548732457654"
    )
    @NotEmpty(message = "Loan number cannot be empty")
    @Pattern(regexp = "^[0-9]{12}$", message = "Loan number must be 12 digits")
    private String loanNumber;


    @Schema(
            description = "Type of the loan",
            example = "Home Loan"
    )
    @NotEmpty(message = "Loan type cannot be empty")
    private String loanType;


    @Schema(
            description = "Total loan amount",
            example = "100000"
    )
    @Positive(message = "Total loan must be a positive number")
    private Integer totalLoan;

    @Schema(
            description = "Amount paid towards the loan",
            example = "25000"
    )
    @PositiveOrZero(message = "Amount paid must be zero or a positive number")
    private Integer amountPaid;

    @Schema(
            description = "Total outstanding amount",
            example = "75000"
    )
    @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
    private Integer outstandingAmount;
}
