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

	// public interface hitEnemyListener {
	// void notifyHitEnemy(int j);
	// }
	//
	// private LinkedList<hitEnemyListener> hitEnemyListeners;
	//
	// public void registerHitEnemyListener(hitEnemyListener l){
	// hitEnemyListeners.add(l);
	// }
	//
	// private void notifyHitEnemyListener(int j) {
	// for(hitEnemyListener l : hitEnemyListeners) {
	// l.notifyHitEnemy(j);
	// }
	// }
	//
	public BulletEnemyQue(World world) {
		this.world = world;
		// hitEnemyListeners = new LinkedList<hitEnemyListener>();
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
			if (j == BulletQue.sizeOfQue) {
				j = -1;
			} else if (j == k)
				break;
			else {
				// checkEnemy(j);
			}
			// bulletQue.getBulletAt(j).checkEnemy();
		}
	}

	public void updateAllBullet() {
			for(int j=getFront();;j++){
	        	int k = getRear();
	        	if(j==BulletQue.sizeOfQue) {
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

	// public void checkEnemy(int i) {
	// Bullet bullet = getBulletAt(i);
	// for(int j=enemyQue.getFront();;j++){
	// int k = enemyQue.getRear();
	// if(j==EnemyQue.sizeOfQue) {
	// j=-1;
	// }
	// else if(j == k)
	// break;
	// else {
	// if(bullet.getState() == STATE.ON) {
	//// System.out.println("AA");
	// Texture shipImg = Ship.getImg();
	// double Width = enemyImg.getWidth();
	// double Height = enemyImg.getHeight();
	// Vector2 bulletPos = bullet.getPosition();
	// if(bulletPos.x >= enemyQue.getEnemyAt(j).getPosition().x && bulletPos.x <
	// enemyQue.getEnemyAt(j).getPosition().x + Width) {
	// if(bulletPos.y >= enemyQue.getEnemyAt(j).getPosition().y && bulletPos.y <
	// enemyQue.getEnemyAt(j).getPosition().y + Height) {
	//// System.out.println("hello");
	// bullet.setState(STATE.OFF);
	// notifyHitEnemyListener(j);
	// }
	// }
	// }
	// }
	// }
	// }
}
