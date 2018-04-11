package model.strategy;

import java.util.List;

import exception.NotInFieldException;
import exception.NotPlaceableException;
import exception.ShipException;
import model.BattleField;
import model.ship.Ship;

public class PlacementRandomStrategy implements PlacementStrategy {
	
	private static final PlacementRandomStrategy instance = new PlacementRandomStrategy();
	
	public static PlacementRandomStrategy getInstance() {
		return instance;
	}

	private PlacementRandomStrategy() {
	}


	/**
	 * @param bf Battle Field
	 * @param ships list of ships
	 * @throws NotInFieldException 
	 */
	public void placeShips(BattleField bf, List<Ship> ships) throws ShipException {
		int bfSize = bf.size();
		for(Ship s : ships) {
			int height = s.getHeight(); 
			int width = s.getWidth();

			boolean placeable = false;
			int cptLoop = 0;
			while(!placeable) {
				int cptCases = 0;
				int x = (int) (Math.random()*(bfSize - width));
				int y = (int) (Math.random()*(bfSize - height));
				
				for (int i = x ; i < (x+width) ; i++) {
					for (int j = y ; j < (y+height) ; j++) {
						if(s.isDestructible(i, j)) {
							cptCases ++;
						}
					}
				}				
				if (cptCases == s.getNbCases()) {
					placeable = true;
					s.setPosition(x, y);
					bf.placeShip(s);
				}
				cptLoop ++;
				if(cptLoop == 200) throw new NotPlaceableException();
			}
		}

	}
	
	public String parse(){
		return null;
	}

}
