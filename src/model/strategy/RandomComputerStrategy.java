package model.strategy;

import java.util.Random;

import exception.NotInFieldException;
import model.BattleField;

/**
 * 
 * @author Eva
 *	random strategy used when the computer plays
 */
public class RandomComputerStrategy implements ComputerStrategy {
	
	private static RandomComputerStrategy instance = new RandomComputerStrategy();
	private Random rand;
	
	public static RandomComputerStrategy getInstance() {
		return instance;
	}
	
	
	private RandomComputerStrategy() {
		rand = new Random();
	}

	/**
	 *  @param b Battle Field
	 */
	public void shot(BattleField b) throws NotInFieldException {
		boolean findShot = false;
		int x = -1, y = -1;
		while(!findShot) {
			x = rand.nextInt(b.size());
			y = rand.nextInt(b.size());
			if(!b.touched(x, y)) {
				findShot = true;
			}
		}
		b.receiveShot(x, y);
	}

	@Override
	public String parse() {
		return null;
	}


}
