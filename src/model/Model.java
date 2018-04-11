package model;

import java.io.IOException;
import java.util.List;
import java.util.Observable;

import exception.NotInFieldException;
import exception.NotPlaceableException;
import exception.ShipException;
import model.ship.Ship;
import model.ship.factory.ShipFactory;
import model.strategy.ComputerStrategy;
import model.strategy.PlacementStrategy;


/**
 * Base class, interface to wich communicate to play battleship
 * @author PUBC
 *
 */
public class Model extends Observable {
	
	public final static int PLAYER = 1, PC = 0;
	private int currentPlayer;
	
	private ModelDAO dao;
	private ShipFactory shipFactory;
	private ComputerStrategy strat;
	private PlacementStrategy placement;
	
	private BattleField ally, opponent;
	
	public Model() {
		int sizeBattleField = 10;
		ally = new BattleField(sizeBattleField);
		opponent = new BattleField(sizeBattleField);
	}
	
	public Model(ShipFactory age, ComputerStrategy strategy, PlacementStrategy placementStrat) {
		int sizeBattleField = 10;
		ally = new BattleField(sizeBattleField);
		opponent = new BattleField(sizeBattleField);
		
		shipFactory = age;
		strat = strategy;
		placement = placementStrat;
	}
	
	/**
	 * @return true if the player or the computer won
	 */
	public boolean won() {
		if(ally.won() || opponent.won()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param sf ShipFactory
	 */
	public void setPeriod(ShipFactory sf) {
		shipFactory = sf;
		
	}
	/**
	 *  Notify Observers
	 */
	private void update() {
		setChanged();
		notifyObservers();
	}
	
	public void setSaveMethod(ModelDAO dao) {
		
	}
	
	public boolean shot(int x, int y) {
		boolean success = false;
		try {
			//Shot on the current battlefield
			switch(currentPlayer) {
			case PLAYER:
				success = opponent.receiveShot(x, y);
				break;
			case PC:
				success = ally.receiveShot(x, y);
				break;
			}
			if(success) {
				endTurn();
				update();
			}
			
			return success;
		}
		catch(NotInFieldException e) {
			System.err.println("Shooting out of battlefield");
		}
		
		return success;
	}
	
	/**
	 * Execute the computer placement strategy of the ships 
	 */
	public void PlaceShipComputer() {
		try {
			List<Ship> listShips = shipFactory.getShips();
			placement.placeShips(opponent, listShips);
			
		} catch (NotPlaceableException e) {
			System.err.println("The computer can no longer place ships");
		} catch(ShipException e) {
			System.err.println("Error while trying to place computer ships");
		}
			
	}
	
/**
 * 
 * @param ship that the player want to place
 * @return true if the player can place the ship on the BattleField
 */
	public boolean placeShip(Ship ship, int x, int y) {
		try {
			ship.setPosition(x, y);
			boolean everythingIsOk = ally.placeShip(ship);
			if(everythingIsOk) {
				update();
			} else {
				return false; 
			}
		} catch (NotInFieldException e) {
			System.err.println("Impossible to place the ship");
		}
		return false;
	}
	
	/**
	 * change the current player
	 */

	private void endTurn() {
		if(currentPlayer == PC) {
			currentPlayer = PLAYER;
		} else {
			currentPlayer = PC;
		}
	}
	
	/**
	 * 
	 * @return true if the current player is the Human ; false if it's the computer
	 */
	public int currentPlayer() {
		return currentPlayer;
	}
	
	public void save() throws IOException {	
		
	}
	
	public BattleField getAlly() {
		return ally;
	}

	public BattleField getOpponent() {
		return opponent;
	}

	public String parse(){
		StringBuilder buff = new StringBuilder("");
		
		buff.append(currentPlayer+"	"+strat.parse()+"	"+placement.parse()+"	"+ally.parse()+"	"+opponent.parse());
		
		return buff.toString();
	
	}

}
