package com.planet.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.planet.game.Enemy.STATE;

public class Ship {
	public PlanetGame planetGame;
	public Texture shipImg;
	private Ship ship;
	private Vector2 position;
	public int x;
	public int y;
	public static final int DIRECTION_UP = 1;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_DOWN = 3;
    public static final int DIRECTION_LEFT = 4;
    public static final int DIRECTION_STILL = 0;
    public static final int SPEED = 4;
    private int currentDirection;
    private int nextDirection;
    private World world;
    private Circle shipCircle;
    private int health = 5;
    STATE state;
    enum STATE {LIVE,DIE};
    BulletEnemyQue bulletEnemyQue;
    
	public Ship(int x, int y, World world) {
		position = new Vector2(x,y);
		shipImg = new Texture("ship.png");
		currentDirection = DIRECTION_STILL;
	    nextDirection = DIRECTION_STILL;
	    this.world = world;
	    shipCircle = new Circle();
	    this.state = STATE.LIVE;
	}    
	
	public void initVariable() {
	    bulletEnemyQue = world.getBulletEnemyQue();
	    registerHitShipListener();
	}
	
	 private static final int [][] DIR_DIFF = new int [][] {
	        {0,0},
	        {0,1},
	        {1,0},
	        {0,-1},
	        {-1,0}
	 };
	    
	 public void setNextDirection(int dir) {
		nextDirection = dir;		
	 }
	 
	 public int getNextDirection() {
		 return nextDirection;
	 }
	 
	 public void move(int dir) { 
	        position.x += SPEED * DIR_DIFF[dir][0];
	        position.y += SPEED * DIR_DIFF[dir][1];
	  }
	 
	 public Vector2 getPosition() {
		    return position;    
		 }
	 
	 public Texture getImg() {
			return shipImg;
	}
	 
	 public STATE getState() {
		 return state;
	 }
	 
	 public void registerHitShipListener() {
		 bulletEnemyQue.registerHitShipListener(new BulletEnemyQue.hitShipListener() {
			
			@Override
			public void notifyHitShip() {
				health--;

			}
		});
	 }
	 
	 public int getHealth() {
		 return health;
	 }
		
	public void setState(STATE state) {
		this.state = state;
	}
	 
	 public void update() {
		 shipCircle.set(position.x + shipImg.getWidth() / 2, position.y + shipImg.getHeight() / 2, shipImg.getWidth() / 2);
		 if(health <= 0) {
			 setState(STATE.DIE);
		 }
	 }
	 
	 public Circle getShipCircle() {
		 return shipCircle;
	 }
}
