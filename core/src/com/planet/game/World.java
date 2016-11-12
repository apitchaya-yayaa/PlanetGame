package com.planet.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.planet.game.Bullet.STATE;

public class World {
	private Ship ship;
    private PlanetGame planetGame;
    private BulletQue bulletQue;
    int TopBullet = 0;
    private Ground ground1,ground2;
//    private Enemy enemy1;
    private EnemyQue enemyQue;
    private STATE stateBullet;
  
    World(PlanetGame planetGame) {
    	ship = new Ship(60,60,this);
        this.planetGame = planetGame;
        ground1 = new Ground(-140,0);
        ground2 = new Ground(610,0);
//        enemy1 = new Enemy(800,400,this);
        bulletQue = new BulletQue(this);
        enemyQue = new EnemyQue(bulletQue);
        enemyQue.createEnemy(800, 400, this);
        
    }
    
    public Ground getGround1() {
    	return ground1;
    }
    
    public Ground getGround2() {
    	return ground2;
    }
    
    public Ship getShip() {
        return ship;
    }
    
//    public Enemy getEnemy() {
//    	return enemy1;
//    }
    
    public EnemyQue getEnemyQue() {
    	return enemyQue;
    }
    
    public int getTopBullet() {
    	return TopBullet;
    }
    
    public void update(float delta) {
        ship.update();
        for(int j=bulletQue.getFront();;j++){
        	int k = bulletQue.getRear();
        	if(j==BulletQue.sizeOfQue) {
       			j=-1;
       		}
       		else if(j == k)
       			break;
       		else {
       			bulletQue.getBulletAt(j).update();
       		}
        	bulletQue.getBulletAt(j).checkEnemy();
       	}   
    }
    
    public BulletQue getBulletQue() {
    	return bulletQue;
    }
}
