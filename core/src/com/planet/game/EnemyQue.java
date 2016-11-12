package com.planet.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.planet.game.Enemy.STATE;

public class EnemyQue {
	public static final int sizeOfQue = 50;
	private Enemy[] enemies = new Enemy[sizeOfQue];
	private int front = 0;
	private int rear = 0;
	private BulletQue bulletQue;
	public static final Texture enemy1Img = new Texture("enemy1.png");
	public static final Texture enemy2Img = new Texture("enemy2.png");
	
	public void registerHitEnemyListener() {
		bulletQue.registerHitEnemyListener(new BulletQue.hitEnemyListener() {
			
			@Override
			public void notifyHitEnemy(int j) {
				enemies[j].getInjured();
				
			}
		});
	}
	
	public EnemyQue (BulletQue bulletQue) {
		this.bulletQue = bulletQue;
		registerHitEnemyListener();
	}
	
	public void insert(Enemy enemy) {
		enemies[rear] = enemy;
		rear++;
		if(rear==sizeOfQue) {
			rear = 0;
		}
		if(front == rear) {
			front++;
		}
		if(front == 50) {
			front = 0;
		}
		enemies[rear] = null;
//	    System.out.println(front+" "+rear);
	}
	
	public void delete() {
		enemies[front] = null;
		front++;
		if(front == 50) {
			front = 0;
		}
	}
	
	public void createEnemy(float x,float y, World world) {
		Enemy enemy1 = new Enemy(x,y, world, enemy1Img);
		Enemy enemy2 = new Enemy(x,y, world, enemy2Img);
		insert(enemy1);
		insert(enemy2);
	}
	
	public int getFront() {
		return front;
	}
	
	public int getRear() {
		return rear;
	}
	
	public Enemy getEnemyAt(int i) {
		return enemies[i];
	}
}
