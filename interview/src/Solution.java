/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/
import java.util.*;
import java.io.*;
import java.lang.Math;
import java.math.*;

public class Solution
{
    private static long upperLimit;
    private static long N;
    private static int sqN;
    private static long K; 
    private static boolean[] flags;
    private static Hashtable<Long, Integer> hash;
//    private static int[] primes;
    private static LinkedList<Long> list = new LinkedList<Long>();
    private static String line,line2;
    private static  ArrayList<Long> primes = new ArrayList<Long>();


    public static void main(String[] args) {
	    try {
                	//BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));   

			BufferedReader scan = new BufferedReader(new FileReader(args[0]));   

			line = scan.readLine();
		    line2 = scan.readLine();

		} catch (IOException e) {
			e.printStackTrace();
		}
	    Scanner linescan = new Scanner(line);
	    N = linescan.nextLong();
	    K = linescan.nextLong();
//	    testValue = new BigInteger(Long.toString(Klong));
	    //used for finding the factors. It is only necessary to go up to sqrt(K) because  every divisor comes with a pair
	    sqN = ((int)Math.ceil(Math.sqrt(K)));
//	    System.out.println(N + " " + sqN);
        primes = primes();
        System.out.println(returnVal());
    }


    public static ArrayList<Long> primes() { 
//        System.out.println(sqN);

        // initially assume all integers are prime
        boolean[] isPrime = new boolean[sqN + 1];
        for (int i = 2; i <= sqN; i++) {
            isPrime[i] = true;
        }

        // mark non-primes <= N using Sieve of Eratosthenes
        for (int i = 2; i*i <= sqN; i++) {

            // if i is prime, then mark multiples of i as nonprime
            // suffices to consider mutiples i, i+1, ..., N/i
            if (isPrime[i]) {
                for (int j = i; i*j <= sqN; j++) {
                    isPrime[i*j] = false;
                }
            }
        }
         hash = new Hashtable<Long, Integer>();
        ArrayList<Long> list1 = new ArrayList<Long>();
//        hash.put((long)1, 1);
//        hash.put(K, 1);
        // count primes
        for (int i = 2; i <= sqN; i++) 
        {
            if (isPrime[i])
            {
//            	System.out.println(i);
    			if(K % i==0)
    			{
    		        hash.put((long)i, 1);
    		        list1.add((long)i);
    		        int listsize = list1.size();
    		        for(int y=0;y<listsize;y++){
    		        	long tempp = i*list1.get(y);
    		        	if(K % tempp == 0){
    		        	list1.add(tempp);
    		        	hash.put(tempp,1);
    		        	}
    		        }
//    				hash.put(K/i,1);
    				long temp = K;
    				int temp2 = 1;
    				int itemp = i*i;
    				while(temp%itemp==0)
    				{
    					
        		        hash.put((long)itemp,1 );
        		        list1.add((long)itemp);
        		        listsize = list1.size();
//        		        System.out.println(listsize);
        		        for(int y=0;y<listsize;y++){
        		        	long tempp = i*list1.get(y);
        		        	if(K % tempp == 0){
        		        	list1.add(tempp);
        		        	hash.put(tempp,1);
        		        	}
        		        }
//    					list1.add(K/itemp);
    					itemp*=i;
    				}
    			}
    	

            }
        }
        hash.put((long)1, 1);
       String s = "" ;
       ArrayList<Long> ints = new ArrayList<Long>(); 
       for (Enumeration<Long> e = hash.keys() ; e.hasMoreElements() ;) {
           ints.add((Long) e.nextElement());

       };
//       System.out.println(s);
//       System.out.println(ints);

        return ints;
    }

    
    public static int returnVal(){
    	int k=0;
    	while(k<primes.size())
    	{	
    	  String[] nn = line2.split(" ");
    	  for(int j=0;j<nn.length;j++)
    	  {
    		  long temp = Long.parseLong(nn[j]);
//    		  System.out.println(temp);
    		  if(temp % primes.get(k)==0)
    		  {
    			  // k-- to keep the index variable at the correct spot, other wise it would skip the following index
    				  primes.remove(k--);
    				  break;
    		  }
    	  }
    	  k++;
    	  
    	}
    	return(primes.size());

    }
}
