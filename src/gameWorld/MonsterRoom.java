package gameWorld;

import java.util.LinkedList;
import java.util.List;
import gameobjects.Hero;
import gameobjects.Monstre;
import libraries.Physics;
import libraries.Vector2;

public class MonsterRoom extends Room {
	private List<Monstre> monstres;

	public MonsterRoom(Hero hero) {
		super(hero);
		// TODO Auto-generated constructor stub
		this.monstres = new LinkedList<Monstre>();
	}
	
	public void updateRoom()
	{
	  super.updateRoom();
	  deleteDeadMonster();
	  updateAcces();
	  for(Monstre monstre : monstres)
		  monstre.updateGameObject();
		 
	}
	
	private void deleteDeadMonster() {
		List<Monstre> monstresTampon = new LinkedList<Monstre>();
		for(Monstre monstre : monstres)
			if(monstre.getLife() > 0)
				monstresTampon.add(monstre);
		monstres.clear();
		monstres.addAll(monstresTampon);
	}
	
	public void updateAcces() {
		for(Acces acces : getAcces())
			if(getHero() != null)
				if(monstres.size() > 0)
					acces.getPorte().setOpen(false);
				else
					acces.getPorte().setOpen(true);
	}
	
	public List<Monstre> getMontres() {
		return monstres;
	}

	public void setMontres(List<Monstre> montres) {
		this.monstres = montres;
	}
	
	public boolean isSpwanable(Vector2 position) {
		for(Monstre monstre : monstres)
			if(monstre.getPosition().equals(position) || !super.isSpwanable(position))
				return false;
		
		return true;
	}
	
	@Override
	public boolean collision(Vector2 direction, Vector2 position, Vector2 size, double speed) {
		// TODO Auto-generated method stub
		Vector2 normalVector = new Vector2(direction);
		normalVector.euclidianNormalize(speed);
		Vector2 positionTest = position.addVector(normalVector);
		for(Monstre monstre : monstres) {
			if(Physics.rectangleCollision(positionTest, size, monstre.getPosition(), monstre.getSize().scalarMultiplication(0.8)))
					return true;
		}
		return false;
	}

	@Override
	public void drawGameObjects() {
		// TODO Auto-generated method stub
		for(Monstre monstre : monstres) {
			monstre.drawGameObject();
		}
	}		
	
}
