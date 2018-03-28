package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import exception.NotPlaceableException;
import model.ship.Ship;
import model.ship.factory.ShipFactory;
import model.strategy.ComputerStrategy;
import model.strategy.PlacementStrategy;

public class Model extends Observable {
	
	private final static boolean PLAYER = false, PC = true;
	private boolean currentPlayer;
	
	private ModelDAO dao;
	private ShipFactory shipFactory;
	private ComputerStrategy strat;
	private PlacementStrategy placement;
	
	private BattleField ally, opponent;


	
	public Model() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean won() {
		if(ally.won() || opponent.won()) {
			return true;
		}
		return false;
	}
	
	public void setPeriod(ShipFactory sf) {
		
	}
	
	public void setSaveMethod(ModelDAO dao) {
		
	}
	
	public boolean shot(int x, int y) {
		return false;
	}
	
	public void PlaceShipComputer() {
		try {
			List<Ship> listShips = shipFactory.getShips();
			placement.placeShips(opponent, listShips);
		} catch (NotPlaceableException e) {
			System.err.println("The computer can no longer place ships");
		}
			
	}
	
	public boolean placeShip(int x, int y, Ship ship) {
		return false;
	}
	
	public void endTurn() {
		
	}
	
	public boolean currentPlayer() {
		return false;
	}
	
	public void save() throws IOException {
		
	}

}
