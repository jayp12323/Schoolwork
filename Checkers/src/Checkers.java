
//Design notes:  The checkerboard is an 8 x 8 
//board with 5 red and 8 black pieces randomly 
//placed on dark gray squares (so as to better 
//see the black pieces).  The program will then
//check to see if any black pieces can jump and
//mark them with a white flag.  **Note: Black 
//pieces move down the checkerboard**  This 
//project implements interfaces, inheritance, 
//and arrays.

import java.applet.Applet;
import java.awt.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

//**********************************************

public class Checkers extends JApplet
{
	private final int APP_WIDTH = 400;
	private final int APP_HEIGHT = 400;
	private final int MAXSIZE = 8;
	
	
	Square checkerboard[][] =new Square[8][8];// declare a TWO D array of Square objects 8 X 8 ***********************
	
	// I would suggest you use different colors from red and black as a black checker will not show
	// on a black square.  So you could use red and blue.  I am using red and black here
	
	//Method to draw checkerboard and applet . Calls methods to draw the board, place the checkers
	// and find jumps
	public void paint(Graphics page)
	{
		setBackground(Color.white);
		fillBoard(page); // draws the checkers
		placeCheckers(page, 5, Color.red);   //draw  red checkers
		placeCheckers(page, 8, Color.black); //draw  black checkers
		CheckJumps(page);	//check if checkers can jump
		setSize (APP_WIDTH,APP_HEIGHT);
	
	}
	// stubs for start and stop
	public void start() {};// not implemented in this program
	public void stop() {};// not implemented in this program
	
	
	//********************************************************
	// This method creats a TWO D array and uses nested for loops to create the checkerboard
	//********************************************************
	
	public void fillBoard(Graphics page)
	{

		 
		 
		for (int row=0; row < checkerboard.length;row++)
	 	{
			for (int col=0; col < checkerboard[row].length;col++)
			{
				if(((row+col) % 2) == 0) // To alternate colors
				{
					checkerboard[row][col] = new Square(); //create square object
					checkerboard[row][col].Squarevar(row,col,400/MAXSIZE,Color.green); //set square variables 
					checkerboard[row][col].draw(page); //draw square
					
					
				}
				else
				{
					checkerboard[row][col] = new Square(); //create square object
					checkerboard[row][col].Squarevar(row,col,400/MAXSIZE,Color.gray); //set square variables 
					checkerboard[row][col].draw(page); //draw square
					}
			}
	 	}
	 }	  
	
	//********************************************************
	//Method to place checkers randomly on gray squares
	//********************************************************
	public void placeCheckers (Graphics page, int num_checkers, Color ncolor)
		{
		int num = num_checkers; 
		Color color2 = ncolor;
		int redcolor = color2.getRed();  //redcolor = 255 if red checker, 0 if black checker
		int i = 0;
		
		int row, col;
		int x, y;
		Circle c = new Circle(); //creates new circle object
		Random generator = new Random(); //creates random number generator

		while(i<num)
		{
			row = generator.nextInt(8); // random row from 0-7
			col = generator.nextInt(8); //random col from 0-7
			Color squarecolor = checkerboard[row][col].GetColor(); // gets color of square at row,col
			Boolean occupied = checkerboard[row][col].GetOcc(); //gets whether said squre is occupied already
			int greencolor = squarecolor.getGreen();
			System.out.println(greencolor);

				if(greencolor !=255 && occupied == false) //if not on a green or occupied square
				{
					//gets square coordinates
					x = checkerboard[row][col].GetX(); 
					y = checkerboard[row][col].GetY();
					//sets circle variables with coordinates
					c.Circvar(x,y,400/MAXSIZE,color2);
					c.draw(page); //draws checker
					checkerboard[row][col].Setoccupied(true); //sets square to be occupied
					checkerboard[row][col].Setcolor2(color2); //sets the color of the checker
					i++;
				}


		}	
	 }
	
	//********************************************************
	//Method to check for valid jumps and mark pieces
	//********************************************************
	public void CheckJumps (Graphics page)
	{
		
		for (int row=0; row < checkerboard.length;row++)
	 	{
			for (int col=0; col < checkerboard[row].length;col++)
			{
				Boolean isocc = checkerboard[row][col].GetOcc(); 
	
				if (isocc == true) //if the square is occupied
				{

					Color coler1 = checkerboard[row][col].GetColor2(); //gets color of checker on square
					int red = coler1.getRed(); 
					if (red == 255) //if the checker is red
					{
						CheckLeftJump(page,row,col,Color.red);
						CheckRightJump(page,row,col,Color.red);

					}
					else  //if the checker is black
					{
						CheckRightJump(page,row,col,Color.black);
						CheckLeftJump(page,row,col,Color.black);
					}
				}
			}
		}
	}
		
		
	
	 
	
	//********************************************************
	//Checks  valid right-hand jumps
	//********************************************************	
	public void CheckRightJump (Graphics page, int row, int col, Color type)
	{
		int add;
		int checkcol = type.getRed();
		
		// these next couple steps are kind of hard to explain. Because red checkers jump upwards, 
		//it is equivalant to them jumping to (row -1). Black jumps down, so (row+1)

		if (checkcol == 255)  
			{add =-1;} //for red
		else
			{add = 1;} //for black
		
		int row2 = row + add;  //sets the row that the checkers would be jumping over
		int col2 = col + 1; //because it is right hand jumps, they both jump the checker in (col+1)
		
		// to set boundaries for which there are no legal jumps, as they would be off the board
		if (((col2>=7 || row2>=7) && checkcol==0) || ((row2<=0 || col2>=7) && checkcol==255)){;}
		
		else
		{
			Circle c = new Circle(); //creates new circle object
		
			Boolean isocc = checkerboard[row2][col2].GetOcc(); //if the space the checker will jump is occupied
			if (isocc == true && (checkerboard[row2+add][col2+1].GetOcc() == false)) //and the next space is unoccupied
			{

				Color coler = checkerboard[row2][col2].GetColor2(); //gets color of square to jump
				int red = coler.getRed(); 
				if (red != checkcol) //if the colors are different, draws the circle on the checker that can jump
				{
					int x = checkerboard[row][col].GetX();
					int y = checkerboard[row][col].GetY();
					c.Circvar(x, y, 400/MAXSIZE/2, Color.yellow);
					c.drawjump(page);
				}
			}
		}
	 }
	
	//********************************************************
	//Checks valid left-hand jumps
	//********************************************************
	public void CheckLeftJump (Graphics page, int row, int col, Color type)
	{

		int add;
		
		// these next couple steps are kind of hard to explain. Because red checkers jump upwards, 
		//it is equivalant to them jumping to (row -1). Black jumps down, so (row+1)
		
		int checkcol = type.getRed();
		if (checkcol == 255)
		{add =-1;} //for red
		else
		{add = 1;}//for black
		int row2 = row + add; 
		int col2 = col - 1;
		
		// sets up only legal moves
		if (((col2<=0 || row2>=7) && checkcol==0) || ((row2<=0 ||col2<=0) && checkcol==255)){;}
		
		
			else
			{
				Circle c = new Circle(); //creates new circle object
				
				Boolean isocc = checkerboard[row2][col2].GetOcc(); //if the space the checker will jump is occupied
				if (isocc == true && (checkerboard[row2+add][col2-1].GetOcc() == false)) //and the next space is unoccupied
				{

					Color coler = checkerboard[row2][col2].GetColor2(); //gets color of square to jump
					int red = coler.getRed(); 
					if (red != checkcol) //if the colors are different, draws the circle on the checker that can jump
					{
						int x = checkerboard[row][col].GetX();
						int y = checkerboard[row][col].GetY();
						c.Circvar(x, y, 400/MAXSIZE/2, Color.yellow);
						c.drawjump(page);
					}
				}
			}
	}

}//close checker class
	
	//*******************************************************
	//Shapes Interface
	//*******************************************************
	interface shapes
	{
		public void SetX(int newX);
		public void SetY(int newY);
		public int GetX();
		public int GetY();
	}// close interface Shapes
	
	
	//*******************************************************
	//Class that represents a square on the board
	//Each square has an x/y location, a size and some other variable to be determined by you
	
	class Square extends JApplet implements shapes
	{
		private int x;
		private int y;
		private int z;
		private Color c;
		private Color c2;
		private boolean occupied=false;
		
	   //-----------------------------------------------------------------
	   //  Constructor for a square object initializes variables, x, y , other variables.
	   //-----------------------------------------------------------------
		public void Squarevar (int xvar, int yvar, int size, Color ncolor)
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
		
		public void Setcolor2 (Color ncolor)
		{
			c2 = ncolor;
		}
		
		public void Setoccupied (boolean isoccupied)
		{
			occupied = isoccupied;
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
	
		public Color GetColor2 ()
		{
			return c2;
		}
		
		public boolean GetOcc()
		{
			return occupied;
		}
	
	 public void draw (Graphics page)
		{
	   	  // draw the square
			page.setColor(c);
			page.fillRect(GetZ()*GetY(),GetZ()*GetX(), GetZ(), GetZ()); 
		} 
			 
		public String toString() 
		{
		        return "Row: " + x + " Col: " + y + " Size: " + z + " Color:" + c + " Color2: " + c2 + " Occ: " + occupied;
		}
		
	}//Close square class
	
	
	//***********************************************************************
	//Class to represent a checker - both black and red on the checkerboard
		class Circle implements shapes
		{
			private int x;
			private int y;
			private int z;
			private Color c;
					
		   //-----------------------------------------------------------------
		   //  Constructor for a square object initializes variables, x, y , other variables.
		   //-----------------------------------------------------------------
			public void Circvar (int xvar, int yvar, int diam, Color ncolor)
			{
				x = xvar;
				y = yvar;
				z = diam;
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
			
			public void SetZ (int newdiam)
			{
				z = newdiam;
			}
	
			public void Setcolor (Color ncolor)
			{
				c = ncolor;
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
			
			public void draw (Graphics page)
			{
				page.setColor(c);
				page.fillOval(50*GetY()+2,50*GetX()+2, GetZ()-5, GetZ()-5); 
			} 
		
			public void drawjump (Graphics page)
			{
				page.setColor(c);
				page.fillOval(50*GetY()+12,50*GetX()+12, GetZ(), GetZ()); 
			} 
			 
			public String toString() 
			{
		        return "Col: " + x + " Row: " + y + " Size: " + z + " Color:" + c;
			}
			
	}
//Close circle class
//End of Checkers.java
//**************************************************************