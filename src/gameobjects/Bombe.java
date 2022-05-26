package gameobjects;

import libraries.Vector2;
import resources.ImagePaths;

public class Bombe extends Objet{
	private int degats;

	public Bombe(String imagePath, Vector2 position, Vector2 size, int prix) {
		super(imagePath, position, size, prix);
		// TODO Auto-generated constructor stub
		this.degats = 3;
		this.setImagePath(ImagePaths.BOMB);
		this.setPrix(20);
	}

	@Override
	public void updateGameObject() {
		// TODO Auto-generated method stub	
	}

	@Override
	public boolean canTake(Hero hero) {
		// TODO Auto-generated method stub
		int compte = hero.getInventaire().getArgent();
		if(compte >= this.getPrix()) {
			hero.getInventaire().setArgent(compte - this.getPrix());
			hero.getInventaire().setBombe(hero.getInventaire().getBombe() + 1);
			return true;
		}
		else
			return false;
	}

	public int getDegats() {
		return degats;
	}

	public void setDegats(int degats) {
		this.degats = degats;
	}
	
}
