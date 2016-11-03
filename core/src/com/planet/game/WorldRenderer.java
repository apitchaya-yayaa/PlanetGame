package com.planet.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

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
	static int cameraspeed = 2;
	
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
        enemy1Img = new Texture("enemy1.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,600);
        defaultground = world.getGround1();
    }
	
	public void render(float delta) {
		//camera.position.x = ship.getPosition().x + 200;
		camera.position.x += cameraspeed;
		camera.update();
		checkGround();
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
        batch.draw(enemy1Img,camera.position.x,camera.position.y);
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

	
}
