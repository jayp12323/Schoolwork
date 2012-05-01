//********************************************************************
//  ArrayStack.java       Authors: Catherine Helwig
//   Represents an array implementation of a stack.
//********************************************************************

import java.util.Iterator;

public class ArrayStack<T> implements StackADT<T>
{
   private final int DEFAULT_CAPACITY = 100;
   private int top;  // indicates the next open slot
   private  T[] stack; 

   //-----------------------------------------------------------------
   //  Creates an empty stack using the default capacity.
   //-----------------------------------------------------------------
   public ArrayStack()
   {
      top = 0;
      stack = (T[])(new Object[DEFAULT_CAPACITY]);
   }

   //-----------------------------------------------------------------
   //  Creates an empty stack using the specified capacity.
   //-----------------------------------------------------------------
   public ArrayStack (int initialCapacity)
   {
	   top = 0;
	   stack = (T[])(new Object[initialCapacity]);	
   }

   //-----------------------------------------------------------------
   //  Adds the specified element to the top of the stack, expanding
   //  the capacity of the stack array if necessary.
   //-----------------------------------------------------------------
   public void push (T element)
   {
	   if (size()==stack.length)
	   {
		   expandCapacity();
	   }
	   
	   stack[top++] = element;
	   // expand capacity if needed
		// store the element in the array
      //increment top
      
   }

   //-----------------------------------------------------------------
   //  Removes the element at the top of the stack and returns a
   //  reference to it. Throws an EmptyStackException if the stack
   //  is empty.
   //-----------------------------------------------------------------
   public T pop() throws EmptyStackException
   {
      if(isEmpty())
      {
    	  throw new EmptyStackException();
      }
		// if isEmpty throw an EmptyStackException
		T result = stack[--top];
	  // decrement top and extract element at the top and and store in result
	  // set element referenced by top to null  
       
      return result;
   }
   
   //-----------------------------------------------------------------
   //  Returns a reference to the element at the top of the stack.
   //  The element is not removed from the stack.  Throws an
   //  EmptyStackException if the stack is empty.  
   //-----------------------------------------------------------------
   public T peek() throws EmptyStackException
   {
	
	    // if isEmpty throw an EmptyStackException
	   if(isEmpty())
	      {
	    	  throw new EmptyStackException();
	      }
			// if isEmpty throw an EmptyStackException
			T result = stack[top-1];
			
      	
     // you do this
       return result;  //(CHANGE THIS IT IS JUST TO GET IT COMPILE
   }

   //-----------------------------------------------------------------
   //  Returns true if the stack is empty and false otherwise. 
   //-----------------------------------------------------------------
   public boolean isEmpty()
   {
	  if(stack[0]==null) 
		  return true; //(CHANGE THIS IT IS JUST TO GET IT COMPILE
	  else
		  return false;
   }
 
   //-----------------------------------------------------------------
   //  Returns the number of elements in the stack.
   //-----------------------------------------------------------------
   public int size()
   {
     return top-1;
	
   }

   //-----------------------------------------------------------------
   //  Returns a string representation of the stack. 
   //-----------------------------------------------------------------
   public String toString()
   {
      String result = "";

     for(int i=0;i<top;i++)
     {
    	 result += stack[i].toString();
     }
     return result;
   }

   //-----------------------------------------------------------------
   //  Creates a new array to store the contents of the stack with
   //  twice the capacity of the old one.
   //-----------------------------------------------------------------
   private void expandCapacity()
   {
      T[] larger = (T[])(new Object[stack.length*2]);
      for (int scan=stack.length-1; scan >= 0; scan--)
          larger[scan] = stack[scan];

       stack = larger;
   }
	
}

 interface StackADT<T>
{
   //  Adds one element to the top of this stack
   public void push (T element);

   //  Removes and returns the top element from this stack
   public T pop();

   //  Returns without removing the top element of this stack
   public T peek();
   
   //  Returns true if this stack contains no elements
   public boolean isEmpty();

   //  Returns the number of elements in this stack
   public int size();

   //  Returns a string representation of this stack
   public String toString();
}

class EmptyStackException extends RuntimeException
{
   //-----------------------------------------------------------------
   //-----------------------------------------------------------------
   public EmptyStackException()
   {
      super ("The stack is empty.");
   }

   //-----------------------------------------------------------------
   //-----------------------------------------------------------------
   public EmptyStackException (String message)
   {
      super (message);
   }
}
