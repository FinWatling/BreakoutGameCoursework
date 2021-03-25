import java.nio.file.Paths;
import javafx.scene.media.Media;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;

/**
 * The GameMedia class adds the functionality to play sound to my project using javafx's built-in "Media Player"
 * Currently this only plays the theme tune whilst the game is running but I am going to add game specific sounds soon!
 * 
 * @author Finlay Watling
 * 
 *
 */
  
 
public class GameMedia {
	  
	/**
	 * Plays the main theme when called
	 */
	public void playMainTheme() {
		
		final String theme = "res/backgroundMusic.mp3";
	    Media media = new Media(Paths.get(theme).toUri().toString());
	    MediaPlayer player = new MediaPlayer(media);
	    player.setVolume(0.1); //no volume for testing the rest of the program.. very annoying after a few hours!!
	    player.play();
	    
	  }
	
	public void PlayHitSound() {
		
		final AudioClip sound = new AudioClip(Paths.get("res/ballhit.wav").toUri().toString());
		sound.play();
		
	}
	
	public void PlayBreakSound() {
		
		final AudioClip sound = new AudioClip(Paths.get("res/brickhit.wav").toUri().toString());
		sound.play();
		
	}

	  
	  
	  
	  
}
 