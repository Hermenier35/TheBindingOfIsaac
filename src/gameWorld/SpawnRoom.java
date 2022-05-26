package gameWorld;

import gameobjects.Hero;
import libraries.Vector2;

public class SpawnRoom extends Room {

	public SpawnRoom(Hero hero) {
		super(hero);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean collision(Vector2 direction, Vector2 position, Vector2 size, double speed) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void drawGameObjects() {
		// TODO Auto-generated method stub
		
	}
	
}
