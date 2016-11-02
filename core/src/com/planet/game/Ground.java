package com.planet.game;

import com.badlogic.gdx.math.Vector2;

public class Ground {
	private Vector2 position;
	
	public Ground(int x,int y) {
		position = new Vector2(x,y);
	}
	
	public Vector2 getPosition() {
		return position;
	}
}
