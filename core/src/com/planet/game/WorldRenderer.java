package com.planet.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class WorldRenderer {
	public static PlanetGame planetGame;
	private static SpriteBatch batch; 
	private static Texture shipImg;
	private static Texture bulletImg;
	private World world;
	Ship ship;
	Bullet[] bullet;
	int TopBullet;
	
	public WorldRenderer(PlanetGame planetGame, World world) {
        this.planetGame = planetGame;
        batch = planetGame.batch;
        this.world = world;
        shipImg = new Texture("ship.png");
        bulletImg = new Texture("bullet.png");
        ship = world.getShip();
        bullet = world.getBullet();
    }
	
	public void render(float delta) {

        TopBullet = world.getTopBullet();
        //System.out.println(TopBullet);
    	Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        SpriteBatch batch = planetGame.batch;
        Vector2 pos = world.getShip().getPosition();
        batch.begin();
        batch.draw(shipImg,ship.getPosition().x, ship.getPosition().y);
        for(int i=0;i<TopBullet;i++) {
        	batch.draw(bulletImg,bullet[i].getPosition().x,bullet[i].getPosition().y);
        }
        batch.end();       
    }
	
}
