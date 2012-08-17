import java.util.LinkedList;
import java.util.Scanner;
import java.util.*;

import java.util.Iterator;


//************************************************************
// You will implement the following methods as described in the main method
//  The class LinearNode has been provided for you to use to create nodes 
//  
//************************************************************
	class Solution{
		public static void main( String args[] ){
			
	// helpers for input/output		

			Scanner in = new Scanner(System.in);
				LinkedList10<Integer> list = new LinkedList10<Integer>();

			int N;
			N = in.nextInt();
		
			String s = "";
			int x;
			
			for(int i=0; i<N; i++){
				s = in.next();
				x = in.nextInt();
				
				
				if (s.equalsIgnoreCase("r"))
				{
				if (list.isEmpty())
					System.out.println("Wrong!");
				
				
				
				}
				else
				{
				if (list.isEmpty())
					list.insertFirst(x);
				
				
				
				}
			}
		

			
		
	
// Code a linked list as pictured in the project specification which contains String B, E and J  and perform the following operations. 
// After each operation print out the list:

// do the appropriate System.out printlns for each operation

// int f = 5;
// int t = 10;
// int two = 2;
// list.insertFirst(f);
// list.insertPrevious(t);
// list.insertAfter(two);
// System.out.println(list);


//  1.  insert B into the list
}
	}
	
class LinkedList10<T> implements ListADT<T>
{
   protected int count;
   protected LinearNode<T> head, tail, current, previous, med,next;

   //===========================================================
   //  Creates an empty list.
   //===========================================================
  public LinkedList10() 
  {
      count = 0;
      head = tail = null;
      current = null;
      previous = null;
	  med = null;
   }  // constructor List

/**************************************************************
// Precondition: head is the head reference of a linked list.
// The list might be empty or it might be non-empty.
//  PostCondition: One node has been added to the list.
//**************************************************************/
  public void insertFirst(T addData)
  {
	  // create a node
	  LinearNode newnode = new LinearNode(addData);
	
	   if(isEmpty())
	   {// is the list is empty
	      head = newnode;  // point head to the new node
	   		tail =head;
	   		current = head;
			med = current;
	   }
	   else  // list is not empty
	   {  
	           newnode.next = head ;
	           head = newnode;
	           current = head;// point head to newnode
			   // if(count %2 ==0)
				
	   }
	   count++;
  }

/**************************************************************
// this method calls insertAfter method after it finds the node it wants to insert a node after
/**************************************************************/
public boolean Insert(T target, T addData)//*************************
{
   // calls find 
	current = find(target);
	
	if(current != null)
		insertAfter((Integer)addData);
	
	else
		insertFirst(addData);
 // if  target is found 
 //the method calls insertafter(addData) WHICH DOES THE ACTUAL INSERT 
	
return false;  // this is just here to allow it to compile
}
/*****************************************************************
// inserts a node at the end of the tail
// CHECKS IF THE LIST IS EMPTY FIRST AND CALLS INSERTFIRST
// ELSE INSERTS ON THE END OF THE LIST
/******************************************************************/
public void insertTail(T target )//******************
{
LinearNode node = new LinearNode(target);
	if(current == null)
	{
		insertFirst(target);
	}

	tail.next=node;
	previous = tail;
	tail = current = node;
	count++;
}


   //===========================================================
   // Precondition:  The List is not empty
   //  Removes the first element in the list and returns a reference
   //  to it.  Throws an EmptyListException if the list is empty.
   //===========================================================
   public T removeFirst() throws EmptyCollectionException //*************************
	{
	   
      head = head.getNext();			
      // delete the first node
      // return the data;
      current = head;
      count--;
      return current.getElement();  // this is to get it to compile  - change it
   } // method removeFirst
   


  /**************************************************************
     *Inserts node with newData after the node current points to. The current
     *node is the same AFTER THE NODE IS INSERTED
     *Should not be used with an empty list. Should not be
     *used when the current node IS NULL.
  /*******Before insertAfter is called current must be pointing to A  node on the list
     **************************************************************/
    public void insertAfter(int target)//*****************
    { 
    	  LinearNode newnode = new LinearNode(target);
   	   if(isEmpty())
   	   {// is the list is empty
   	      head =  newnode; 
   	      current =head;// point head to the new node
   	   }
   	   
   	   if(current!=tail)  // list is not empty
   	   {  
   	           newnode.next = current.next;
   	           current.next = newnode;
   	           current = newnode;// point head to newnode
   	   }
   	  if(current==tail)  // list is not empty
  	   {  
   		  		previous = current;
  	           current.next = newnode;
  	           current = newnode;// point head to newnode
  	   }
   	   count++;
	 }// close find
    
	    public void insertPrevious(T newData)//*****************
    { 
    	  LinearNode newnode = new LinearNode(newData);
   	   if(isEmpty())
   	   {// is the list is empty
   	      head =  newnode; 
   	      current =head;// point head to the new node
   	   }
   	   
   	   if(current!=head)  // list is not empty
   	   {  
   	           newnode.previous = current.previous;
   	           current.previous = newnode;
			   newnode.next = current;
   	           current = newnode;// point head to newnode
   	   }
   	  if(current==head)  // list is not empty
  	   {  
  	           newnode.next = current;
			   current.previous = newnode;
  	           head = current = newnode;//			   point head to newnode
				previous = null;
  	   }
   	   count++;
	 }
/*********************************************************
Finds the first node containing the target data, and returns a
reference to that node. If key is not in the list, null is returned.
*********************************************************/
 public LinearNode<T> find(T target) // WE DID THIS IN CLASS
	 {
      if (!isEmpty())
      {   
    	  LinearNode node = new LinearNode(target);;

    	  Boolean found=false;
		// set up a for loop with current initialed to point to what head is pointing to.
	       
		 // inside the loop compare the element current is presently pointing to, to target
		 // if they are equal return current.element
		 // advance previous so that it follows current
    	  current = med;

  		while(found == false && current !=null)
  		{
  			if(node.getElement().equals(current.getElement()))
  			{
  				return current;
  			}
  			else
  			{
	  			previous = current;
	  			current = current.next;
  			}
  		}
      }
	 
    	  return null;
		  
   }    // close method
  
   public LinearNode<T> findL(T target) // WE DID THIS IN CLASS
	 {
      if (!isEmpty())
      {   
    	  LinearNode node = new LinearNode(target);;

    	  Boolean found=false;
		// set up a for loop with current initialed to point to what head is pointing to.
	       
		 // inside the loop compare the element current is presently pointing to, to target
		 // if they are equal return current.element
		 // advance previous so that it follows current
    	  current = med;

  		while(found == false && current !=null)
  		{
  			if(node.getElement().equals(current.getElement()))
  			{
  				return current;
  			}
  			else
  			{
	  			previous = current;
	  			current = current.previous;
  			}
  		}
      }
	 
    	  return null;
		  
   }    // close method
   
   public LinearNode<T> compareadd(int target) // WE DID THIS IN CLASS
	 {
	   LinearNode<Integer> node = new LinearNode<Integer>(target);
      if (!isEmpty())
      {   
		if((Integer)med.getElement() < target)
		{
	    	  Boolean found=false;
			// set up a for loop with current initialed to point to what head is pointing to.
		       
			 // inside the loop compare the element current is presently pointing to, to target
			 // if they are equal return current.element
			 // advance previous so that it follows current
	    	  current = med;
			  previous = current.previous;
	
	  		while(found == false && current !=null)
	  		{
	  			if((Integer)current.getElement() <= target)
	  			{
	  				insertAfter(target);
	  			}
	  			else
	  			{
		  			next = current;
		  			current = current.previous;
	  			}
	  		}
		}
	  
		if((Integer)med.getElement() < target)
		{
			Boolean found=false;
			// set up a for loop with current initialed to point to what head is pointing to.
		       
			 // inside the loop compare the element current is presently pointing to, to target
			 // if they are equal return current.element
			 // advance previous so that it follows current
	    	  current = med;
	
	  		while(found == false && current !=null)
	  		{
	  			if((Integer)current.getElement() >= target)
	  			{
	  				insertAfter(target);
	  			}
	  			else
	  			{
		  			previous = current;
		  			current = current.next;
	  			}
	  		}
		}
	  
	  if((Integer)med.getElement() == target)
		{
    	 
    	  current = med;
    	  this.insertAfter(target);
		}
      }
	  return null;
	  
       // close method
   }
  
   //===========================================================
//  Precondition: Throws an EmptyListException if the list is empty. 
// Throws a  NoSuchElementException if the specified element is not found on the list.
//  PostCondition:Removes the first instance of the specified element from the
//  list if it is found in the list and returns a reference to it.
// SEVERAL SITUATIONS MUST BE HANDLED AS LISTED BELOW
      //===========================================================
	public T remove (T data) throws  EmptyCollectionException, ElementNotFoundException //**************
   {
		LinearNode node = find(data);
		if(isEmpty())
		{
			EmptyCollectionException ece = new EmptyCollectionException("List is empty");
			throw ece;
		}
		
	 if(node!=null)
	 {
  			   // MAKE SURE TARGET IS ON THE LIST  
			  
			   // conditions you have to address:		  
     	//(1) if there is only one node on the list 
		  if(size() == 1)
		  {
			  head = current = tail = previous = null;
}
       //(2) if (current points to head node and there is more than one node on the list 
		    if(current==head && current.next!=null)
		    {
		        head = current.next;
		        current = head;
		    }
  
            
       //  (3) else if current equals tail - we are at the last node - HINT - USE PREVIOUS 
		    if(current==tail)
		    {
		    	previous.next = null;
		   	   	current = previous;
		   	   	
		   }    
	 
       //(4)IF NONE OF THE ABOVE IS TRUE  we are somewhere in the middle of the list
	     // where current is pointing to node to be deleted
		// and previous is right behind it - delete THAT  node 
		    else
		    {
		        previous.next =current =  current.next;
		    }
		 }
	 	count--;
		    // return the removed element
         return  null;// this is just to get it to compile
         
   }  // method remove
   
   
	//===========================================================
   //  Removes the last element in the list and returns a reference
   //  to it.  Throws an EmptyListException if the list is empty.
	// CALLS FIND TO LOCATE CURRENT ON THE TAIL NODE 
	// DETERMINES FIRST IF THE THERE IS ONLY ONE NODE AND THEN DELETES THAT
   //===========================================================
   

   public T removeLast() throws EmptyCollectionException //*******************************
	{
	   LinearNode<T> result = find(tail.getElement());
	   previous.next = null;
	   tail = previous;
	   current = tail;
      // remove last node
      			
	   count--;
      return result.getElement();// was result


     
   } // method removeLast

   

   //===========================================================
   //  Finds the first instance of the specified element from the
   //  list if it is found in the list and returns true. 
   //  Returns false otherwise.  Calls the find method to find target                                     
   //===========================================================
   public boolean contains (T targetElement) throws  EmptyCollectionException //****************
   {
       if(isEmpty())
    	   throw new EmptyCollectionException("empty");// throws exception if list is empty  
	   LinearNode<T> result = find(targetElement);
	  return(result!=null); // To satisfy the compiler
  
   }  // method contains 
   
 
   //===========================================================
   //  Returns true if the list is empty and false otherwise
   //===========================================================
   public boolean isEmpty() 
	{
      return (count == 0);
   }  // method isEmpty

   //===========================================================
   //  Returns the number of elements in the list.
   //===========================================================
   public int size() //*******************
	{
      // put in code
		return count;  // CHANGE THIS< IT IS TO GET IT TO COMPILE
   }  // method size



 //===========================================================
   //  Returns ... 
   //===========================================================
   public Iterator<T> iterator() 
	{
      return new LinkedIterator<T>(head, count);
   }  // method elements


   //===========================================================
   //  Returns a string representation of the list.
   //===========================================================
	  public String toString() //*******************************
	{
// LinearNode<T> temp = head;
  String result = "";
   Iterator<T> iter = iterator();
   
//		
//   while (temp !=null) 
//		{
//	   result += temp.getElement()+ " ";
//	   temp = temp.next;
//		}

   
   while(iter.hasNext())
   {
	   T element = iter.next();
	   result += element.toString() + "\n";  
   }
   
   return result;
   } // method toString

     //===========================================================
   //  Returns the first element of the list. 
   //===========================================================
   public T first() //***************************
     {
      // put in code
	   
		  return head.getElement();  // this is to get it to compile  - change it

   }  // method firstElement

   //===========================================================
   //  Returns the last element of the list. 
   //===========================================================
   public T last()//********************
	 {
     // put in code
	    return tail.getElement();  // this is to get it to compile  - change it

   }  // method lastElement

}  // class LinkedList


class EmptyCollectionException extends RuntimeException
{
   //-----------------------------------------------------------------
   //  Sets up this exception with an appropriate message.
   //-----------------------------------------------------------------
   public EmptyCollectionException (String collection)
   {
      super ("The " + collection + " is empty.");
   }
}

  class LinkedIterator<T> implements Iterator
    {
       private int count;  // the number of elements in the collection
       private LinearNode<T> current, previous2;  // the current position
    
       //-------------------------------------------------------------
       //  Sets up this iterator using the specified items.
       //-------------------------------------------------------------
       public LinkedIterator (LinearNode<T> collection, int size)
       {
          current = collection;
          count = size;
       }
    
       //-------------------------------------------------------------
       //  Returns true if this iterator has at least one more element
       //  to deliver in the iteraion.
       //-------------------------------------------------------------
       public boolean hasNext()
       {
          return (current!= null);
       }
       
       public boolean hasPrevious()
       {
          return (previous2!= null);
       }
    
       //-------------------------------------------------------------
       //  Returns the next element in the iteration. If there are no
       //  more elements in this itertion, a NoSuchElementException is
       //  thrown.
       //-------------------------------------------------------------
       public T next() //*********************************************
       {
    	   if (! hasNext())
             throw new NoSuchElementException();
          T element = current.getElement();  // get the data
          current = current.getNext();   // advance to the next node
          return element;
            // this is to get it to compile  - change it
       }
    
       public T previous() //*********************************************
       {
    	   if (! hasPrevious())
             throw new NoSuchElementException();
          T element = previous2.getElement();  // get the data
          previous2 = previous2.getPrevious();   // advance to the next node
          return element;
            // this is to get it to compile  - change it
       }
       
       //-------------------------------------------------------------
       //  The remove operation is not supported.
       //-------------------------------------------------------------
       public void remove() throws UnsupportedOperationException
       {
          throw new UnsupportedOperationException();
       }
    }

class ElementNotFoundException extends RuntimeException
{
   //-----------------------------------------------------------------
   //  Sets up this exception with an appropriate message.
   //-----------------------------------------------------------------
   public ElementNotFoundException (String collection)
   {
      super ("The target element is not in this " + collection);
   }
}

interface ListADT<T>
{
   //  Removes and returns the first element from this list
   public T removeFirst ();

  
   //  Removes and returns the specified element from this list
   public T remove (T element);

   //  Returns a reference to the first element on this list
   public T first ();

   //  Returns a reference to the last element on this list
   public T last ();

   //  Returns true if this list contains the specified target element
   public boolean contains (T target);

   //  Returns true if this list contains no elements
   public boolean isEmpty();

   //  Returns the number of elements in this list
   public int size();

   //  Returns an iterator for the elements in this list
   public Iterator<T> iterator();

   //  Returns a string representation of this list
   public String toString();
}

 //************************************************************
 //  LinearNode.java      
 //
 //  Represents a node in a linked list.
 //************************************************************
 
// YOU MUST PUT IN MORE SETTERS and GETTERS

class LinearNode<T>
 {
    public LinearNode<T> next, previous;
    private T element;
 
    //---------------------------------------------------------
    //  Creates an empty node.
    //---------------------------------------------------------
    
    public LinearNode (T elem)
    {
    	next = null;
    	element = elem;
    	previous = null;
    }


       public LinearNode ()
       {
          next = null;
          element = null;
          previous = null;
       }
       

       
       public LinearNode<T> getNext()
       {
          return next;
       }
       
       public void setNext (LinearNode<T> node)
       {
          next = node;
       }
       
       public LinearNode<T> getPrevious()
       {
          return previous;
       }

       public void setPrevious (LinearNode<T> node)
       {
          previous = node;
       }
       
       public T getElement()
       {
          return element;
       }
    
       
       public void setElement (T elem)
       {
          element = elem;
       }
   }
 
