package com.planet.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Enemy {
	public PlanetGame planetGame;
	public Texture enemy1Img;
	private Enemy enemy1;
	private Vector2 position;
	private int speed = 5;
	
	public Enemy(int x, int y) {
		position = new Vector2(x,y);
	}    
	
	public void move() {
		position.x -= speed;
	}
}
