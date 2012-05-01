
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;




//**********************************************


@SuppressWarnings({ "unused", "serial" })

public class Minesweeper extends JApplet
{
	private final int APP_WIDTH = 630;
	private final int APP_HEIGHT = 630;
	private final int MAXSIZE = 9;
	int done = 0;
	boolean finished = false;
	private String file = "out.txt"; 



	
	
	protected Square checkerboard[][] =new Square[9][9];
	JPanel panels[][] = new JPanel[9][9];
	Container c = new Container();
    private ActionHandler action;  // is the object that implements ActionListener and contains the actionPerformed method
	public void paint(Graphics page)
	{
		action = new ActionHandler();
		setSize (APP_WIDTH+10,APP_HEIGHT+10);
		fillBoard(page);
		fillBoard2(page);
		setBackground(Color.white);
		placebombs(page, 10, Color.red);   //draws bombs
		placenums(page, Color.black); //places numbers
	
	}

	public void fillBoard(Graphics page)
	{

		 
		for (int row=0; row < 9;row++)
	 	{
			for (int col=0; col < 9;col++)
			{
				checkerboard[row][col] = new Square();
				checkerboard[row][col].Squarevar(row, col, 70, Color.white);
				checkerboard[row][col].fill(page);
				checkerboard[row][col].Squarevar(row, col, 70, Color.black);
				checkerboard[row][col].draw(page);
				checkerboard[row][col].setclicked(false);

				checkerboard[row][col].addMouseListener(action);


			}
	 	}
	}
//
	public void fillBoard2(Graphics page)
	{

		 
		 
		for (int row=0; row < 9;row++)
	 	{
			for (int col=0; col < 9;col++)
			{

//				JButton blank= new JButton("button");
//				blank.setSize(70, 70);
				panels[row][col] = new JPanel();
				panels[row][col].setBorder(BorderFactory.createLineBorder(Color.black));
				panels[row][col].setPreferredSize(new Dimension(70, 70));
//				panels[row][col].setBackground(Color.gray);
				panels[row][col].setBounds(col*70, row*70, 70, 70);
				panels[row][col].addMouseListener(action);
				add(panels[row][col]);
				panels[row][col].add((Component)checkerboard[row][col]);


			}
	 	}
	 }

	public void placebombs (Graphics page, int num_checkers, Color ncolor)
		{
		int num = num_checkers;
		int i = 0;
		
		int row, col;
		Random generator = new Random();

		while(i<num)
		{
			row = generator.nextInt(9);
			col = generator.nextInt(9);
			Boolean occupied = checkerboard[row][col].GetOcc();
			if(occupied == false)
				{

					checkerboard[row][col].Setoccupied(true);
					i++;
					bomb b = new bomb();
					b.bombvar(col,row);
//					b.draw(page);
				}
			}	
		}
//	
//	//********************************************************
//	//Method to check for valid jumps and mark pieces
//	//********************************************************
	public void placenums (Graphics page, Color ncolor)
	{
	Color color2 = ncolor;
	

	for (int row=0; row < checkerboard.length;row++)
 	{
		for (int col=0; col < checkerboard[row].length;col++)
		{
			int num=0;
			Boolean occupied = checkerboard[row][col].GetOcc();
			if(occupied == false)
			{
				int x = checkerboard[row][col].GetX();
				int y = checkerboard[row][col].GetY();
				for(int i = x-1; i<x+2;i++)
				{
					if(i<0 || i > 8){}
					else
					{
						for(int j = y-1; j<y+2;j++)
						{
							if(j<0 || j > 8){}
							else
							{
								if(checkerboard[i][j].GetOcc()==true)
								{
									num = num+1;
								}
							}
						}
					}
				}
				checkerboard[row][col].Squarevar(x, y, 630/MAXSIZE, color2);
				String num2 = "";
				num2 = Integer.toString(num);
				Font f = new Font("Ariel",Font.PLAIN,20);
				checkerboard[row][col].Setnum(num2);
//				checkerboard[row][col].drawnumbs(page, num2,f);
				


			}
		}	
 
	}
	}


	class ActionHandler implements MouseListener
    {
	
		Graphics page = getGraphics();
		public void mouseClicked(MouseEvent e) 
		{
			if(finished==false)
			{
			JPanel cl = (JPanel) e.getSource();
			Square check = (Square)cl.getComponent(0);
			
			int x2 = check.GetX(); //row
			int y2 = check.GetY();//col
			System.out.println(checkerboard[y2][x2].Getnum());

			Boolean occupied = checkerboard[y2][x2].GetOcc();
			if(checkerboard[y2][x2].Getclicked()==false)
			{
				if(e.getButton()==3 &&check.Getflag()==false)
				{
					flag f = new flag(y2,x2);
					f.draw(page);
					checkerboard[y2][x2].setclicked(true);
					checkerboard[y2][x2].setflag(true);
				}

					
				if(e.getButton()==1)
				{
					checkerboard[y2][x2].setclicked(true);

					if (occupied==false)
					{

						String num2 = checkerboard[y2][x2].Getnum();
						Font f = new Font("Ariel",Font.PLAIN,20);
						checkerboard[y2][x2].Squarevar(x2, y2, 70, Color.white);
						checkerboard[y2][x2].fill(page);
						checkerboard[y2][x2].Setcolor(Color.black);
						checkerboard[y2][x2].draw(page);
						checkerboard[y2][x2].setclicked(true);
						checkerboard[y2][x2].drawnumbs(page, num2,f);
					}
					if(occupied==true) 
					{	
						
						bomb b =  new bomb();
						b.bombvar(y2,x2);
						int row1 = check.GetX();
						int col1 = check.GetY();
						checkerboard[row1][col1].Setcolor(Color.white);
						checkerboard[row1][col1].fill(page);
						checkerboard[row1][col1].Setcolor(Color.black);
						checkerboard[row1][col1].draw(page);
						b.draw(page);
						FileOutputStream out;
						try {
							out = new FileOutputStream(file);
							PrintStream ps = new PrintStream(out);
											     
						for(int row = 0;row<9;row++)
						{
							for(int col = 0;col<9;col++)
							{
								ps.println(checkerboard[row][col].toString());
								System.out.println(checkerboard[col][row].Getclicked());
								if(checkerboard[row][col].Getclicked()==false &&checkerboard[row][col].GetOcc()==false)
								{
									
									String num2 = checkerboard[col][row].Getnum();
	
									checkerboard[row][col].Setcolor(Color.black);
									int x = checkerboard[row][col].GetX();
									System.out.println("row" + row + "col" + col);
									int y = checkerboard[row][col].GetY();
									checkerboard[row][col].Squarevar(x, y, 630/MAXSIZE, Color.white);
									checkerboard[row][col].fill(page);
									checkerboard[row][col].Setcolor(Color.black);
									checkerboard[row][col].draw(page);
									Font f = new Font("Ariel",Font.PLAIN,20);
									checkerboard[row][col].drawnumbs(page,num2,f);
								}
								if(checkerboard[row][col].Getclicked()==false &&checkerboard[row][col].GetOcc()==true)
								{
									checkerboard[row][col].Squarevar(row, col, 70, Color.white);
									int x = checkerboard[row][col].GetX();
									int y = checkerboard[row][col].GetY();
									String num2 = checkerboard[col][row].Getnum();
									checkerboard[row][col].fill(page);
									checkerboard[row][col].Setcolor(Color.black);
									checkerboard[row][col].draw(page);
									bomb b1 = new bomb();
									b.bombvar(y,x);
									b.draw(page);						
								}
//									
							}
	
						}
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						finished=true;
					}		
//						
//						
					}
//	
				}
			
			else{
			if(e.getButton()==3 &&check.Getflag()==true)
			{
				Color c = new Color(238,238,238);
				check.Setcolor(c);
				check.fill(page);
				check.Setcolor(Color.black);
				check.draw(page);				
				check.setclicked(false);
				check.setflag(false);
			}
			}
			

		}
		}

		public void mouseEntered(MouseEvent e) {
			
		}

		public void mouseExited(MouseEvent e) {
			
		}

		public void mousePressed(MouseEvent e) {
			
		}

		public void mouseReleased(MouseEvent e) {
			
		}

} //close ActionHandler
}//close checker class
	
	//*******************************************************
	//Shapes Interface
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
		private String num1 = "0";
		private boolean occupied=false,clicked=false,flag=false;
		
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
			page.setColor(c);
			page.drawRect(GetZ()*GetY(),GetZ()*GetX(), GetZ(), GetZ()); 
		} 
		
	 public void fill (Graphics page)
		{
	   	  // draw the square
			page.setColor(c);
			page.fillRect(GetZ()*GetY(),GetZ()*GetX(), GetZ(), GetZ()); 
		} 
	 public void drawnumbs (Graphics page, String num,Font f)
		{
	   	  // draw the square
			page.setColor(Color.black);
			page.setFont(f);
			page.drawString(num,GetZ()*GetY()+25,GetZ()*GetX()+45); 
		} 
		public String toString() 
		{
		        return "Row: " + x + " Col: " + y + " Size: " + z  + " Occ: " + occupied + " Num " + num1 + " Clicked " + clicked;
		}
		
	}//Close square class
	
	
	//***********************************************************************
	//Class to represent a checker - both black and white on the checkerboard
		class bomb
		{
			private int xvar;
			private int yvar;
			private int xvars[] = new int[5];
			private int yvars[] = new int[5];

		   //-----------------------------------------------------------------
		   //  Constructor for a square object initializes variables, x, y , other variables.
		   //-----------------------------------------------------------------
			public void bombvar (int xvarsn, int yvarsn)
			{
				xvar = xvarsn;
				yvar = yvarsn;
				xvars[0]=35;xvars[1]=35;xvars[2]=33;xvars[3]=35;xvars[4]=35;

				int yx = 14;
			    yvars[0]=yx+0;yvars[1]=yx-5;yvars[2]=yx-8;yvars[3]=yx-5;yvars[4]=yx;

//				
				for (int i=0;i<5;i++)
				{
					xvars[i] = xvars[i]+xvar*70;
					yvars[i] = yvars[i]+yvar*70;
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
	
	
			public void draw (Graphics page)
			{
				page.setColor(Color.black);
				page.drawPolygon(xvars,yvars,yvars.length); 
				page.setColor(Color.red);
				page.fillOval(xvar*70+10, yvar*70+15, 50, 50);
			} 
		
			public String toString() 
			{
		        return "xvars: " + xvars + " yvars: " + yvars;
			}
			
	}
		
		class flag
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

		 



	 //close ActionHandler		