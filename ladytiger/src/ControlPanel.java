// Class to handle listener and does the search

// THIS CLASS WILL SET UP AND INSTANTIATE THE BUTTONS (START AND HELP AND ANY OTHERS YOU WANT0
// THE LISTENER IS IN THIS CLASS AND REALLY CONTROLS THE APPLICION
// YOU SEND REFERENCES TO THE OUTPUTAREA, THE DRAWROOMS CLASS AND TWO D ARRAY OF INTS

 import java.awt.*;
   import javax.swing.*;
import javax.swing.Timer;

   import java.util.*;
   import java.awt.event.*;
import java.util.ArrayList;

// This is controls for the buttons and runs the simulations  
// sets up the listener for the buttons and starts the simulation

public class ControlPanel extends JPanel

{
final int numRooms =22;
  
    private ArrayStack< Integer> roomStack; // used for backtracking 
	 
	 
	 // DECLARE AN ARRAY OF BOOLEANS WITH SIZE OF THE NUMBER OF ROOMS(22)
        boolean[] visited = new boolean[numRooms];
   
    private  ActionHandler action;

  // DECLARE OBJECTS OF DRAWROOMS, THE JBUTTONS , THE JTEXTAREA CLASS AND AN TWO ARRAY OF INT
  // DECLARE OBJECT OF ACTIONHANDLER CLASS FOR LISTENER CLASS FOR THE JBUTTONS
   
	
	private int[][] rooms;
	private int lady,tiger,hero;
	private boolean fateDecided;
	private int nextroom;
	javax.swing.Timer t;

private DrawRooms dr;

private JTextArea outputArea;
private JButton start = new JButton("START");
private JButton help = new JButton("HELP");
private int currentRoom = 0;


// constructor sets up buttons and listener
  public ControlPanel(JTextArea outputArea, DrawRooms dr, int[][] rooms)
   {
     this.rooms = rooms;
     this.dr = dr;
	  this.outputArea = outputArea;
	  
     action = new ActionHandler();
      roomStack = new ArrayStack<Integer>();
     
	  // create the buttons and add the action listener to them as you did before in dataing service
	  
	  // TO add the buttons to this panel (ControlPanel class is a panel)
	  
	  // just use add method as below
	  start.addActionListener(action);
	  help.addActionListener(action);
	     
      add(start);
	   add(help);
	   
     
	}

/********************************************************************
 
 /********************************************************************
*class ActionHandler handles events from the buttons – it is nested inside Lady&Tiger
*The actionPerformed method is called when the buttons are pushed	 
	************************************************************/
    class ActionHandler implements ActionListener
    {
     		
		public void actionPerformed(ActionEvent e) // a button was pressed 
        {
        // IF SOMEONE PRESSES THE HELP BUTTON A DIALOG BOX POPS UP
        // the JOPtionPane dialog box displays instructions for entering data
		  // get code from Datingservice for help button


       // when start buttons is pressed ,
		  
		  if (e.getActionCommand().equals("START"))
		 {
			  currentRoom = nextroom = 0;
			  fateDecided = false;
		      roomStack = new ArrayStack<Integer>();

           outputArea.setText(""); // clear data from the JTextArea
			  
			  // assign a room number for lady and tiger and start room e.g.
			  lady = 10;
			  tiger = 4;
			  hero=0;	
	          dr.MoveMan(hero,hero); 
// do other two variable- tiger etc. 
			  
			  // These should match where you placed them in the grid in DrawRooms.  
			 //Alternatively, you could have methods in DrawRooms
			 // that set the locations.  You would call those methods with the "dr" ojbect
			 // sent from the TigerLilyGUI class
		for(int i=0;i<visited.length;i++)
				visited[i]=false;
			 
  
  // no rooms have been visited yet so implement a loop to initialize each location in array of booleans declared above 
  // constructor to  false
  
    // CALLS METHOD TO BEGIN SEARCH
	  search();
	  }
	} // actionperformed
	} // close class


 //*************************************************
 // helper method to find next available room
 //*************************************************
 private void nextRoom( int room) // helper method is private 
 { 
	 currentRoom = room;
	 
	 //create action listener to call nextRoom every 1/10 of a second
	 ActionListener action = new ActionListener()
	{

	public void actionPerformed(ActionEvent e) {
	 
		// from the unvisited rooms adjacent to room 
		 // If there is no unvisited room adjacent to room returns noRoom
		 // pick the next room randomly from all unvisited adjacent rooms 
		Random localRandom = new Random();

		ArrayList<Integer> ArrayList = new ArrayList<Integer>();

		// run a loop that traverses the row adjacent to "room" and finds those that are not visited and have a 1 in them
		 //	for (  ) 
		 
		  // if ( rooms[room][ i)  is unvisited and has a 1) // get a list of unvisited rooms adjacent to room 
		  //   add i to array of unvisited rooms
		
	    for (int i = 0; i < 20; i++)
	    {
	      if ((rooms[currentRoom][i] == 1) && (visited[i] == false))
	        ArrayList.add(i);
	    }
	    if (ArrayList.size() > 0)
	    {
	    	// pick a random room if there are unvisited rooms
    	int rand;
	      rand = localRandom.nextInt(ArrayList.size());
	      nextroom = ArrayList.get(rand);  
	    }
	    else
	    {
	    	nextroom = -1;
	    }
	 
	 
	 if (nextroom > 0)
     {
		 // if there is an unvisited room, visit that room 
		   //{
			 
			   // mark it visited
		 
       visited[nextroom] = true;

       outputArea.append(" This is the latest room " + nextroom + "\n");

       if ((nextroom == lady) || (nextroom == tiger))
       {
    		 // set fatedecided to true and call method in drawRooms that will place the prisoner in that location
       	fateDecided = true;
       	
     // when loop ends either the lady or the tiger was found
       	/*if ( room ==lady) 
       		output to JTextArea whether lady or tiger found*/
       		
       		 // call method in DrawRooms that places the prisoner in his final location  (with Tiger or Lily)
       	dr.MoveMan(nextroom,nextroom);

        if (nextroom == lady)
  	      outputArea.append(" He found the lady in room " + nextroom);
  	    else if(nextroom == tiger)
  	      outputArea.append(" He found the tiger in room " + nextroom);
      	currentRoom = nextroom = 0;
       	t.stop();
       	
       	
       }
       else
       {
       	roomStack.push(nextroom);
       	dr.MoveMan(currentRoom,nextroom); 
       	currentRoom = roomStack.peek();
//       	nextRoom(roomStack.peek());

       }
     }
	 // else  
	 // nothing found in this room so pop stack and go back to last room  -  backtrack

     else{
       	roomStack.pop();
         dr.MoveMan(roomStack.peek(),currentRoom);
        	currentRoom = roomStack.peek();
//        	nextRoom(roomStack.peek());

     }
			}
		};
		t = new javax.swing.Timer(100,action);
		t.start();
//		while(!fateDecided);
	 
 }
 
 
   //*************************************************
 //  Method to conduct search by the player
 //*************************************************

 public void search() 
 { 
	 	visited[currentRoom] = true;
	    roomStack.push(currentRoom);

	    outputArea.append("Starting in room " + currentRoom + " the prisoner visits rooms:");


	     nextRoom(currentRoom);

	   



 } 
 }




//System.out.println(" nStarting in room " + currentRoom + " the prisoner visits rooms:"); 
	// keep a running output of where the prisoner is
 
   //  outputArea.append("Starting in room " + currentRoom + " the prisoner visits rooms:"); 

 //while (! fateDecided) 
 // { 
// Is there an unvisited room adjacent to the current room? - calls next Room method
	
 
 
	 
	    // System.out.println( room); 
	   // outputArea.append(" This is the latest room \n " + room);

   // if he finds lady or tiger
	 
     //   else 
	  // push the room on the stack
	    
     // roomStack.pop(); 
// }  // close loop




	 // dr.MoveMan(currentRoom);






 

 