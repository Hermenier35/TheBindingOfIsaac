package gameobjects;

import libraries.Vector2;
import resources.ImagePaths;

public class Botm extends Passif {

	public Botm(String imagePath, Vector2 position, Vector2 size, int prix) {
		super(imagePath, position, size, prix);
		// TODO Auto-generated constructor stub
		this.setImagePath(ImagePaths.BLOOD_OF_THE_MARTYR);
		this.setPrix(15);
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
			hero.getInventaire().setBotm(hero.getInventaire().getBotm()+1);
			return true;
		}
		else
			return false;
	}

}
