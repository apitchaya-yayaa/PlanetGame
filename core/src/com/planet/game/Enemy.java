package com.planet.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Enemy {
	public PlanetGame planetGame;
	public Texture enemyImg;
	private Enemy enemy1;
	private Vector2 position;
	private int speed = 3;
	private int health = 4;
	private BulletQue bulletQue;
	Rectangle enemyRectangle;
	STATE state;
	double lastTimeShoot;
	Sound bomb = Gdx.audio.newSound(Gdx.files.internal("Bomb.mp3"));
//	private int min = 300;
//	private int max = 580;
//	private int chance = 3;
//	private int randomy;
	
	enum STATE {LIVE, DIE};
	
	public Enemy(float x, float y,World world,Texture Img) {
		position = new Vector2(x,y);
		bulletQue = world.getBulletQue();
		enemyImg = Img; 
		enemyRectangle = new Rectangle();
		enemyRectangle.setSize(enemyImg.getWidth(), enemyImg.getHeight());
		this.state = STATE.LIVE;
		this.lastTimeShoot = -1;
		
	}    
//	public void EnemyArray() {
//		enemyRectangle.x = MathUtils.random(0, 800-64);
//	    enemyRectangle.y = 480;
//	    enemyRectangle.width = enemyImg.getWidth();
//	    enemyRectangle.height = enemyImg.getHeight();
//	}
	
	public void update() {
		if(health <= 0) {
			this.state = STATE.DIE;
			bomb.play(1.0f);
		}
	}
	
	public void move() {
		position.x -= speed;
	}
	
	public Vector2 getPosition() {
		return position;  
	}
	
	public void setPositionX(float x) {
		position.x = x;
	}
	
	public Rectangle getRectangle() {
		return enemyRectangle;
	}
	
	public Texture getImg() {
		return enemyImg;
	}
	
	public void setRectCenter(float x, float y) {
		enemyRectangle.setCenter(x, y);
	}
	
	public void setState(STATE state) {
		this.state = state;
	}
	
	public STATE getState() {
		return state;
	}
	
	public void getInjured() {
		health--;
	}
	
	public double getLastTimeShoot() {
		return lastTimeShoot;
	}
	
	public void setLastTimeShoot(double time) {
		this.lastTimeShoot = time;
	}
	
	public int getHealth() {
		return health;
	}
}
