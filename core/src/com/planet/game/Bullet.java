package com.planet.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
	public PlanetGame planetGame;
	public Texture bulletImg;
	private BulletQue bulletQue;
	private EnemyQue enemyQue;
	private Vector2 position;
	private int speed = 10;
	
	private STATE state;
	enum STATE {ON,OFF};
	
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
	
	public void checkEnemy() {
		for(int j=enemyQue.getFront();;j++){
        	int k = enemyQue.getRear();
        	if(j==EnemyQue.sizeOfQue) {
        		j=-1;
       		}
       		else if(j == k)
       			break;
       		else {
       			if(state == STATE.ON) {
       				if(position.x >= enemyQue.getEnemyAt(j).getPosition().x && position.x < enemyQue.getEnemyAt(j).getPosition().x) {
       					
       				}
       			}
       		}
		}	
	}
}
