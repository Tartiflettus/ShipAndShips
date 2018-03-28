package model.strategy;

import model.BattleField;
import model.ship.Ship;

public class PlacementRandomStrategy implements PlacementStrategy {
	
	private static final PlacementRandomStrategy instance = new PlacementRandomStrategy();
	
	public static PlacementRandomStrategy getInstance() {
		return instance;
	}

	private PlacementRandomStrategy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void placeShips(BattleField bf, Ship... ships) {
		// TODO Auto-generated method stub

	}

}
