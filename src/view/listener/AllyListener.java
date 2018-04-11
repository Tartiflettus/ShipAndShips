package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Model;

public class AllyListener implements ActionListener {
	private int x, y;
	private Model model;
	
	public AllyListener(Model mod, int i, int j) {
		model = mod;
		x = i;
		y = j;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// SI ETAT DU JEU = PLACER LES BATEAUX // TODO 
		if(!model.won()) {
			//model.placeShip(ship, x, y);
			
		}
	}

}
