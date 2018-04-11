package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import model.Model;
import model.ship.Ship;
import view.FieldView;

public class AllyListener implements ActionListener {
	private int x, y;
	private Model model;
	private FieldView field;
	
	public AllyListener(Model mod, FieldView fieldView, int i, int j) {
		model = mod;
		field = fieldView;
		x = i;
		y = j;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(model.getGameState() == Model.GameState.PLACEMENT) {
			final Ship s = field.getCurrentShip();
			if(field.currentOrientationchanged()) {
				s.changeOrientation();
			}
			if(model.placeShip(field.getCurrentShip(), x, y)) {
				field.removeShip(s);
			}
		}
	}

}
