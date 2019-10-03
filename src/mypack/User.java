package mypack;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User
{
	
	
	private String firstName;
	private String lastName;
	private String uuid;
	private byte pinHash[];
	
	
	public String getFirstName()
	{
		return this.firstName;
	}
	
	private ArrayList<Account> accounts;
	           
	public User(String firstName,String lastName,String pin,Bank theBank)
	{
		
		this.firstName=firstName;
		this.lastName=lastName;
		
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			this.pinHash=md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			System.err.print("no such algo:"+e);
			e.printStackTrace();
			System.exit(1);
		}
		
		
		this.uuid=theBank.getNewUserUUID();
		
		this.accounts = new ArrayList<Account>();
		
		System.out.printf(":new user %s,%s with Id %s created. \n",firstName,lastName,this.uuid);
		
	}
	
	
	public void addAccount(Account anAccount)
	{
		this.accounts.add(anAccount);
	}
	
	
	public String getUUID()
	{
		return this.uuid;
	}
	
	public boolean validatePin(String apin)
	{
		
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			return MessageDigest.isEqual(md.digest(apin.getBytes()),this.pinHash);
		} catch (NoSuchAlgorithmException e) {
			System.err.print("no such algo:"+e);
			e.printStackTrace();
			System.exit(1);
		}
		return false;
		
 	
	}


	public void printAccountsSummary()
	{
		System.out.printf("\n\n%s 's account summary",this.firstName);
		for(int a=0;a<accounts.size();a++)
		{
			System.out.printf("%d) %s\n",a+1,this.accounts.get(a).getSummaryLine());
		}
		
		System.out.println();
		
	}


	public int numAccount()
	{	
		return this.accounts.size();
	}


	public void printAccTransHistory(int theAcctIDx)
	{
		 this.accounts.get(theAcctIDx).printTransHistory();
		
	}


	public double getAcctBalance(int acctIndx) 
	{
		
		return this.accounts.get(acctIndx).getBalance();
	}


	public String getAcctUUID(int acctIndx)
	{
		return this.accounts.get(acctIndx).getUUID();
	}


	public void addAcctTransaction(int acctIndx , double amount, String memo)
	{
		this.accounts.get(acctIndx).addTransaction(amount,memo);
		
	}
	

}
