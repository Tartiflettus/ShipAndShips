package model;

import java.util.ArrayList;
import java.util.List;

import exception.NotInFieldException;
import model.ship.Ship;



/**
 * 2D field with ships on it
 * @author Victor
 *
 */
public class BattleField {
	
	private List<Ship> ships;
	private boolean[][] touched;
	
	
	
	private boolean invalidPos(int x, int y) {
		return x < 0 || x >= size() || y < 0 || y >= size();
	}

	
	/**
	 * Construct a battlefield
	 * @param size of the battlefield
	 */
	public BattleField(int size) {
		this.touched = new boolean[size][size];
		this.ships = new ArrayList<Ship>();
	}
	
	/**
	 * Size of the battlefield (in cases)
	 * @return size of the battlefield (in cases)
	 */
	public int size() {
		return touched.length;
	}
	
	
	/**
	 * Receive a shot at a given position on the field
	 * @param x absissa
	 * @param y ordinate
	 * @return true if a ship was touched
	 * @throws NotInFieldException 
	 */
	public boolean receiveShot(int x, int y) throws NotInFieldException {
		if(invalidPos(x, y)) throw new NotInFieldException();
			
		for(Ship s : ships) {
			if(s.receiveShot(x, y)) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Indicates whether a ship is destroyed at a given position
	 * @param x absissa
	 * @param y ordinate
	 * @return true if a ship is destroyed at the given position
	 * @throws NotInFieldException 
	 */
	public boolean destroyed(int x, int y) throws NotInFieldException {
		if(invalidPos(x, y)) throw new NotInFieldException();
		
		for(Ship s : ships) {
			if(s.isDestructible(x, y) && s.isDestroyed()) {
				return true;
			}
		}
		return false;
	}

	
	/**
	 * Get the ship at a given position
	 * @param x absissa
	 * @param y ordinate
	 * @return the ship at the given position, or null
	 * @throws NotInFieldException 
	 */
	public Ship getShip(int x, int y) throws NotInFieldException {
		if(invalidPos(x, y)) throw new NotInFieldException();
		
		for(Ship s : ships) {
			if(s.isDestructible(x, y)) {
				return s;
			}
		}
		return null;
	}
	
	
	/**
	 * Indicates whether a position has already been shot
	 * @param x absissa
	 * @param y ordinate
	 * @return true if the position has already been shot
	 * @throws NotInFieldException 
	 */
	public boolean touched(int x, int y) throws NotInFieldException {
		if(invalidPos(x, y)) throw new NotInFieldException();
		
		return touched[x][y];
	}
	
	
	/**
	 * Indicates if all ships are destroyed
	 * @return true if all ships are destroyed
	 */
	public boolean won() {
		for(Ship s : ships) {
			if(!s.isDestroyed()) {
				return false;
			}
		}
		return true;
	}
	
	
	
	/**
	 * Place a ship at the given position
	 * @param s ship to be placed
	 * @return true if the ship was correctly placed
	 * @throws NotInFieldException 
	 */
	public boolean placeShip(Ship s) throws NotInFieldException {
		final int x = s.getX(), y = s.getY();
		final boolean horizontal = s.OrientationChanged();
		final int w = s.getWidth(), h = s.getHeight();
		final int xe = x+w, ye = y+h;
		
		if(horizontal) {
			for(int i=x; i < Math.min(xe, size()); i++) {
				if(getShip(i, y) != null) {
					return false;
				}
			}
		}else {
			for(int i=y; i < Math.min(ye, size()); i++) {
				if(getShip(x, i) != null) {
					return false;
				}
			}
		}
		
		ships.add(s);
		return true;
	}

}
