import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
  
  public class GameMedia {
  
	  private MediaPlayer player;
	  
	  public void playSound() {
		  
		String sound = "res/backgroundMusic.mp3";
		
	    if (player != null) player.stop();
	    Media media = new Media(new File(sound).toURI().toString());
	    MediaPlayer player = new MediaPlayer(media);
	    player.setVolume(0.00);
	    player.play();
	    
	  }

	  
	  
	  
	  
}
 