import java.nio.file.Paths;
import javafx.scene.media.Media;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;

/**
 * The GameMedia class adds the functionality to play sound to my project using javafx's built-in "MediaPlayer" and "AudioClip" imports.
 * The Mediaplayer object is used to play the game's main theme and I'm using the AudioClip object to play the game specific sounds. 
 * I am doing it this way as AudioClip can be called over and over with minimal latency, whereas the MediaPlayer needs to be reset to time 0 before playing the sound again. 
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
	    player.setVolume(0.4); //no volume for testing the rest of the program.. very annoying after a few hours!!
	    player.play();
	    
	  }
	
	/**
	 * Plays the Hit Sound when called
	 */
	
	public void PlayHitSound() {
		
		final AudioClip sound = new AudioClip(Paths.get("res/ballhit.wav").toUri().toString());
		sound.play();
		
	}
	
	/**
	 * Plays the Block Break Sound when called
	 */
	
	public void PlayBreakSound() {
		
		final AudioClip sound = new AudioClip(Paths.get("res/brickhit.wav").toUri().toString());
		sound.play();
		
	}

	/**
	 * Plays the Wall Hit Sound when called
	 */
	
public void PlayWallHitSound() {
		
		final AudioClip sound = new AudioClip(Paths.get("res/hitwall.wav").toUri().toString());
		sound.play();
		
	}
	  
	/**
	 *  Plays the Brick Cracking Sound when called
	 */

public void PlayBrickCrackSound() {
	
	final AudioClip sound = new AudioClip(Paths.get("res/crack.wav").toUri().toString());
	sound.play();
	
}
	  
	  
	  
}
 