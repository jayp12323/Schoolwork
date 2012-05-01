
/*We implement the rooms as a two- dimensional array of rooms. 

The rows and columns of rooms are indexed by the room numbers. If i and j are two room numbers, then
•	if there is a door between room i and room j --  rooms( i, j) =1
•	if there is no door between room i and room j -- rooms( i, j) =0

The prisoner “visits” rooms until he discovers the lady or the tiger. 

To ensure that the program eventually terminates, the prisoner never revisits the same room. 
Below is an algorithm (method) that systematically moves the prisoner through the house until the lady or the tiger is discovered. 
(Notice that he never “visits” a room twice, although he may backtrack through a previously visited room.)
Algorithm for Search Method:
Mark the initial room as visited. 
Push the initial room onto a stack of room numbers. 

The room at the top of the stack is the room currently occupied by the prisoner 
The stack “remembers” previously visited rooms 
// loop to search
while both the lady and the tiger are undiscovered 
{
 if there is an unvisited room r adjacent to the room on top of the stack (the current room) 
  Visit r;
  mark r as visited. 
  if the lady or tiger is in r, the search is over 
   otherwise push r onto the stack, that is,  move the prisoner to room r.
	
 else if there is no unvisited room adjacent to the room on top of the stack
backtrack to the previous room, that is,  pop the stack
// the most recently visited room is now on top of the stack 
} 
report the results 
*/
/*We implement the rooms as a two- dimensional array of rooms. 

The rows and columns of rooms are indexed by the room numbers. If i and j are two room numbers, then
•	if there is a door between room i and room j --  rooms( i, j) =1
•	if there is no door between room i and room j -- rooms( i, j) =0

The prisoner “visits” rooms until he discovers the lady or the tiger. 

To ensure that the program eventually terminates, the prisoner never revisits the same room. 
Below is an algorithm (method) that systematically moves the prisoner through the house until the lady or the tiger is discovered. 
(Notice that he never “visits” a room twice, although he may backtrack through a previously visited room.)
Algorithm for Search Method:
Mark the initial room as visited. 
Push the initial room onto a stack of room numbers. 

The room at the top of the stack is the room currently occupied by the prisoner 
The stack “remembers” previously visited rooms 
// loop to search
while both the lady and the tiger are undiscovered 
{
 if there is an unvisited room r adjacent to the room on top of the stack (the current room) 
  Visit r;
  mark r as visited. 
  if the lady or tiger is in r, the search is over 
   otherwise push r onto the stack, that is,  move the prisoner to room r.
	
 else if there is no unvisited room adjacent to the room on top of the stack
backtrack to the previous room, that is,  pop the stack
// the most recently visited room is now on top of the stack 
} 
report the results 
*/

// THIS CLASS WILL JUST SET UP ALL THE CLASSES THAT WILL BE USED. IT IS THE MAIN SOURCE FOR THE PROGRAM
// IT WILL CREATE THE OUTPUTAREA, THE ARRAY OF INTEGERS ( I AM GIVING YOU THIS)
// IT WILL CREATE OBJECTS OF THE CONTROLPANEL AND DRAWROOMS CLASSES
// IT WILL ALSO CREATE A PANEL WITH A LABEL ON THAT SAYS WELCOME
import java.awt.*;

import javax.swing.*;

import java.util.*;
import java.awt.event.*;
import java.util.ArrayList;


// This version is for the class - separate out the controller
//  sets up the GUI and other classes

 @SuppressWarnings("serial")
public class LadyOrTigerGUIS extends JFrame
 {
   

 private int[][] rooms =
	 { 
		      {1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		      {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			  {0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, 
			  {0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0}, 
			  {0,0,0,1,0,0,1,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0},  
			  {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},  
			  {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},  
			  {0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0}, 
			  {0,0,0,0,1,0,0,0,0,0,1,1,0,1,0,0,0,0,0,0,0,0},  
			  {0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0},  
			  {0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0},  
			  {0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,1,0,0,0,0},  
			  {0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0}, 
			  {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0}, 
			  {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0}, 
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0}, 
			  {0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0}, 
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
			  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0}, 
		};
 

 
 
 
// 2d array representation of the house or network 

 
 private JTextArea outputArea;
 
 
 
 //DECLARE OBJECTS (DO NOT INSTANTIATE HERE) OF THE DRAWROOMS AND CONTROLPANEL CLASSES E.G
 // 
 DrawRooms drawroom;
 ControlPanel controls;
 
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
     drawroom = new DrawRooms(rooms);
	 
	   
	// INSTANTIATE AN OBJECT OF CONTROLPANEL CLASS (CALL IT CONTROLS) - SEND IT THE OUTPUTAREA, DRAWROOM AND THE ARRAY OF INTS
   	   controls = new ControlPanel(outputArea,drawroom,rooms);
	  
    // PUT A BORDER AROUND IT
   controls.setBorder(BorderFactory.createLoweredBevelBorder());
   controls.setBorder(BorderFactory.createLineBorder(Color.red,2));

   controls.setBackground(java.awt.Color.white);


   
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
		 toppanel.add(controls);
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
	   c.setPreferredSize(new Dimension(900,700));
 } 
 
 
 
 }