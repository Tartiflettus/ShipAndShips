package model.ship.factory;

import java.util.ArrayList;
import java.util.List;

import model.ship.Ship;
import model.ship.modern.Cruiser;
import model.ship.modern.Gondola;



/**
 * A factory to construct modern ships
 * @author Maureen
 *
 */
public class ModernShipFactory implements ShipFactory {
	private static final ModernShipFactory instance = new ModernShipFactory();
	
	
	private ModernShipFactory() {
	}
	
/**
 * return the list of Modern Ships
 */
	public List<Ship> getShips() {
		ArrayList<Ship> tabShip = new ArrayList<>(2);

		Ship ship1 = new Cruiser();
		Ship ship2 = new Gondola();
		
		tabShip.add(ship1);
		tabShip.add(ship2);
		
		return tabShip;
	}

	/**
	 * 
	 * @return the instance of ModernShipFactory
	 */
	public static ModernShipFactory getInstance() {
		return instance;
	}
}
