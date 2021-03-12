import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The GameMedia class adds the functionality to play sound to my project using javafx's built-in "Media Player"
 * Currently this only plays the theme tune whilst the game is running but I am going to add game specific sounds soon!
 * 
 * @author Finlay Watling
 *
 */
  
 
public class GameMedia {
	  
	/**
	 * Plays the main theme when called
	 */
	public void playMainTheme() {
		
		String sound = "res/backgroundMusic.mp3";
	    Media media = new Media(new File(sound).toURI().toString());
	    MediaPlayer player = new MediaPlayer(media);
	    player.setVolume(0.1); //no volume for testing the rest of the program.. very annoying after a few hours!!
	    player.play();
	    
	  }

	  
	  
	  
	  
}
 