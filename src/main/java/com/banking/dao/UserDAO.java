package com.banking.dao;

import java.util.List;

import com.banking.exception.InsufficientBalanceException;
import com.banking.exception.NegativeAmountException;
import com.banking.model.User;


public interface UserDAO {
	
	public boolean addAccount(User user);
	public boolean deleteAccount(int accountNumber);
	public boolean updateAccount(User user);
	public User validateAccount(int accountNumber,String password);
	public User viewUserAccount(int accountNumber);
	public int withdrawAmount(int accountNumber , int amount) throws InsufficientBalanceException;
	public int depositAmount(int accountNumber , int amount) throws NegativeAmountException;
	public int viewBalance(int accountNumber);
	public void transferAmount(int senderAccountNumber , int reciverAccountNumber , int amount)throws InsufficientBalanceException ;
	public boolean isUserExists(int accountNUmber);
}
