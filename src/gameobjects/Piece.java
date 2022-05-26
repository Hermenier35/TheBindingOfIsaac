package gameobjects;

import libraries.Vector2;
import resources.ImagePaths;

public class Piece extends Consommable {
	private int valeur;
	
	public Piece(String imagePath,Vector2 position, Vector2 size, int valeur, int prix) {
		super(imagePath, position, size, prix);
		// TODO Auto-generated constructor stub
		this.valeur = valeur;
		this.setImagePath(image());
	}

	public int getValeur() {
		return valeur;
	}
	
	private String image() {
		if(valeur == 10)
			return ImagePaths.DIME;
		else if(valeur == 5)
			return ImagePaths.NICKEL;
		else
			return ImagePaths.COIN;
	}

	@Override
	public boolean canTake(Hero hero) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateGameObject() {
		// TODO Auto-generated method stub
		
	}
	
}
