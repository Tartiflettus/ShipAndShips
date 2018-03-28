package model.strategy;

import java.util.List;

import exception.NotPlaceableException;
import model.BattleField;
import model.ship.Ship;

public interface PlacementStrategy {

	public void placeShips(BattleField bf, List<Ship> ships) throws NotPlaceableException;

}
