package com.planet.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {
	private PlanetGame planetGame;
	private Texture shipImg;
	private Ship ship;
    World world;
    WorldRenderer worldRenderer;
	 
    public GameScreen(PlanetGame planetGame) {
        this.planetGame = planetGame;
        shipImg = new Texture("astronaut.png");
        world = new World(planetGame);
        worldRenderer = new WorldRenderer(planetGame,world);
        ship = world.getShip();

    }
    
    private void update(float delta) {
    	updateShipDirection();
    	world.update(delta);
    	ship.setNextDirection(ship.DIRECTION_STILL);
    }
    
    public void render(float delta) {
    	Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	update(delta);
    	SpriteBatch batch = planetGame.batch;
        batch.begin();
        batch.draw(shipImg, ship.getPosition().x, ship.getPosition().y);
        batch.end();
    }
    
    private void updateShipDirection() {
    	Ship ship = world.getShip();
    	if(Gdx.input.isKeyPressed(Keys.UP)) {
    		ship.setNextDirection(Ship.DIRECTION_UP);
        }
    	if(Gdx.input.isKeyPressed(Keys.DOWN)) {
    		ship.setNextDirection(Ship.DIRECTION_DOWN);
        }
    	if(Gdx.input.isKeyPressed(Keys.LEFT)) {
    		ship.setNextDirection(Ship.DIRECTION_LEFT);
        }
    	if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
    		ship.setNextDirection(Ship.DIRECTION_RIGHT);
        }
    }
}
