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
	private World world;
	
	public WorldRenderer(PlanetGame planetGame, World world) {
        this.planetGame = planetGame;
        batch = planetGame.batch;
        this.world = world;
        shipImg = new Texture("astronaut.png");
    }
	
	public void render(float delta) {
    	Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        SpriteBatch batch = planetGame.batch;
        Vector2 pos = world.getShip().getPosition();
        batch.begin();
        batch.draw(shipImg, pos.x ,pos.y);
        batch.end();
        
    }
}
