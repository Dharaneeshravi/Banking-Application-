package com.dharaneesh.accounts.service;

import com.dharaneesh.accounts.constants.AccountsConstants;
import com.dharaneesh.accounts.dto.AccountsDto;
import com.dharaneesh.accounts.dto.CustomerDto;
import com.dharaneesh.accounts.entity.Accounts;
import com.dharaneesh.accounts.entity.Customer;
import com.dharaneesh.accounts.exception.CustomerAlreadyExistsException;
import com.dharaneesh.accounts.exception.ResourceNotFoundException;
import com.dharaneesh.accounts.mapper.AccountsMapper;
import com.dharaneesh.accounts.mapper.CustomerMapper;
import com.dharaneesh.accounts.repository.AccountsRepository;
import com.dharaneesh.accounts.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     *
     * @param customerDto
     */
    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer=CustomerMapper.mapCustomerDtoToCustomer(customerDto,new Customer());
        Optional<Customer> optionalCustomer=customerRepository.findByMobileNumber(customer.getMobileNumber());
        if(optionalCustomer.isPresent())
        {
            throw new CustomerAlreadyExistsException("Customer already registered with mobile number "
                    +customer.getMobileNumber());
        }
        customer.setCreateAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer=customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    /**
     *
     * @param mobileNumber
     * @return
     */

    @Override
    public CustomerDto fetchAccounts(String mobileNumber) {

        Customer customer=customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()->new ResourceNotFoundException("Customer","MobleNumber",mobileNumber.toString()));

        Accounts accounts=accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(()->new ResourceNotFoundException("Account","CustomerId",customer.getCustomerId().toString()));

        CustomerDto customerDto=CustomerMapper.mapCustomerToCustomerDto(customer,new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountDto(accounts,new AccountsDto()));
        return customerDto;

    }

    /**
     *
     * @param customerDto
     * @return
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {

        boolean isUpdated=false;

        AccountsDto accountsDto=customerDto.getAccountsDto();

        if(accountsDto!=null)
        {
            Accounts accounts=accountsRepository.findById(accountsDto.getAccountNumber())
                    .orElseThrow(()->new ResourceNotFoundException("Account","AccountNumber",accountsDto.getAccountNumber().toString()));

            AccountsMapper.mapToAccounts(accountsDto,accounts);
            accounts=accountsRepository.save(accounts);

            Long customerId=accounts.getCustomerId();

            Customer customer=customerRepository.findById(customerId)
                    .orElseThrow(()->new ResourceNotFoundException("Customer","CustomerId",customerId.toString()));
            CustomerMapper.mapCustomerDtoToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated=true;
        }
        return isUpdated;
    }

    /**
     *
     * @param mobileNumber
     * @return
     */

    @Override
    public boolean deleteAccount(String mobileNumber) {

        Customer customer=customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()->new ResourceNotFoundException("Customer","MobileNumber",mobileNumber.toString()));
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;

    }

    /**
     *
     * @param customer
     * @return
     */
    private Accounts createNewAccount(Customer  customer)
    {
        Accounts accounts=new Accounts();
        Long accountNumber=1000000000L+ new Random().nextInt(999999999);
        accounts.setAccountNumber(accountNumber);
        accounts.setCustomerId(customer.getCustomerId());
        accounts.setAccountType(AccountsConstants.SAVINGS);
        accounts.setBranchAddress(AccountsConstants.ADDRESS);
        accounts.setCreatedBy("Anonymous");
        accounts.setCreateAt(LocalDateTime.now());

        return accounts;
    }
}
