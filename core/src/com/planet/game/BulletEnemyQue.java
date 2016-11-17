package com.planet.game;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.planet.game.Bullet.STATE;
import com.planet.game.BulletQue.hitEnemyListener;

public class BulletEnemyQue {
	public static final int sizeOfQue = 50;
	private BulletEnemy[] bullets = new BulletEnemy[sizeOfQue];
	private int front = 0;
	private int rear = 0;
	World world;
	Ship ship;
	EnemyQue enemyQue;

	 public interface hitShipListener {
		 void notifyHitShip();
	 }
	
	 private LinkedList<hitShipListener> hitShipListeners;
	
	 public void registerHitShipListener(hitShipListener l){
		 hitShipListeners.add(l);
	 }
	
	 private void notifyHitShipListener() {
		 for(hitShipListener l : hitShipListeners) {
			 l.notifyHitShip();
		 }
	 }
	
	public BulletEnemyQue(World world) {
		this.world = world;
		ship = world.getShip();
		enemyQue =  world.getEnemyQue();
		hitShipListeners = new LinkedList<hitShipListener>();
	}

	public void initVariable(Ship ship) {
		this.ship = ship;
	}

	public void insert(BulletEnemy bulletEnemy) {
		bullets[rear] = bulletEnemy;
		rear++;
		if (rear == sizeOfQue) {
			rear = 0;
		}
		if (front == rear) {
			front++;
		}
		if (front == sizeOfQue) {
			front = 0;
		}
		bullets[rear] = null;
		// System.out.println(front+" "+rear);
	}

	public void delete() {
		bullets[front] = null;
		front++;
		if (front == 50) {
			front = 0;
		}
	}
	
	public void update(){
		
	}

	public void createBullet(float x, float y) {
		BulletEnemy bulletEnemy = new BulletEnemy(x, y, world);
		insert(bulletEnemy);
	}

	public int getFront() {
		return front;
	}

	public int getRear() {
		return rear;
	}

	public BulletEnemy getBulletAt(int i) {
		return bullets[i];
	}

	public void checkAllBullet() {
		for (int j = getFront();; j++) {
			int k = getRear();
			if (j == BulletEnemyQue.sizeOfQue) {
				j = -1;
			} else if (j == k) {
				break;
			}
			else {
				checkShip(j);
			}
		}
	}

	public void updateAllBullet() {
			for(int j=getFront();;j++){
	        	int k = getRear();
	        	if(j==BulletEnemyQue.sizeOfQue) {
	       			j=-1;
	       		}
	       		else if(j == k)
	       			break;
	       		else {
	       			if(bullets[j].getState() == BulletEnemy.STATE.ON) {
	       				bullets[j].move();
	       			}
	       		}
	       	}
}

	private void checkShip(int i) {
		BulletEnemy bulletEnemy = getBulletAt(i);
       	if(bulletEnemy.getState() == BulletEnemy.STATE.ON) {
       		Texture shipImg = ship.getImg();
       		Ship ship = world.getShip();
       		double Width = shipImg.getWidth();
       		double Height = shipImg.getHeight();
       		Vector2 bulletPos = bulletEnemy.getPosition();
       		if(bulletPos.x >= ship.getShipCircle().x - 46 && bulletPos.x < ship.getShipCircle().x - 46 + Width) {
       			if(bulletPos.y >= ship.getShipCircle().y - 40 && bulletPos.y < ship.getShipCircle().y - 40 + Height) {
   	   				notifyHitShipListener();
       				bulletEnemy.setState(BulletEnemy.STATE.OFF);
       				if(ship.getState() == Ship.STATE.DIE) {
       					bulletEnemy.setState(BulletEnemy.STATE.ON);
       				}
       			}
       		}
       	}
   	}
		
	
}
