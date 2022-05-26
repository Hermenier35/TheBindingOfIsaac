package gameobjects;

import libraries.Vector2;
import resources.ImagePaths;

public class Coeur extends Consommable {
	private int lifeRecovered;
	
	public Coeur(String imagePath, Vector2 position,Vector2 size, int lifeRecovered, int prix) {
		super(imagePath, position, size, prix);
		// TODO Auto-generated constructor stub
		this.lifeRecovered = lifeRecovered;
		this.setImagePath(image());
	}

	public int getLifeRecovered() {
		return lifeRecovered;
	}
	
	private String image() {
		if(lifeRecovered == 2)
			return ImagePaths.HEART_PICKABLE;
		else 
			return ImagePaths.HALF_HEART_PICKABLE;
	}
	
	@Override
	public boolean canTake(Hero hero) {
		// TODO Auto-generated method stub
		int compte = hero.getInventaire().getArgent();
		if(hero.getLife().getVie() < hero.getLife().getVieMax() && compte >= this.getPrix()) {
			hero.getLife().setVie(hero.getLife().getVie() + lifeRecovered);
			hero.getInventaire().setArgent(compte - this.getPrix());
			return true;
		}
		else 
			return false;
	}

	@Override
	public void updateGameObject() {
		// TODO Auto-generated method stub
		
	}
}
