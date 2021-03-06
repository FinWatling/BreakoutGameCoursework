// The model represents all the actual content and functionality of the game
// For Breakout, it manages all the game objects that the View needs
// (the bat, ball, bricks, and the score), provides methods to allow the Controller
// to move the bat (and a couple of other functions - change the speed or stop 
// the game), and runs a background process (a 'thread') that moves the ball 
// every 20 milliseconds and checks for collisions 

import javafx.scene.paint.*;
import javafx.application.Platform;
//import java.util.ArrayList;

public class Model 
{

    public int B              = 6;      // Border round the edge of the panel
    public int M              = 40;     // Height of menu bar space at the top

    public int BALL_SIZE      = 30;  	//the size of the ball
    public int BRICK_WIDTH    = 75;     //width of each brick
    public int BRICK_HEIGHT   = 40;		//height of each brick

    public int BAT_MOVE       = 8;      // Distance to move bat on each keypress
    public int BALL_MOVE      = 2;      // Units to move the ball on each step

    public int HIT_BRICK      = 100;     // Points for hitting a brick
    public int HIT_BOTTOM     = -100;   // Points Penalty for hitting the bottom of the screen
    public int BRICK_X 		  = 100; 			// Used to display rows of bricks

    // The other parts of the model-view-controller setup
    View view;
    Controller controller;

    // The game 'model' - these represent the state of the game
    // and are used by the View to display it
    public GameObj ball;                // The ball
    public GameObj[] bricks;    		// The bricks
    public PersistentGameObj[] healthbricks;// Bricks with Health
//    public ArrayList<GameObj> albricks = new ArrayList<GameObj>();
//    public ArrayList<PersistentGameObj> alhealthbricks;
    public GameObj bat;                 // The bat
    private int score = 0;               // The score

    // variables that control the game 
    public String gameState = "running";// Set to "finished" to end the game
    public boolean fast = false;        // Set true to make the ball go faster

    // initialisation parameters for the model
    public int width;                   // Width of game
    public int height;                  // Height of game

    // CONSTRUCTOR - needs to know how big the window will be
    public Model( int w, int h )
    {
        Debug.trace("Model::<constructor>");  
        width = w; 
        height = h;


    }

    
    // Animating the game
    // The game is animated by using a 'thread'. Threads allow the program to do 
    // two (or more) things at the same time. In this case the main program is
    // doing the usual thing (View waits for input, sends it to Controller,
    // Controller sends to Model, Model updates), but a second thread runs in 
    // a loop, updating the position of the ball, checking if it hits anything
    // (and changing direction if it does) and then telling the View the Model 
    // changed.
    
    // When we use more than one thread, we have to take care that they don't
    // interfere with each other (for example, one thread changing the value of 
    // a variable at the same time the other is reading it). We do this by 
    // SYNCHRONIZING methods. For any object, only one synchronized method can
    // be running at a time - if another thread tries to run the same or another
    // synchronized method on the same object, it will stop and wait for the
    // first one to finish.
    
    // Start the animation thread
    public void startGame()
    {
        initialiseGame();                           // set the initial game state
        Thread t = new Thread( this::runGame );     // create a thread running the runGame method
        t.setDaemon(true);                          // Tell system this thread can die when it finishes
        t.start();                                  // Start the thread running
    }   
    
    // Initialise the game - reset the score and create the game objects 
    public void initialiseGame()
    {       
        score = 0;
        ball   = new GameObj(width/2, height/2, BALL_SIZE, BALL_SIZE, Color.BLACK);
        bat    = new GameObj(width/2, height - BRICK_HEIGHT*3/2, BRICK_WIDTH*2, BRICK_HEIGHT/4, Color.GREY);
        
        healthbricks = new PersistentGameObj[]{
        new PersistentGameObj(0, BRICK_X+(BRICK_HEIGHT*3), BRICK_WIDTH, BRICK_HEIGHT, Color.LIGHTGREEN, 3),
        new PersistentGameObj(BRICK_WIDTH, BRICK_X+(BRICK_HEIGHT*3), BRICK_WIDTH, BRICK_HEIGHT, Color.LIGHTGREEN, 3),
        new PersistentGameObj(BRICK_WIDTH*2, BRICK_X+(BRICK_HEIGHT*3), BRICK_WIDTH, BRICK_HEIGHT, Color.LIGHTGREEN, 3),
        new PersistentGameObj(BRICK_WIDTH*3, BRICK_X+(BRICK_HEIGHT*3), BRICK_WIDTH, BRICK_HEIGHT, Color.LIGHTGREEN, 3),
        new PersistentGameObj(BRICK_WIDTH*4, BRICK_X+(BRICK_HEIGHT*3), BRICK_WIDTH, BRICK_HEIGHT, Color.LIGHTGREEN, 3),
        new PersistentGameObj(BRICK_WIDTH*5, BRICK_X+(BRICK_HEIGHT*3), BRICK_WIDTH, BRICK_HEIGHT, Color.LIGHTGREEN, 3),
        new PersistentGameObj(BRICK_WIDTH*6, BRICK_X+(BRICK_HEIGHT*3), BRICK_WIDTH, BRICK_HEIGHT, Color.LIGHTGREEN, 3),
        new PersistentGameObj(BRICK_WIDTH*7, BRICK_X+(BRICK_HEIGHT*3), BRICK_WIDTH, BRICK_HEIGHT, Color.LIGHTGREEN, 3)
        		
        		
        };
        
        //TODO: Implement Arraylist and loop
//        int rows = 8;
//        int cols = 3;
//        
//        for (int i=0;i<rows;i++) {
//        	for (int k=0;k<cols;k++) {
//        		GameObj item = new GameObj(BRICK_WIDTH*i, BRICK_X+(BRICK_HEIGHT*(k-1)), BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK);
//        		albricks.add(item);
//        	}
//        	
//        }
        
        
        bricks = new GameObj[]{
        new GameObj(0, BRICK_X, BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK), //first row of 8
        new GameObj(BRICK_WIDTH, BRICK_X, BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK),
        new GameObj(BRICK_WIDTH*2, BRICK_X, BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK),
        new GameObj(BRICK_WIDTH*3, BRICK_X, BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK),
        new GameObj(BRICK_WIDTH*4, BRICK_X, BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK),
        new GameObj(BRICK_WIDTH*5, BRICK_X, BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK),
        new GameObj(BRICK_WIDTH*6, BRICK_X, BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK),
        new GameObj(BRICK_WIDTH*7, BRICK_X, BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK),
        
        new GameObj(0, BRICK_X+BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK), //second row of 8
        new GameObj(BRICK_WIDTH, BRICK_X+BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK),
        new GameObj(BRICK_WIDTH*2, BRICK_X+BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK),
        new GameObj(BRICK_WIDTH*3, BRICK_X+BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK),
        new GameObj(BRICK_WIDTH*4, BRICK_X+BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK),
        new GameObj(BRICK_WIDTH*5, BRICK_X+BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK),
        new GameObj(BRICK_WIDTH*6, BRICK_X+BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK),
        new GameObj(BRICK_WIDTH*7, BRICK_X+BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK),
        
        new GameObj(0, BRICK_X+(BRICK_HEIGHT*2), BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK), //third row of 8
        new GameObj(BRICK_WIDTH, BRICK_X+(BRICK_HEIGHT*2), BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK),
        new GameObj(BRICK_WIDTH*2, BRICK_X+(BRICK_HEIGHT*2), BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK),
        new GameObj(BRICK_WIDTH*3, BRICK_X+(BRICK_HEIGHT*2), BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK),
        new GameObj(BRICK_WIDTH*4, BRICK_X+(BRICK_HEIGHT*2), BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK),
        new GameObj(BRICK_WIDTH*5, BRICK_X+(BRICK_HEIGHT*2), BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK),
        new GameObj(BRICK_WIDTH*6, BRICK_X+(BRICK_HEIGHT*2), BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK),
        new GameObj(BRICK_WIDTH*7, BRICK_X+(BRICK_HEIGHT*2), BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK)};
        
    
        
        
        
    }

    
    // The main animation loop
    public void runGame()
    {
        try
        {
            Debug.trace("Model::runGame: Game starting"); 
            // set game true - game will stop if it is set to "finished"
            setGameState("running");
            while (!getGameState().equals("finished"))
            {
                updateGame();                        // update the game state
                modelChanged();                      // Model changed - refresh screen
                Thread.sleep( getFast() ? 3 : 6 ); // sleep for 3ms or 6ms depending on if getfast is enabled or disabled
     //I have changed this sleep value so that the game updates more frequently, this is so that the game looks smooth on high refresh rate monitors.
            }
            Debug.trace("Model::runGame: Game finished"); 
        } catch (Exception e) 
        { 
            Debug.error("Model::runAsSeparateThread error: " + e.getMessage() );
        }
    }
  
    // updating the game
    private synchronized void updateGame()
    {
        GameMedia gm = new GameMedia();
        
        // move the ball one step (the ball knows which direction it is moving in)
        ball.moveX(BALL_MOVE);                      
        ball.moveY(BALL_MOVE);
        // get the current ball position (top left corner)
        // Deal with possible edge of board hit
        if (ball.topX >= width - B - BALL_SIZE) {
        	
        	ball.changeDirectionX();
        	gm.PlayWallHitSound();
        }
        
        if (ball.topX <= 0 + B) {
        	
        	ball.changeDirectionX();
        	gm.PlayWallHitSound();
        }
        
        //Bat now no longer phases through the game window
        
      //if the bat hits the border of the wall move the bat back one movement
        if (bat.topX >= width - B - bat.width && bat.dirX != 0) bat.moveX(-BAT_MOVE); 
        if (bat.topX <= 0 + B)  bat.moveX(BAT_MOVE); // if the bat hits the border of the wall move the bat forward one movement
        
        if (ball.topY >= height - B - BALL_SIZE)  // Bottom
        { 
            ball.changeDirectionY(); 
            addToScore( HIT_BOTTOM );  // score penalty for hitting the bottom of the screen
            
        }
        if (ball.topY <= 0 + M)  ball.changeDirectionY();

       // check whether ball has hit a (visible) brick
        boolean hit = false;
        for (GameObj brick : bricks) {
        if (brick.hitBy(ball) && brick.visible) { //if a brick is visible and is hit by the ball
        	
        	addToScore(HIT_BRICK);				  //add the hit brick value to the score
        	hit = true;							 //change the Bool "hit" to true
        	gm.PlayBreakSound();				 //play the break sound effect 
        	brick.visible = false; 			     //and make the brick no longer visible
        }}
        
        
        //The following code implements the objlives variable added with PersistentGameObj
        
        for (PersistentGameObj brick : healthbricks) {
        	//if a brick is visible and is hit by the ball and the lives are more than 0
        	if(brick.hitBy(ball) && brick.visible && (brick.objlives > 0)) {
        		addToScore(HIT_BRICK);
        		hit = true;
        		brick.objlives--; //remove a life from that brick
        		brick.colour = Color.GOLD; //change its colour
        		gm.PlayBrickCrackSound(); //play the cracking sound
        		if (brick.objlives == 1) { //if the brick has only one life left
        			brick.colour = Color.RED; //set the colour to red
        			gm.PlayBrickCrackSound(); //play the crack sound
        			
        		}
        		
        		}
        	
            if (brick.hitBy(ball) && brick.visible && (brick.objlives == 0)) { //only make the brick invisible if it has run out of lives
            	addToScore(HIT_BRICK+50); //on the last hit of the healthbricks, a bonus of 50 points is earned
            	hit = true; 
            	gm.PlayBreakSound(); //play the final breaking sound
            	brick.visible = false; //make the brick invisible 
            }}
        

        if (hit) {
            ball.changeDirectionY();
        }
        
        // check whether ball has hit the bat
        if ( ball.hitBy(bat) ) {
        	gm.PlayHitSound();
            ball.changeDirectionY();
        }
        
    }

    // This is how the Model talks to the View
    // Whenever the Model changes, this method calls the update method in
    // the View. It needs to run in the JavaFX event thread, and Platform.runLater 
    // is a utility that makes sure this happens even if called from the
    // runGame thread
    public synchronized void modelChanged()
    {
        Platform.runLater(view::update);
    }
    
    
    // Methods for accessing and updating values
    // these are all synchronized so that the can be called by the main thread 
    // or the animation thread safely
    
    // Change game state - set to "running" or "finished"
    public synchronized void setGameState(String value)
    {  
        gameState = value;
    }
    
    // Return game running state
    public synchronized String getGameState()
    {  
        return gameState;
    }

    // Change game speed - false is normal speed, true is fast
    public synchronized void setFast(Boolean value)
    {  
        fast = value;
    }
    
    // Return game speed - false is normal speed, true is fast
    public synchronized Boolean getFast()
    {  
        return(fast);
    }

    // Return bat object
    public synchronized GameObj getBat()
    {
        return(bat);
    }
    
    // return ball object
    public synchronized GameObj getBall()
    {
        return(ball);
    }
    
    // return bricks
    public synchronized GameObj[] getBricks()
    {
        return(bricks);
    }
    
    public synchronized PersistentGameObj[] getHealthBricks()
    {
    	return(healthbricks);
    		
    }
    
    // return score
    public synchronized int getScore()
    {
        return(score);
    }
    
     // update the score
    private synchronized void addToScore(int n)    
    {
        score += n;
        
        
    }
    
    // move the bat one step - -1 is left, +1 is right
    public synchronized void moveBat( int direction )
    {   
    	
        int dist = direction * BAT_MOVE;    // Actual distance to move
        Debug.trace( "Model::moveBat: Move bat = " + dist );
        bat.moveX(dist);
    }
    
    
    
    //sets the bat's X position to the position of the mouse
    public synchronized void moveBatMouse(int x)
    {
    	bat.setX(x); //sets the bat's x position to the mouse's location
    }
    
    
    
    
    
    
    
    
    
}   
    