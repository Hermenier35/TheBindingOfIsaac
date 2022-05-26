package gameobjects;

import libraries.Vector2;

public class Key extends Objet {

	public Key(String imagePath, Vector2 position, Vector2 size, int prix) {
		super(imagePath, position, size, prix);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateGameObject() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canTake(Hero hero) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean collisionHero(Hero hero) {
		// TODO Auto-generated method stub
		return false;
	}

}
