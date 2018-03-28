package model;

import model.ship.Ship;

public class BattleField {
	
	private Ship[][] field;
	private boolean[][] touched;

	public BattleField(int size) {
		// TODO Auto-generated constructor stub
	}
	
	public boolean receiveShot(int x, int y) {
		return false;
	}
	
	public boolean destroyed(int x, int y) {
		return false;
	}
	
	public Ship getShip(int x, int y) {
		return null;
	}
	
	public boolean touched(int x, int y) {
		return false;
	}
	
	public boolean won() {
		return false;
	}
	

}
