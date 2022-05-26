package gameWorld;

import libraries.StdDraw;
import libraries.Vector2;

public abstract class Decors {
	private String imagePath;
	private Vector2 position;
	private Vector2 size;
	private int degree;
	
	public Decors(String imagePath, Vector2 position, Vector2 size, int degree) {
		this.imagePath = imagePath;
		this.position = position;
		this.size = size;
		this.degree = degree;
	}
	
	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				degree);
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

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}
	
}
