package com.planet.game;

public class BulletQue {
	public static final int sizeOfQue = 50;
	private Bullet[] bullets = new Bullet[sizeOfQue];
	private int front = 0;
	private int rear = 0;
	World world;
	
	public BulletQue(World world) {
		this.world = world;
	}
	
	public void insert(Bullet bullet) {
		bullets[rear] = bullet;
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
		bullets[rear] = null;
//	    System.out.println(front+" "+rear);
	}
	
	public void delete() {
		bullets[front] = null;
		front++;
		if(front == 50) {
			front = 0;
		}
	}
	
	public void createBullet(float x,float y) {
		Bullet bullet = new Bullet(x,y,world);
		insert(bullet);
	}
	
	public int getFront() {
		return front;
	}
	
	public int getRear() {
		return rear;
	}
	
	public Bullet getBulletAt(int i) {
		return bullets[i];
	}
}
