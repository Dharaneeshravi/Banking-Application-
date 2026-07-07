package com.dharaneesh.loans.controller;

import com.dharaneesh.loans.constants.LoansConstants;
import com.dharaneesh.loans.dto.ErrorResponseDto;
import com.dharaneesh.loans.dto.LoansDto;
import com.dharaneesh.loans.dto.ResponseDto;
import com.dharaneesh.loans.service.ILoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
@Tag(
        name = "CRUD REST APIs for Loans in IOB Bank",
        description = "CRUD REST APIs in IOB Bank to CREATE, UPDATE, FETCH AND DELETE loan details"
)
public class LoansController {

    private ILoansService loansService;

    @Operation(
            summary = "Create Loan API",
            description = "Create Loan API is used to create loan details for a customer in IOB Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Loan created successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam
                                                      @Pattern(regexp = "^[0-9]{10}$") String mobileNumber)
    {
        loansService.createLoans(mobileNumber);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto(LoansConstants.STATUS_201,LoansConstants.MESSAGE_201)
        );
    }

    @Operation(
            summary = "Fetch Loan Details API",
            description = "Fetch Loan Details API is used to retrieve loan details for a customer in IOB Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchLoansDetails(@RequestParam @Pattern(regexp = "^[0-9]{10}$") String mobileNumber)
    {
        LoansDto loansDto = loansService.fetchLoans(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }

    @Operation(
            summary = "Update Loan Details REST API",
            description = "Update Loan Details REST API is used to update loan details for a customer in IOB Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoansDetails(@Valid @RequestBody LoansDto loansDto)
    {
        boolean isUpdated=loansService.updateLoans(loansDto);

        if(isUpdated)
        {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto(LoansConstants.STATUS_200,LoansConstants.MESSAGE_200)
            );
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    new ResponseDto(LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_UPDATE)
            );
        }

    }


    @Operation(
            summary = "Delete Loan Details REST API",
            description = "REST API to delete Loan details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoansDetails(@RequestParam
                                                              @Pattern(regexp = "^[0-9]{10}$") String mobileNumber)
    {
        boolean isDeleted=loansService.deleteLoans(mobileNumber);

        if(isDeleted)
        {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto(LoansConstants.STATUS_200,LoansConstants.MESSAGE_200)
            );
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    new ResponseDto(LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_DELETE)
            );
        }

    }
}
