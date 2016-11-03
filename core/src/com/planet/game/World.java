package com.planet.game;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class World {
	private Ship ship;
    private PlanetGame planetGame;
    private BulletQue bulletQue = new BulletQue();
    int TopBullet = 0;
    private Ground ground1,ground2;
  
    World(PlanetGame planetGame) {
    	ship = new Ship(60,60,this);
        this.planetGame = planetGame;
        ground1 = new Ground(-140,0);
        ground2 = new Ground(610,0);
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
       		else
       			bulletQue.getBulletAt(j).update();
       	}
    }
    
    public BulletQue getBulletQue() {
    	return bulletQue;
    }
}
