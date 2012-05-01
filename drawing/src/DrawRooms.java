
//********************************************************************
//  DrawRooms - Application version  -     
//  Draws room - marks a 1 for those which have a door
// places prisoner , tiger and lily images on the grid      
//  Sets up a grid of rooms   -
// USE CODE FROM CHECKERS TO SET UP THE GRID
//********************************************************************

import java.awt.*;

import javax.swing.*;
import java.util.*;
import java.util.Timer;
import java.awt.event.*;
import java.util.ArrayList;
import java.net.*;


//********************************************************************
// CREATE A TWO-D ARRAY OF ROOMS(LIKE THE ONE OF SQUARES in Checkers

//
// DECLARE THREE IMAGES AS BELOW  - YOU WILL USE IMAGEICONS
//
// I GIVE CODE ON HOW TO SCALE THE IMAGE  - this is easiest way -  can also do it with TWO D graphics method


@SuppressWarnings({ "unused", "serial" })
public class DrawRooms  extends JPanel
{


	int x1 = 0;
	 int num2 = -1;

	 int[][] Arooms = 
 {
 	{1,1,1,0,1,1,0,0,0,1,1,1,1},
	{1,0,1,1,1,0,1,1,1,1,0,0,1},
	{0,0,0,0,1,0,1,0,1,0,1,0,0},
	{1,1,1,0,1,1,1,0,1,0,1,1,1},
	{1,0,1,0,0,0,0,1,1,1,0,0,1},
	{1,0,1,1,1,1,1,1,0,1,1,1,1},
	{1,0,0,0,0,0,0,0,0,0,0,0,0},
	{1,1,1,1,1,1,1,1,1,1,1,1,1}
};
;
	 int num;


	int w,h,s2,h2;
	 Graphics2D p2;
	 String turn = "middle";
	 int numroom = 22;
	 ImageIcon imgTigerSmall;
	int corx = 0;
	int corleft = 0;
	int corright = 0;
    boolean leftdoor = false;



	// declare instance variables, fonts used and images  - (they will all be ImageIcons)
	
	// also a constant for number of rooms(22)   - 

	
	//DECLARE BUT DO NOT INSTANTIATE A TWO D ARRAY OF INTEGERS - AROOMS - TO STORE THE ARRAY SENT IN THE CONSTRUCTOR 
	// BELOW FROM THE GUI CLASS
	
		// CONSTRUCTOR
	public DrawRooms()
	   {
//		 Image image1 = null;
		 
		// THIS WILL ASSIGN A REFERENCE TO ARRAY OF INTS CREATED  IN GUI CLASS
//	    this.Arooms = Arooms;
	    w = 800;
	    h = 470;
		s2 = (int)Math.round(Math.sqrt(2));
		h2 = (int)Math.round((h-353)/s2);
		
		// YOU NEED TO SET THE PREFERRED SIZE TO GET THE DISPLAY THE SIZE YOU WANT IT.
		 setPreferredSize(new Dimension( w, h));
		 
	//	  CREATE  THREE  IMAGEICONS E.G. THIS SHOWs HOW TO SCALE IT

//		  ImageIcon imgTigerSmall = new ImageIcon();  // create the imageicon
//			Image image = imgTigerSmall.getImage(); // get an image object from it
//	        image  = image.getScaledInstance(38, 38, Image.SCALE_SMOOTH);// reduce its size
//	       imgTigerSmall = new ImageIcon(image); // change it back to an imageIcon
//		  
		  
//       setBackground(Color.yellow);

 
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
	//	this is just like checkers - 
	// calls fillRooms method and sets background color
	//------------------------------------------------
	public void paintComponent (Graphics page)
	{
	 super.paintComponent(page);
	 p2 = (Graphics2D)page;

	 //drt drawright = new drt(p2,w);
	// t.schedule(drawright, 4000);
	 if(turn=="middle")
		 drawroom();
	 if(turn=="turn")
		 drawturn();
	 if(turn=="midright")
		 drawmidrightturn();
	 if(turn=="midleft")
		 drawmidleftturn();
	 if(turn=="aleftturn")
		 drawaleftturn();
	 if(turn=="arightturn")
		 drawarightturn();	 
	 if(turn=="free")
		 drawfree();
	 if(turn=="r2")
		 drawac2rturn();	
	 if(turn=="l2")
		 drawac2lturn();
	 //drawleftturn(p2);
//	 drawrightturn(p2);
		
	} // end paint method
	
		
	//------------------------------------------------
	//	A method to fill the grid with rooms  - use same method you used in Checkers fillboard
	//------------------------------------------------
	public void drawroom ()
	{
//		 int[] lvarx = {0,w/5,w/5,0}; //left wall
//		 int[] lvary = {0,0,h-h2,h-1};
//		 
//		 int[] rvarx = {w/5*4,w,w,w/5*4}; //right wall
//		 int[] rvary = {0,0,h-1,h-h2};
		 
		 int[] cvarx = {0,w-1,w-1,0}; // center wall
		 int[] cvary = {0,0,h-h2,h-h2};
		 
p2.setPaint(new GradientPaint(w/2, 0,new Color(250,250,250), w/2, h, new Color(80,80,80)));
//p2.fillPolygon(lvarx, lvary, 4);
//p2.fillPolygon(rvarx, rvary, 4);
p2.fillPolygon(cvarx, cvary, 4);
p2.setColor(new Color(102/2, 102, 170/2));
//floor
int[] bvarx = {0,w,w,0};
int[] bvary = {h-h2,h-h2,h-1,h-1};
p2.fillPolygon(bvarx, bvary, 4);




p2.setPaint(new GradientPaint(w/2, 90,new Color(144,0,0), w/2, 340, new Color(40,0,0)));

//		 p2.setColor(new Color(144,0,0));
		 
		p2.fillRect(w/2-160/2, h-h2-270, 160, 270); //door
		p2.setColor(Color.black);
		p2.drawRect(w/2-160/2, h-h2-270, 160, 270);//door frame
		p2.fillOval(w/2-160/2+10, h-h2-130, 10, 10); //doorknob
		p2.drawRect(1, 0, w-1, h-1); //room frame
		p2.drawLine(1, 0, 1, h-h2); //back left corner
		p2.drawLine(1, h-h2, 1, h-1); //left floor 
		p2.drawLine(w-1, 0, w-1, h-h2); //back right corner 
		p2.drawLine(w-1, h-h2, w, h-1); //right floor
		p2.drawLine(0, h-h2, w-1, h-h2); //center floor
		p2.drawLine(w/2,0,w/2,5); //light fixture
		p2.setColor(new Color(255,255,128));
		p2.fillOval(w/2-5, 5, 10, 10); //light
					   		
	} // end  method
	
	public void drawturn ()
	{
		
		 int[] lvarx = {0,w/2,w/2,0}; //left wall
		 int[] lvary = {0,0,h/4*3,h};
		 
		 int[] rvarx = {w/2,w,w,w/2}; //right wall
		 int[] rvary = {0,0,h,h/4*3};
		
		 p2.setPaint(new GradientPaint(w/2, 0,new Color(250,250,250), w/2, h, new Color(80,80,80)));
		 p2.fillPolygon(lvarx, lvary, 4);
		 p2.fillPolygon(rvarx, rvary, 4);
		
		
		p2.setColor(new Color(102/2, 102, 170/2));
		//floor
		int[] bvarx = {w/2,w,0};
		int[] bvary = {h/4*3,h,h};
		p2.fillPolygon(bvarx, bvary, 3);
		
		
		p2.setPaint(new GradientPaint(w/2, 90,new Color(144,0,0), w/2, 340, new Color(40,0,0)));
		
		//right door
		int[] rdvarx = {w-80,w,w,w-80};
		int[] rdvary = {h-(24+270),h-270,h,h-24};
		//left door
		int[] ldvarx = {80,0,0,80};
		int[] ldvary = {h-(24+270),h-270,h,h-24};
		p2.fillPolygon(rdvarx,rdvary,4); //door
		p2.fillPolygon(ldvarx, ldvary, 4);
		p2.setColor(Color.black);
		p2.fillOval(w-80+5, h-(24+270/2), 10, 10); //doorknob		

		
		
		
		
		p2.drawRect(0, 0, w-1, h-1); //room frame
		p2.drawLine(w/2,h/4*3 , 0, h-1); //left floor 
		p2.drawLine(w/2, h/4*3, w-1, h-1); //right floor
		p2.drawLine(w/2, 0, w/2, h/4*3); //center
		
		p2.drawLine(w/2,0,w/2,5); //light fixture
		p2.setColor(new Color(255,255,128));
		p2.fillOval(w/2-5, 5, 10, 10); //light
		

	}
	
	
	public void drawmidrightturn ()
	{

		
		 int[] lvarx = {0,3*w/4,3*w/4,0}; //left wall
		 int[] lvary = {0,0,h/3*4,h};
		 
		 int[] rvarx = {w/4*3,w,w,w/4*3}; //right wall
		 int[] rvary = {0,0,h,h/3*4};
		 
		
		 p2.setPaint(new GradientPaint(w/2, 0,new Color(250,250,250), w/2, h, new Color(80,80,80)));
		 p2.fillPolygon(lvarx, lvary, 4);
		 p2.fillPolygon(rvarx, rvary, 4);
		 
			p2.setColor(new Color(102/2, 102, 170/2));
			//floor
			int[] bvarx = {w/4*3,w,0};
			int[] bvary = {h/4*3,h,h};
			p2.fillPolygon(bvarx, bvary, 3);
		 
			p2.setPaint(new GradientPaint(w/2, 90,new Color(144,0,0), w/2, 340, new Color(40,0,0)));

			//right door
			int[] rdvarx = {170,330,330,170};
			int[] rdvary = {h-(33+270),h-(270+65),h-65,h-33};
			p2.fillPolygon(rdvarx,rdvary,4); //door
			p2.setColor(Color.black);
			p2.fillOval(164+20, h-(38+270/2), 10, 10); //doorknob	
//		 
		 
		p2.setColor(Color.black);
		p2.drawRect(0, 0, w-1, h-1); //room frame
		p2.drawLine(w/4*3,h/4*3 , 0, h-1); //left floor 
		p2.drawLine(w/4*3, h/4*3, w-1, h-1); //right floor
		p2.drawLine(w/4*3, 0, w/4*3, h/4*3); //center
		p2.drawLine(w/2,0,w/2,5); //light fixture
		p2.setColor(new Color(255,255,128));
		p2.fillOval(w/2-5, 5, 10, 10); //light

		
	}
	
	public void drawmidleftturn ()
	{
		if (num % 4 == 0)
			corx++;
		if(num == 20)
			corleft++;
		if((num+1) % 2 ==0 || (num+1) % 11 == 0)
			corright++;

		
		int[] lvarx = {0,1*w/4,1*w/4,0}; //left wall
		 int[] lvary = {0,0,h/4*3,h};
		 
		 int[] rvarx = {w/4*1,w,w,w/4}; //right wall
		 int[] rvary = {0,0,h,h/4*3};
		 
		 
		
		 p2.setPaint(new GradientPaint(w/2, 0,new Color(250,250,250), w/2, h, new Color(80,80,80)));
		 p2.fillPolygon(lvarx, lvary, 4);
		 p2.fillPolygon(rvarx, rvary, 4);
		 
			p2.setColor(new Color(102/2, 102, 170/2));
			//floor
			int[] bvarx = {w/4,w,0};
			int[] bvary = {h/4*3,h,h};
			p2.fillPolygon(bvarx, bvary, 3);
		 
			p2.setPaint(new GradientPaint(w/2, 90,new Color(144,0,0), w/2, 340, new Color(40,0,0)));

//			//right door
			int[] rdvarx = {w-170+(4*num+corx),w-330+(4*num)+corx,w-330+(4*num)+corx,w-170+(4*num)+corx};
			int[] rdvary = {h-(33+270)+(num+corright),h-(270+65)+(num+corleft),h-65+(num+corleft),h-33+(num+corright)};
			p2.fillPolygon(rdvarx,rdvary,4); //door
			p2.setColor(Color.black);
			p2.fillOval(w-(330-10), h-(40+270/2+10), 10, 10); //doorknob	
//		 
		 
		p2.setColor(Color.black);
		p2.drawRect(0, 0, w-1, h-1); //room frame
		p2.drawLine(w/4,h/4*3 , 0, h-1); //left floor 
		p2.drawLine(w/4, h/4*3, w-1, h-1); //right floor
		p2.drawLine(w/4, 0, w/4, h/4*3); //center
		p2.drawLine(w/2,0,w/2,5); //light fixture
		p2.setColor(new Color(255,255,128));
		p2.fillOval(w/2-5, 5, 10, 10); //light

		
	}
	
	public void drawarightturn ()
	{

		 int[] lvarx = {0,3*w/4-(w/2/80*num),3*w/4-(w/2/80*num),0}; //left wall
		 int[] lvary = {0,0,h/3*4,h};
		 
		 int[] rvarx = {w/4*3-(w/2/80*num),w,w,w/4*3-(w/2/80*num)}; //right wall
		 int[] rvary = {0,0,h,h/3*4};
		 
		
		 p2.setPaint(new GradientPaint(w/2, 0,new Color(250,250,250), w/2, h, new Color(80,80,80)));
		 p2.fillPolygon(lvarx, lvary, 4);
		 p2.fillPolygon(rvarx, rvary, 4);
		 
			p2.setColor(new Color(102/2, 102, 170/2));
			//floor
			int[] bvarx = {w/4*3-(w/2/80*num),w,0};
			int[] bvary = {h/4*3,h,h};
			p2.fillPolygon(bvarx, bvary, 3);
		 
			p2.setPaint(new GradientPaint(w/2, 90,new Color(144,0,0), w/2, 340, new Color(40,0,0)));
			int xcorrect = (int) Math.round(6.25*num);
			int yrcorrect = -1*(33)+(int)Math.round(1.425*num);
			int ylcorrect = -1*(65)+(int)Math.round(1.025*num);			

			int[] rdvarx = {w-170+xcorrect,w-330+xcorrect,w-330+xcorrect,w-170+xcorrect};
			int[] rdvary = {h-270+yrcorrect,h-270+ylcorrect,h+ylcorrect,h+yrcorrect};
//			if(!leftdoor && num2 == -1)
//			{
//				num2+=2;
//			}
//			System.out.println(num2);
			int ylcorrectl = -(int)Math.ceil(.000277*Math.pow(num,3)-0.0557*Math.pow(num, 2) + 4.6249*num-122.765);
			int yrcorrectl = -(int)Math.ceil(.00017966*Math.pow(num,3)-0.0361*Math.pow(num, 2) + 2.9886*num-38.1004);
			xcorrect = (int) Math.round(-230+5*(num));

			
		

				int[] ldvarx = {xcorrect,160+xcorrect,160+xcorrect,xcorrect};
				int[] ldvary = {h-270+ylcorrectl,h-270+yrcorrectl,h+yrcorrectl,h+ylcorrectl};
				if(ldvarx[1]>=0)
				{
				p2.fillPolygon(ldvarx,ldvary,4); //door
	//			p2.setColor(Color.black);
	//			p2.fillOval(w-(164+90), h-(40+270/2), 10, 10); //doorknob
//				System.out.println(yrcorrectl);

				
			
			}
			for (int i = 0;i<rdvarx.length;i++)
			{
				if (rdvarx[i]>w || rdvary[i]>h)
				{
					leftdoor = true;
				}
				

								
			}
			p2.fillPolygon(rdvarx,rdvary,4); //door
			p2.setColor(Color.black);
//			p2.fillOval(w-170+xcorrect, h-270/2+yrcorrect, 10, 10); //doorknob	
//		 
		 
		p2.setColor(Color.black);
		p2.drawRect(0, 0, w-1, h-1); //room frame
		p2.drawLine(w/4*3-(w/2/80*num),h/4*3 , 0, h-1); //left floor 
		p2.drawLine(w/4*3-(w/2/80*num), h/4*3, w-1, h-1); //right floor
		p2.drawLine(w/4*3-(w/2/80*num), 0, w/4*3-(w/2/80*num), h/4*3); //center
		p2.drawLine(w/2,0,w/2,5); //light fixture
		p2.setColor(new Color(255,255,128));
		p2.fillOval(w/2-5, 5, 10, 10); //light

		
	}
	
	public void drawaleftturn ()
	{

		if (num % 4 == 0)
			corx++;
		if(num == 20)
			corleft++;
		if((num+1) % 3 ==0 || (num+1) % 10 == 0)
			corright++;

		int[] lvarx = {0,1*w/4+(w/2/80*num),1*w/4+(w/2/80*num),0}; //left wall
		 int[] lvary = {0,0,h/4*3,h};
		 
		 int[] rvarx = {w/4*1+(w/2/80*num),w,w,w/4+(w/2/80*num)}; //right wall
		 int[] rvary = {0,0,h+37,h/4*3};
		 
		 
		
		 p2.setPaint(new GradientPaint(w/2, 0,new Color(250,250,250), w/2, h, new Color(80,80,80)));
		 p2.fillPolygon(lvarx, lvary, 4);
		 p2.fillPolygon(rvarx, rvary, 4);
			p2.setColor(new Color(102/2, 102, 170/2));
			//floor
			int[] bvarx = {w/4+(w/2/80*num),w,0};
			int[] bvary = {h/4*3,h+37,h};
			p2.fillPolygon(bvarx, bvary, 3);
		 
			p2.setPaint(new GradientPaint(w/2, 90,new Color(144,0,0), w/2, 340, new Color(40,0,0)));
			int xcorrect = (int) Math.round(6.25*num);
			int yrcorrect = -1*(33)+(int)Math.round(1.425*num);
			int ylcorrect = -1*(65)+(int)Math.round(1.025*num);			

			int[] rdvarx = {w-170+xcorrect,w-330+xcorrect,w-330+xcorrect,w-170+xcorrect};
			int[] rdvary = {h-270+yrcorrect,h-270+ylcorrect,h+ylcorrect,h+yrcorrect};
//			if(!leftdoor && num2 == -1)
//			{
//				num2+=2;
//			}
//			System.out.println(num2);
			int ylcorrectl = -(int)Math.ceil(.000277*Math.pow(num,3)-0.0557*Math.pow(num, 2) + 4.6249*num-122.765);
			int yrcorrectl = -(int)Math.ceil(.00017966*Math.pow(num,3)-0.0361*Math.pow(num, 2) + 2.9886*num-38.1004);
			
			int wcx = (int)Math.ceil(-0.00028*Math.pow(num,3)+ 0.0154*Math.pow(num, 2) +  4.3715*num+186.7399);
			int wcy = (int)Math.ceil(0.00035*Math.pow(num,3)+-0.0088*Math.pow(num, 2) +  1.6078*num+34.7041);

			xcorrect = (int) Math.round(-230+5*(num));

			
		

				int[] ldvarx = {xcorrect,160+xcorrect,160+xcorrect,xcorrect};
				int[] ldvary = {h-270+ylcorrectl,h-270+yrcorrectl,h+yrcorrectl,h+ylcorrectl};
				if(ldvarx[1]>=0)
				{
				p2.fillPolygon(ldvarx,ldvary,4); //door
	//			p2.setColor(Color.black);
	//			p2.fillOval(w-(164+90), h-(40+270/2), 10, 10); //doorknob
//				System.out.println(yrcorrectl);

				
			
			}
			for (int i = 0;i<rdvarx.length;i++)
			{
				if (rdvarx[i]>w || rdvary[i]>h)
				{
					leftdoor = true;
				}
				

								
			}
			p2.fillPolygon(rdvarx,rdvary,4); //door
			p2.setColor(Color.black);
//			p2.fillOval(w-170+xcorrect, h-270/2+yrcorrect, 10, 10); //doorknob	
			
	
//		 
		 
		p2.setColor(Color.black);
		p2.drawRect(0, 0, w-1, h-1); //room frame
		p2.drawLine(w/4+(5*num),h/4*3 , 0, h-1); //left floor 
		p2.drawLine(w/4+(5*num), h/4*3, w+wcx, h+wcy); //right floor
		p2.drawLine(w/4+(5*num), 0, w/4+(5*num), h/4*3); //center
		p2.drawLine(w/2,0,w/2,5); //light fixture
		p2.setColor(new Color(255,255,128));
		p2.fillOval(w/2-5, 5, 10, 10);
	}//light
		
	public void drawac2rturn ()
	{


		 int[] lvarx = {0,w-5*num,w-5*num,0}; //left wall
		 int[] lvary = {0,0,h/3*4,h};
		 
		 int[] rvarx = {w-5*num,w,w,w-5*num}; //right wall
		 int[] rvary = {0,0,h,h/3*4};
				 
		p2.setPaint(new GradientPaint(w/2, 0,new Color(250,250,250), w/2, h, new Color(80,80,80)));
		p2.fillPolygon(lvarx, lvary, 4);
		p2.fillPolygon(rvarx, rvary, 4);
		p2.setColor(new Color(102/2, 102, 170/2));
		//floor
		int[] bvarx = {0,w-5*num,w,0};
		int[] bvary = {h/4*3,h/4*3,h-1,h-1};
		p2.fillPolygon(bvarx, bvary, 4);
//	
//	
//	
//	
//		p2.setPaint(new GradientPaint(w/2, 90,new Color(144,0,0), w/2, 340, new Color(40,0,0)));
//	
//	//			 p2.setColor(new Color(144,0,0));
//				 
//				p2.fillRect(w/2-160/2, h/5*4-270, 160, 270); //door
				p2.setColor(Color.black);
//				p2.drawRect(w/2-160/2, h/5*4-270, 160, 270);//door frame
//				p2.fillOval(w/2-160/2+10, h/5*4-130, 10, 10); //doorknob
				p2.drawRect(0, 0, w, h-1); //room frame
//				p2.drawLine(w/5, 0, w/5, h/5*4); //back left corner
//				p2.drawLine(w/5, h/5*4, 0, h-1); //left floor 
				p2.drawLine(w-5*num, 0, w-5*num, h/4*3); //back right corner 
				p2.drawLine(w-5*num, h/4*3, w, h-1); //right floor
				p2.drawLine(w-5*num, h/4*3, 0, h/4*3); //center floor
				p2.drawLine(w/2,0,w/2,5); //light fixture
				p2.setColor(new Color(255,255,128));
				p2.fillOval(w/2-5, 5, 10, 10); //light
	


		
	}
	
	public void drawac2lturn ()
	{


		 int[] lvarx = {0,5*num,5*num,0}; //left wall
		 int[] lvary = {0,0,h/3*4,h};
		 
		 int[] rvarx = {5*num,w,w,5*num}; //right wall
		 int[] rvary = {0,0,h,h/3*4};
				 
		p2.setPaint(new GradientPaint(w/2, 0,new Color(250,250,250), w/2, h, new Color(80,80,80)));
		p2.fillPolygon(lvarx, lvary, 4);
		p2.fillPolygon(rvarx, rvary, 4);
		p2.setColor(new Color(102/2, 102, 170/2));
		//floor
		int[] bvarx = {0,5*num,w,w};
		int[] bvary = {h,h/4*3,h/4*3+(h-h/4*3+37)*num/40,h};
		p2.fillPolygon(bvarx, bvary, 4);
//	
//	
//	
//	
		p2.setPaint(new GradientPaint(w/2, 90,new Color(144,0,0), w/2, 340, new Color(40,0,0)));
	
	//			 p2.setColor(new Color(144,0,0));
				int lcor =(int)Math.ceil(0.00004*Math.pow(num,3)+ 0.0031*Math.pow(num, 2) +  1.5420*num+-0.0083);
				int rcor = (int)Math.ceil(0.00009*Math.pow(num,3)+ 0.0073*Math.pow(num, 2) +  2.3167*num+-0.0193);
				
				int[] rdvarx = {320+(int)Math.ceil(3.75*num),320+(int)Math.ceil(3.75*num)+160,320+(int)Math.ceil(3.75*num)+160,320+(int)Math.ceil(3.75*num)};
				int[] rdvary = {h-118+lcor-270,h-118+rcor-270,h-118+rcor,h-118+lcor};
				
				p2.fillPolygon(rdvarx,rdvary,4); //door
				p2.setColor(Color.black);
//				p2.drawRect(w/2-160/2, h/5*4-270, 160, 270);//door frame
//				p2.fillOval(w/2-160/2+10, h/5*4-130, 10, 10); //doorknob
				p2.drawRect(0, 0, w, h-1); //room frame
				p2.drawLine(5*num, h/4*3,w, h/4*3+(h-h/4*3+37)*num/40); //back right corner 
				p2.drawLine(5*num, h/4*3, 0, h-1); //right floor
				p2.drawLine(5*num, h/4*3, 5*num, 0); //center floor
				p2.drawLine(w/2,0,w/2,5); //light fixture
				p2.setColor(new Color(255,255,128));
				p2.fillOval(w/2-5, 5, 10, 10); //light
		
	}

	public void drawal3()
	{


			if (num % 4 == 0)
				corx++;
			if(num == 20)
				corleft++;
			if((num+1) % 3 ==0 || (num+1) % 10 == 0)
				corright++;

			int[] lvarx = {0,1*w/4+(w/2/80*num),1*w/4+(w/2/80*num),0}; //left wall
			 int[] lvary = {0,0,h/4*3,h};
			 
			 int[] rvarx = {w/4*1+(w/2/80*num),w,w,w/4+(w/2/80*num)}; //right wall
			 int[] rvary = {0,0,h,h/4*3};
			 
			 
			
			 p2.setPaint(new GradientPaint(w/2, 0,new Color(250,250,250), w/2, h, new Color(80,80,80)));
			 p2.fillPolygon(lvarx, lvary, 4);
			 p2.fillPolygon(rvarx, rvary, 4);
				p2.setColor(new Color(102/2, 102, 170/2));
				//floor
				int[] bvarx = {w/4+(w/2/80*num),w,0};
				int[] bvary = {h/4*3,h,h};
				p2.fillPolygon(bvarx, bvary, 3);
			 
				p2.setPaint(new GradientPaint(w/2, 90,new Color(144,0,0), w/2, 340, new Color(40,0,0)));
				int xcorrect = (int) Math.round(6.25*num);
				int yrcorrect = -1*(33)+(int)Math.round(1.425*num);
				int ylcorrect = -1*(65)+(int)Math.round(1.025*num);			

				int[] rdvarx = {w-170+xcorrect,w-330+xcorrect,w-330+xcorrect,w-170+xcorrect};
				int[] rdvary = {h-270+yrcorrect,h-270+ylcorrect,h+ylcorrect,h+yrcorrect};
//				if(!leftdoor && num2 == -1)
//				{
//					num2+=2;
//				}
//				System.out.println(num2);
				int ylcorrectl = -(int)Math.ceil(.000277*Math.pow(num,3)-0.0557*Math.pow(num, 2) + 4.6249*num-122.765);
				int yrcorrectl = -(int)Math.ceil(.00017966*Math.pow(num,3)-0.0361*Math.pow(num, 2) + 2.9886*num-38.1004);
				xcorrect = (int) Math.round(-230+5*(num));

				
			

					int[] ldvarx = {xcorrect,160+xcorrect,160+xcorrect,xcorrect};
					int[] ldvary = {h-270+ylcorrectl,h-270+yrcorrectl,h+yrcorrectl,h+ylcorrectl};
					if(ldvarx[1]>=0)
					{
					p2.fillPolygon(ldvarx,ldvary,4); //door
		//			p2.setColor(Color.black);
		//			p2.fillOval(w-(164+90), h-(40+270/2), 10, 10); //doorknob
//					System.out.println(yrcorrectl);

					
				
				}
				for (int i = 0;i<rdvarx.length;i++)
				{
					if (rdvarx[i]>w || rdvary[i]>h)
					{
						leftdoor = true;
					}
					

									
				}
				p2.fillPolygon(rdvarx,rdvary,4); //door
				p2.setColor(Color.black);
//				p2.fillOval(w-170+xcorrect, h-270/2+yrcorrect, 10, 10); //doorknob	
				
		
//			 
			 
			p2.setColor(Color.black);
			p2.drawRect(0, 0, w-1, h-1); //room frame
			p2.drawLine(w/4+(w/2/80*num),h/4*3 , 0, h-1); //left floor 
			p2.drawLine(w/4+(w/2/80*num), h/4*3, w-1, h-1); //right floor
			p2.drawLine(w/4+(w/2/80*num), 0, w/4+(w/2/80*num), h/4*3); //center
			p2.drawLine(w/2,0,w/2,5); //light fixture
			p2.setColor(new Color(255,255,128));
			p2.fillOval(w/2-5, 5, 10, 10);
		}//light

	
	
	
		public void drawfree ()
		{

			p2.draw3DRect(50, 50, 50, 50, true);
			 
	}
		
		
		
	 public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	public void reset()
	{
		corx= corright = corleft=0;
		num2 = -1;
		leftdoor = false;
	}
	
} 	
	
// end FillRoomclass


