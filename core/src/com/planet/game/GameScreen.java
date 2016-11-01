package com.planet.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {
	private PlanetGame planetGame;
	private Texture shipImg;
	 
    public GameScreen(PlanetGame planetGame) {
        this.planetGame = planetGame;
        shipImg = new Texture("astronaut.png");
    }
    
    public void render(float delta) {
    	SpriteBatch batch = planetGame.batch;
        batch.begin();
        batch.draw(shipImg, 100, 100);
        batch.end();
    }
}
