package com.planet.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.*;;

public class Bullet implements Disposable  {
	public PlanetGame planetGame;
	public Texture bulletImg;
	private BulletQue bulletQue;
	private EnemyQue enemyQue;
	private Vector2 position;
	private int speed = 10;
	private Texture enemyImg;
	World world;
	private STATE state;
	enum STATE {ON,OFF};
	//Rectangle bulletRectangle;
	
	
	public Bullet(float x,float y,World world) {
		position = new Vector2(x,y);
		state = STATE.ON;
		this.world = world;  
		enemyQue = world.getEnemyQue();
//		bulletRectangle = new Rectangle();
//		bulletRectangle.setSize(bulletImg.getWidth(), bulletImg.getHeight());
	}
	
	public Vector2 getPosition() {
	    return position;    
	 }
	
	public void update() {
		move();
//		checkEnemy();
		
	}
	
	public void move() {
		position.x += speed;
	}
	
	public STATE getState() {
		return state;
	}
	
//	public Rectangle getRect() {
//		return bulletRectangle;
//	}
	
//	public void checkEnemy() {
//		for(int j=enemyQue.getFront();;j++){
//        	int k = enemyQue.getRear();
//        	if(j==EnemyQue.sizeOfQue) {
//        		j=-1;
//       		}
//       		else if(j == k)
//       			break;
//       		else {
//       			if(state == STATE.ON) {
////       				System.out.println("AA");
//       				enemyImg = enemyQue.getEnemyAt(j).getImg();
//       				double Width = enemyImg.getWidth();
//       				double Height = enemyImg.getHeight();		
//       				if(position.x >= enemyQue.getEnemyAt(j).getPosition().x && position.x < enemyQue.getEnemyAt(j).getPosition().x + Width) {
//       					if(position.y >= enemyQue.getEnemyAt(j).getPosition().y && position.y < enemyQue.getEnemyAt(j).getPosition().y + Height) {
////       						System.out.println("hello");
//       							state = STATE.OFF;
//       					}
//       				}
//       			}
//       		}
//		}	
//	}
	
	@Override
	public void dispose () {
		this.dispose();
	}
}
