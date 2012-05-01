import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JApplet;
import javax.swing.JPanel;

	@SuppressWarnings("serial")
	public class Square extends JApplet
	{
		private int x;
		private int y;
		private int z;
		private Color c;
		private String num1 = "0";
		private boolean occupied=false,clicked=false,flag=false;
		
	   //-----------------------------------------------------------------
	   //  Constructor for a square object initializes variables, x, y , other variables.
	   //-----------------------------------------------------------------
		public Square (int xvar, int yvar, int size, Color ncolor)
		{
			x = xvar;
			y = yvar;
			z = size;
			c = ncolor;
		}
		
		// setters and getters and other methods for all instance variables e.g. toString etc.
		public void SetX (int newx)
		{
			x = newx;
		}
		
		public void SetY (int newy)
		{
			y = newy;
		}
		
		public void SetZ (int size)
		{
			y = size;
		}
	
		public void Setcolor (Color ncolor)
		{
			c = ncolor;
		}
		
		public void Setnum (String nnum)
		{
			num1 = nnum;
		}
		
		public void Setoccupied (boolean isoccupied)
		{
			occupied = isoccupied;
		}
		
		public void setclicked (boolean isclicked)
		{
			clicked = isclicked;
		}
		
		public void setflag (boolean isflag)
		{
			flag = isflag;
		}
		
		public int GetX ()
		{
			return x;
		}
		
		public int GetY ()
		{
			return y;
		}
	
		public int GetZ ()
		{
			return z;
		}
		
		public Color GetColor ()
		{
			return c;
		}
	
		
		public boolean GetOcc()
		{
			return occupied;
		}

		public boolean Getflag()
		{
			return flag;
		}
		
		public boolean Getclicked()
		{
			return clicked;
		}
		

		public String Getnum()
		{
			return num1;
		}
		
	 public void draw (Graphics page)
		{
	   	  // draw the square
			page.setColor(Color.black);
			page.drawRect(GetZ()*GetY(),GetZ()*GetX(), GetZ(), GetZ()); 
		} 
		
	 public void fill (Graphics page)
		{
	   	  // draw the square
			page.setColor(Color.white);
			page.fillRect(GetZ()*GetY(),GetZ()*GetX(), GetZ(), GetZ()); 
		} 
	 public void drawnumbs (Graphics page, String num,Font f)
		{
	   	  // draw the square
			page.setColor(Color.black);
			page.setFont(f);
			page.drawString(num,GetZ()*GetY()+(int) Math.round(z/2.8),GetZ()*GetX()+(int) Math.round(z/5*3)); 
		} 
		public String toString() 
		{
		        return "Row: " + x + " Col: " + y + " Size: " + z  + " Occ: " + occupied + " Num " + num1 + " Clicked " + clicked;
		}
		
	}