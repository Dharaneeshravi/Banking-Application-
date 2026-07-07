package com.dharaneesh.loans.service;

import com.dharaneesh.loans.dto.LoansDto;

public interface ILoansService {

    /**
     *
     * @param mobileNumber
     */
    void createLoans(String mobileNumber);

    /**
     *
     * @param mobileNumber
     * @return
     */
    LoansDto fetchLoans(String mobileNumber);

    /**
     *
     * @param loansDto
     * @return
     */
    boolean updateLoans(LoansDto loansDto);

    /**
     *
     * @param mobileNumber
     * @return
     */
    boolean deleteLoans(String mobileNumber);
}
