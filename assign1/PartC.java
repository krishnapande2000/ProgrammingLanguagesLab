import java.util.*; 
import java.lang.Integer;
import java.util.concurrent.Callable; 
import java.util.concurrent.FutureTask; 

//this class is to create threads to calculate one element of the product result matrix at a time
//Its initialised through an object and invoked by the start function of thread created
class FindElement implements Runnable
{
  int[][] result;
  int[][] a;
  int[][] b;
  int r;

  public FindElement(int[][] a,int[][] b,int[][] result,int row) //constructor to receive elements
  {
    this.a=a;
    this.b=b;
    this.result=result;
    this.r=row;
  }

  public void run()
  {
     for(int i=0;i<b[0].length;i++)
     {
       result[r][i]=0;
       for(int j=0;j<a[r].length;j++)
       {
         result[r][i] += a[r][j]*b[j][i];
       }
     }
  }
  //changes here are directly reflected in the array of main class as arrays are passed by refrence only

}


//this class helps generate a row of random elements for the matrix passed to it
//it is initialised through the object and invoked by start function of the thread created
class MatrixGen implements Runnable
{
  int[][] a;
  int row;

  public MatrixGen(int[][] a,int row)
  {
    this.a=a;
    this.row=row;
  }
  public void run()
  {
    for(int i=0;i<a.length;i++)
    {
      a[row][i]=(int)(Math.random()*11); //to generate elemnts between 0 to 10
    }
  }

}


public class PartC
{
  //function to aid in display of generated matrices for checking
  public static void printer(int[][] a)
   {
      for(int i=0;i<a.length;i++)
      {
        for(int j=0;j<a[0].length;j++)
        {
          System.out.print(a[i][j] + " ");
        }
        System.out.println();
      }
      System.out.println("*******");
   }

	public static void main(String[] args) throws Exception 
  { 
  
    Scanner sc= new Scanner(System.in);      
  	System.out.print("Enter no of threads ");    
  	int no_of_threads= sc.nextInt();  

    //size of the matrices used: 
    int size = 1000;

  	int[][] a = new int[size][size];
    int[][] b = new int[size][size];
    int[][] result = new int[a.length][b[0].length];

    long starttime=System.currentTimeMillis();

    //For each of the following loops we create a List to hold the threads created and once we reach the limit
    // we wait for the threasd to finish using the join() function that halts execution till the thread completes

    //GENERATING FIRST MATRIX - A using runnable MatrixGen class threads
    List<Thread> thread_list= new ArrayList<>();
    for(int i=0; i < a.length ; i++)
     {
        MatrixGen runnable = new MatrixGen(a,i);
        Thread t=new Thread(runnable);
        t.start();
        thread_list.add(t);
        if(thread_list.size()==no_of_threads)
        {
          for(Thread tr : thread_list)
          {
            tr.join();
          }
          thread_list.clear();

        }
     }
     
     //GENERATING SECOND MATRIX - B using runnable MatrixGen class threads
     for(int i=0; i < b.length ; i++)
     {
        MatrixGen runnable = new MatrixGen(b,i);
        Thread t = new Thread(runnable);
        t.start();
        thread_list.add(t);
        if(thread_list.size()==no_of_threads)
        {
          for(Thread tr : thread_list)
          {
            tr.join();
          }
          thread_list.clear();

        }
     }

     //to make sure the generation finishes before we start multiplying
     for(Thread t : thread_list)
      {
        t.join();
      }
     thread_list.clear();

     //uncomment the following lines to print the generated matrices
     //printer(a);
    // printer(b);

     //MULTIPLY A AND B TO GIVE RESULT USING RUNNABLE FindElement CLASS THREADS
     for(int i=0; i < a.length ; i++)
     {
        FindElement runnable = new FindElement(a,b,result,i);
        Thread t = new Thread(runnable);
        t.start();
        thread_list.add(t);
        if(thread_list.size()==no_of_threads)
        {
          for(Thread tr : thread_list)
          {
            tr.join();
          }
          thread_list.clear();

        }
     }
     for(Thread t : thread_list)
    {
      t.join();
    }
    thread_list.clear();
    long endtime=System.currentTimeMillis();

    
    System.out.println("THE FINAL MATRIX :");
    printer(result);
    System.out.println("time taken for 1000*1000 matrix multiplication (in ms) = " + (endtime-starttime));


    


  }
}