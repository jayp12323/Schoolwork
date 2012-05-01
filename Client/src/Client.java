//********************************************************************
////   THERE IS MORE CODE TO DO,  YOU NEED TO DO ANOTHER CONSTRUCTOR AND SETTERS AND GETTERS
// Client.java       
//
//  Responsible for creating objects that represent the clients.
//********************************************************************

public class Client implements Comparable<Client>
{
	  // initialize variables

	static int count = 0;
	protected String ln, fn, gen, num, hob;
	protected int age;

	//CREATE ANOTHER CONSTRUCTION HERE
	//constructs client with only ln, fn, and increments count and NO AGE

	public Client (String lastn, String firstn, String gender, String phone, String hobby)
	{
		 ln =lastn; 
		 fn =firstn;
		 gen = gender;
		 num = phone;
		 hob = hobby;
		 count++;
	}
	
	public Client (String lastn, String firstn, String gender, String phone, String hobby,int setage)
	{
		 ln =lastn; 
		 fn =firstn;
		 age = setage;
		 gen = gender;
		 num = phone;
		 hob = hobby;
		 count++;
	}
	
	public Client (String lastn, String firstn)
	{
		 ln =lastn; 
		 fn =firstn;
	}
// put instance variables here.  A client has an age a senior client does not.

// put constructors here

	public void Setln (String newln)
	{
		ln = newln;
	}
	
	public void Setfn (String newfn)
	{
		fn = newfn;
	}
	
	public void Setgen (String newgen)
	{
		gen = newgen;
	}

	public void Setage (int newage)
	{
		age = newage;
	}
	
	public void Setnum (String newnum)
	{
		num = newnum;
	}
	
	public void Sethob (String newhob)
	{
		hob = newhob;
	}
	public String Getln ()
	{
		return ln;
	}
	
	public String Getfn ()
	{
		return fn;
	}

	public String Getgen ()
	{
		return gen;
	}
	
	public int Getage ()
	{
		return age;
	}

	public String Getnum ()
	{
		return num;
	}
	
	public String Gethob()
	{
		return hob;
	} 
	public static int Getcount()
	{
		return count;
	}	
	
// put toString method here      
	public String toString() 
	{
		if(gen==null)
		{
			return "Name: " +fn +ln;	
		}
		if(age==0)
		{
			return fn +" " +ln +" " +gen +" " + num +" " +hob;	
		}
		else
		{
			return fn +" " +ln +" " +gen +" " + num +" " +hob +" "+ age;	
		}
		
	}	/*public Client(parameters to fill the values for the instance variables above)
	{
		
	count++;
	}
	*/
	
	
	
		/** Description -compares clients first and last names lexically for placement in the database
	 * 
	 * @param other refers to another Client
	 * @param result - possible values
	 *                 0 if this client and other have same names, 1, if other is greater, -1 if less than
	 * @return			0, 1, -1
	 */

	public int compareTo(Client other)
	{
	int result;
	
	if(ln.equals((other).ln))
		result = fn.compareTo((other).fn);
	else result = ln.compareTo((other).ln);
	
	return result;
	}

	
	//this method determines if the first and last name of one object other is the
	//same as the last and first name of the current object
	public boolean equals(Object other1)
	{
	
	boolean result = false;
	Client other = (Client) other1;
	if(ln.equals((other).ln))
		result = fn.equals((other).fn);
	else result = ln.equals((other).ln);
	return result;
	}
	
		
	
// DO SETTERS AND GETTERS FOR THE OTHER INSTANCE VARIABLES*******************
	
	//returns a String representation of the client object
	/*public String toString()
	{
		return fn + " " + ln + "  " + sex + "  " + age + "  " + phone 
			+ "  " + hobby;
		
	}
	*/

	
} 
//class client


class SeniorClient extends Client
{


  // do code for this class
//constructor 
	     public SeniorClient (String lastname, String firstname, String gender, String phone, String hobby)
		  {
		  super(lastname, firstname, gender, phone, hobby);
		  }
		 public String toString()
	     {
			 return super.toString();
	     }


}  // close Senior