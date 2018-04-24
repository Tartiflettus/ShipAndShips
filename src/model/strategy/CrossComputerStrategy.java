package model.strategy;

import java.io.Serializable;
import java.util.Random;

import exception.NotInFieldException;
import model.BattleField;

/**
 * 
 * @author Eva
 * strategy cross used when the computer plays 
 *
 */
public class CrossComputerStrategy implements ComputerStrategy, Serializable {

	private static CrossComputerStrategy instance = new CrossComputerStrategy();
	private int lastShotX;
	private int lastShotY;
	private Random rand;

	private CrossComputerStrategy() {
		lastShotX = -1;
		lastShotY = -1;
		rand = new Random();
	}

	public static CrossComputerStrategy getInstance() {
		return instance;
	}
	
	
	@Override
	public void shot(BattleField b) throws NotInFieldException {
		if (lastShotX != -1 && lastShotX != -1) {
			if (b.getShip(lastShotX, lastShotY) != null && b.destroyed(lastShotX, lastShotY)) {
				lastShotX = -1;
				lastShotY = -1;
				normalShot(b);
			} else if (b.getShip(lastShotX, lastShotY) != null && !b.destroyed(lastShotX, lastShotY)) {
				finishHim(b);
			}
		} else {
			normalShot(b);
		}
	}

	/**
	 * shot according to the strategy
	 * @param b battlefield of the enemy
	 * @throws NotInFieldException
	 */
	private void normalShot(BattleField b) throws NotInFieldException {
		boolean findShot = false;
		int x = -1, y = -1;
		while (!findShot) {
			x = rand.nextInt(b.size());
			y = rand.nextInt(b.size());
			/*if (!somethingAround(b, x, y)) {
				findShot = true;
			}*/
			if(!b.touched(x, y)) { // if we did'nt already shot this area
				findShot = true;
			}
			System.out.println("Searching a good shot");
		}
		if(b.receiveShot(x, y)) {
			lastShotX = x;
			lastShotY = y;
		}
	}

	/**
	 * tell if something is around the position given
	 * @param b battlefield of the enemy
	 * @param x 
	 * @param y 
	 * @return boolean
	 * @throws NotInFieldException
	 */
	private boolean somethingAround(BattleField b, int x, int y) throws NotInFieldException {
		for (int l = y - 1; l < y + 3; l++) {
			for (int c = x - 1; c < x + 3; c++) {
				if (isInMap(b, c, l) && b.touched(c, l)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * tell if the given position belongs to the battlefield
	 * @param b battlefield of the enemy
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isInMap(BattleField b, int x, int y) {
		return x >= 0 && x < b.size() && y >= 0 && y < b.size();
	}

	/**
	 * finish a enemy ship already detected by the computer
	 * @param b battlefield of the enemy
	 * @throws NotInFieldException
	 */
	private void finishHim(BattleField b) throws NotInFieldException {
		// south
		if (lastShotY + 1 < b.size() && !b.touched(lastShotX, lastShotY + 1)) {
			b.receiveShot(lastShotX, lastShotY + 1);
			// east
		} else if (lastShotX + 1 < b.size() && !b.touched(lastShotX + 1, lastShotY)) {
			b.receiveShot(lastShotX + 1, lastShotY);
			// west
		} else if (lastShotX - 1 >= 0 && !b.touched(lastShotX - 1, lastShotY)) {
			b.receiveShot(lastShotX - 1, lastShotY);
			// north
		} else if (lastShotY - 1 >= 0 && !b.touched(lastShotX, lastShotY - 1)) {
			b.receiveShot(lastShotX, lastShotY - 1);
		}else { // no direction is shotable
			lastShotX = -1;
			lastShotY = -1;
			normalShot(b);
		}
	}

	@Override
	public String parse() {
		// TODO Auto-generated method stub
		return null;
	}

}
