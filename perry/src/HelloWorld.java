import java.math.BigInteger;
import java.util.Random;


public class HelloWorld {

	int N;
    public static void main(String[] args) {
    	Countdown(10);
   
    }
 	public static void Countdown(int N)
	{
	if(N<=0)
	{
	System.out.println("Blastoff");
	}
	else{
	System.out.print(N + "\n");
	N-=2;
	Countdown(N);
	}
	}

}