package model.ship.modern;

import model.ship.Ship;


/**
 * A gondola from the modern period
 * @author Maureen
 *
 */
public class Gondola extends Ship {

	public Gondola() {
		height = 2;
		width = 1;
		nbCases = height*width;
		nbCasesTouched = 0;
		isPlaced = false;
	}
	
	@Override
	public String toString() {
		return "Gondola";
	}

}
