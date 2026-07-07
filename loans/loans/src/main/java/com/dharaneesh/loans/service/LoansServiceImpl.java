package com.dharaneesh.loans.service;

import com.dharaneesh.loans.constants.LoansConstants;
import com.dharaneesh.loans.dto.LoansDto;
import com.dharaneesh.loans.exception.LoanAlreadyExistsException;
import com.dharaneesh.loans.exception.ResourcesNotFoundException;
import com.dharaneesh.loans.mapper.LoansMapper;
import com.dharaneesh.loans.model.Loans;
import com.dharaneesh.loans.repository.LoansRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private LoansRepository loansRepository;

    /**
     *
     * @param mobileNumber
     */
    @Override
    public void createLoans(String mobileNumber) {

        Optional<Loans> optionalLoans=loansRepository.findByMobileNumber(mobileNumber);

        if(optionalLoans.isPresent()){

            throw new LoanAlreadyExistsException("Loan already exists for mobile number: " + mobileNumber);
        }

        loansRepository.save(createNewLoans(mobileNumber));

    }

    /**
     *
     * @param mobileNumber
     * @return
     */

    @Override
    public LoansDto fetchLoans(String mobileNumber)
    {
       Loans loans=loansRepository.findByMobileNumber(mobileNumber)
               .orElseThrow(()->new ResourcesNotFoundException("Loans","mobileNumber",mobileNumber));
       return LoansMapper.mapToLoansDto(loans,new LoansDto());
    }

    /**
     *
     * @param loansDto
     * @return
     */
    @Override
    public boolean updateLoans(LoansDto loansDto) {

        Loans loans=loansRepository.findByLoanNumber(loansDto.getLoanNumber())
                .orElseThrow(()->new ResourcesNotFoundException("Loans","loanNumber",loansDto.getLoanNumber()));

        LoansMapper.mapToLoans(loansDto,loans);
        loansRepository.save(loans);
        return true;
    }

    /**
     *
     * @param mobileNumber
     * @return
     */

    @Override
    public boolean deleteLoans(String mobileNumber) {

        Loans loans=loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()->new ResourcesNotFoundException("Loans","mobileNumber",mobileNumber));
        loansRepository.deleteById(loans.getLoanId());
        return true;
    }

    /**
     *
     * @param mobileNumber
     * @return
     */
    private Loans createNewLoans(String mobileNumber) {

        Loans loans = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        loans.setMobileNumber(mobileNumber);
        loans.setLoanNumber(String.valueOf(randomLoanNumber));
        loans.setLoanType(LoansConstants.HOME_LOAN);
        loans.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        loans.setAmountPaid(0);
        loans.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return loans;
    }
}
