package gameobjects;

import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;

public abstract class Objet {
	private String imagePath;
	private Vector2 position;
	private Vector2 size;
	private int prix;
	
	public Objet(String imagePath, Vector2 position, Vector2 size, int prix) {
		this.imagePath = imagePath;
		this.position = position;
		this.size = size;
		this.prix = prix;
	}
	
	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}
	
	public abstract void updateGameObject();
	
	public void drawPrice() {
		String prix = "" + this.prix;
		StdDraw.text(position.getX() + 0.01, position.getY() + 0.04 ,prix);
	}
	
	public boolean collisionObjet(Vector2 position, Vector2 size) {
			if(Physics.rectangleCollision(getPosition(), getSize(), position, size))
				return true;
		return false;		
	}
	
	public abstract boolean canTake(Hero hero);
	
	public boolean collisionHero(Hero hero) {
		if(Physics.rectangleCollision(hero.getPosition(), hero.getSize(), position, size.scalarMultiplication(1.2))) {
			return true;
		}
		return false;
	}

	public String getImagePath() {
		return imagePath;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getSize() {
		return size;
	}
	
	public void setSize(Vector2 size) {
		this.size = size;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	
}
