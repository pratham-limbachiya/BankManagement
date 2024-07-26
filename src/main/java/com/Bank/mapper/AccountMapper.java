package com.Bank.mapper;  //this is mapping for dto and entity package

import java.util.List;

import com.Bank.dto.AccountDto;
import com.Bank.entity.Account;

public class AccountMapper {
	
	public static Account mapToAccount(AccountDto accountDto)
	{
		Account account=new Account(accountDto.getId()
				,accountDto.getAccountHolderName()
				,accountDto.getBalance());
		
		return account;
	}
	
	public static AccountDto mapToAccountDto(Account account)
	{
		AccountDto accountdto=new AccountDto(
				account.getId(),
				account.getAccountHolderName(),
				account.getBalance()
				);
		
		return accountdto;
				
	}
	

}
