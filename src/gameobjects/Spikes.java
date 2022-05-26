package gameobjects;

import libraries.Vector2;
import resources.ImagePaths;

public class Spikes extends Objet {
	private int degats;
	public Spikes(String imagePath, Vector2 position, Vector2 size, int prix) {
		super(imagePath, position, size, prix);
		// TODO Auto-generated constructor stub
		this.degats = 1;
		this.setImagePath(ImagePaths.SPIKES);
	}

	public int getDegats() {
		return degats;
	}

	public void setDegats(int degats) {
		this.degats = degats;
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

}
