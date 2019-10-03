package mypack;

import java.util.ArrayList;
import java.util.Random;

public class Bank
{
	
	
	private String name;
	private ArrayList<User> users;
	private ArrayList<Account> acccounts;
	
	public String getName()
	{
		return this.name;
	}
	
	
	public Bank(String name)
	{
		this.name = name;
		this.users = new ArrayList<User>();
				this.acccounts=new ArrayList<Account>();
	}
	
	
	public String getNewUserUUID()
	{
		
		String uuid;
		int len = 6;
		Random rand = new Random();
		
		boolean nonUnique = false;
		
		do {
			
			
		uuid= "";
		for(int c= 0;c<len;c++)
		{
			uuid += ((Integer)rand.nextInt(10)).toString();
		}

	    nonUnique = false;

	    for(User u:this.users)
		{
			if(uuid.compareTo(u.getUUID())==0)
			{
				nonUnique = true;
				break;
			}
		}
	    

		}while(nonUnique);
		
        return uuid;		
	}
	
	
	public String getNewAccountUUID()
	{
		
		String uuid;
		int len =10;
		Random rand = new Random();

		boolean nonUnique = false;

		do {

			uuid = "";
			for (int c = 0; c < len; c++) {
				uuid += ((Integer) rand.nextInt(10)).toString();
			}

			nonUnique = false;

			for (Account acc : this.acccounts) {
				if (uuid.compareTo(acc.getUUID()) == 0) {
					nonUnique = true;
					break;
				}
			}

		} while (nonUnique);

		return uuid;		
		
		
	}
	
	
	  public void addAccount(Account anAccount) { this.acccounts.add(anAccount); }
	  
	 
	public User addUser(String firstName,String lastName,String pin)
	{
		
		User newUser = new User(firstName, lastName, pin, this);
		this.users.add(newUser);
		
		Account newAccount =new Account("Saving", newUser, this);
		newUser.addAccount(newAccount);
		this.addAccount(newAccount);

		return newUser;
		
	}
	
	public User userLogin(String uuid,String pin)
	{
		for(User user:this.users)
		{
			if(user.getUUID().compareTo(uuid) == 0 && user.validatePin(pin))
			{
				return user;
			}
				
		}
		return null;
	
	}
	
	
	
}
