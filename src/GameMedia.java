import java.nio.file.Paths;
import javafx.scene.media.Media;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;

/**
 * The GameMedia class adds the functionality to play sound to my project using javafx's built-in "MediaPlayer" and "AudioClip" imports.
 * The Mediaplayer object is used to play the game's main theme and I'm using the AudioClip object to play the game specific sounds. 
 * I am doing it this way as AudioClip can be called over and over with minimal latency,
 *  whereas the MediaPlayer needs to be reset to time zero before playing the sound again. 
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
		
		final String theme = "res/backgroundMusic.mp3"; //the relative path to the file
		//below creates a new media object, converts the theme string to a uri then back to a string and applies it as the content to the media object.
	    Media media = new Media(Paths.get(theme).toUri().toString()); 
	    MediaPlayer player = new MediaPlayer(media); //creates a new mediaplayer object called player that contains the media
	    player.setVolume(0.1); 
	    player.play();
	    
	  }
	
	/**
	 * Plays the Hit Sound when called
	 */
	
	public void PlayHitSound() {
	
		//demonstrating how paths.get can be used with an inline string instead of a pre existing variable.
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
 