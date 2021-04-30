import java.util.Scanner; 
import java.lang.Integer;
import java.util.concurrent.Callable; 
import java.util.concurrent.FutureTask; 


//the following function calculates the value of the sum of the required series as per the simpsons formula
// starting from the "start"th point from -1 for the given number fo divisions
class Simspson implements Callable
{
	public int tpoints;
  int start;
  
  double n=2;
  double d=1000001;
  double delx=n/d;

	public Simspson(int start,int tpoints)
	{
    this.start=start;
		this.tpoints=tpoints;
   
	}

  //utility function to calculate the value of the function at a point x
  public double f(double x)
  {
    double a=-1;
    double p=(a*x*x)/2;
    double fx=(Math.exp(p))/(Math.sqrt(2*Math.PI));
    return fx;
  }


	public Double call() throws Exception
	{
      double sum=0;
		  for(int i=start;i<start+tpoints;i++)
	    {
        double a=-1;
        double x= a + (double)i*delx; //calculating the ith point from -1 using delx
        double fx=f(x);

        //as per the series requirements we either multiply f(x) by 4 or 2 depending on 
        // even or odd position from the beginning
        if(i%2==0)
        {
          fx=fx*(2);
        }
        else
        {
          fx=fx*4;
        }

        sum+=fx;
	      
	    }

      //returning the sum for this interval
      return sum;
	}
}


public class PartB
{
   public static double f(double x)
  {
    double p=(-1*x*x)/2;
    double fx=(Math.exp(p))/(Math.sqrt(2*Math.PI));
    return fx;
  }
	public static void main(String[] args) throws Exception 
  	{ 
  
    Scanner sc= new Scanner(System.in);      
  	System.out.print("Enter no of threads ");    
  	int no_of_threads= sc.nextInt();  

    //array to hold the threads created : is FutureTask to handle the values returned through callable threads
  	FutureTask[] arrayofThreads = new FutureTask[no_of_threads]; 
    int total=1000001; // no of divisions on the scale to apply simpsons rule
    long starttime=System.currentTimeMillis();
    int start=1;

    //in the following loop we divide the total task between the threads almost equally 
    // so they can work concurrently using the callable interface
    for (int i = 0; i < no_of_threads; i++) 
    { 
      int tPoints=total/no_of_threads;
      if(i==0)
      {
        tPoints+=(total%no_of_threads); //for when the total isnt divisible by number of threads
      }
      Callable callable = new Simspson(start,tPoints); 
      arrayofThreads[i] = new FutureTask(callable); 
      Thread t = new Thread(arrayofThreads[i]); 
      t.start(); 
      start+=tPoints;
    } 
    double tsum=0;

    // this loop ensures that the threads finish their tasks and the values returned are added 
    // to tsum to calculate the numerator series 
    for (int i = 0; i < no_of_threads; i++) 
    { 
      tsum += (Double)arrayofThreads[i].get(); 
    } 

    //adding the first and last terms of the series with coefficient 1
    tsum+=f(1);
    tsum+=f(-1);

    double delx=2/(double)total; //delta x for total divisions between -1 and 1
    double three=3;
    double val= delx*tsum/three; 

    long endtime=System.currentTimeMillis();
    System.out.print("The value of the integral is ");
    System.out.println(val);
    System.out.println("time taken (in ms) = " + (endtime-starttime));
    
  }
}