package com.planet.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
	public PlanetGame planetGame;
	public Texture bulletImg;
	private Bullet bullet;
	private Vector2 position;
	private int speed = 10;
	public Bullet(float x,float y) {
		position = new Vector2(x,y);
	}
	
	public Vector2 getPosition() {
	    return position;    
	 }
	
	public void update() {
		move();
	}
	
	public void move() {
		position.x += speed;
	}
}
