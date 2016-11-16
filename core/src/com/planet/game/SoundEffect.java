package com.planet.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundEffect {
	private Music bgm;
	Sound shot;
	Sound bomb;
	
	public SoundEffect() {
		bgm = Gdx.audio.newMusic(Gdx.files.internal("bgm.mp3"));
		shot = Gdx.audio.newSound(Gdx.files.internal("bullet.mp3"));
		bomb = Gdx.audio.newSound(Gdx.files.internal("Bomb.mp3"));
	}
	
	public Music getMusic() {
		return bgm;
	}
	
	public Sound getSoundShot() {
		return shot;
	}
	
	public Sound getSoundBomb() {
		return bomb;
	}
}
