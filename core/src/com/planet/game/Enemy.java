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
	Rectangle enemy;
	Array<Rectangle> enemyArray;
	
	public Enemy(float x, float y,World world,Texture Img) {
		position = new Vector2(x,y);
		bulletQue = world.getBulletQue();
		enemyImg = Img; 
		enemy = new Rectangle();
		enemyArray = new Array<Rectangle>();
		
	}    
//	public void EnemyArray() {
//		enemy.x = MathUtils.random(0, 800-64);
//	    enemy.y = 480;
//	    enemy.width = enemyImg.getWidth();
//	    enemy.height = enemyImg.getHeight();
//	    enemyArray.add(enemy);
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
	
	public Texture getImg() {
		return enemyImg;
	}
}
