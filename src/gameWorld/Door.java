package gameWorld;

import libraries.StdDraw;                    
import libraries.Vector2;
import resources.ImagePaths;

public class Door extends Decors {
	
	private boolean isOpen;

	public Door(String imagePath, Vector2 position, Vector2 size, boolean isOpen, int degree) {
		super(imagePath, position, size, degree);
		// TODO Auto-generated constructor stub
		this.isOpen = isOpen;
	}
	
	public void drawGameObject()
	{
		if(isOpen)
			setImagePath(ImagePaths.OPENED_DOOR);
		else
			setImagePath(ImagePaths.CLOSED_DOOR);
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				getDegree());
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
		drawGameObject();
	}
}
