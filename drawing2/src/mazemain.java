//********************************************************************
//  Dots.java      
//
//  Demonstrates mouse events.
//********************************************************************

   import javax.swing.JFrame;

   public class mazemain
   {
   //-----------------------------------------------------------------
   //  Creates and displays the application frame.
   //-----------------------------------------------------------------
      public static void main (String[] args) 
      {
      // JFrame frame = new JFrame ("DatingServiceGUI");
      // frame.getContentPane().add (new DatingServiceGUI());
     
         JFrame frame = new mazegui();
         frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      
         frame.pack();
         frame.setVisible(true);
      }
   }

 

         

