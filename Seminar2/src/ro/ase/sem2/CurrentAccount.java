package ro.ase.sem2;

import ro.ase.sem2.exceptii.IllegalTransferException;
import ro.ase.sem2.exceptii.InsufficientFundsException;

public class CurrentAccount extends BankAccount{
	
	//majuscule= acest atribut este static
	//static=nu apartin unei instante ci apartin clasei in sine
	public static double MAX_CREDIT=5000;

	//super se refera la instanta parintelui
	public CurrentAccount() {
		super();
		
	}

	//puteam sa avem un atribvut de sus maxcredit daca nu era static
	//folosim this pentru alte variabile
	public CurrentAccount(double balance, String iban) {
		super(balance, iban);
	}

	//overload= mai multe functii cu nr de param diferit sau tipul pentru o metoda
	@Override
	public void Deposit(double amount) {
		this.setBalance(getBalance()+amount);
		
	}

	@Override
	public void Withdraw(double amount) throws InsufficientFundsException {
		if(this.getBalance()>=amount)
		{
		this.setBalance(getBalance()-amount);
		}
		else
		{

			throw new InsufficientFundsException("Fonduri insuficiente");

		}
		
	}

	@Override
	public void Transfer(double amount, Account destination) throws IllegalTransferException, InsufficientFundsException {
	if(((BankAccount)destination).iban.equals(this.iban))
	{
		throw new IllegalTransferException("conturile coincid");
	}
	else
	{
		this.Withdraw(amount);
		destination.Deposit(amount);
		
	}
		
}

	
	

	
	

}