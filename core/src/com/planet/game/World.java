package com.planet.game;

public class World {
	private Ship ship;
    private PlanetGame planetGame;
    private Bullet[] bullet = new Bullet[500];
    int TopBullet = 0;
    
    World(PlanetGame planetGame) {
    	ship = new Ship(60,60,this);
        this.planetGame = planetGame;
        this.planetGame = planetGame;
    }
    
    public Ship getShip() {
        return ship;
    }
    
    public Bullet[] getBullet() {
    	return bullet;
    }
    
    public int getTopBullet() {
    	return TopBullet;
    }
    
    public void createBullet() {
    	bullet[TopBullet++] = new Bullet(ship.getPosition().x + 95,ship.getPosition().y + 35);
    	if(TopBullet == bullet.length)
    		TopBullet = 0;
        //System.out.println(TopBullet);

    }
    
    public void update(float delta) {
        ship.update();
        for(int i = 0;i<TopBullet;i++){
        	bullet[i].update();
        }
    }
}
