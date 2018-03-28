package model.strategy;

import exception.NotPlaceableException;
import model.BattleField;
import model.ship.Ship;

public interface PlacementStrategy {

	public void placeShips(BattleField bf, Ship... ships) throws NotPlaceableException;
	
	public String parse();

}
