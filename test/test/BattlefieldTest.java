package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.NotInFieldException;
import model.BattleField;

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
	

}
