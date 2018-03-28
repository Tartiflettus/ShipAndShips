package model.strategy;

public class RandomComputerStrategy implements ComputerStrategy {
	
	private static RandomComputerStrategy instance = new RandomComputerStrategy();

	
	public RandomComputerStrategy getInstance() {
		return instance;
	}
	
	
	private RandomComputerStrategy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void shot(int x, int y) {
		// TODO Auto-generated method stub

	}

}
