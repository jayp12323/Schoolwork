/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;


public class Solution
{
	private static int[] n2;
	private static BigInteger N,K;
	private static long Klong;
	private static String line,line2;
	static LinkedList<Thread> t = new LinkedList<Thread>();
   	public static void main(String[] args) {	   		
   	
// Read in the first line of the file, set the N and K variables   		
	try {
//		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));   
		BufferedReader scan = new BufferedReader(new FileReader(args[0]));   
	    line = scan.readLine();
	    line2 = scan.readLine();
	    Scanner linescan = new Scanner(line);
	    N = linescan.nextBigInteger();
	    Klong = linescan.nextLong();
	    K = new BigInteger(Long.toString(Klong));
	    //used for finding the factors. It is only necessary to go up to sqrt(K) because  every divisor comes with a pair
	} catch (Exception e) {
	    System.err.println("Error:" + e.getMessage());
	}

 //Set up divisors array
  ArrayList<BigInteger> divisors = new ArrayList<BigInteger>();
  boolean div2 = true;
  boolean div3 = true;
  boolean div5 = true;
  if(K.remainder(new BigInteger("2"))!=BigInteger.ZERO)
  {
	  div2 = false;
  }
  if(K.remainder(new BigInteger("3"))==BigInteger.ZERO)
  {
		div3 = false;
  } 
  if(K.remainder(new BigInteger("5"))!=BigInteger.ZERO)
  {
		div5 = false;
  }
  int two = 0;
  int three =0;
  int five = 0;
  System.out.println(divisors);

  for(long i=1;i*i<K.longValue();i++)
  {
	  if (div2==false)
	  {
		  two = (two + 1) % 2;
		  if(two==0)
		  	continue;
	  }
	  
	  if (div3==false)
	  {
		  three = (three + 1) %3;
		  if(three==0)
		  	continue;
	  }
	  
	  if (div5==false)
	  {
		  five = (five + 1) % 5;
		  if(five==0)
		  	continue;
	  }
	  System.out.println(i);
	  BigInteger i2 = new BigInteger(Long.toString(i));
	  //if it is a divisor, add it to the array
	if(K.remainder(i2)==BigInteger.ZERO)
	{
		divisors.add(i2);
		divisors.add(K.divide(i2));
	}
  }
  System.out.println(divisors);
   int k=0;
   // the size of the divisor array shrinks when one is a factor of an unfriendly number
	while(k<divisors.size())
	{	
	  String[] nnnn = line2.split(" ");
	  for(int j=0;j<nnnn.length;j++)
	  {
		  BigInteger temp = new BigInteger(nnnn[j]);
		  if(temp.remainder((BigInteger)divisors.get(k))==BigInteger.ZERO)
		  {
			  // k-- to keep the index variable at the correct spot, other wise it would skip the following index
				  divisors.remove(k--);
				  break;
		  }
	  }
	  k++;
	  
	}
	// the remaining divisors will be the ones that arent factorable into any of the unfriendly numbers
	System.out.println(divisors.size());
  
  }
	   		   	
}		
