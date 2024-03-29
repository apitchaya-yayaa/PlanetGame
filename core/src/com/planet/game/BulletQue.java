package com.planet.game;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.planet.game.Bullet.STATE;

public class BulletQue {
	public static final int sizeOfQue = 50;
	private Bullet[] bullets = new Bullet[sizeOfQue];
	private int front = 0;
	private int rear = 0;
	World world;
	EnemyQue enemyQue;
	Sound shot = Gdx.audio.newSound(Gdx.files.internal("Bullet.mp3"));
	
	public interface hitEnemyListener {
		void notifyHitEnemy(int j);
	}
	
	private LinkedList<hitEnemyListener> hitEnemyListeners;
	
	public void registerHitEnemyListener(hitEnemyListener l){
		hitEnemyListeners.add(l);
	}
	
	private void notifyHitEnemyListener(int j) {
		for(hitEnemyListener l : hitEnemyListeners) {
			l.notifyHitEnemy(j);
		}
	}
	
	public BulletQue(World world) {
		this.world = world;
		hitEnemyListeners = new LinkedList<hitEnemyListener>();
	}
	
	
	public void initVariable (EnemyQue enemyQue) {
		this.enemyQue = enemyQue;
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
		//shot.play(1.0f);
		
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
	
	public void checkAllBullet() {
		for(int j=getFront();;j++){
        	int k = getRear();
        	if(j==BulletQue.sizeOfQue) {
       			j=-1;
       		}
       		else if(j == k)
       			break;
       		else {
       			checkEnemy(j);
       		}
//        	bulletQue.getBulletAt(j).checkEnemy();
       	}   
	}
	
	public void checkEnemy(int i) {
		Bullet bullet = getBulletAt(i);
		for(int j=enemyQue.getFront();;j++){
        	int k = enemyQue.getRear();
        	if(j==EnemyQue.sizeOfQue) {
        		j=-1;
       		}
       		else if(j == k)
       			break;
       		else {
       			if(bullet.getState() == STATE.ON) {
//       				System.out.println("AA");
       				Texture enemyImg = enemyQue.getEnemyAt(j).getImg();
       				Enemy enemy = enemyQue.getEnemyAt(j);
       				double Width = enemyImg.getWidth();
       				double Height = enemyImg.getHeight();
       				Vector2 bulletPos = bullet.getPosition();
       				if(bulletPos.x >= enemyQue.getEnemyAt(j).getPosition().x && bulletPos.x < enemyQue.getEnemyAt(j).getPosition().x + Width) {
       					if(bulletPos.y >= enemyQue.getEnemyAt(j).getPosition().y && bulletPos.y < enemyQue.getEnemyAt(j).getPosition().y + Height) {
//       						System.out.println("hello");
       							bullet.setState(STATE.OFF);
       							notifyHitEnemyListener(j);
       							if(enemy.getState() == Enemy.STATE.DIE) {
       								bullet.setState(STATE.ON);
       							}
       					}
       				}
       			}
       		}
		}
	}
}
