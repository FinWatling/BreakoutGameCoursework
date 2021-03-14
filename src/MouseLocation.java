import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseLocation implements EventHandler<MouseEvent>{

	View view;
	Controller controller;
	
	
	@Override
	public void handle(MouseEvent e) {
		
		controller.userMouseInteraction(e);
		
		
	}


	
}
