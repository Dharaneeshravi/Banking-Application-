package com.dharaneesh.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Accounts information"
)
public class AccountsDto {

    @Schema(
            description = "Account number of IOB",
            example = "5432198760"
    )
    @NotEmpty(message = "Account number can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Account number must be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account Type of IOB",
            example = "saving"
    )
    @NotEmpty(message = "Account Type can not be null or empty")
    private String accountType;

    @Schema(
            description = "IOB Branch Address"
    )
    @NotEmpty(message = "Branch Address  can not be null or empty")
    private String branchAddress;
}
