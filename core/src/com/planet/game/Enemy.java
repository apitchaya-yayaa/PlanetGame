package com.planet.game;

import com.badlogic.gdx.Gdx;
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
	private int health = 2;
	private BulletQue bulletQue;
	Rectangle enemyRectangle;
	
	public Enemy(float x, float y,World world,Texture Img) {
		position = new Vector2(x,y);
		bulletQue = world.getBulletQue();
		enemyImg = Img; 
		enemyRectangle = new Rectangle();
		enemyRectangle.setSize(enemyImg.getWidth(), enemyImg.getHeight());
		
	}    
//	public void EnemyArray() {
//		enemyRectangle.x = MathUtils.random(0, 800-64);
//	    enemyRectangle.y = 480;
//	    enemyRectangle.width = enemyImg.getWidth();
//	    enemyRectangle.height = enemyImg.getHeight();
//	}
	
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
}
