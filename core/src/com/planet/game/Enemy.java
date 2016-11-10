package com.planet.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Enemy {
	public PlanetGame planetGame;
	public Texture enemy1Img;
	private Enemy enemy1;
	private Vector2 position;
	private int speed = 2;
	private int health = 2;
	private BulletQue bulletQue;
	
	public Enemy(float x, float y,World world) {
		position = new Vector2(x,y);
		bulletQue = world.getBulletQue();
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
}
