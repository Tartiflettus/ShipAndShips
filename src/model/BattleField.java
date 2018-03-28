package model;

import java.util.ArrayList;
import java.util.List;

import model.ship.Ship;

public class BattleField {
	
	private List<Ship> ships;
	private boolean[][] touched;

	public BattleField(int size) {
		this.touched = new boolean[size][size];
		this.ships = new ArrayList<Ship>();
	}
	
	public boolean receiveShot(int x, int y) {
		for(Ship s : ships) {
			if(s.receiveShot(x, y)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean destroyed(int x, int y) {
		for(Ship s : ships) {
			/*if(s.isDestructible(x, y) && s.isDestroyed()) {
				return true;
			}*/
		}
		return false;
	}
	
	public Ship getShip(int x, int y) {
		for(Ship s : ships) {
			/*if(s.isDestructible(x, y)) {
				return s;
			}*/
		}
		return null;
	}
	
	public boolean touched(int x, int y) {
		return touched[x][y];
	}
	
	public boolean won() {
		return false;
	}
	

}
