package gameWorld;

import java.util.LinkedList;
import java.util.List;

import gameobjects.Hero;
import gameobjects.Objet;
import libraries.Physics;
import libraries.Vector2;

public class Magasin extends Room {
	private List<Objet> objets;

	public Magasin(Hero hero) {
		super(hero);
		// TODO Auto-generated constructor stub
		this.objets = new LinkedList<Objet>();
	}
	
	public void updateRoom()
	{
	  super.updateRoom();
	  deleteObjetUse();
	  for(Objet objet : objets) {
		  objet.updateGameObject();
	  }
	}
	
	private void deleteObjetUse() {
		List<Objet> objetsTampon = new LinkedList<Objet>();
		for(Objet objet : objets) {
			if(!objet.collisionHero(getHero()) || !objet.canTake(getHero()))
				objetsTampon.add(objet);	
		}
		objets.clear();
		objets.addAll(objetsTampon);
	}
	
	public boolean isSpwanable(Vector2 position) {
		for(Objet objet : objets)
			if(objet.getPosition().equals(position) || !super.isSpwanable(position))
				return false;
		
		return true;
	}

	public List<Objet> getObjets() {
		return objets;
	}

	public void setObjets(List<Objet> objets) {
		this.objets = objets;
	}

	@Override
	public boolean collision(Vector2 direction, Vector2 position, Vector2 size, double speed) {
		// TODO Auto-generated method stub
		Vector2 normalVector = new Vector2(direction);
		normalVector.euclidianNormalize(speed);
		Vector2 positionTest = position.addVector(normalVector);
		for(Objet objet : objets) {
			if(Physics.rectangleCollision(positionTest, size, objet.getPosition(), objet.getSize().scalarMultiplication(0.8)))
					return true;
		}
		return false;
	}

	@Override
	public void drawGameObjects() {
		// TODO Auto-generated method stub
		for(Objet objet : objets){
			objet.drawGameObject();
			objet.drawPrice();
		}
	}
	
}
