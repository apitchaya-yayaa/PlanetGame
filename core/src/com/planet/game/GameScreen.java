package com.planet.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends ScreenAdapter {
	private PlanetGame planetGame;
	private Ship ship;
    World world;
    static public WorldRenderer worldRenderer;
    SoundEffect soundEffect;
	 
    public GameScreen(PlanetGame planetGame) {
        this.planetGame = planetGame;
        init();
        
    }
    
    public void init() {
    	world = new World(planetGame);
        worldRenderer = new WorldRenderer(planetGame,world);
        ship = world.getShip();
        soundEffect = new SoundEffect();
        soundEffect.getMusic().setLooping(true);
        soundEffect.getMusic().setVolume(0.5f);
        soundEffect.getMusic().play();
        world.initVariable(worldRenderer);
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
    	updateAction();
    	worldRenderer.render(delta);
    }
    
    private void updateShipDirection() {
    	Ship ship = world.getShip();
    	if(Gdx.input.isKeyPressed(Keys.UP)) {
    		//ship.setNextDirection(Ship.DIRECTION_UP);
    		ship.move(Ship.DIRECTION_UP);
        }
    	if(Gdx.input.isKeyPressed(Keys.DOWN)) {
    		//ship.setNextDirection(Ship.DIRECTION_DOWN);
    		ship.move(Ship.DIRECTION_DOWN);
        }
    	if(Gdx.input.isKeyPressed(Keys.LEFT)) {
    		//ship.setNextDirection(Ship.DIRECTION_LEFT);
    		ship.move(Ship.DIRECTION_LEFT);
        }
    	if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
    		//ship.setNextDirection(Ship.DIRECTION_RIGHT);
    		ship.move(Ship.DIRECTION_RIGHT);
        }
    }
    
    private void updateAction() {
    	BulletQue bulletQue = world.getBulletQue();
    	BulletEnemyQue bulletEnemyQue = world.getBulletEnemyQue();
    	EnemyQue enemyQue = world.getEnemyQue();
    	Ship ship = world.getShip();
    	if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
    		if(ship.getState() == Ship.STATE.LIVE) {
    			bulletQue.createBullet(ship.getPosition().x + 90,ship.getPosition().y + 35);
    			soundEffect.getSoundShot().play(1.0f);
    		}
    		else {
    			init();
    		}
    	}
    	
    }
//    
//    private WorldRenderer getWorldRenderer() {
//    	return worldRenderer;
//    }
}
