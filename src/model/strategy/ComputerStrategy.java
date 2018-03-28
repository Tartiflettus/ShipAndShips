package model.strategy;

import exception.NotInFieldException;
import model.BattleField;

public interface ComputerStrategy {

	/**
	 * shot at a position according to the strategy
	 * @param b battlefield of the enemy
	 * @throws NotInFieldException
	 */
	public void shot(BattleField b) throws NotInFieldException;
}
