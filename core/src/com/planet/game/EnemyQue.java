package com.planet.game;

public class EnemyQue {
	public static final int sizeOfQue = 50;
	private Enemy[] enemies = new Enemy[sizeOfQue];
	private int front = 0;
	private int rear = 0;
	
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
		Enemy enemy = new Enemy(x,y, world);
		insert(enemy);
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
