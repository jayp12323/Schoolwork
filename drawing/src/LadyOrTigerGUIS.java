
import java.awt.*;

import javax.swing.*;

import java.util.*;
import java.awt.event.*;
import java.util.ArrayList;


// This version is for the class - separate out the controller
//  sets up the GUI and other classes

 @SuppressWarnings({ "serial", "unused" })
public class LadyOrTigerGUIS extends JFrame
 {
   
		ActionListener action;
		JButton help;
		JButton start;
		JButton enter;
		JButton midleft;
		JButton midright;
		JButton turn;
		JButton free;
		JButton r2;


		javax.swing.Timer t;
		public int frame=0;

 
 private JTextArea outputArea;
 
 //DECLARE OBJECTS (DO NOT INSTANTIATE HERE) OF THE DRAWROOMS AND CONTROLPANEL CLASSES E.G
 // 
 DrawRooms drawroom;
 int current=0;
 String[] rturns = {"middle","midright","turn","midleft"};
 String[] lturns = {"middle","midleft","turn","midright"};

 
 // I AM GIVING CODE FOR THE JTEXTAREA TO SHOW YOU HOW TO PUT IT IN A SCROLLPANE
//************************************************************************************************
// 
//************************************************************************************************
public LadyOrTigerGUIS() 
 { 
 Container c = getContentPane();
  //   instantiate the JTextArea declared above  to output client names - e.g
   outputArea = new JTextArea(7,60);
   outputArea.setEditable(false);
   outputArea.setBackground(Color.cyan);// where outputArea is the JTextArea
 
   JScrollPane listScrollPane = new JScrollPane(outputArea);
   outputArea.append( "“House of Trials.” \n. The following is an excerpt from the famous short story “The Lady or the Tiger ”" +
	" based on the story \n of a barbaric king in medieval times.  The king built a 21- room house “House of Trials” with the lowest\n" + 
	"level as an arena. The roof of the house is built using a one- way mirror that allows spectators to peek in\n " +
	"A blindfolded prisoner is led into the house and abandoned in one of the rooms, where his blindfold is removed.\n " +
   "In another room a lady waits, and in a third room a tiger snarls.\n "+
  " Unable to hear the cheers and jeers of curious spectators, the prisoner wanders through  the house,\n" +
  " from room to room to room,until he finds either the lady who leads him to marital bliss\n" +
  " or the tiger that . . . . ");

  //INSTANTIATE HERE IN THIS ORDER
   
  // DRAWROOMS  - SEND THE ROOMS ARRAY AS A PARAMETER TO THE DRAWROOMS CONSTRUCTOR
     drawroom = new DrawRooms();
	 
	   
	// INSTANTIATE AN OBJECT OF CONTROLPANEL CLASS (CALL IT CONTROLS) - SEND IT THE OUTPUTAREA, DRAWROOM AND THE ARRAY OF INTS
	  
    // PUT A BORDER AROUND IT
   
		// CREATE THE WELCOME LABEL AND SET ITS FONT  - EXAMPLE BELOW
   	JLabel welcomeLabel = new JLabel("Welcome to the House of Trials");
     	welcomeLabel.setFont (new Font ("Comic Sans", Font.BOLD, 16));
	 // SEE MY GUI SLIDES FOR SYNTAX IN CREATING THE LABEL
   
	 JPanel welcomepane = new JPanel();
		welcomepane.setBorder(BorderFactory.createLoweredBevelBorder());
	    welcomepane.setBackground(java.awt.Color.green);
	    welcomepane.add(welcomeLabel);

	// set up the  WELCOME Panel TO HOLD THE LABEL
	// CREATE A BACKGROUND COLOR FOR IT AND SET ITS BORDER
	
	 
	// YOU WILL NEED SEVERAL PANELS, ONE FOR THE TOP OF THE SCREEN  - CALL IT TOPPANEL
		 JPanel toppanel = new JPanel();
		 action = new ActionListener()
				 {
				
				public void actionPerformed(ActionEvent e) {
					if (e.getActionCommand().equals("turn right")) // help button pressed
			        {
						
						rightturn();
						
			        }
					if (e.getActionCommand().equals("turn left")) // help button pressed
			        {

						leftturn();
			        }
					if(e.getActionCommand().equals("enter"))
					{
					
						drawroom.setturn("middle");
						drawroom.repaint();
					}
					if(e.getActionCommand().equals("midleft"))
					{
					
						drawroom.setturn("midleft");
						drawroom.repaint();
					}
					if(e.getActionCommand().equals("midright"))
					{
					
						drawroom.setturn("midright");
						drawroom.repaint();
					}
					if(e.getActionCommand().equals("turn"))
					{
					
						drawroom.setturn("turn");
						drawroom.repaint();
					}
					if(e.getActionCommand().equals("free"))
					{
					
						drawroom.setturn("free");
						drawroom.repaint();
					}
				
					if(e.getActionCommand().equals("l2"))
					{
					
						firstlturn();					}
				
				
				}
				
			};
		help = new JButton("turn left");
		help.addActionListener(action);
		start = new JButton("turn right");
		start.addActionListener(action);
		enter = new JButton("enter");
		enter.addActionListener(action);
		midleft = new JButton("midleft");
		midleft.addActionListener(action);
		midright = new JButton("midright");
		midright.addActionListener(action);
		turn = new JButton("turn");
		turn.addActionListener(action);
		free = new JButton("free");
		free.addActionListener(action);
		r2 = new JButton("l2");
		r2.addActionListener(action);

		 toppanel.add(help);
		 toppanel.add(start);
		 toppanel.add(enter);
		 toppanel.add(midleft);
		 toppanel.add(midright);
		 toppanel.add(turn);
		 toppanel.add(free);
		 toppanel.add(r2);



		 toppanel.add(listScrollPane);
		 c.add(toppanel,BorderLayout.NORTH);
		 c.add(welcomepane,BorderLayout.SOUTH);

		 // where c is the contentPane
JPanel gridpane = new JPanel();	
gridpane.add(drawroom);
JScrollPane gridScrollPane = new JScrollPane(gridpane);

c.add(gridScrollPane,BorderLayout.CENTER);

	// THE BUTTONS WILL CREATED AND ADDED TO A PANEL IN CONTROLPANEL CLASS SO ALL YOU HAVE TO DO IS ADD
	// THE OBJECT OF THE CONTROLPANEL CLASS - controls - to the TOPPANEL
	
	// CREATE THE TOPPANEL OBJECT TO HOLD THE OBJECT OF THE CONTROLPANEL CLASS(WHICH HOLDS THE BUTTONS) AND THE JTEXTAREA
	
	// ADD THE CONTROLS PANEL OBJECT AND THE SCROLLPANE FOR THE JTEXTAREA  - YOU CAN USE A BORDERLAYOUT OR TRY A BOX LAYOUT

   // GET THE CONTENTPANE AND ADD THE TOPPANEL(NORTH) AND WELCOME PANEL(SOUTH)
	
	// DRAWROOMS WILL BE IN THE CENTER BUT WE HAVE NOT DONE THAT YET.  
	   c.setPreferredSize(new Dimension(850,700));
	   

 }
void rightturn()
{
//	ActionListener action = new ActionListener()
//	{
//
//		public void actionPerformed(ActionEvent e) {
//			t.stop();
//	    	drawroom.setturn(rturns[++current % rturns.length]);
//			drawroom.repaint();		
//		}
//		
//	};
//			t = new javax.swing.Timer(1000/60,action);
//
//		    	current = turnfind(drawroom.getturn(),"r");
//		    	drawroom.setturn(rturns[++current % rturns.length]);
//				drawroom.repaint();	
//				t.start();
	
	ActionListener action = new ActionListener()
	{

		public void actionPerformed(ActionEvent e) {
			if (frame==81){
				
				frame=0;
				drawroom.setNum(0);
				t.stop();
			}
			else
			{
	    	drawroom.setNum(frame++);
	    	drawroom.setturn("r2");
			drawroom.repaint();
			}
		}
		
	};
			t = new javax.swing.Timer(1000/80,action);
				t.start();

    
}

void leftturn()
{
	ActionListener action = new ActionListener()
	{

		public void actionPerformed(ActionEvent e) {

			if (frame==81){
				
				frame=0;
				drawroom.setNum(0);
				t.stop();
				drawroom.setturn("midright");
				drawroom.reset();
				drawroom.repaint();
			}
			else
			{
	    	drawroom.setNum(frame++);
	    	drawroom.setturn("aleftturn");
			drawroom.repaint();
			}
		}
		
	};
			t = new javax.swing.Timer(1000/80,action);
				t.start();


}

void firstlturn()
{
	ActionListener action = new ActionListener()
	{

		public void actionPerformed(ActionEvent e) {

			if (frame==41){
				
				frame=0;
				drawroom.setNum(0);
				t.stop();
//				drawroom.setturn("midleft");
				drawroom.reset();
//				drawroom.repaint();
//				leftturn();
				
			}
			else
			{
	    	drawroom.setNum(frame++);
	    	drawroom.setturn("l2");
			drawroom.repaint();
			}
		}
		
	};
			t = new javax.swing.Timer(1000/80,action);
				t.start();


}

    public int turnfind(String s,String rol)
    {
    	boolean found=false;
    	int j=0;
    	int pos = 0;
    	String[] turns;
    	if (rol=="r")
    		turns = rturns;
    	else
    		turns = lturns;
    	while(found==false && j<turns.length)
    	{
    		if (turns[j].equalsIgnoreCase(s))
    		{
    			found =true;
    			pos =j;
    		}
    		j++;
    	}
    	if(found==false)
    		return 0;
    	else
    		return pos;
    	
    }

 
 }
 
	

