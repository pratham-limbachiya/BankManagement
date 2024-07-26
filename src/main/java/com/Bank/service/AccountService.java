package com.Bank.service;

import java.util.List;

import com.Bank.dto.AccountDto;


public interface AccountService {

	
	//this is default method in interfece and this method is override is compulsory 
	
	AccountDto createaccount(AccountDto accountDto);
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposit(Long id,Double Amount);
	
	AccountDto withdraw(Long id,Double amount);
	
	List<AccountDto> getAccountalldetails();
	
	void getdeleteByID(Long id);
	
	void getdeletealldetails();
}
