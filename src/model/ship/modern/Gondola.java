package model.ship.modern;

import model.ship.Ship;


public class Gondola extends Ship {

	public Gondola() {
		height = 2;
		width = 1;
		nbCases = height*width;
		nbCasesTouched = 0;
	}

}
