import javafx.scene.paint.Color;

/**
 * The PersistentGameObj class extends the GameObj class and allows the use of the objlives variable. I created this so that I could 
 * add bricks that had lives without changing all of my existing objects whilst allowing me to add functionality to the new objects. 
 * 
 * @author Finlay Watling
 * 
 *
 */


public class PersistentGameObj extends GameObj{
	
	int objlives = 0; //objlives int defaults to 0
	
	public PersistentGameObj(int x, int y, int w, int h, Color c, int objlives) {
		super(x, y, w, h, c);
		this.objlives = objlives;
		
		
		
		
	}

	}
