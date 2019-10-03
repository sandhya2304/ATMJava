package mypack;

import java.util.ArrayList;

public class Account
{
	
	
	private String name;
	
	private String uuid;
	
	private User holder;
	
	private ArrayList<Transaction> transactions;
	
	
	public Account(String name,User userHolder,Bank theBank)
	{
		this.name = name;
		this.holder = userHolder;
		
		this.uuid = theBank.getNewAccountUUID();
		
		this.transactions = new ArrayList<Transaction>();
		
		
		//holder.addAccount(this);
		// theBank.addAccount(this);
		
	}
	
	public String getUUID()
	{
		return this.getUUID();
	}

	public String getSummaryLine()
	{
		double balance = this.getBalance();
		
		if(balance >=0)
		{
			return String.format("%s : $%0.2f : %s", this.uuid,balance,this.name);
		}else
		{
			return String.format("%s : $(%0.2f) : %s", this.uuid,balance,this.name);
		}
		
		
	}

	public double getBalance()
	{
		double balance =0;
		for(Transaction t : this.transactions)
		{
			balance += t.getAmount();
		}
		
		return balance;
	}

	public void printTransHistory()
	{
		System.out.printf("\n transaction history of account %s\n",this.uuid);
		for(int t= this.transactions.size()-1;t>=0;t--)
		{
			System.out.println(this.transactions.get(t).getSummaryLine());
		}
		
		System.out.println();
	}

	public void addTransaction(double amount, String memo)
	{
		Transaction trans = new Transaction(amount, memo,this);
		this.transactions.add(trans);
	}
	
	
	

}
