import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;


//************************************************************************
//This class represents a room on the grid of rooms,
// references should be kept if it has a door ( this can be a boolean)
// and if it contains an image of the lady,tiger, hero
// You should supply the image name in constructor if known
// otherwise have a setter or getter to set the image name when known
// it should have a equals method that compares two rooms to see if they have
// the same x and y location
// no compareTo required 

//************************************************************************

class Room
{
	// set up the instance variables, same as square class e.g
		private Image imagename = null;
		int x;
		int y;
		boolean occ = false, visited=false;
	
	
   //-----------------------------------------------------------------
   //  Constructor for a room object.
   //-----------------------------------------------------------------
	public Room (int x1, int y1, Image imageName1)
	{
		this.x = x1;
		this.y = y1;
		this.imagename = imageName1;
		if(this.imagename != null){
			this.occ = true;
		}
		this.visited=false;
				
	}


	public boolean isVisited() {
		return visited;
	}


	public void setVisited(boolean visited) {
		this.visited = visited;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public String getimagename() {
		return imagename.toString();
	}


	public void setimagename(Image imagename) {
		this.imagename = imagename;
	}
	
	public boolean isocc(){
		return occ;
	}
	
	public boolean equals(Room obj){
		if(this.getX()==obj.getX() && this.getY()==obj.getY())
			return true;
		else
			return false;
	}
	

	public void draw (Graphics page)
	{
   	  // draw the square
		page.setColor(Color.black);
		page.drawRect(28*getY(),28*getX(), 25,25); 
	} 
	
	public void drawnum (Graphics page)
	{
		Font f = new Font("Comic Sans", Font.BOLD, 10);
		page.setFont(f);
		page.setColor(Color.black);
		page.drawString("1", 28*getY()+3,28*getX()+23); 
	} 
	
	public void drawimg (Graphics page)
	{
		page.drawImage(imagename, 28*getY(),28*getX(),null); 
	} 
		 
	public String toString() 
	{
	        return "X: " + x + " Y: " + y + " Occ: " + occ;
	}
	
   // setters getters and tostring and equals  - toString should indicate if room has a door
	// and an image  and x and y location	
} // end room class

