package com.planet.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer.Random;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
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
    private BulletEnemyQue bulletEnemyQue;
    private int min = 300;
	private int max = 580;
	private int score;
    Texture enemy1Img = new Texture("enemy1.png");
    Texture enemy2Img = new Texture("enemy2.png");
    Texture enemy3Img = new Texture("enemy3.png");
    int n = (int)(Math.random()*(max-min))+150;
    int m = (int)(Math.random()*(max-min))+150;
    int k = (int)(Math.random()*(max-min))+150;
    private OrthographicCamera camera;
    double lastSpawn = -1; 
    WorldRenderer worldRenderer;
    
    World(PlanetGame planetGame) {
    	ship = new Ship(60,60,this);
        this.planetGame = planetGame;
        ground1 = new Ground(-140,0);
        ground2 = new Ground(610,0);
        bulletQue = new BulletQue(this);
        enemyQue = new EnemyQue(bulletQue);
        bulletQue.initVariable(enemyQue);
        bulletEnemyQue = new BulletEnemyQue(this);
        worldRenderer = GameScreen.worldRenderer;
        score = 0;
        
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
    
    public int getScore() {
    	return score;
    }
    
    public void initVariable(WorldRenderer worldRenderer) {
    	ship.initVariable();
    	this.worldRenderer = worldRenderer;
    	camera = worldRenderer.getCameraPosition();
    }
    
    public EnemyQue getEnemyQue() {
    	return enemyQue;
    }
    
    public int getTopBullet() {
    	return TopBullet;
    }
    
    public void update(float delta) {
        ship.update();
        spawnEnemy();
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
       	}   
        bulletQue.checkAllBullet();
        for(int j=enemyQue.getFront();;j++){
        	int k = enemyQue.getRear();
        	if(j==EnemyQue.sizeOfQue) {
        		j=-1;
       		}
       		else if(j == k)
       			break;
       		else {
       			Enemy enemy = enemyQue.getEnemyAt(j);
       			if(enemy.getState() == Enemy.STATE.LIVE) {
           			enemy.update();
       			}
       			else {
       				if(enemy.checkScore == 1) {
       		        	addScore();
       		        }
       			}
       		}
        }
        bulletEnemyQue.checkAllBullet();
        bulletEnemyQue.updateAllBullet();
        checkEnemyYPosition();
    }
    
    public void checkEnemyYPosition() {
    	 for(int i=enemyQue.getFront();;i++){
         	int k = enemyQue.getRear();
         	if(i==EnemyQue.sizeOfQue) {
         		i=-1;
        		}
        		else if(i == k)
        			break;
        		else {
        			if(ship.getPosition().y > enemyQue.getEnemyAt(i).getPosition().y) {
        				if(enemyQue.getEnemyAt(i).checkEnemyCanMove(enemyQue, true)) {
        					enemyQue.getEnemyAt(i).getPosition().y += 1;
        				}
        			}
        			else if(ship.getPosition().y < enemyQue.getEnemyAt(i).getPosition().y) {
        				if(enemyQue.getEnemyAt(i).checkEnemyCanMove(enemyQue, false)) {
        					enemyQue.getEnemyAt(i).getPosition().y -= 1;
        				}
        			}
        		}
         }
    }
    
    public void spawnEnemy() {
    	int prob = (int)(Math.random() * 1000)-1;
    	int n = (int)(Math.random()*(max-min))+150;
    	if(lastSpawn == -1 || System.currentTimeMillis() - lastSpawn >= 1200) {
    		if(prob < 5) {
    			int nbImg = ((int)(Math.random() * 10) - 1) % 3;
    			Texture willBeUsed;
    			if(nbImg == 0) {
    				willBeUsed = enemy1Img;
    			} else if(nbImg == 1) {
    				willBeUsed = enemy2Img;
    			} else {
    				willBeUsed = enemy3Img;
    			}
    			enemyQue.createEnemy(camera.position.x + 400 ,n, this,willBeUsed);
    			lastSpawn = System.currentTimeMillis();
    		}
    	}
    }
    
    public void addScore() {
    	for(int j=enemyQue.getFront();;j++){
        	int k = enemyQue.getRear();
        	if(j==EnemyQue.sizeOfQue) {
        		j=-1;
       		}
       		else if(j == k)
       			break;
       		else {
       			Enemy enemy = enemyQue.getEnemyAt(j);
       			if(enemy.getState() == Enemy.STATE.DIE && enemy.checkScore == 1) {
           			score += 246;
           			enemy.checkScore = 0;
       			}
       		}
        }
    }
    
    public BulletQue getBulletQue() {
    	return bulletQue;
    }
    
    public BulletEnemyQue getBulletEnemyQue() {
    	return bulletEnemyQue;
    }
    
    public static int randInt(int min, int max) { 
        int randomNum = (int) Math.round((Math.random() * (max - min)) + min);
        return randomNum;
    }
    
    
}
