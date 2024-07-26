package com.Bank.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bank.dto.AccountDto;
import com.Bank.service.AccountService;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	private AccountService accountService;

	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}
	
	//add account rest api
	@PostMapping("/")
	public ResponseEntity<AccountDto> addaccount(@RequestBody AccountDto accountdto)
	{
		return new ResponseEntity<>(accountService.createaccount(accountdto),HttpStatus.CREATED);
	}
	
	//Get Account rest api for single id
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getaccountbyid(@PathVariable Long id)
	{
		AccountDto accountdto=accountService.getAccountById(id);
		
		return ResponseEntity.ok(accountdto);
	}
	
	//Deposite Rest API
	
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id ,@RequestBody Map <String,Double> request)
	{
		Double amount=request.get("amount");
		AccountDto accountDto=accountService.deposit(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	//withdraw Rest API
	
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String, Double> request)
	{
		Double amount=request.get("amount");
		AccountDto accountDto=accountService.withdraw(id,amount);
		return ResponseEntity.ok(accountDto);
	}
	
	//get all accounts details show
	@GetMapping("/allaccounts")
	public ResponseEntity<List<AccountDto>> Accountalldetails()
	{
		List<AccountDto> account=accountService.getAccountalldetails();
		return ResponseEntity.ok(account);
	}
	
	//get account delete from single data
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteaccountallbyid(@PathVariable Long id)
	{
		accountService.getdeleteByID(id);
		return ResponseEntity.ok("Account Deleted");
	}
	
	//delete from all data delete
	
	@DeleteMapping("/alldatadelete")
	public ResponseEntity<String> deleteallAcoounts()
	{
		accountService.getdeletealldetails();
		return ResponseEntity.ok("All deleted successfully");
	}
}
