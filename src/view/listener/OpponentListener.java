package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		// SI ETAT DU JEU != PLACEMENT DES BATEAUX //TODO
		if(!model.won() && model.currentPlayer() == Model.PLAYER && buttonIsActive == true) {
			model.shot(x, y);
			buttonIsActive = false;
		}
		
	}

}
