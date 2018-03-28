package model.ship.modern;

import model.ship.Ship;


public class Cruiser extends Ship {

	public Cruiser() {
		height = 8;
		width = 2;
		nbCases = height*width;
		nbCasesTouched = 0;
		isPlaced = false;
	}

}
