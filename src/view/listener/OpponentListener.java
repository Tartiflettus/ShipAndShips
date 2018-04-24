package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import model.Model;

public class OpponentListener implements ActionListener {
	private int x, y;
	private Model model;
	boolean buttonIsActive = true;
	
	public OpponentListener(Model mod, int i, int j) {
		model = mod;
		x = i;
		y = j;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(model.currentPlayer() == Model.PLAYER && model.getGameState() == Model.GameState.IN_GAME && !model.won()
				&& buttonIsActive == true) {
			model.shot(x, y);
			buttonIsActive = false;
		}
		
	}

}
