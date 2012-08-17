import java.util.LinkedList;


//************************************************************
// You will implement the following methods as described in the main method
//  The class LinearNode has been provided for you to use to create nodes 
//  
//************************************************************
public class ProjectTest10

{
public static void main(String args[])
   {

     String B = "B";
		String E = "E";
		String J = "J";
	
// Code a linked list as pictured in the project specification which contains String B, E and J  and perform the following operations. 
// After each operation print out the list:

// do the appropriate System.out printlns for each operation
LinkedList10Handout list = new LinkedList10Handout();

//  1.  insert B into the list
list.insertFirst(B);
System.out.println("This is (list) with B: " + list);
list.Insert(B,E);
System.out.println("This is (list) with b and e: " + list);

//3.Insert J on the end; // can use insertAfter

list.Insert(E,J);
System.out.println("This is (list) with b and e and j: " + list);


// can use insertAfter
	
		
	

      		
// 4.  insert  a new node after E
list.Insert(E,new String("new1"));
System.out.println("This is (list) with node after e: " + list);
      
// 5.  delete J
list.remove(J);
System.out.println("This is (list) with J removed " + list);

    // print out the list
	 
	 
// 6. insert J back into the list on the tail
list.Insert("new1",J);
System.out.println("This is (list) with J reinserted " + list);
    // print out the list
     
// 7.	 insert A at the front
// print out the list
list.insertFirst("A");
System.out.println("This is (list) with A at the beginning " + list);
      
 	  
//  8.Write a method that deletes  from a Linked List the item with the largest value and print out the list
	list.removehighest();
	System.out.println("This is (list) with the largest value removed " + list);

   // 9. Write a method to display a linked list backwards and print out the list

	
	System.out.println("This is (list) printed backwards " + list.printbackwards());

}
// Sample output

  /* This is (list) with B B
   
   This is (list) with e and b B
   E
 
    This is list with B E and J
    B
    E
	 J  
     I am in insertAfter with target E
    This is list with F inserted B
    E
    F
    J
    
    
    J
    This is list with J removed B
    E
    F
    
    
    This is list with J inserted on the tail B
    E
    F
    J
    
    
    This is list with A at the front A
    B
    E
    F
    J
    
    This is list highest item deleted A
    B
    E
    F
    
    
    This is list backwards 
    F E B A 
    */
    

public void DeleteEndNode(LinearNode head)
{ 
	
   LinearNode cur = head;
   LinearNode prev = null; 
	
   if (cur != null) 
   { 
      while (cur.getNext() != null) 
      { 
         prev = cur; 
         cur = cur.getNext(); 
      }// end while
	
      // Cur points to node to delete 
      if (prev == null) 
         // only one node to delete 
         head = null; 
      else 
         prev.setNext(null); 
	
      cur.setNext(null); 
      
   }// end if
} // end DeleteEndNode
	
	
//	Insert and delete at the end of the linked list with a tail pointer:

void insertEndNode (LinearNode head, LinearNode tail, LinearNode newNode) 
{ 
   if (head == null) 
   { 
      head = newNode; 
      tail = head; 
   } // end if
   else 
   { 
      tail.setNext(newNode); 
      tail = tail.getNext(); 
   } // end else

} // end insertEndNode 
	
void insertfirstNode (LinearNode head, LinearNode tail, LinearNode newNode) 
{ 
   if (head == null) 
   { 
      head = newNode; 
      tail = head; 
   } // end if
   else 
   { 
      tail.setNext(newNode); 
      tail = tail.getNext(); 
   } // end else

} // end insertEndNode



public int listCount(LinearNode head)
// --------------------------------------------------
// Returns the number of items in a linked list.
// Precondition:  head points to the first element
//			in a list of type node.
// Postcondition:  The number of items in the list
//                 	is returned.
// --------------------------------------------------
{
   int count = 0;
	
   for(LinearNode cur = head; cur != null; count++, cur = cur.getNext());
	
   return count;
   
} // end listCount

/*public int listCount(LinearNode cur)
// --------------------------------------------------
// Returns the number of items in a linked list.  The
//  initial call should have the beginning of the list
//  as it's input argument, i.e. listCount(head);
// Precondition:  cur points to a list of type node
// Postcondition:  The number of items in the list
//                 is returned.
// --------------------------------------------------
{
   if(cur != null)
      return 1 + listCount(cur.getNext());
   else  // base case
      return 0;

} // end listCount

*/



public void deleteEndNode(LinearNode head) 
{ 
	
   LinearNode cur = head;
   LinearNode prev = null; 
	
   if (cur != null) 
   { 
      while (cur.getNext() != null) 
      { 
         prev = cur; 
         cur = cur.getNext(); 
      }// end while
	
      // Cur points to node to delete 
      if (prev == null) 
         // only one node to delete 
         head = null; 
      else 
         prev.setNext(null); 
	
      cur.setNext(null); 
      
   }// end if
} // end DeleteEndNode
	
	
//	Insert and delete at the end of the linked list with a tail pointer:






}