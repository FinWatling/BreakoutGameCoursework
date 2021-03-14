import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

//TODO: WORK OUT WHY THIS FIXED MY ISSUES

public class MouseLocation implements EventHandler<MouseEvent>{

	View view;
	Controller controller;
	
	
	@Override
	public void handle(MouseEvent e) {
		
		controller.userMouseInteraction(e);
		
		
	}


	
}
