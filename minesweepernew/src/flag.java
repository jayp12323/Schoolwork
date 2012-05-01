import java.awt.Color;
import java.awt.Graphics;

public class flag
		{
			private int xvar;
			private int yvar;


		   //-----------------------------------------------------------------
		   //  Constructor for a square object initializes variables, x, y , other variables.
		   //-----------------------------------------------------------------
			public flag (int xvarsn, int yvarsn)
			{
				xvar = xvarsn*70;
				yvar = yvarsn*70;

			}
			
			// setters and getters and other methods for all instance variables e.g. toString etc.
			public void SetX (int newx)
			{
				xvar = newx;
			}
			
			public void SetY (int newy)
			{
				yvar = newy;
			}
			
			public int GetX ()
			{
				return xvar;
			}
			
			public int GetY ()
			{
				return yvar;
			}
	
	
			public void draw (Graphics page)
			{
				page.setColor(Color.black);
				page.drawLine(xvar+55,yvar+10,xvar+55,yvar+65); 
				page.setColor(Color.red);
				page.fillRect(xvar+10, yvar+10, 45, 30);
			} 
		
			public String toString() 
			{
		        return "xvars: " + xvar + " yvars: " + yvar;
			}
			
	}	 
		// close class