package gameWorld;

import libraries.StdDraw;
import libraries.Vector2;

public class Wall extends Decors {
	private int degree;

	public Wall(String imagePath, Vector2 position, Vector2 size, int degree) {
		super(imagePath, position, size, degree);
		// TODO Auto-generated constructor stub
		this.degree = degree;
	}
	
	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				degree);
	}
	
}
