import java.util.Scanner; 
import java.util.Random; 
import java.util.concurrent.Callable; 
import java.util.concurrent.FutureTask; 
import java.lang.Integer;

//this class counts the number of random points lying in the circle for a thread and returns it for later use
class PointsinCircle implements Callable
{
	int tPoints;
  //constructor has one formal parameter the number of random points to be tested
	public PointsinCircle(int tPoints)
	{
		this.tPoints=tPoints;
	}
	public Integer call() throws Exception
	{
     int no_in_circle=0;

    for(int i=0;i<tPoints;i++)
    {
      double x = Math.random();
      double y=Math.random();
      if(x*x + y*y <= 1)
      {
        no_in_circle++;
      }
    }
		return no_in_circle;

	}

}

public class PartA
{
	public static void main(String[] args) throws Exception 
  	{ 
  
    Scanner sc= new Scanner(System.in);   
  	System.out.print("Enter no of threads ");  
  	int no_of_threads= sc.nextInt();  

    //array to hold the threads created : is FutureTask to handle the values returned through callable threads
    FutureTask[] arrayofThreads = new FutureTask[no_of_threads]; 
    int total=1000000;
    long starttime=System.currentTimeMillis();

    //in the following loop we divide the total task between the threads almost equally 
    // so they can work concurrently using the callable interface
    for (int i = 0; i < no_of_threads; i++) 
    { 
      int tPoints=total/no_of_threads;
      if(i==0)
      {
        tPoints+=(total%no_of_threads);
      }
      Callable callable = new PointsinCircle(tPoints); 
      arrayofThreads[i] = new FutureTask(callable); 
      Thread t = new Thread(arrayofThreads[i]); 
      t.start(); 
    } 
    int tPoints_in_circle=0;

    // this loop ensures that the threads finish their tasks and the values returned are added 
    // to count the total number of points in the circle
    for (int i = 0; i < no_of_threads; i++) 
    { 
      tPoints_in_circle += (Integer)arrayofThreads[i].get(); 
    } 

    //val of pi calculated using the approximation required
    double valofpi=4*((double)tPoints_in_circle/(double)total);

    long endtime=System.currentTimeMillis();
    System.out.print("The value of pi calculated is ");
    System.out.println(valofpi);
    System.out.println("time taken (in ms) = " + (endtime-starttime));
    
  } 
}