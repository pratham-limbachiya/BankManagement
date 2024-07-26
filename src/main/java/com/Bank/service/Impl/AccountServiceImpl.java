package com.Bank.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bank.dto.AccountDto;
import com.Bank.entity.Account;
import com.Bank.mapper.AccountMapper;
import com.Bank.repository.AccountRepository;
import com.Bank.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private  AccountRepository  accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}


	//this method is override by Accountservice class
	@Override
	//this is create api for post method
	public AccountDto createaccount(AccountDto accountDto) {
		
		Account account=AccountMapper. mapToAccount(accountDto);
		
		Account savedAccount=accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
		
	}


	//this method for fetch single data by id 
	@Override
	public AccountDto getAccountById(Long id) {
	
		Account account=accountRepository
				.findById(id)
				.orElseThrow(()->new RuntimeException("Account does not exists"));
		
		return AccountMapper.mapToAccountDto(account);
	}


	//this api is update the data in this method we are create a deposit add in the current balance	
	//this two method is same deposit and withdraw because using  we are using optional method or get method
	@Override
	public AccountDto deposit(Long id, Double Amount) {
		
		Account account=accountRepository
				.findById(id)	
				.orElseThrow(()->new RuntimeException("Account does not exists"));
		
		double Total=account.getBalance()+Amount;
		account.setBalance(Total);
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
		
	}

	//this method is updata data by withdraw the amount in the current balance.

	@Override
	public AccountDto withdraw(Long id, Double amount) {
		
		Optional<Account> account=accountRepository.findById(id);
				
		if(account.isPresent())
		{
			Account account1=account.get();
			
			if(account1.getBalance()<amount)
			{
				System.out.println("Insufficient Balance");
			}
			else
			{
					double Total=account1.getBalance()-amount;
					
					account1.setBalance(Total);
					
					Account Updatedaccount=accountRepository.save(account1);
					
					return AccountMapper.mapToAccountDto(Updatedaccount);
					
			}
		}
		else
		{
			System.out.println("Account Not found");
		}
		return null;
	}

	//this is method is create by all data show by the user.
	@Override
	public List<AccountDto> getAccountalldetails() {
		
		List<Account> account= accountRepository.findAll();
		return account.stream().map((accounts) -> AccountMapper.mapToAccountDto(accounts)).collect(Collectors.toList());

	}


	//this method is show by delete by the single data by fetching in the id.
	@Override
	public void getdeleteByID(Long id) {
		
		Account accounts=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));
		accountRepository.deleteById(id);
	}


	//this method is show by all data deleted.
	@Override
	public void getdeletealldetails() 
	{
		
		List<Account> account=accountRepository.findAll();
		accountRepository.deleteAll(account);
	}

}
