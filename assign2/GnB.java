/*
TO COMPILE:
javac GnB.java

TO RUN:
java GnB <no_of_requests>

eg:
java GnB 1000
here 1000 is the number of requests





*/

import java.util.*; 
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random; 


//user defined structure to help store customers in the linkedlist 
class User extends GnB
{
	public long acc_no;
	public int cash;

	//lock defined for each user so only one thread can work at a time on one user while changing balance
	ReentrantLock lock;
	public User(long acc_no,int cash)
	{
		this.acc_no=acc_no;
		this.cash=cash;
		this.lock=new ReentrantLock();
	}

	//helps check whether the corresponding linked list is locked when we are changing the cash amount of a user
	void check_list_lock(int branch) 
	{
		switch(branch)
		{
			case 0:
				while(Listlock0.isLocked())
				{

				}
				return;
			case 1:
				while(Listlock1.isLocked())
				{
					
				}
				return;
			case 2:
				while(Listlock2.isLocked())
				{
					
				}
				return;
			case 3:
				while(Listlock3.isLocked())
				{
					
				}
				return;
			case 4:
				while(Listlock4.isLocked())
				{
					
				}
				return;
			case 5:
				while(Listlock5.isLocked())
				{
					
				}
				return;
			case 6:
				while(Listlock6.isLocked())
				{
					
				}
				return;
			case 7:
				while(Listlock7.isLocked())
				{
					
				}
				return;
			case 8:
				while(Listlock8.isLocked())
				{
					
				}
				return;
			case 9:
				while(Listlock9.isLocked())
				{
					
				}
				return;										
		}

	}

	//Utility function to set the amount of the user to a new value after deposit or withdrawal
	void setBalance(int amount)
	{
		try
		{
			int branch=(int)(acc_no/M);
			check_list_lock(branch);
			this.lock.lock();
			this.cash=amount;
		}
		finally
		{
			this.lock.unlock();
		}
	}


}



//runnable class that is used to solve all types of requests that are sent for the BANK.
/*
this class has four inputs in the constructor. the inputs that are not required in a certain request are ignored

INPUTS- int request,long source,long destination,int cash

Request index is as follows:

0- Deposit money
1- Withdraw money
2- Trasfer Money
3- Add a customer
4- Delete a customer given account number 
5- trasfer a customer from one branch to another 

source is the accno used mainly 
destination is only required for transfer type operations


In operations 0,1,2 fine grained locks of the users are acquired and released as required in the setbalance function

in operations 3,4,5 the corresponding linkedlist from the array of 10 have been locked while in use for addition or removal of a customer

As we have used Linked List for each operation searching the corresponding acc_no in the list takes linear time. This has been slightly optimised 
by using break statements wherever possible.( The list stores acc_nos in an increasing order when generated, so we can employ break statements using this)


*/
class Updater extends GnB implements Runnable 
{
	int request;
	long source;
	long destination;
	int cash;

	public Updater(int request,long source,long destination,int cash)
	{
		this.request=request;
		this.source=source;
		this.cash=cash;
		this.destination=destination;
	}
	
	void acquire_list_lock(int branch)
	{
		switch(branch)
		{
			case 0:
				Listlock0.lock();
				break;
			case 1:
				Listlock1.lock();
				break;
			case 2:
				Listlock2.lock();
				break;
			case 3:
				Listlock3.lock();
				break;
			case 4:
				Listlock4.lock();
				break;
			case 5:
				Listlock5.lock();
				break;
			case 6:
				Listlock6.lock();
				break;
			case 7:
				Listlock7.lock();
				break;
			case 8:
				Listlock8.lock();
				break;
			case 9:
				Listlock9.lock();
				break;										
		}
	}

	void releas_list_lock(int branch)
	{
		switch(branch)
		{
			case 0:
				Listlock0.unlock();
				break;
			case 1:
				Listlock1.unlock();
				break;
			case 2:
				Listlock2.unlock();
				break;
			case 3:
				Listlock3.unlock();
				break;
			case 4:
				Listlock4.unlock();
				break;
			case 5:
				Listlock5.unlock();
				break;
			case 6:
				Listlock6.unlock();
				break;
			case 7:
				Listlock7.unlock();
				break;
			case 8:
				Listlock8.unlock();
				break;
			case 9:
				Listlock9.unlock();
				break;										
		}

	}
	public void run()
	{
		try
		{

		if(request==0) //DEPOSIT
		{
			int branch=(int)(source/M);
			int f=0;
			for (int i = 0; i < Bank_branch[branch].size(); i++) 
			{ 

            	User llElement = Bank_branch[branch].get(i); 
            	if(llElement.acc_no==source)
            	{
            		f=1;
            		int newamount= llElement.cash+cash;
            		Bank_branch[branch].get(i).setBalance(newamount);

            		break;
            	}
            	if(llElement.acc_no > source) 
	            	{
	            		break;
	            	}

            }
            if(f==0)
            {
            	System.out.println("User accno not found - "+source);

            }
            else
            {
            	System.out.println("R0 User accno and deposited cash: - "+source + " "+cash);

            }
            return;

		}
		else if(request==1) //WIHTRDRAWAL
		{
			int branch=(int)(source/M);
			int f=0;
			for (int i = 0; i < Bank_branch[branch].size(); i++) 
			{ 
            	User llElement = Bank_branch[branch].get(i); 
            	if(llElement.acc_no==source)
            	{
            		f=1;
            		int newamount= llElement.cash-cash;
            		if(newamount<0)
            		{
            			System.out.println("R1 withdrawal more than existing cash.error in account "+llElement.acc_no);
            			return;
            		}
            		else llElement.setBalance(newamount);
            		break;
            	}
            	if(llElement.acc_no > source) 
	            	{
	            		break;
	            	}

            }
            if(f==0)
            {
            	System.out.println("R1 User accno not found - "+source);

            }
            else
            {
            	System.out.println("R1 User accno and withdrawed cash: - "+source + " "+cash);

            }
            return;

		}
		else if(request==2) //MONEY TRANSFER 
		{
			int sb=(int)(source/M);
			int db=(int)(destination/M);
			int f=0;
			for (int i = 0; i < Bank_branch[sb].size(); i++) 
			{ 
            	User llElement = Bank_branch[sb].get(i); 
            	if(llElement.acc_no==source)
            	{
            		f=1;
            		int newamount= llElement.cash-cash;
            		if(newamount<0)
            		{
            			System.out.println("R2 withdrawal more than existing cash.error in account "+llElement.acc_no);
            			return;
            		}
            		else llElement.setBalance(newamount);
            		break;
            	}
            	if(llElement.acc_no > source) 
	            	{
	            		break;
	            	}

            }
            if(f==0)
            {
            	System.out.println("R2 User accno not found - "+source);
            	return;

            }
            f=0;

            for (int i = 0; i < Bank_branch[db].size(); i++) 
			{ 

            	User llElement = Bank_branch[db].get(i); 
            	if(llElement.acc_no==destination)
            	{
            		f=1;
            		int newamount= llElement.cash+cash;
            		llElement.setBalance(newamount);
            		break;
            	}
            	if(llElement.acc_no > destination) 
	            	{
	            		break;
	            	}

            }
            if(f==0)
            {
            	System.out.println("R2 User accno not found - "+destination);

            	//putting the source money back in the source as this transaction was not valid
            	for (int i = 0; i < Bank_branch[sb].size(); i++) 
				{ 
	            	User llElement = Bank_branch[sb].get(i); 
	            	if(llElement.acc_no==source)
	            	{
	            		f=1;
	            		int newamount= llElement.cash+cash;
	            		llElement.setBalance(newamount);
	            		break;
	            	}
	            	if(llElement.acc_no > source) 
	            	{
	            		break;
	            	}

	            }
            	return;

            }
            else
            {
            	System.out.println("R2 User accno source,destination and deposited cash: - "+source + " "+ destination + " "+cash);

            }
            return;




		}
		else if(request==3) //ADD NEW USER GIVEN ACCOUNT NO AND CASH
		{

			int branch=(int)(source/M);
			User newUser= new User(source,cash);
			try
			{
				acquire_list_lock(branch);
				Bank_branch[branch].add(newUser);
				
			}
			finally
			{
				releas_list_lock(branch);

            	System.out.println("R3 NEW User accno and deposited cash: - "+source + " "+cash);

            
			}
			

		}
		else if(request==4) //FIND AND REMOVE USER GIVEN ACCOUNT NUMBER
		{
			int branch=(int)(source/M);
			
			int index=-1;
			for (int i = 0; i < Bank_branch[branch].size(); i++) 
			{ 
            	User llElement = Bank_branch[branch].get(i); 
            	if(llElement.acc_no==source)
            	{
            		index=i; break;
            	}
            	if(llElement.acc_no > source) 
	            	{
	            		break;
	            	}

            }
            if(index==-1)
            {
        		System.out.println("R4 User accno not found - "+source);
        		return;

            }
            else 
            {
            	acquire_list_lock(branch);
            	Bank_branch[branch].remove(index);
				releas_list_lock(branch);

            	System.out.println("R4 User accno REMOVED: - "+source );


			}
			return;



		}
		else if(request==5) //TRANSFER USER ACCOUNT FROM ONE ACCOUNT NUMBER TO OTHER
		{
			int sb=(int)(source/M);
			int db=(int)(destination/M);
			// try
			// {
			
				int index=-1;
				for (int i = 0; i < Bank_branch[sb].size(); i++) 
				{ 
	            	User llElement = Bank_branch[sb].get(i); 
	            	if(llElement.acc_no==source)
	            	{
	            		index=i; break;
	            	}
	            	if(llElement.acc_no > source) 
	            	{
	            		break;
	            	}

	            }
	            if(index==-1)
	            {
            		System.out.println("R5 User accno not found for tranfer request - "+source);
            		return;

	            }
	            else 
	            {
	            	acquire_list_lock(sb);
	            	cash=Bank_branch[sb].get(index).cash;
	            	Bank_branch[sb].remove(index);
	            	releas_list_lock(sb);

	            	acquire_list_lock(db);
	            	User newUser= new User(destination,cash);
					Bank_branch[db].add(newUser);
					releas_list_lock(db);


            	System.out.println("R5 transfer done to  "+db + " with new account number "+destination);

				}
				return;
			// }
			// finally
			// {
				
				
			// }
			



		}
		}
		catch(NullPointerException e) {
			//System.out.println("NullPointerException thrown!");
		}

	}
}

//this runnable class is used to generate the initial users with random three-digit money in their accounts
// and add it to the correspniding LinkedList of the array Bank_branch[]
class GenUsers extends GnB implements Runnable 
{
	long branch;

	public GenUsers(long branch)
	{
		this.branch=branch;

	}

	public void run()
	{
		for(int i=1;i<=10000;i++)
		{
			long acc_no= branch*(M) + i;
			int cash = ( int )( Math.random() * 9999 );
			if( cash <= 1000 ) {
			    cash = cash + 1000;
			}
			User newUser= new User(acc_no,cash);
			Bank_branch[(int)branch].add(newUser);
		}
	}
}


//the main class that is extended to all classes above for easy acces of global public variables of the class

class GnB
{


	public static LinkedList<User>[] Bank_branch = new LinkedList[10]; //stores all user info
	static long M=10000000000L; //refrence constant 10^10

	//locks defined for each linkedlist in the array
	ReentrantLock Listlock0=new ReentrantLock();
	ReentrantLock Listlock1=new ReentrantLock();
	ReentrantLock Listlock2=new ReentrantLock();
	ReentrantLock Listlock3=new ReentrantLock();
	ReentrantLock Listlock4=new ReentrantLock();
	ReentrantLock Listlock5=new ReentrantLock();
	ReentrantLock Listlock6=new ReentrantLock();
	ReentrantLock Listlock7=new ReentrantLock();
	ReentrantLock Listlock8=new ReentrantLock();
	ReentrantLock Listlock9=new ReentrantLock();

	static int  Last_acc_no[]= new int[10];

	//returns a random integer between 0-9 with equal probabaillty
	public static int random_branch()
    {
    	int min =0; 
    	int max=9; 
        return new Random().nextInt(max - min + 1) + min;
    }


    //as per the probabilities for request type given in the question i.e 0.33,0.33,0.33,0.003,0.003,0.004
    //this function returns a request number from 0-5 used in generation of required number of request 
	 public static int random_request()
	{
		double r=Math.random();
		if(r<=0.33)
		{
			return 0;
		}
		else if(r>0.33 && r<=0.66)
		{
			return 1;
		}
		else if(r>0.66 && r<=0.99)
		{
			return 2;
		}
		else if(r>0.99 && r<=0.993)
		{
			return 3;
		}
		else if(r>0.993 && r<=0.996)
		{
			return 4;
		}
		else 
		{
			return 5;
		}

	}




	//used in start of main() for initialization of LinkedLists of Bank_branch[] to put user info
	//and creation of 10^4 users per branch using the Gen Users class and a threadpool of 10 for convinience
	//we also maintain an array of last acc_no of each branch for future refrences while adding new customers to the branch
	public static void init()
	{
		for (int j=0; j<10; j++)
   			Bank_branch[j]=new LinkedList<User>();

   		ExecutorService executorService = Executors.newFixedThreadPool(10);
   		for(int i=0;i<10;i++)
   		{
   			Runnable worker=new GenUsers(i);
   			executorService.execute(worker);

   		}
   		executorService.shutdown();
   		while (!executorService.isTerminated()) {
		}

		for (int j=0; j<10; j++)
			Last_acc_no[j]= 10000;

	}



	public static void main(String args[])
	{
		//reading no_of_requests form input
		long no_of_requests=Long.parseLong(args[0]);

		init();

		//defining an array of executor services to maintain exactly 10 threads of updaters for each branch 
		//working at a time

		ExecutorService es[] = new ExecutorService[10];

		for(int i=0;i<10;i++)
		{
			es[i] = Executors.newFixedThreadPool(10);
		}

		long starttime=System.currentTimeMillis();

		//requests are generated here usign the utility random functions defined earlier and Last_acc_no stored for each branch
		//finally a runnable object is created and given to the executor service corressponding to the branch 

		for(int i=0;i<no_of_requests;i++)
		{
			int request =random_request();
			
			int cash = ( int )( Math.random() * 999 );
			if( cash <= 100 ) {
			    cash = cash + 100;
			}

			int sb = random_branch();
			int h= new Random().nextInt(1000) + 1;
			long source = sb*M + h;

			int db=random_branch();
			int g= new Random().nextInt(1000) + 1;
			long destination= db*M + g;


			if(request == 3) //new customer = new source acc_no
			{
				source = sb*M + Last_acc_no[sb] + 1;
				Last_acc_no[sb]++;

			}

			if(request==5) //transfer to new branch so new account number for destination
			{
				destination = db*M + Last_acc_no[db] + 1;
				Last_acc_no[db]++;
			}

			Runnable worker = new Updater(request,source,destination,cash);
			es[sb].execute(worker);

		}

		//wait for all threads to finish working 
		for(int i=0;i<10;i++)
		{
			es[i].shutdown();
			while(!es[i].isTerminated())
			{

			}
		}


		long endtime=System.currentTimeMillis();

		System.out.println("DONE");

		System.out.println("time taken (in ms) = " + (endtime-starttime));

		
		


	}
}
