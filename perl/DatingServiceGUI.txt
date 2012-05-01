  /****** 
  2012 edition
  Since some students have had more experience with GUI's than others, I am giving sample
  //code for setting up components.  (This will be the only time, after this you should know how to do it.
   ASTERICKS INDICATE AREAS WHERE YOU SUPPLY CODE
I will give sample code and then you will do the rest.  Since the JList as we are using it is not in either of your texts
, I have supplied full code for it */

//program to set up a client database using a List class
   import java.util.StringTokenizer;
   import java.io.*;
   import java.text.*;
   import java.util.*;
   import java.awt.*;
   import java.awt.event.*;
   import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;

/********************************************************************
* class DatingService
********************************************************************/
public class DatingServiceGUI extends JFrame
{

  protected String status, gender;
       
   // Here you declare all the components for the GUI
	private JList jList;
	private DefaultTableModel tablemodel;
	private JTable table;
	private DefaultListModel listModel; // List with a vector
   
	
	// declare JButtons for all the fields except status and gender which will be JComboBoxes
    
	/*********************** declare the buttons-  add, delete etc.example below*/
   private  JButton addButton;
   private  JButton delButton;
   private  JButton findButton;
   private  JButton helpButton;
	// declare more JButtons delete, find, help
 
	// Declare all the labels for textfields and combo boxes -here is a sample
	  private  JLabel addLabel;
	  private  JLabel firstLabel;
	  private  JLabel lastLabel;
	  private  JLabel phoneLabel;
	  private  JLabel hobbyLabel;
	  private  JLabel ageLabel;
	  private  JLabel genderLabel;
	  private  JLabel statusLabel;

	  

	  
	  
	/// Declare Textfields

	  
	  private JTextField firstField;
	  private JTextField lastField;
	  private JTextField phoneField;
	  private JTextField hobbyField;
	  private JTextField ageField;
	  
	  // Declare Comboboxes

	  
	  private JComboBox statusBox;
	  private JComboBox genderBox;

	  // combo box for type of client
	  
	  private JTextArea outputArea; // textarea where output will be sent
	  
     private String filename ="Client.dat";  // filename to read in data
     private String file = "SOUT.txt";   //  output file

     private ActionHandler action = new ActionHandler();  // is the object that implements ActionListener and contains the actionPerformed method
     
	  private ArrayList<Client> List = new ArrayList<Client>();


/********************************************************************
* DatingService::DatingService()
* setup GUI and load database file
******************************************************************/
public DatingServiceGUI()
{
  super("Dating Service  "); // Set title
	  
	 
// set up the JList – I am giving you this code because this is not in your text.  
 //By using the DefaultListModel instead of a simple JList we have access to a vector - a type of ArrayList
	 
//************************************************************************   

  //Set up container
  
  Container c = getContentPane();
  c.setBackground(Color.white);
  c.setLayout(new BorderLayout());
  
//************************************************************************   

  // I am creating a table instead of the jlist. 
  // declare column names and empty cells
	 String[] columnNames = {"First Name", "Last Name","Gender","Phone #","Hobbies","Age"};
	 Object[][] data = new Object[4][6];
	
	//make table model, and override isCellEditable method to make cells uneditable 
	 tablemodel = new DefaultTableModel(data,columnNames)
	 {
		 public boolean isCellEditable(int x, int y)
		 {
			 return false;
		 }	 
	 };
	 
	 //create table 
	 table = new JTable(tablemodel);
	 // make the cells uneditable
	 for(int x=0;x>6;x++){for(int y=0;y<4;y++){tablemodel.isCellEditable(x, y);}}
	 
	 
	 // set up table pane
	 JScrollPane listScrollPane = new JScrollPane(table);
	 table.setFillsViewportHeight(true);
	 listScrollPane.setPreferredSize(new Dimension(500, 150));
	// allows only one item to be selected at a time

	 table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
	 
	
	   // The nature of the table doesn't really allow for a border
	 	
		
		BuildService(); //this method reads in Clients from file

 
	       
 //************************************************************************   
		//Set up area that the Find button will output to
JPanel outputPane = new JPanel(); 
       
	       outputArea = new JTextArea("Results", 15,30);
	       outputArea.setEditable(false);
	       outputPane.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.blue));
	       outputPane.add(outputArea);
	       
//************************************************************************   
	       //Set up the Pane of labels for text fields and comboboxes
JPanel labelPane = new JPanel();
	
	labelPane.setLayout(new GridLayout(0, 1,5,5));// where labelPane is the panel for the labels
			addLabel = new JLabel();		
			firstLabel = new JLabel("first name");
			lastLabel = new JLabel("last name");
			phoneLabel = new JLabel("phone");
			hobbyLabel = new JLabel("hobby");
			ageLabel = new JLabel("age");
			genderLabel = new JLabel("gender");
			statusLabel = new JLabel("status");
  
			//Add labels to pane
		 	labelPane.add(firstLabel);
		    labelPane.add(lastLabel);
		    labelPane.add(phoneLabel);
		    labelPane.add(hobbyLabel);
		    labelPane.add(ageLabel);
		    labelPane.add(statusLabel);
		    labelPane.add(genderLabel);
			labelPane.setBorder(BorderFactory.createLineBorder(Color.red,2));
		    labelPane.setBackground(java.awt.Color.yellow);
//************************************************************************   

		    // Set up pane for the user enterable fields and comboboxes
JPanel fieldPane = new JPanel();

	fieldPane.setLayout(new GridLayout(0, 1,5,5));// where labelPane is the panel for the labels
	      // look up what the gridlayout parameters mean  - 0 means as many rows as necessay and 1 means in one column(See GUI) 
			// set up fields
				firstField = new JTextField(16);
				lastField = new JTextField(16);
				ageField = new JTextField(16);
				hobbyField = new JTextField(16);
				phoneField = new JTextField(16);
				
			// set up combo boxes
				statusBox = new JComboBox();
				statusBox.addItem(" ");
				statusBox.addItem("R");
				statusBox.addItem("S");
			// add the actionListener to it
				statusBox.addActionListener(action);
				
			// now create a gender combo box the same way
				genderBox = new JComboBox();
				genderBox.addItem(" ");
				genderBox.addItem("M");
				genderBox.addItem("F");
			// add the actionListener to it
				genderBox.addActionListener(action);
				
			//add fields and combo boxes to pane
			 	fieldPane.add(firstField);
			    fieldPane.add(lastField);
			    fieldPane.add(phoneField);
			    fieldPane.add(hobbyField);
			    fieldPane.add(ageField);
			    fieldPane.add(statusBox);
			    fieldPane.add(genderBox);
				fieldPane.setBorder(BorderFactory.createLoweredBevelBorder());
			    fieldPane.setBackground(java.awt.Color.white);
			    
      // add the labels to the label panel in the same order that you add textfields to their panel(

	  // add the JTextFields and combo boxes to the textfield panel
	  
	   // put the combo boxes on the end of the panel for textfields  - trust me on this.

	  // make sure labels and textfields match - the firstname label matches placement of firtname textfield- in order of how they are put on the panels.      	   
           
     // get the contentpane and add the various panels to the frame using a borderlayout
//************************************************************************   
			  //set up pane with the 4 buttons
JPanel buttonPane = new JPanel();
		    	    
	buttonPane.setBorder(BorderFactory.createLineBorder(Color.blue,4));// 4 is the width of line
	
			// set up buttons and add them to the pane
		       addButton = new JButton("ADD");
		       helpButton = new JButton("HELP");// Setup Buttons
		       delButton = new JButton("DELETE");// Setup Buttons
		       findButton = new JButton("FIND");// Setup Buttons

		       
		       helpButton.setToolTipText("Displays information to help you use the Dating Service Program");
		       helpButton.setMargin(new Insets(5,5,10,5));
		       helpButton.addActionListener(action);
		       buttonPane.add(helpButton);
		       
		       addButton.setToolTipText("Adds a client to the database after fields are filled");
		       addButton.setMargin(new Insets(5,5,10,5));
		       addButton.addActionListener(action);

		       buttonPane.add(addButton);
		       
		       delButton.setToolTipText("Deletes a client from the database");
		       delButton.setMargin(new Insets(5,5,10,5));
		       delButton.addActionListener(action);

		       buttonPane.add(delButton);
		       
		       findButton.setToolTipText("Finds client from the database");
		       findButton.setMargin(new Insets(5,5,10,5));
		       findButton.addActionListener(action);

		       buttonPane.add(findButton);
		       
		       
//************************************************************************   
// add panes to container		       	
 c.add(fieldPane, BorderLayout.CENTER);
 c.add(outputPane,BorderLayout.EAST);
 c.add(labelPane, BorderLayout.WEST);

 c.add(buttonPane,BorderLayout.SOUTH);
 c.add(listScrollPane,BorderLayout.NORTH);// where c is the contentPane
 setSize( 500, 500);

		
		// the Textfield panel goes in the center, the JTextArea is East, button panel is
		// south and listScollpane is north
		
    }  // close constructor
   
   public void BuildService()
      {
      
         try {
        	 //set up output file
             FileOutputStream out = new FileOutputStream(file);
		     PrintStream ps = new PrintStream(out);
		     ps.println("Jason Phelps");
		     
		     //create scanner to iterate through input file
            Scanner scan = new Scanner(new File(filename));   
				Scanner linescan;      
			//declare strings
            String lastname  = " ", firstname  = " ", phone  = " ", hobby  = "",data;
            String gender  = " ",status  = " ";
            int age;
            String line;
            int tabline = 0;
                    
           while(scan.hasNext()) 
            {
               line = scan.nextLine();//reads in a line from the file
					//System.out.println(line); // for debugging
					
					linescan = new Scanner(line);
					if(linescan.hasNext()) //if there is a line to read
					{
					status = linescan.next(); //status is the first thing read
					Object[] obj = new Object[6]; //used for creating the client file, and adding it to the table
					int i=0; //used for entering into the table
					
							//while there are things to be read on the line, and so it doesnt read garbage at the end of the line
						  while(linescan.hasNext()&&i<5)
						  {
							  obj[i] = linescan.next(); //makes an array of the client
							  table.setValueAt(obj[i], tabline, i); //updates the table
							  i++;
						  }
						  	//used to instantiate the client 
							firstname = (String)obj[0];
							lastname = (String)obj[1];
							gender = (String)obj[2];
							phone = (String)obj[3];
							hobby = (String)obj[4];
							
							//if regular client
						if(status.charAt(0)=='R')
						{
							//get age
							  obj[5] = linescan.nextInt();
							  age = (Integer)obj[5];
							  //update table
							  table.setValueAt(obj[5], tabline, 5);
							  tabline++;
							  //add client
							    Client client = new Client(lastname,firstname, gender, phone, hobby,age);
			                    // add it to the ArrayList 
								  List.add(client);
								  //print it to the output
								  ps.println(client);
						  }

						else
						{
							  SeniorClient sclient = new SeniorClient(lastname,firstname, gender, phone, hobby);
							  //no age so no need to update the table
			                    // add it to the ArrayList // 
								  List.add(sclient);
								  //print it to the output
							      ps.println(sclient);

							  tabline++;
							  
						 }
				
					}
					
						
					
           } // close outer while 
			  }  // close try 
            catch(FileNotFoundException ef)
            {
               System.out.println("File Not Found: ") ; // !!FILE_NOT_FOUND
            }
            catch(IOException e)
            {
               System.out.println("IOException " + e.getMessage() ); // !!READ_ONLY!!
            }	
      
         System.out.println("Output file has been created: " + file);


        }// close method


/********************************************************************
*class ActionHandler handles events from the buttons – it is nested inside DatingService
*The actionPerformed method is called when the buttons are pushed	 
	************************************************************/
    class ActionHandler implements ActionListener
    {
      // declare Strings to store data retrieved from the textfields
		//e.g String firstname
		
		public void actionPerformed(ActionEvent e) // a button was pressed 
        {
		  

          if (e.getActionCommand().equals("HELP"))
          { // help button pressed
        	  JOptionPane.showMessageDialog (null,  "Enter data in all the fields and then press add \n",   
        			  "INSTRUCTIONS", JOptionPane.PLAIN_MESSAGE );
          }

			
		 if (e.getActionCommand().equals("ADD")) // help button pressed
		 {
			 int num = 1;
			 	//get values from user enterable fields and combo boxes
	             String fn = firstField.getText(); 
	             String ln = lastField.getText(); 
	             String ph = phoneField.getText(); 
	             String ho = hobbyField.getText(); 
	             status = (String)statusBox.getSelectedItem();
	             gender = (String)genderBox.getSelectedItem();
	             
	             //if any of the boxes are empty
             if(fn.equals("") || ln.equals("") || ph.equals("") || ho.equals("") ||status.equals("") || gender.equals(""))
	             {
	            	 JOptionPane.showMessageDialog (null,  "Make sure data is entered in all the fields",   
			                  "INSTRUCTIONS", JOptionPane.PLAIN_MESSAGE );	            
	             }

	
		 
         	else
         	{
	             if(status.equalsIgnoreCase("r")) // If it’s a regular client...
	             {
	            	 //if no proper number is inputted in num field
	            	 try
	            	 {
	            		 Integer.parseInt(ageField.getText());
	            	 }
	            	 catch(NumberFormatException n)
	            	 {
	            		 JOptionPane.showMessageDialog (null,  "Make sure data is entered in all the fields",   
				                  "INSTRUCTIONS", JOptionPane.PLAIN_MESSAGE );	
	            		 num = 0;
	            	 }
	            	 
            	 //if a proper number is inputted in num field
	            	 
	                 if(num==1)
	                 {
	                	 //get age and create client
		                 int age = Integer.parseInt(ageField.getText());
		                 Client client = new Client(ln,fn, gender, ph, ho,age);
		                 //add it to the table
		                 tablemodel.addRow(new Object[]{fn,ln,gender,ph,ho,age});
		                 
		                 //make cells uneditable
		    			 int numrows = table.getRowCount();
		    			 int numcols = table.getColumnCount();
		    			 for(int y=0;y<numcols;y++){tablemodel.isCellEditable(numrows, y);}
		    			 
		    			 //add it to the arraylist and blank fields
		    			 List.add(client);
			             firstField.setText("");
			             lastField.setText("");
			             ageField.setText("");
			             phoneField.setText("");
			             hobbyField.setText("");
			             statusBox.setSelectedIndex(0);
			             genderBox.setSelectedIndex(0);
			             
			             //write to output file
			             try {
			            	 //set up output file
			                 FileOutputStream out = new FileOutputStream(file,true);
			    		     PrintStream ps = new PrintStream(out);
			    		     ps.println(client + " was created");
			             }
			             catch(FileNotFoundException ef)
			             {
			                System.out.println("File Not Found: ") ; // !!FILE_NOT_FOUND
			             }
			             catch(IOException ef)
			             {
			                System.out.println("IOException " + ef.getMessage() ); // !!READ_ONLY!!
			             }	
	                 }
	             }
	             else
	             {
	            	 //create client and add it to the table
	                 SeniorClient sclient = new SeniorClient(ln,fn, gender, ph, ho);
	                 tablemodel.addRow(new Object[]{fn,ln,gender,ph,ho});
	                 
	                 //make cells uneditable
	    			 int numrows = table.getRowCount();
	    			 int numcols = table.getColumnCount();
	    			 for(int y=0;y<numcols;y++){tablemodel.isCellEditable(numrows, y);}
	    			 
	    			 //add it to the arraylist and blank fields
	             	 List.add(sclient);
		             firstField.setText("");
		             lastField.setText("");
		             ageField.setText("");
		             phoneField.setText("");
		             hobbyField.setText("");
		             statusBox.setSelectedIndex(0);
		             genderBox.setSelectedIndex(0);
		             
		             //write to output file
		             try {
		            	 //set up output file
		                 FileOutputStream out = new FileOutputStream(file,true);
		    		     PrintStream ps = new PrintStream(out);
		    		     ps.println(sclient + " was created");
		             }
		             catch(FileNotFoundException ef)
		             {
		                System.out.println("File Not Found: ") ; // !!FILE_NOT_FOUND
		             }
		             catch(IOException ef)
		             {
		                System.out.println("IOException " + ef.getMessage() ); // !!READ_ONLY!!
		             }	
	            }


		 }// close if status is senior client

     } // close add

   //  if the delete button was pressed
			 if (e.getActionCommand().equals("DELETE")) // help button pressed
        {
            /*THE USER SELECTS A CLIENT IN THE JLIST WINDOW AND WE WILL NOW  DELETE IT -  
				// FIRST GET THE  VALUE the user selected IN THE JList  WINDOW */

         int temp = table.getSelectedRow();
         int good = 1;
         
         //checks to see if a row is selected
         try{
         tablemodel.getDataVector().elementAt(temp);
         }
         catch(ArrayIndexOutOfBoundsException b)
         {
				JOptionPane.showMessageDialog(null,"Please select a client to delete");
				good = 0;

         }
         
         //if a row is selected
         if (good==1)
         {
        	 //creates an object from the row
             Vector vec =  (Vector)tablemodel.getDataVector().elementAt(temp);
	         Object[] obj = new Object[vec.size()];
	         vec.toArray(obj);
	         //if regular client
	         if (((String)obj[0]).equalsIgnoreCase("r"))
	         {
	        	 //create client to be deleted
	        	 Client client = new Client((String)obj[1],(String)obj[0],(String)obj[2],(String)obj[3],(String)obj[4],(Integer)obj[5]);
	        	 List.remove(client); 	  // Remove client from the JList

	        	 //write to output file
	             try {
	            	 //set up output file
	                 FileOutputStream out = new FileOutputStream(file,true);
	    		     PrintStream ps = new PrintStream(out);
	    		     ps.println(client + " was deleted");
	             }
	             catch(FileNotFoundException ef)
	             {
	                System.out.println("File Not Found: ") ; // !!FILE_NOT_FOUND
	             }
	             catch(IOException ef)
	             {
	                System.out.println("IOException " + ef.getMessage() ); // !!READ_ONLY!!
	             }
	         }
	         //if senior client
	         else
	         {
	        	 //create senior client to be deleted
	        	 SeniorClient sclient = new SeniorClient((String)obj[1],(String)obj[0],(String)obj[2],(String)obj[3],(String)obj[4]);
	        	 List.remove(sclient); 
	        	 
	        	 //print to output file
	             try {
	            	 //set up output file
	                 FileOutputStream out = new FileOutputStream(file,true);
	    		     PrintStream ps = new PrintStream(out);
	    		     ps.println(sclient + " was deleted");
	             }
	             catch(FileNotFoundException ef)
	             {
	                System.out.println("File Not Found: ") ; // !!FILE_NOT_FOUND
	             }
	             catch(IOException ef)
	             {
	                System.out.println("IOException " + ef.getMessage() ); // !!READ_ONLY!!
	             }
	             
	         }
	         
	         //remove row from table
	         tablemodel.removeRow(temp);
         }
     } // close if delete
         

//     if find is pressed
	if (e.getActionCommand().equals("FIND")) // help button pressed
         {
         Iterator<Client> iter = List.iterator();
         outputArea.setText(""); // clear data from the JTextArea
         String choice = JOptionPane.showInputDialog(null, "Find by Hobby or Age - Enter H or A", "Input H or A  ", JOptionPane.PLAIN_MESSAGE );
//
//             based on choice H or A, get the hobby or age from the user 
//				  by displaying the inputDialogbos again, 
//				
        		//if by hobby
			if(choice.equalsIgnoreCase("h"))
			{
				String hobby = JOptionPane.showInputDialog(null, "Enter Hobby", "Enter Hobby  ", JOptionPane.PLAIN_MESSAGE );
				//if nothing is inputted
				if(hobby==null)
				{
					JOptionPane.showMessageDialog(null, "Plese select a valid hobby");
				}
				
				
				String response="";
				//iterates through clients and tries to find one with the hobby listed
				while(iter.hasNext())
				{
					Client client = iter.next();
					//if there is a client with a matching hobby, prints it to the output field
					if(hobby.equalsIgnoreCase(client.Gethob())) 
					{
						response += client.toString() + "\n";
						outputArea.append(response + "\n");
					}
				}
			}
			//if find by age
			if(choice.equalsIgnoreCase("a"))
			{
				String age1 = JOptionPane.showInputDialog(null, "Enter Age", "Enter Age  ", JOptionPane.PLAIN_MESSAGE );
				//if nothing is inputted
				if(age1==null)
				{
					JOptionPane.showMessageDialog(null, "Plese select a valid age");
				}
				int num=1;
				//if a number is not inputted
				try
				{
					Integer.parseInt(age1);
				} 
				catch(NumberFormatException t)
				{
					JOptionPane.showMessageDialog(null,"Please enter a valid number");
					num = 0;
				}
				//if a proper number is inputted
				if(num == 1)
				{
					//iterates through the clients to check to see if there is a client with an age +/- 5 of the one inputted
					int age = Integer.parseInt(age1);
					String response="";
					while(iter.hasNext())
					{
						Client client = iter.next();
						//if there is, print it to the output area
						if(age+5 > client.Getage() && age-5 < client.Getage()) 
						{
							response = client.toString() + "\n";
							outputArea.append(response + "\n");
						}
					}
				}
			}
			//if neither "a" nor "h" are inputted
			if(!choice.equalsIgnoreCase("a") && !choice.equalsIgnoreCase("h"))
			{
				JOptionPane.showMessageDialog(null, "Plese select H or A");
			}
  
     }// close find

   } // close actionperformed


    }//close ActionHandler
}// close class

 
