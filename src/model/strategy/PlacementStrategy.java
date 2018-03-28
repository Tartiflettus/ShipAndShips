package model.strategy;

import model.BattleField;
import model.ship.Ship;

public interface PlacementStrategy {

	public void placeShips(BattleField bf, Ship... ships);

}
