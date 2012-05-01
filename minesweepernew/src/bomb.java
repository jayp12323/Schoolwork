import java.awt.Color;
import java.awt.Graphics;

public class bomb
		{
			private int xvar;
			private int yvar;
			private int z;
			private int xvars[] = new int[5];
			private int yvars[] = new int[5];

		   //-----------------------------------------------------------------
		   //  Constructor for a square object initializes variables, x, y , other variables.
		   //-----------------------------------------------------------------
			public bomb (int xvarsn, int yvarsn, int size)
			{
				xvar = xvarsn;
				yvar = yvarsn;
				int z = size;
				xvars[0]=(int) Math.floor(z/2);xvars[1]=(int) Math.floor(z/2);xvars[2]=(int) Math.floor(z/2-1);xvars[3]=(int) Math.floor(z/2);xvars[4]=(int) Math.floor(z/2);

				int yx = (int) Math.floor(z/5);
			    yvars[0]=yx+0;yvars[1]=yx-(int) Math.floor(z/14);yvars[2]=yx-(int) Math.floor(z/9);yvars[3]=yx-(int) Math.floor(z/14);yvars[4]=yx;

//				
				for (int i=0;i<5;i++)
				{
					xvars[i] = xvars[i]+xvar*size;
					yvars[i] = yvars[i]+yvar*size;
				}
				

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
			public int Getz ()
			{
				return z;
			}
	
			public void draw (Graphics page)
			{
				page.setColor(Color.black);
				page.drawPolygon(xvars,yvars,yvars.length); 
				page.setColor(Color.red);
				page.fillOval(xvar*z+(int) Math.floor(z/10), yvar*z+(int) Math.floor(z/14*3), (int) Math.floor(z/14*10), (int) Math.floor(z/14));
			} 
		
			public String toString() 
			{
		        return "xvars: " + xvars + " yvars: " + yvars;
			}
			
	}