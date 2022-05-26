package gameWorld;

import libraries.Vector2;
import resources.ImagePaths;

public class Rock extends Decors {

	public Rock(String imagePath, Vector2 position, Vector2 size, int degree) {
		super(imagePath, position, size, degree);
		// TODO Auto-generated constructor stub
		this.setImagePath(ImagePaths.ROCK);
	}
	
}
