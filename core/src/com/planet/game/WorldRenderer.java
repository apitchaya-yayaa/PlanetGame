package com.planet.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.planet.game.Bullet.STATE;
import java.util.Iterator;

public class WorldRenderer {
	public static PlanetGame planetGame;
	private static SpriteBatch batch; 
	private static Texture shipImg;
	private static Texture bulletImg;
	private Texture bgImg;
	private Texture groundImg;
	private Texture enemy1Img;
	private World world;
	Ship ship;
	private BulletQue bulletQue;
	int TopBullet;
	private Ground ground1,ground2;
	private OrthographicCamera camera;
	private Ground defaultground;
	static int cameraspeed = 1;
//	Enemy enemy1;
	private static int enemyGab = 150;
	private EnemyQue enemyQue;
	private STATE stateBullet;
	private Bullet bullet;
	Rectangle bullets;
	Array<Rectangle> bulletArray;
	Rectangle enemy;
	ShapeRenderer shapeRenderer;
	
	public WorldRenderer(PlanetGame planetGame, World world) {
        this.planetGame = planetGame;
        batch = planetGame.batch;
        this.world = world;
        shipImg = new Texture("ship.png");
        bulletImg = new Texture("bullet.png");
        ship = world.getShip();
        bulletQue = world.getBulletQue();
        bgImg = new Texture("bgstop.png");
        ground1 = world.getGround1();
        ground2 = world.getGround2();
        groundImg = new Texture("groundmove.png");
//        enemy1Img = new Texture("enemy1.png");
//        enemy1 = world.getEnemy();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,600);
        defaultground = world.getGround1();
        enemyQue = world.getEnemyQue();
        bulletArray = new Array<Rectangle>();
        bullets = new Rectangle();
        enemy = new Rectangle();
//        stateBullet = Bullet.getState(); 
        shapeRenderer = new ShapeRenderer();
        
    }
	
	public void render(float delta) {
		//camera.position.x = ship.getPosition().x + 200;
		camera.position.x += cameraspeed;
		camera.update();
		checkGround();
		checkEnemy();
        TopBullet = world.getTopBullet();
        //System.out.println(TopBullet);
    	Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        SpriteBatch batch = planetGame.batch;
        Vector2 pos = world.getShip().getPosition();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(bgImg,camera.position.x - 400,camera.position.y - 300);
        batch.draw(groundImg,ground2.getPosition().x ,ground2.getPosition().y);
        batch.draw(groundImg,ground1.getPosition().x , ground1.getPosition().y);
//        batch.draw(enemy1Img,enemy1.getPosition().x,enemy1.getPosition().y);
        batch.draw(shipImg,ship.getPosition().x, ship.getPosition().y);
        for(int j=bulletQue.getFront();;j++){
        	int k = bulletQue.getRear();
        	if(j==BulletQue.sizeOfQue) {
        		j=-1;
       		}
       		else if(j == k)
       			break;
       		else
       			batch.draw(bulletImg,bulletQue.getBulletAt(j).getPosition().x,bulletQue.getBulletAt(j).getPosition().y);
       	}
        batch.end();  
        drawEnemyInQue(batch);
        shapeRectangleEnemy();
//        shapeRectangleBullet();
        //update();
    }
	
	private void update() {
		Iterator <Rectangle> iter_bullet = bulletArray.iterator();
//		while(iter_bullet.hasNext()) {
//		    Rectangle bullets = iter_bullet.next();
//		    bullets.y -= 200 * Gdx.graphics.getDeltaTime();
//		    if(bullets.y + 64 < 0) iter_bullet.remove();
//		}
//		batch.begin();
//		for(Rectangle bullets: bulletArray) {
//		    batch.draw(bulletImg, bullets.x, bullets.y);
//		}
//		batch.end();
		if(bullets.overlaps(enemy)) {
		    iter_bullet.remove();
		}
	}
	
	private void drawEnemyInQue(SpriteBatch batch) {
		batch.begin();
		for(int j=enemyQue.getFront();;j++){
        	int k = enemyQue.getRear();
        	if(j==EnemyQue.sizeOfQue) {
        		j=-1;
       		}
       		else if(j == k)
       			break;
       		else {
       			Texture IMG = enemyQue.getEnemyAt(j).getImg();
       			batch.draw(IMG,enemyQue.getEnemyAt(j).getPosition().x,enemyQue.getEnemyAt(j).getPosition().y);
       			Vector2 enemyPos = enemyQue.getEnemyAt(j).getPosition();
   				enemyQue.getEnemyAt(j).setRectCenter(enemyPos.x + IMG.getWidth() / 2, enemyPos.y + IMG.getHeight() / 2);
       		}
       	}
		batch.end();
	}
	
	public OrthographicCamera getCamera() {
		return camera;
	}
	
	public void checkGround() {
		if(camera.position.x - defaultground.getPosition().x != 400) {
			//System.out.println("Hello");
			if(camera.position.x - defaultground.getPosition().x > 400) {
				float x = defaultground.getPosition().x + groundImg.getWidth();
				if(defaultground==ground1) {
					ground2.setPosition(x,0);
				}
				else {
					ground1.setPosition(x,0);
				}
			}
			else {
				float x = defaultground.getPosition().x - groundImg.getWidth();
				if(defaultground==ground1) {
					ground2.setPosition(x,0);
				}
				else {
					ground1.setPosition(x,0);
				}
			}
		}
		if(camera.position.x - ground1.getPosition().x == 400 ) {
			defaultground = ground1;
		}
		else if(camera.position.x - ground2.getPosition().x == 400) {
			defaultground = ground2;
		}
	}
	
	public void checkEnemy() {
//		if(enemy1.getPosition().x - camera.position.x <= enemyGab) {
//			enemy1.setPositionX(camera.position.x+enemyGab);
//		}
		for(int j=enemyQue.getFront();;j++){
        	int k = enemyQue.getRear();
        	if(j==EnemyQue.sizeOfQue) {
        		j=-1;
       		}
       		else if(j == k) {
       			break;
       		}
       		else {
       			if(enemyQue.getEnemyAt(j).getPosition().x - camera.position.x <= enemyGab) {
       				enemyQue.getEnemyAt(j).setPositionX(camera.position.x+enemyGab);
       			}
       		}
		}
	}
	
	public void shapeRectangleEnemy() {
		shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        for(int j=enemyQue.getFront();;j++){
        	int k = enemyQue.getRear();
        	if(j==EnemyQue.sizeOfQue) {
        		j=-1;
       		}
       		else if(j == k)
       			break;
       		else {
       			Texture IMG = enemyQue.getEnemyAt(j).getImg();
       			Rectangle enemyRect = enemyQue.getEnemyAt(j).getRectangle();
       			shapeRenderer.rect(enemyRect.x, enemyRect.y, enemyRect.getWidth(), enemyRect.getHeight());
       		}
       	}
        shapeRenderer.end();
	}
	
//	public void shapeRectangleBullet() {
//		shapeRenderer.setProjectionMatrix(camera.combined);
//        shapeRenderer.begin(ShapeType.Filled);
//        shapeRenderer.setColor(Color.RED);
//        for(int j=bulletQue.getFront();;j++){
//        	int k = bulletQue.getRear();
//        	if(j==EnemyQue.sizeOfQue) {
//        		j=-1;
//       		}
//       		else if(j == k)
//       			break;
//       		else {
//       			Rectangle bulletRect = bulletQue.getBulletAt(j).getRect();  			
//       			shapeRenderer.rect(bulletRect.x, bulletRect.y, bulletRect.getWidth(), bulletRect.getHeight());
//       		}
//       	}
//        shapeRenderer.end();
//	}
	
}
