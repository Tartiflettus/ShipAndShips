package model.strategy;

import java.util.List;

import exception.ShipException;
import model.BattleField;
import model.ship.Ship;

public interface PlacementStrategy {
	
	public String parse();
	
	public void placeShips(BattleField bf, List<Ship> ships) throws ShipException;


}
