package com.planet.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShipTest {
	PlanetGame planetGame = new PlanetGame();
	World world = new World(planetGame);
	Ship ship = new Ship(2,4,world);
	
	@Test
	public void checkNextDirection() {
		int dir = 0;
		ship.setNextDirection(dir);
		assertEquals(dir,ship.getNextDirection());
	}

}
