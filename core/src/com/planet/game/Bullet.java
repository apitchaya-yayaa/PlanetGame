package com.planet.game;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.*;;

public class Bullet implements Disposable  {
	public PlanetGame planetGame;
	public static final Texture bulletImg = new Texture("bullet.png");
	private BulletQue bulletQue;
	private EnemyQue enemyQue;
	private Vector2 position;
	private int speed = 10;
	private Texture enemyImg;
	World world;
	private STATE state;
	enum STATE {ON,OFF};
	Rectangle bulletRectangle;
	
//	public interface hitEnemyListener {
//		void notifyHitEnemy(int j);
//	}
//	
//	private LinkedList<hitEnemyListener> hitEnemyListeners;
//	
//	public void registerHitEnemyListener(hitEnemyListener l){
//		hitEnemyListeners.add(l);
//	}
//	
//	private void notifyHitEnemyListener(int j) {
//		for(hitEnemyListener l : hitEnemyListeners) {
//			l.notifyHitEnemy(j);
//		}
//	}
	
	
	public Bullet(float x,float y,World world) {
		position = new Vector2(x,y);
		state = STATE.ON;
		this.world = world;  
		enemyQue = world.getEnemyQue();
		bulletRectangle = new Rectangle();
		bulletRectangle.setSize(bulletImg.getWidth(), bulletImg.getHeight());
//		hitEnemyListeners = new LinkedList<hitEnemyListener>();
	}
	
	public Vector2 getPosition() {
	    return position;    
	 }
	
	
	public void update() {
		move();
		bulletRectangle.setCenter(position.x + bulletImg.getWidth() / 2, position.y + bulletImg.getHeight() / 2);
	}
	
	public void move() {
		position.x += speed;
	}
	
	public STATE getState() {
		return state;
	}
	
	public Rectangle getRect() {
		return bulletRectangle;
	}
	
	
	@Override
	public void dispose () {
		this.dispose();
	}
	
	public void setState(STATE state) {
		this.state = state;
	}
}
