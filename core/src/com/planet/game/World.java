package com.planet.game;

public class World {
	private Ship ship;
    private PlanetGame planetGame;
    
    World(PlanetGame planetGame) {
    	ship = new Ship(60,60,this);
        this.planetGame = planetGame;
        this.planetGame = planetGame;
    }
    
    public Ship getShip() {
        return ship;
    }
    
    public void update(float delta) {
        ship.update();
    }
}
