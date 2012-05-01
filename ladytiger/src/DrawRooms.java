
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
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.net.*;


//********************************************************************
// CREATE A TWO-D ARRAY OF ROOMS(LIKE THE ONE OF SQUARES in Checkers

//
// DECLARE THREE IMAGES AS BELOW  - YOU WILL USE IMAGEICONS
//
// I GIVE CODE ON HOW TO SCALE THE IMAGE  - this is easiest way -  can also do it with TWO D graphics method


public class DrawRooms  extends JPanel
{
	Room[][] rooms =  new Room[20][22];

	 int x1 = 0,y1=0;
	 int[][] Arooms;

	 
	 int numroom = 22;
	 Image image1,image2,image3;
	 

	  private URL url;


	// declare instance variables, fonts used and images  - (they will all be ImageIcons)
	
	// also a constant for number of rooms(22)   - 

	
	//DECLARE BUT DO NOT INSTANTIATE A TWO D ARRAY OF INTEGERS - AROOMS - TO STORE THE ARRAY SENT IN THE CONSTRUCTOR 
	// BELOW FROM THE GUI CLASS
	
		// CONSTRUCTOR
	public DrawRooms( int[][] arooms)
	   {
		 try
		 {
		  image1 = ImageIO.read(new File("tiger.gif"));
		  image2 = ImageIO.read(new File("prisoner.gif"));
		  image3 = ImageIO.read(new File("princess.jpg"));

		 }
		 catch(IOException e)
		 {
			 ;
		 }
//		 Image image;
		 
		// THIS WILL ASSIGN A REFERENCE TO ARRAY OF INTS CREATED  IN GUI CLASS
	    this.Arooms = arooms;
		
		// YOU NEED TO SET THE PREFERRED SIZE TO GET THE DISPLAY THE SIZE YOU WANT IT.
		 setPreferredSize(new Dimension( 620, 620));
		 
	//	  CREATE  THREE  IMAGEICONS E.G. THIS SHOWs HOW TO SCALE IT

		// create the imageicon
// get an image object from it
	        image1  =  image1.getScaledInstance(28, 28, Image.SCALE_SMOOTH);// reduce its size
	        image2  =  image2.getScaledInstance(28, 28, Image.SCALE_SMOOTH);// reduce its size
	        image3  =  image3.getScaledInstance(28, 28, Image.SCALE_SMOOTH);// reduce its size

	        
//		  
		  
//       setBackground(Color.yellow);

 
	  }
	  
	  // this method is called from the Controller class to reset where the prisoner will be at the end of run.
	  
	  // repaint() this will cause paintComponent to be called again and the prisoner to be moved to position x1
	  public void MoveMan( int x1, int y1)
	  {
	    this.x1 = x1;
	    this.y1 = y1;
		 repaint();
	}
	//------------------------------------------------
	//	this is just like checkers - 
	// calls fillRooms method and sets background color
	//------------------------------------------------
	public void paintComponent (Graphics page)
	{
	 super.paintComponent(page);
	   
	 fillRooms(page,Color.black);
		
	} // end paint method
	
	//------------------------------------------------
	//	A method to fill the grid with rooms  - use same method you used in Checkers fillboard
	//------------------------------------------------
	public void fillRooms (Graphics page, Color color1)
	{
		 
			for (int row=0; row < rooms.length;row++)
		 	{
				for (int col=0; col < rooms[row].length;col++)
				{
					
					
						rooms[row][col] = new Room(row,col,null); //create square object
						rooms[row][col].draw(page); //draw square
						if (Arooms[row][col]==1)
							rooms[row][col].drawnum(page);
						
						if(row==4 && col==4)
						{
							rooms[row][col].setimagename(image1);
							rooms[row][col].drawimg(page);
						}

						if(row==10 && col==10)
						{
							rooms[row][col].setimagename(image3);
							rooms[row][col].drawimg(page);
						}
						
						if(row==y1 && col==x1)
						{
							rooms[row][col].setimagename(image2);
							rooms[row][col].drawimg(page);
						}
				}
				
		 	}


		// declare variables 
		// use nested for loops
		// fills the panel just like in checkers 
		
		//below is example of drawing an icon in row 4 col 4
		// do same for other three icons
							
					// to place the images of the Tiger and the hero and lady
					// when you get the location (the row and col) where you want to put an image
					// use
					 // where x and y is the location of the room you are currently creating
					  

					
				// if the room has a door draw a "1" on it
				// 	TO DO THIS YOU WILL USE THE ARRAYOF INTS FROM THE CONSTRUCTOR THAT WAS CREATED IN  GUI CLASS
				// MAKE SURE THE ROW AND COL ARE WITHING THE BOUNDS OF THE ARRAY AND MATCH
				// THE ROW AND COL IN ROOMS ARRAY TO THIS ARRAY e.g, if (rooms[row][col] == 1) THEN PUT A "1" IN THE ROOM IN THE GRID
				// YOU CAN SET THE FONT AND THE COLOR
				// use method drawString()  (look it up)
					  						
				   		
	} // end  method
	
} // end FillRoomclass

