package com.planet.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends ScreenAdapter {
	private PlanetGame planetGame;
	private Ship ship;
    World world;
    WorldRenderer worldRenderer;
    
	 
    public GameScreen(PlanetGame planetGame) {
        this.planetGame = planetGame;
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
    	updateAction();
//    	world.update(delta);
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
    		bulletQue.createBullet(ship.getPosition().x + 90,ship.getPosition().y + 35);
    	}
    	for(int j=enemyQue.getFront();;j++){
        	int k = enemyQue.getRear();
        	if(j==EnemyQue.sizeOfQue) {
        		j=-1;
       		}
       		else if(j == k)
       			break;
       		else {
       			Enemy enemy = enemyQue.getEnemyAt(j);
       			if(enemy.getLastTimeShoot() == -1 || System.currentTimeMillis() - enemy.getLastTimeShoot() >= 1000) {
           			bulletEnemyQue.createBullet(enemy.getPosition().x - 10 ,enemy.getPosition().y + 30);
           			enemy.setLastTimeShoot(System.currentTimeMillis());
       			}
       		}
       	}
    	
    }
}
