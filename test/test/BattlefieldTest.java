package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.NotInFieldException;
import model.BattleField;
import model.ship.modern.Cruiser;
import model.ship.modern.Gondola;

/**
 * Test class for BattleField
 * @author victor
 *
 */
class BattlefieldTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	
	/////////////////////////////////////////////////////////////
	@Test
	void testReceiveShotEmpty() throws NotInFieldException {
		BattleField b = new BattleField(10);
		assertFalse(b.receiveShot(1, 1));
	}
	
	@Test
	void testReceiveShotOOB() throws NotInFieldException {
		BattleField b = new BattleField(10);
		assertThrows(NotInFieldException.class, ()-> b.receiveShot(-1, 0));
	}
	

	@Test
	void testReceiveShotRight() throws NotInFieldException {
		BattleField b = new BattleField(10);
		Cruiser c = new Cruiser();
		c.setPosition(0, 0);
		b.placeShip(c);
		
		assertTrue(b.receiveShot(0, 0));
	}
	
	
	
	/////////////////////////////////////////////////////////////
	
	@Test
	void testPlaceShipRight() throws NotInFieldException {
		BattleField b = new BattleField(10);
		Cruiser c = new Cruiser();
		c.setPosition(0, 0);
		b.placeShip(c);
		
		assertNotNull(b.getShip(0, 0)); 
	}
	
	@Test
	void testPlaceShipOOB() throws NotInFieldException {
		BattleField b = new BattleField(10);
		Cruiser c = new Cruiser();
		c.setPosition(10, 0);
		assertThrows(NotInFieldException.class, ()->b.placeShip(c));
	}
	
	@Test
	void testPlaceShipFewOOB() throws NotInFieldException {
		BattleField b = new BattleField(10);
		Cruiser c = new Cruiser();
		c.setPosition(0, 3);
		assertThrows(NotInFieldException.class, ()->b.placeShip(c));
	}
	
	@Test
	void testPlaceShipAlmostOOB() throws NotInFieldException {
		BattleField b = new BattleField(10);
		Cruiser c = new Cruiser();
		c.setPosition(0, 2);
		assertTrue(b.placeShip(c));
	}
	
	
	/////////////////////////////////////////////////////////////
	
	@Test
	void testWonNoShip() {
		BattleField b = new BattleField(10);
		assertTrue(b.won());
	}
	
	@Test
	void testWonRight() throws NotInFieldException {
		BattleField b = new BattleField(10);
		b.placeShip(new Gondola());
		b.receiveShot(0, 0);
		b.receiveShot(0, 1);
		
		assertTrue(b.won());
	}
	
	@Test
	void testNotWon() throws NotInFieldException {
		BattleField b = new BattleField(10);
		b.placeShip(new Cruiser());
		
		assertFalse(b.won());
	}
	
	
	/////////////////////////////////////////////////////////////
	
	
	@Test
	void testTouchedRight() throws NotInFieldException {
		BattleField b = new BattleField(10);
		b.receiveShot(0, 0);
		
		assertTrue(b.touched(0, 0));
	}
	
	@Test
	void testNotTouched() throws NotInFieldException {
		BattleField b = new BattleField(10);
		b.receiveShot(0, 0);
		
		assertFalse(b.touched(1, 0));
	}
	
	@Test
	void testTouchedOOB() throws NotInFieldException {
		BattleField b = new BattleField(10);
		assertThrows(NotInFieldException.class, ()->b.receiveShot(-1, 0));
	}
	
}
