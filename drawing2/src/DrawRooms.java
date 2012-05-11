
//********************************************************************
//  DrawRooms - Application version  -     
//  Draws room - marks a 1 for those which have a door
// places prisoner , tiger and lily images on the grid      
//  Sets up a grid of rooms   -
// USE CODE FROM CHECKERS TO SET UP THE GRID
//********************************************************************

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;
import java.util.Timer;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.net.*;

@SuppressWarnings({ "unused", "serial" })
public class DrawRooms  extends JPanel
{
	Image tiger;
	Image princess;

	int x1 = 0;
	 int num2 = -1;
	 Maze maze = new Maze();
	 int[][] grid = maze.getgrid();
	 int num;
	 int[] current = {0,0};
	 int[] next = {0,0};


	int w,h,s2,h2;
	 Graphics2D p2;
	 String turn = "middle";

    boolean leftdoor = false;

	
		// CONSTRUCTOR
	public DrawRooms(Maze maze)
	   {
		try
		 {
			//uploading pictures
		  tiger = ImageIO.read(new File("tiger.gif"));
		  princess = ImageIO.read(new File("princess.jpg"));

		 }
		 catch(IOException e)
		 {
			 ;
		 }
//		 Image image1 = null;
		 this.maze = maze;
		// THIS WILL ASSIGN A REFERENCE TO ARRAY OF INTS CREATED  IN GUI CLASS
		 
	    w = 600;
	    h = 600;
		s2 = (int)Math.round(Math.sqrt(2));
		h2 = (int)Math.round((h-275)/s2);
		
		// YOU NEED TO SET THE PREFERRED SIZE TO GET THE DISPLAY THE SIZE YOU WANT IT.
		 setPreferredSize(new Dimension( w, h));
		 
 
	  }
	  
	  // this method is called from the Controller class to reset where the prisoner will be at the end of run.
	  
	  public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}


	public Graphics2D getP2() {
		return p2;
	}

	public void setP2(Graphics2D p2) {
		this.p2 = p2;
	}
	
	public void setturn(String s){
		turn = s; 
	}

	public String getturn(){
		return turn; 
	}
	
	// repaint() this will cause paintComponent to be called again and the prisoner to be moved to position x1
	  public void MoveMan( int x1)
	  {
	    this.x1 = x1;
		 repaint();
	}
	  
	//------------------------------------------------'
	//	calls methods to draw the rooms and/or animation
	//------------------------------------------------
	  
	public void paintComponent (Graphics page)
	{
	 super.paintComponent(page);
	 p2 = (Graphics2D)page;

	 if(turn=="middle")
		 drawroom();

	 if(turn=="aleftturn")
		 drawaleftturn();
	 if(turn=="arightturn")
		 drawarightturn();	 

	 if(turn=="enter")
		 drawenter();

	} // end paint method
	
		
	//------------------------------------------------
	//	draws the default view, looking at a wall that may or may not have a door
	//------------------------------------------------
	public void drawroom ()
	{
		
		 int[] cvarx = {0,w-1,w-1,0}; // center wall
		 int[] cvary = {0,0,h,h};
		 
		 p2.setPaint(new GradientPaint(w/2, 0,new Color(250,250,250), w/2, h, new Color(80,80,80)));
		 p2.fillPolygon(cvarx, cvary, 4);

		 p2.setPaint(new GradientPaint(w/2, 90,new Color(144,0,0), w/2, 340, new Color(40,0,0)));
		
		 current = maze.getCurrent(); //current room coordinates
		 next = maze.getgridturn(); //room coordinates of room currently facing, can give out of bounds coordinates
		 
		 //if the wall leads out of bounds do nothing
		 int[] new1 = {current[0]+next[0],current[1]+next[1]};
		 if (new1[0]<0 || new1[0] >3 || new1[1]<0 || new1[1]>3){}
		 
		 
		 //else if it leads to a room draw a door if not currently in and ending room
		 else if (grid[new1[0]][new1[1]]!=0)
		 {
		p2.fillRect(w/2-w/3/2, 2*h/5, w/3, 3*h/5); //door
		p2.setColor(Color.black);
		p2.drawRect(w/2-w/3/2, 2*h/5, w/3, 3*h/5);//door frame
		p2.fillOval(w/2-200/2+20, h/2+h/5, 10, 10); //doorknob
	}
		 
		 //if in an ending room, draw the princess or tiger
		 if (current[0]==3&&current[1] ==0)
		 {
			 princess  =  princess.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
		        p2.drawImage(princess, w/2-200, h/2-200, null);
	}
		 else if (current[0]==3&&current[1] ==3)
		 {
			 tiger  =  tiger.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
		        p2.drawImage(tiger, w/2-200, h/2-200, null);
	}
		p2.drawRect(1, 1, w-1, h-2); //room frame
		p2.drawLine(1, 1, 1, h-h2); //back left corner
		p2.drawLine(1, h-1, 1, h-1); //left floor 
		p2.drawLine(w-1, 0, w-1, h-1); //back right corner 
		p2.drawLine(w-1, h-1, w, h-1); //right floor
		p2.drawLine(0, h, w-1, h); //center floor
		p2.drawLine(w/2,0,w/2,5); //light fixture
		p2.setColor(new Color(255,255,128));
		p2.fillOval(w/2-5, 5, 10, 10); //light
					   		
	} // end  method
	
	
	//------------------------------------------------
	//draws the right turn
	//------------------------------------------------

	public void drawarightturn ()
	{

		int lcx = (int)(600*Math.sin(num*Math.PI/240));
		int lcy = (int) (125*Math.sin(num*Math.PI/120));
		int ch = (int)Math.ceil(-0.00018*Math.pow(num,3)+ 0.0460*Math.pow(num, 2) + -0.0394*num+ 0.0796);

		int locxl = (int) (w+300*Math.sin(num*Math.PI/120));
		int locyl = (int) (600*Math.cos(num*Math.PI/240)) ;
		int locxr = (int) (-300*Math.sin(num*Math.PI/120));
		int locyr = (int) (-600*Math.cos(num*Math.PI/240));

		int[] floorx = {locxr,w-num*5,locxl};
		int[] floory = {h-locyr,h-lcy,h+locyl};

		int[] lvarx = {0,w,w,0}; //left wall
		 int[] lvary = {0,0,h,h};
		 
		 int[] rvarx = {floorx[1],w,w,floorx[1]}; //right wall
		 int[] rvary = {0,0,h,floory[1]};
		 
		 p2.setPaint(new GradientPaint(w/2, 0,new Color(250,250,250), w/2, h, new Color(80,80,80)));
		 p2.fillPolygon(lvarx, lvary, 4);

			p2.setColor(new Color(102/2, 102, 170/2));

			int[] bvarx = {floorx[0],floorx[1],floorx[2]};
			int[] bvary = {floory[0],floory[1],floory[2]};
			p2.fillPolygon(bvarx, bvary, 3);
			p2.setPaint(new GradientPaint(w/2, 90,new Color(144,0,0), w/2, 340, new Color(40,0,0)));

		p2.drawLine(floorx[0],floory[0],floorx[1],floory[1]); //left floor 
		p2.drawLine(floorx[1],floory[1],floorx[2],floory[2]); //right floor
		p2.drawLine(floorx[1], 0, floorx[1], floory[1]);
					
		p2.setColor(new Color(102/2, 102, 170/2));


		p2.setPaint(new GradientPaint(w/2, 90,new Color(144,0,0), w/2, 340, new Color(40,0,0)));

	p2.setColor(Color.black);
	p2.drawRect(0, 0, w-1, h-1); //room frame
//center
	p2.drawLine(w/2,0,w/2,5); //light fixture
	p2.setColor(new Color(255,255,128));
	p2.fillOval(w/2-5, 5, 10, 10);
	}
	
	//------------------------------------------------
	//draws the left turn
	//------------------------------------------------

	public void drawaleftturn ()
	{
			int lcx = (int)(600*Math.sin(num*Math.PI/240));
			int lcy = (int) (125*Math.sin(num*Math.PI/120));

			int locxl = (int) (0-300*Math.sin(num*Math.PI/120));
			int locyl = (int) (600*Math.cos(num*Math.PI/240)) ;
			int locxr = (int) (w+300*Math.sin(num*Math.PI/120));
			int locyr = (int) (w+600*Math.cos(num*Math.PI/240-Math.PI/2));

			int[] floorx = {locxl,num*5,locxr};
			int[] floory = {h + locyl,h-lcy,h+locyr};

			int[] lvarx = {0,w,w,0}; //left wall
			 int[] lvary = {0,0,h,h};
			 
			 int[] rvarx = {floorx[1],w,w,floorx[1]}; //right wall
			 int[] rvary = {0,0,h,floory[1]};
		
			 p2.setPaint(new GradientPaint(w/2, 0,new Color(250,250,250), w/2, h, new Color(80,80,80)));
			 p2.fillPolygon(lvarx, lvary, 4);

				p2.setColor(new Color(102/2, 102, 170/2));

				int[] bvarx = {floorx[0],floorx[1],floorx[2]};
				int[] bvary = {floory[0],floory[1],floory[2]};
				p2.fillPolygon(bvarx, bvary, 3);
				p2.setPaint(new GradientPaint(w/2, 90,new Color(144,0,0), w/2, 340, new Color(40,0,0)));

			p2.drawLine(floorx[0],floory[0],floorx[1],floory[1]); //left floor 
			p2.drawLine(floorx[1],floory[1],floorx[2],floory[2]); //right floor
			p2.drawLine(floorx[1], 0, floorx[1], floory[1]);
		
						
			p2.setColor(new Color(102/2, 102, 170/2));


			p2.setPaint(new GradientPaint(w/2, 90,new Color(144,0,0), w/2, 340, new Color(40,0,0)));

		p2.setColor(Color.black);
		p2.drawRect(0, 0, w-1, h-1); //room frame
		p2.drawLine(w/2,0,w/2,5); //light fixture
		p2.setColor(new Color(255,255,128));
		p2.fillOval(w/2-5, 5, 10, 10);//light
	}
			
		
	 public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	public void reset()
	{
		num2 = -1;
		leftdoor = false;
	}
	
//------------------------------------------------
//draws the animation for the entering of a door
//------------------------------------------------

public void drawenter ()
{

	// center wall
	 int[] cvarx = {0,w-1,w-1,0}; 
	 int[] cvary = {0,0,h,h};
	 
	 p2.setPaint(new GradientPaint(w/2, 0,new Color(250,250,250), w/2, h, new Color(80,80,80)));
	 p2.fillPolygon(cvarx, cvary, 4);

	 //door
	 p2.setPaint(new GradientPaint(w/2, 90,new Color(144,0,0), w/2, 340, new Color(40,0,0)));
		p2.fillRect(w/2-(w/3/2*num/40), h-(3*h/5*num/40), w/3*num/40, 3*h/5*num/40); //door

	p2.setColor(Color.black);
	 current = maze.getCurrent();
	 next = maze.getgridturn();
	 //if the wall can not lead to another room ie out of bounds do nothing
	 int[] new1 = {current[0]+next[0],current[1]+next[1]};
	 if (new1[0]<0 || new1[0] >3 || new1[1]<0 || new1[1]>3){}
	 
	 //else draw the door animation if a door exists
	 else if (grid[new1[0]][new1[1]]==1)
	 {
	p2.drawRect(w/2-w/3/2 -num*5, 2*h/5-num*6, w/3+num*10, 3*h/5+num*12); //door
	p2.drawRect(w/2-(w/3/2*num/40), h-(3*h/5*num/40), w/3*num/40, 3*h/5*num/40); //door
	p2.fillOval(w/2-(w/3/2-30)*num/40, h-(3*h/5*num/40/7*4), 10, 10); //doorknob
	 }
	 // if it is and end room, draw the princess or tiger
	 if (next[0]==3&&next[1] ==0)
	 {
	        princess  =  princess.getScaledInstance(num*10, num*10, Image.SCALE_SMOOTH);
	        p2.drawImage(princess, w/2-num*5, h/2-num*5, null);
	 }
	 else if (next[0]==3&&next[1] ==3)
	 {
		 tiger  =  tiger.getScaledInstance(num*10, num*10, Image.SCALE_SMOOTH);
	        p2.drawImage(tiger, w/2-num*5, h/2-num*5, null);
    }
	 
	p2.drawRect(1, 1, w-1, h-2); //room frame
	p2.drawLine(1, 1, 1, h-h2); //back left corner
	p2.drawLine(1, h-1, 1, h-1); //left floor 
	p2.drawLine(w-1, 0, w-1, h-1); //back right corner 
	p2.drawLine(w-1, h-1, w, h-1); //right floor
	p2.drawLine(0, h, w-1, h); //center floor
	p2.drawLine(w/2,0,w/2,5); //light fixture
	p2.setColor(new Color(255,255,128));
	p2.fillOval(w/2-5, 5, 10, 10); //light
				   		
} // end  method

} 	
	
// end FillRoomclass


