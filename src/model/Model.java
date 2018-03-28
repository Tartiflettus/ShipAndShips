package model;

import java.io.IOException;
import java.util.Observable;

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
		return false;
	}
	
	public void setPeriod(ShipFactory sf) {
		
	}
	
	public void setSaveMethod(ModelDAO dao) {
		
	}
	
	public boolean shot(int x, int y) {
		return false;
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
