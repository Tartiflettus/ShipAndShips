package model;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Observable;

import exception.NotInFieldException;
import exception.NotPlaceableException;
import exception.ShipException;
import model.ship.Ship;
import model.ship.factory.ModernShipFactory;
import model.ship.factory.ShipFactory;
import model.strategy.ComputerStrategy;
import model.strategy.CrossComputerStrategy;
import model.strategy.PlacementRandomStrategy;
import model.strategy.PlacementStrategy;
import model.strategy.RandomComputerStrategy;

/**
 * Base class, interface to wich communicate to play battleship
 * 
 * @author PUBC
 *
 */

public class Model extends Observable implements Serializable {
	
	public enum GameState{PLACEMENT, IN_GAME};
	
	public final transient static int PLAYER = 0, PC = 1;
	
	private int currentPlayer;
	private GameState gameState = GameState.PLACEMENT;
	
	transient private ModelDAO dao;
	transient private ShipFactory shipFactory;
	
	private int sizeBattleField;
	private ComputerStrategy strat;
	private PlacementStrategy placement;
	

	private BattleField ally, opponent;
	
	//private Ship currentShip;
	private List<Ship> shipsNoPlaced;
	private boolean shipsPlacedComputer;

	public Model() {
		// defaultvalues
		sizeBattleField = 10;

		ally = new BattleField(sizeBattleField);
		opponent = new BattleField(sizeBattleField);

		shipFactory = ModernShipFactory.getInstance();
		strat = RandomComputerStrategy.getInstance();
		placement = PlacementRandomStrategy.getInstance();
		shipsNoPlaced = getShipFactory().getShips();
		shipsPlacedComputer = false;
	}

	public Model(ShipFactory age, ComputerStrategy strategy, PlacementStrategy placementStrat) {
		newGame(age, strategy, placementStrat);
	}
	
	public void newGame(ShipFactory age, ComputerStrategy strategy, PlacementStrategy placementStrat) {
		sizeBattleField = 10;
		gameState = GameState.PLACEMENT;
		ally = new BattleField(sizeBattleField);
		opponent = new BattleField(sizeBattleField);

		shipFactory = age;
		strat = strategy;
		placement = placementStrat;
		shipsNoPlaced = getShipFactory().getShips();
		shipsPlacedComputer = false;
		update();
	}
	

	public List<Ship> getShipsNoPlaced() {
		return shipsNoPlaced;
	}
	

	/**
	 * @return true if the player or the computer won
	 */
	public boolean won() {
		if ((ally.won() || opponent.won()) && gameState == GameState.IN_GAME) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param sf
	 *            ShipFactory
	 */
	public void setPeriod(ShipFactory sf) {
		shipFactory = sf;

	}

	/**
	 * set the strategy chosen by the player
	 * @param e
	 */
	public void setStrategy(String s) {
		switch(s) {
		case "Cross" : 
			strat = CrossComputerStrategy.getInstance();
			break;
		case "Random":
			strat = RandomComputerStrategy.getInstance();
		}
	}

	/**
	 * Notify Observers
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
			// Shot on the current battlefield
			switch (currentPlayer) {
			case PLAYER:
				success = opponent.receiveShot(x, y);
				break;
			case PC:
				success = ally.receiveShot(x, y);
				break;
			}
			if(!won()) {
				endTurn();
			}
			update();

			return success;
		} catch (NotInFieldException e) {
			System.err.println("Shooting out of battlefield");
		}

		return success;
	}
	
	
	/**
	 * check if an ally case is touched
	 * @param x absissa
	 * @param y ordinate
	 * @return whether this case is touched
	 */
	public boolean allyTouched(int x, int y) {
		try {
			return ally.touched(x, y);
		} catch (NotInFieldException e) {
			System.err.println("Checking if ally touched out of field");
		}
		return false;
	}
	
	/**
	 * check if an opponent case is touched
	 * @param x absissa
	 * @param y ordinate
	 * @return whether this case is touched
	 */
	public boolean opponentTouched(int x, int y) {
		try {
			return opponent.touched(x, y);
		} catch (NotInFieldException e) {
			System.err.println("Checking if opponent touched out of field");
		}
		return false;
	}
	
	
	
	public Ship getAllyShip(int x, int y) {
		try {
			return ally.getShip(x, y);
		} catch (NotInFieldException e) {
			System.err.println("Searching ally ship out of field");
		}
		return null;
	}
	
	public Ship getOpponentShip(int x, int y) {
		try {
			return opponent.getShip(x, y);
		} catch (NotInFieldException e) {
			System.err.println("Searching opponent ship out of field");
		}
		return null;
	}
	

	/**
	 * Execute the computer placement strategy of the ships
	 */
	public void placeShipComputer() {
		try {
			if (shipsPlacedComputer == false) {
				List<Ship> listShips = shipFactory.getShips();
				placement.placeShips(opponent, listShips);
				shipsPlacedComputer = true;
			} 

		} catch (NotPlaceableException e) {
			System.err.println("The computer can no longer place ships");
		} catch (ShipException e) {
			System.err.println("Error while trying to place computer ships");
		}

	}

	/**
	 * 
	 * @param ship
	 *            that the player want to place
	 * @return true if the player can place the ship on the BattleField
	 */
	public boolean placeShip(Ship ship, int x, int y) {
		try {
			ship.setPosition(x, y);
			boolean everythingIsOk = ally.placeShip(ship);
			if (everythingIsOk) {
				shipsNoPlaced.remove(ship);
				update();
				return true;
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
		if (currentPlayer == PC) {
			currentPlayer = PLAYER;
		} else {
			currentPlayer = PC;
			try {
				if(!won()) {
					strat.shot(ally);
					currentPlayer = PLAYER;
				}
			} catch (NotInFieldException e) {
				System.err.println("Computer error when he wants to shot us");
			}
		}
	}

	/**
	 * sauvegarde l'état actuel du jeu dans le fichier de chemin fn.souss par l'intermediaire du DAO
	 * @param fn
	 * @throws IOException
	 */
	
	public void save(String fn) throws IOException {	
		
		dao.save(this, fn);
		
	}
	
	/**
	 * charge l'objet Model du fichier fn et set les champs de l'objet dans le model actuel
	 * @param fn
	 * @throws IOException
	 */
	
	public void load(String fn) throws IOException{
		
		Model info = dao.load(fn);
		
		gameState = info.getGameState();
		currentPlayer = info.currentPlayer();
		ally = info.getAlly();
		opponent = info.getOpponent();
		strat = info.getStrat();
		placement = info.getPlacement();
		sizeBattleField = info.getSizeBattleField();
		
		
	}
	
	/**
	 * 
	 * @return true if the current player is the Human ; false if it's the computer
	 */
	public int currentPlayer() {
		return currentPlayer;
	}

	public BattleField getAlly() {
		return ally;
	}

	public BattleField getOpponent() {
		return opponent;
	}
	
	public ComputerStrategy getStrat(){
		return strat;
	}
	
	public PlacementStrategy getPlacement(){
		return placement;
	}
	
	public int getSizeBattleField(){
		return sizeBattleField;
	}


	public String parse(){
		StringBuilder buff = new StringBuilder("");
		
		buff.append(currentPlayer+"	"+strat.parse()+"	"+placement.parse()+"	"+ally.parse()+"	"+opponent.parse());
		
		return buff.toString();
	
	}

	/**
	 * Access state of the game
	 * 
	 * @return state of the game
	 */
	public GameState getGameState() {
		return gameState;
	}
	
	
	/**
	 * Set the game state
	 * @param gs new game state
	 */
	public void setGameState(GameState gs) {
		gameState = gs;
		update();
	}
	
	
	/**
	 * Access the ship factory of this model
	 * @return ship factory of this model
	 */
	public ShipFactory getShipFactory() {
		return shipFactory;
	}


}
