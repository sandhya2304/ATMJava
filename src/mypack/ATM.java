package mypack;

import java.util.Scanner;

public class ATM {

	public static void main(String[] args) 
	{
		
		Scanner sc = new Scanner(System.in);
		
		
		Bank thebank= new Bank("State bank");
		
		
		User userHolder = new User("ram","sharma","1234",thebank);
		
		Account newAccount = new Account("Checking", userHolder, thebank);
		
        userHolder.addAccount(newAccount);
        thebank.addAccount(newAccount);
        
        
        User curUser;

        while(true)
        {
        	curUser = ATM.mainMenuPrompt(thebank,sc);
        	ATM.printUserMenu(curUser,sc);
        	
        	
        }
        
        
	}


	public static User mainMenuPrompt(Bank thebank, Scanner sc)
	{
		
		String userId;
		String pin;
		User authUser;
		
		do
		{
			System.out.printf("Welcome %s\n\n",thebank.getName());
			System.out.println("Enter the user id:");
			userId = sc.nextLine();
			System.out.println("enetr the pin: ");
			pin = sc.nextLine();
			
			authUser = thebank.userLogin(userId, pin);
			if(authUser==null)
			{
				
				System.out.println("Enter the correct userid/pin, Sorry wrong ");
				
			}
				
		}while(authUser==null);
		
		
		return authUser;
	}
	
	public static void printUserMenu(User theUser, Scanner sc)
	{
		theUser.printAccountsSummary();
		
		int choice;
		
		do
		{
			System.out.printf("welcome %s,What would you like to do:",theUser.getFirstName());
			System.out.println(" 1) show account transaction history");
			System.out.println(" 2) withdrawl");
			System.out.println(" 3) deposit");
		
			System.out.println(" 4) transfer");
			System.out.println(" 5) quit");
			System.out.println();
			
			System.out.print("Enter choice");
			choice = sc.nextInt();
			
			if(choice<1 || choice>5)
			{
				
				System.out.println("invlaid choice plz try agaian");
			}
			
		}while(choice<1 || choice>5);
		
		switch(choice)
		{
		
		case 1: ATM.showTransHistory(theUser,sc);
		  break;
		case 2: ATM.withdrawFunds(theUser,sc);
		  break;
		case 3: ATM.depositFuds(theUser,sc);
		  break;
		case 4: ATM.TransferFunds(theUser,sc);
		  break;

		}
    if(choice!=5)
    {
    	ATM.printUserMenu(theUser, sc);
    }
		
		
	}



	public static void withdrawFunds(User theUser, Scanner sc)
	{
		
		
	}


	public static void showTransHistory(User theUser, Scanner sc)
    {
		
    	int theAcct;
    	
    	do
    	{
    		System.out.printf("Enter the number(1-%d) of the account\n"+"whose transaction u want to see"+
    				theUser.numAccount());
    		
    		theAcct = sc.nextInt()-1;
    		if(theAcct < 0 || theAcct >= theUser.numAccount())
    		{
    			System.out.println("invalid account plz try again!!!");
    		}
    		
    	}while(theAcct < 0 || theAcct >= theUser.numAccount());
    	
    	
    	theUser.printAccTransHistory(theAcct);
		
	}


    public static void TransferFunds(User theUser, Scanner sc)
    {
		int fromAcct;
		int toAcct;
		double amount;
		double acctBal;
				
		do
		{
			System.out.printf("Enter the number(1-%d) of the account\n"+"to transafer from",theUser.numAccount());
    				fromAcct = sc.nextInt()-1;
    				if(fromAcct < 0 || fromAcct >= theUser.numAccount())
    				{
    					System.out.println("invalid account plz try again!!!");
    				}
				
		}while(fromAcct < 0 || fromAcct >= theUser.numAccount());
		acctBal = theUser.getAcctBalance(fromAcct);
		
		
		do
		{
			System.out.printf("Enter the number(1-%d) of the account\n"+"to transafer to",theUser.numAccount());
    				toAcct = sc.nextInt()-1;
    				if(toAcct < 0 || toAcct >= theUser.numAccount())
    				{
    					System.out.println("invalid account plz try again!!!");
    				}
				
		}while(toAcct < 0 || toAcct >= theUser.numAccount());
		
		
		do
		{
			System.out.printf("Enter the amoutn to transfer (max $%.02f): $",acctBal);
			amount = sc.nextDouble();
			if(amount < 0)
			{
				System.out.println("amount must be greater than zero");
			}else if(amount > acctBal)
			{
				System.out.printf("amount must not greater than account balance %.02f . \n",acctBal);
			}
			
			
		}while(amount < 0 || amount > acctBal);
		
		theUser.addAcctTransaction(fromAcct, -1 * amount ,String.format("transfer to acct %s",theUser.getAcctUUID(toAcct)));
		theUser.addAcctTransaction(toAcct, -1 * amount ,String.format("transfer to acct %s",theUser.getAcctUUID(fromAcct)));
	}

	public static void depositFuds(User theUser, Scanner sc) 
	{
		
		int toAcct;
		
		double amount;
		double acctBal;
		String memo;
				
		do
		{
			System.out.printf("Enter the number(1-%d) of the account\n"+"to transafer from",theUser.numAccount());
			toAcct = sc.nextInt()-1;
    				if(toAcct < 0 || toAcct >= theUser.numAccount())
    				{
    					System.out.println("invalid account plz try again!!!");
    				}
				
		}while(toAcct < 0 || toAcct >= theUser.numAccount());
		acctBal = theUser.getAcctBalance(toAcct);
		

		do
		{
			System.out.printf("Enter the amoutn to deposit (max $%.02f): $",acctBal);
			amount = sc.nextDouble();
			if(amount < 0)
			{
				System.out.println("amount must be greater than zero");
			}
			
			
		}while(amount < 0);
		
		sc.nextLine();
		
		System.out.println("Enter the memo:");
		memo = sc.nextLine();
		
		theUser.addAcctTransaction(toAcct,amount, memo);
		
	}



}
