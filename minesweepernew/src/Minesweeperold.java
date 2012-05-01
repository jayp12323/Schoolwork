import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;



@SuppressWarnings("serial")
public class Minesweeperold extends JApplet
{
	private final int APP_WIDTH = 630;
	private final int APP_HEIGHT = 630;
	private final int numcols = 9;
	int size1 = APP_WIDTH/numcols;
	int done = 0;
	boolean finished = false;
	private String file = "out.txt"; 



	
	
	 Square checkerboard[][] =new Square[numcols][numcols];
	JPanel panels[][] = new JPanel[numcols][numcols];
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

		 
		for (int row=0; row < numcols;row++)
	 	{
			for (int col=0; col < numcols;col++)
			{
				checkerboard[row][col] = new Square(row, col, 630/numcols, Color.white);
				checkerboard[row][col].fill(page);
				checkerboard[row][col].Setcolor(Color.black);
				checkerboard[row][col].draw(page);
				checkerboard[row][col].setclicked(false);

				checkerboard[row][col].addMouseListener(action);


			}
	 	}
	}
//
	public void fillBoard2(Graphics page)
	{

		 
		 
		for (int row=0; row < numcols;row++)
	 	{
			for (int col=0; col < numcols;col++)
			{

//				JButton blank= new JButton("button");
//				blank.setSize(630/numcols, 630/numcols);
				panels[row][col] = new JPanel();
				panels[row][col].setBorder(BorderFactory.createLineBorder(Color.black));
				panels[row][col].setPreferredSize(new Dimension(630/numcols, 630/numcols));
//				panels[row][col].setBackground(Color.gray);
				panels[row][col].setBounds(col*630/numcols, row*630/numcols, 630/numcols, 630/numcols);
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
			row = generator.nextInt(numcols);
			col = generator.nextInt(numcols);
			Boolean occupied = checkerboard[row][col].GetOcc();
			if(occupied == false)
				{

					checkerboard[row][col].Setoccupied(true);
					i++;
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
				checkerboard[row][col].Setcolor(color2);
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
			System.out.println(checkerboard[x2][y2].Getnum());

			Boolean occupied = checkerboard[x2][y2].GetOcc();
			if(checkerboard[x2][y2].Getclicked()==false)
			{
				if(e.getButton()==3 &&check.Getflag()==false)
				{
					flag f = new flag(y2,x2);
					f.draw(page);
					checkerboard[x2][y2].setclicked(true);
					checkerboard[x2][y2].setflag(true);
				}

					
				if(e.getButton()==1)
				{
					checkerboard[x2][y2].setclicked(true);

					if (occupied==false)
					{

						String num2 = checkerboard[x2][y2].Getnum();
						Font f = new Font("Ariel",Font.PLAIN,20);
						checkerboard[x2][y2].Setcolor(Color.white);
						checkerboard[x2][y2].fill(page);
						checkerboard[x2][y2].Setcolor(Color.black);
						checkerboard[x2][y2].draw(page);
						checkerboard[x2][y2].setclicked(true);
						checkerboard[x2][y2].drawnumbs(page, num2,f);
					}
					if(occupied==true) 
					{	
						
						int row1 = check.GetX();
						int col1 = check.GetY();
						checkerboard[row1][col1].Setcolor(Color.white);
						checkerboard[row1][col1].fill(page);
						checkerboard[row1][col1].Setcolor(Color.black);
						checkerboard[row1][col1].draw(page);
						bomb b =  new bomb(col1,row1,size1);
						b.draw(page);
						FileOutputStream out;
						try {
							out = new FileOutputStream(file);
							PrintStream ps = new PrintStream(out);
											     
						for(int row = 0;row<numcols;row++)
						{
							for(int col = 0;col<numcols;col++)
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
									checkerboard[row][col].Setcolor(Color.white);
									checkerboard[row][col].fill(page);
									checkerboard[row][col].Setcolor(Color.black);
									checkerboard[row][col].draw(page);
									Font f = new Font("Ariel",Font.PLAIN,20);
									checkerboard[row][col].drawnumbs(page,num2,f);
								}
								if(checkerboard[row][col].Getclicked()==false &&checkerboard[row][col].GetOcc()==true)
								{
									
									checkerboard[row][col].Setcolor(Color.white);
									checkerboard[row][col].fill(page);
									checkerboard[row][col].Setcolor(Color.black);
									checkerboard[row][col].draw(page);
									bomb b1 = new bomb(col,row,size1);

									b1.draw(page);
														
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

