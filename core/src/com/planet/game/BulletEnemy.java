package com.planet.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.planet.game.Bullet.STATE;

public class BulletEnemy {
	public PlanetGame planetGame;
	public static final Texture bulletEnemyImg = new Texture("bulletenemy.png");
	private Vector2 position;
	private EnemyQue enemyQue;
	private int speed = 5;
	World world;
	private STATE state;
	enum STATE {ON,OFF};
	Rectangle bulletEnemyRectangle;
	
	public BulletEnemy (float x,float y,World world) {
		position = new Vector2(x,y);
		state = STATE.ON;
		this.world = world;  
		enemyQue = world.getEnemyQue();
		bulletEnemyRectangle = new Rectangle();
		bulletEnemyRectangle.setSize(bulletEnemyImg.getWidth(), bulletEnemyImg.getHeight());
	}
	
	public Vector2 getPosition() {
	    return position;    
	 }
	
	
	public void update() {
		move();
		bulletEnemyRectangle.setCenter(position.x + bulletEnemyImg.getWidth() / 2, position.y + bulletEnemyImg.getHeight() / 2);
	}
	
	public void move() {
		position.x -= speed;
	}
	
	public STATE getState() {
		return state;
	}
	
	public Rectangle getRect() {
		return bulletEnemyRectangle;
	}
	
	public void setState(STATE state) {
		this.state = state;
	}
}

