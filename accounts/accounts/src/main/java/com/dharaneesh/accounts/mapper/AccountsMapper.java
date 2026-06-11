package com.dharaneesh.accounts.mapper;

import com.dharaneesh.accounts.dto.AccountsDto;
import com.dharaneesh.accounts.entity.Accounts;

public class AccountsMapper {

    public static Accounts mapToAccounts(AccountsDto accountsDto,Accounts accounts)
    {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }

    public static AccountsDto mapToAccountDto(Accounts accounts,AccountsDto accountsDto)
    {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }
}
