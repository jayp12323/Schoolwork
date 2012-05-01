// MPLEMENT METHODS MARKED  WITH **************
// BE SURE TO COMPLETE METHOD IN ARRAYORDEREDLIST
//********************************************************************
//  ListADT.java    //
//  Defines the interface to a general list collection. Specific
//  types of lists will extend this interface to complete the
//  set of necessary operations.
//********************************************************************
import java.util.Iterator;

//********************************************************************
//  ArrayList.java       //  Represents an array implementation of a list. The front of
//  the list is kept at array index 0. This class will be extended
//  to create a specific kind of list.
//********************************************************************
public class ArrayList<T> implements ListADT<T>
{
   protected final int DEFAULT_CAPACITY = 100;
   private final int NOT_FOUND = -1;
   protected int rear;
   protected T[] list; 

   //-----------------------------------------------------------------
   //  Creates an empty list using the default capacity.
   //-----------------------------------------------------------------
   public ArrayList()
   {
      rear = 0;
      list = (T[])(new Object[DEFAULT_CAPACITY]);
   }

   //-----------------------------------------------------------------
   //  Creates an empty list using the specified capacity.
   //-----------------------------------------------------------------
   public ArrayList (int initialCapacity)
   {
      rear = 0;
      list = (T[])(new Object[initialCapacity]);
   }

   //-----------------------------------------------------------------
   //  Removes and returns the last element in the list.
   //-----------------------------------------------------------------
 
 public T removeLast () throws EmptyCollectionException                      //XXXXXXXXXXXXXXX
   {
      T result  = null;

      if (isEmpty())
         throw new EmptyCollectionException ("list");
      else
    	  rear--;
      		
    	  
// WRITE CODE TO REMOVE THE LAST ITEM
     
      return result;
   }
	
//-----------------------------------------------------------------
   //  Adds the specified element to the list if it's not already
   //  present. Expands the capacity of the array if necessary.
   //-----------------------------------------------------------------
   public void add (T element)  //xxxxxxxxxxxxxxxxxxxxxxxxx
   {
	   if(!contains(element))
	   {
		   if (size() == list.length)
		   {
			   expandCapacity();
		   }
		   
		   list[rear++]=element;
	         // INCREMENT REAR
	   }			   

   }

//-----------------------------------------------------------------
   //  Removes and returns the first element in the list.
   //-----------------------------------------------------------------  
public T removeFirst() throws EmptyCollectionException//********************
   {
      if (isEmpty())
         throw new EmptyCollectionException ("list"); //XXXXXXXXXXXXXXX
     T result = list[0];
	  
      list[0] = null;



      for (int scan=0; scan < rear; scan++)
         list[scan] = list[scan+1];

	  rear--;

	 // STORE THE FIRST ELEMENT IN RESULT
      //DECREMENT REAR AND USE A LOOP TO MOVE THE ELEMENTS UP
		// TO FILL THE FIRST POSITION
		// This is traditionial implementation think of a more efficient solution to above
      
      return result;
   }

   //-----------------------------------------------------------------
   //  Removes and returns the specified element.
   //-----------------------------------------------------------------
   public T remove (T element)  //*******************
   {
      T result = null;
      int index = find (element);

      if (index == NOT_FOUND)
         throw new ElementNotFoundException ("list");

      result = list[index];
      list[index] = null;
      

      for (int scan=index; scan < rear; scan++)
         list[scan] = list[scan+1];

		 // store the element in the list[index] in result  
	   
      rear--;
      // shift the appropriate elements up to cover the missing element
      
      return result;
   }
  
   //-----------------------------------------------------------------
   //  Returns a reference to the element at the front of the list.
   //  The element is not removed from the list.  Throws an
   //  EmptyCollectionException if the list is empty.  
   //-----------------------------------------------------------------
   public T first() throws EmptyCollectionException
   {
      if (isEmpty())
         throw new EmptyCollectionException ("list"); 

      return list[0];
   }
   //-----------------------------------------------------------------
   //  Returns a reference to the element at the rear of the list.
   //  The element is not removed from the list.  Throws an
   //  EmptyCollectionException if the list is empty.  
   //-----------------------------------------------------------------
   public T last() throws EmptyCollectionException
   {
      if (isEmpty())
         throw new EmptyCollectionException ("list"); 

      return list[rear-1];
   }

   //-----------------------------------------------------------------
   //  Returns true if this list contains the specified element.
   //-----------------------------------------------------------------
   public boolean contains (T target)
   {
      return (find(target) != NOT_FOUND);
   }

   //-----------------------------------------------------------------
   //  Returns the array index of the specified element, or the
   //  constant NOT_FOUND if it is not found.
   //-----------------------------------------------------------------
   private int find (T target)//XXXXXXXXXXXXXXXXXXX
   {
      int scan = 0, result = NOT_FOUND;
      boolean found = false;
    
	  // IF IT IS NOT EMPTY RUN A LOOP
      if (isEmpty())
          throw new EmptyCollectionException ("list"); 
      while(!found && scan++<rear)
    	  if (target.equals(list[scan]))
    		  found = true;
	  // THE LOOP SHOULD iterate WHILE !FOUND AND SCAN IS LESS THAn REAR
	  // IT COMPARES TARGET TO LIST[SCAN] UNTIL IT FINDS IT
     
      if (found)
         result = scan;

      return result;
   }

   //-----------------------------------------------------------------
   //  Returns true if this list is empty and false otherwise. 
   //-----------------------------------------------------------------
   public boolean isEmpty()
   {
      return (rear == 0);
   }
 
   //-----------------------------------------------------------------
   //  Returns the number of elements currently in this list.
   //-----------------------------------------------------------------
   public int size()
   {
      return rear;
   }

   //-----------------------------------------------------------------
   //  Returns an iterator for the elements currently in this list.
   //-----------------------------------------------------------------
   public Iterator<T> iterator()
   {
      return new ArrayIterator<T> (list, rear);
   }

   //-----------------------------------------------------------------
   //  Returns a string representation of this list. 
   //-----------------------------------------------------------------
   public String toString()
   {
      String result = "";

      for (int scan=0; scan < rear; scan++) 
         result = result + list[scan].toString() + "\n";

      return result;
   }

   //-----------------------------------------------------------------
   //  Creates a new array to store the contents of the list with
   //  twice the capacity of the old one.
   //-----------------------------------------------------------------
   protected void expandCapacity()
   {
      T[] larger = (T[])(new Object[list.length*2]);

      for (int scan=0; scan < list.length; scan++)
         larger[scan] = list[scan];

      list = larger;
   }
}

//********************************************************************
//  ArrayOrderedList.java      Represents an array implementation of an ordered list.
//********************************************************************
 class ArrayOrderedList<T> extends ArrayList<T> implements OrderedListADT<T>
{
   //-----------------------------------------------------------------
   //  Creates an empty list using the default capacity.
   //-----------------------------------------------------------------
   public ArrayOrderedList()
   {
      super();
   }

   //-----------------------------------------------------------------
   //  Creates an empty list using the specified capacity.
   //-----------------------------------------------------------------
   public ArrayOrderedList (int initialCapacity)
   {
      super(initialCapacity);
   }

   //-----------------------------------------------------------------
   //  Adds the specified Comparable element to the list, keeping
   //  the elements in sorted order.
   //-----------------------------------------------------------------
  public void add (T element)  // ***********************
   {
	  if(!contains(element))
	   {
		  if (size() == list.length)
		  {
			  expandCapacity();
		  }
		  Comparable<T> temp = (Comparable<T>)element;

		  int index = 0, result=0;

		// FIND THE PLACE TO ADD THE ITERM
		  while (index < rear && result != -1)
		  {
      //go down the array to find the correct location to insert  - comparing temp to each element)
			  index++;
	    	 result = temp.compareTo(list[index]);
		  }
	     index = index-1;
	     
	     for (int scan1=index; index < list.length; scan1++)
	     	{
	 			list[scan1+1] = list[scan1];
	     	}
	       // NOW MOVE ELEMENTS DOWN TO MAKE ROOM FOR NEW ELEMENT
			 // and put that element ( list[index])
				list[index] = element;
	            rear++;
	   }
   }
}

class ArrayIterator<T> implements Iterator
{
   private int count;    // the number of elements in the collection
   private int current;  // the current position in the iteration 
   private T[] items; 

   //-----------------------------------------------------------------
   //  Sets up this iterator using the specified items.
   //-----------------------------------------------------------------
   public ArrayIterator (T[] collection, int size)
   {
      items = collection;
      count = size;
      current = 0;
   }

   //-----------------------------------------------------------------
   //  Returns true if this iterator has at least one more element
   //  to deliver in the iteraion.
   //-----------------------------------------------------------------
   public boolean hasNext()
   {
      return (current < count);
   }

   //-----------------------------------------------------------------
   //  Returns the next element in the iteration. If there are no
   //  more elements in this itertion, a NoSuchElementException is
   //  thrown.
   //-----------------------------------------------------------------
   public T next()
   {
      if (!hasNext())
    	  throw new ElementNotFoundException("Element not there");
 

      return items[current++]; 

   }

   //-----------------------------------------------------------------
   //  The remove operation is not supported in this collection.
   //-----------------------------------------------------------------
   public void remove() throws UnsupportedOperationException
   {
      throw new UnsupportedOperationException();
   }
}


interface OrderedListADT<T> extends ListADT<T>
{
   //  Adds the specified element to this list at the proper location
   public void add (T element);
}

interface ListADT<T>
{
   //  Removes and returns the first element from this list
   public T removeFirst ();

  
   //  Removes and returns the specified element from this list
   public T remove (T element);

   //  Returns a reference to the first element on this list
   public T first ();

  
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


 class EmptyCollectionException extends RuntimeException
{
    static final long serialVersionUID = 23423;

   //-----------------------------------------------------------------
   //  Sets up this exception with an appropriate message.
   //-----------------------------------------------------------------
   public EmptyCollectionException (String collection)
   {
      super ("The " + collection + " is empty.");
   }
}


class ElementNotFoundException extends RuntimeException
{
    static final long serialVersionUID = 23423;

   //-----------------------------------------------------------------
   //  Sets up this exception with an appropriate message.
   //-----------------------------------------------------------------
   public ElementNotFoundException (String collection)
   {
      super ("The target element is not in this " + collection);
   }
}

class NonComparableElementException extends RuntimeException
{
   static final long serialVersionUID = 23423;

   //-----------------------------------------------------------------
   //  Sets up this exception with an appropriate message.
   //-----------------------------------------------------------------
   public NonComparableElementException (String collection)
   {
      super ("The " + collection + " requires comparable elements.");
   }
}


