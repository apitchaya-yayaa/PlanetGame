package com.planet.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.planet.game.Bullet.STATE;

public class BulletEnemy {
	public PlanetGame planetGame;
	public static final Texture bulletImg = new Texture("bulletenemy.png");
	private Vector2 position;
	private int speed = 10;
	private Texture enemyImg;
	World world;
	private STATE state;
	enum STATE {ON,OFF};
}
