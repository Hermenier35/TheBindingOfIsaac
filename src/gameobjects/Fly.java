package gameobjects;


import libraries.Vector2;

public class Fly extends Monstre {

	public Fly(Vector2 position, Vector2 size, int life, String imagePath, double speed,
			boolean projectile, int meleeDegat) {
		super(position, size, life, imagePath, speed, projectile, meleeDegat);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void randomDeplacement() {
		// TODO Auto-generated method stub
		processMovement();
	}
	
	private void processMovement() {
		if(getCurrentRoom().getHero().getPosition().getY() < getPosition().getY() &&
		   getCurrentRoom().getHero().getPosition().getX() < getPosition().getX()) {
			goDownNext();
			goLeftNext();
		}
		if(getCurrentRoom().getHero().getPosition().getY() > getPosition().getY() &&
		   getCurrentRoom().getHero().getPosition().getX() < getPosition().getX()) {
			goUpNext();
			goLeftNext();
		}
		if(getCurrentRoom().getHero().getPosition().getY() > getPosition().getY() &&
		   getCurrentRoom().getHero().getPosition().getX() > getPosition().getX()) {
			goUpNext();
			goRightNext();
		}
		if(getCurrentRoom().getHero().getPosition().getY() < getPosition().getY() &&
		   getCurrentRoom().getHero().getPosition().getX() > getPosition().getX()) {
			goDownNext();
			goRightNext();
		}
		if(getCurrentRoom().getHero().getPosition().getY() == getPosition().getY() &&
		   getCurrentRoom().getHero().getPosition().getX() > getPosition().getX()) {
			goRightNext();
		}
		if(getCurrentRoom().getHero().getPosition().getY() == getPosition().getY() &&
		   getCurrentRoom().getHero().getPosition().getX() < getPosition().getX()) {
			goLeftNext();
		}
		if(getCurrentRoom().getHero().getPosition().getY() < getPosition().getY() &&
		   getCurrentRoom().getHero().getPosition().getX() == getPosition().getX()) {
			goDownNext();
		}
		if(getCurrentRoom().getHero().getPosition().getY() > getPosition().getY() &&
		   getCurrentRoom().getHero().getPosition().getX() == getPosition().getX()) {
			goUpNext();
		}
	}	
	
}
