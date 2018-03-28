package model.ship;

public abstract class Ship {
	protected int x;
	protected int y;
	protected int height;
	protected int width;
	protected int nbCases;
	protected int nbCasesTouched;
	
	/**
	 * 
	 * @return true the Orientation is different from the initial orientation
	 */
	public boolean OrientationChanged() {
		 return (height >= width) ? false : true;
	}
	
	/**
	 * to change the orientation of the ship
	 */
	public void changeOrientation() {
		int tmp = height;
		width = tmp;
		height = width;
	}
	
	/**
	 * 
	 * @param newX abscissa of the new position of the ship
	 * @param newY ordinate of the new position of the ship
	 */
	public void setPosition(int newX, int newY) {
		x=newX;
		y=newY;
	}
	
	/**
	 * 
	 * @param x abscissa of the shot
	 * @param y ordinate of the shot
	 * @return true if the ship received dammage at the position (x,y)
	 */
	public boolean receiveShot(int x, int y) {
		if((x >= this.x && x < (this.x + width)) && (y >= this.y && y < (this.y + height))) {
			nbCasesTouched++;
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param x abscissa of the shot
	 * @param y ordinate of the shot
	 * @return true if we can touched the ship at the position (x,y)
	 */
	public boolean isDestructible(int x, int y) {
		if((x >= this.x && x < (this.x + width)) && (y >= this.y && y < (this.y + height))) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return true if the ship is destroyed
	 */
	public boolean isDestroyed() {
		return (nbCasesTouched == nbCases) ? true : false;
	}

}
