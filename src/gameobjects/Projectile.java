package gameobjects;

import gameWorld.BossRoom;
import gameWorld.MonsterRoom;
import gameWorld.Room;
import gameWorld.Wall;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;

public class Projectile {
	private Vector2 direction;
	private Vector2 position;
	private Vector2 positionDepart;
	private Vector2 size;
	private double distance;
	private int degats;
	private double vitesse;
	private String imagePath;

	public Projectile(double distance, int degats, double vitesse, String imagePath, Vector2 direction, Vector2 position, Vector2 size, Vector2 positionDepart) {
		// TODO Auto-generated constructor stub
		this.direction = direction;
		this.position = position;
		this.size = size;
		this.positionDepart = positionDepart;
		this.distance = distance;
		this.degats = degats;
		this.vitesse = vitesse;
		this.imagePath = imagePath;
	}
	
	public boolean projectileDown() {
		if(position.distance(positionDepart) > getDistance())
			return true;
		return false;
	}
	
	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}
	
	public void updateGameObject()
	{
		move();
	}
	
	public boolean collisionWall(Room currentRoom) {
		for(Wall wall : currentRoom.getWalls()) {
			if(Physics.rectangleCollision(getPosition(), getSize(), wall.getPosition(), wall.getSize()))
				return true;
		}
		return false;		
	}
	
	public boolean collisionMonstre(Room currentRoom) {
		if(currentRoom instanceof MonsterRoom)
			for(Monstre monstre : ((MonsterRoom) currentRoom).getMontres()) {
				if(Physics.rectangleCollision(getPosition(), getSize(), monstre.getPosition(), monstre.getSize())) {
					monstre.setLife(monstre.getLife() - degats );
					return true;
				}
			}
		if(currentRoom instanceof BossRoom)
			if(Physics.rectangleCollision(position, size, ((BossRoom) currentRoom).getBoss().getPosition(), ((BossRoom) currentRoom).getBoss().getSize())){
				((BossRoom) currentRoom).getBoss().setLife(((BossRoom) currentRoom).getBoss().getLife() - degats );
				return true;
			}
		return false;		
	}
	
	public boolean collisionHero(Hero hero) {
		if(Physics.rectangleCollision(hero.getPosition(), hero.getSize(), getPosition(), getSize())) {
				hero.getLife().setVie(hero.getLife().getVie() - getDegats());
			return true;
		}
		return false;
	}
	
	private void move()
	{
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
	}
	
	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(getDirection());
		normalizedVector.euclidianNormalize(getVitesse());
		return normalizedVector;
	}

	public Vector2 getDirection() {
		return direction;
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}

	public Vector2 getPositionDepart() {
		return positionDepart;
	}

	public void setPositionDepart(Vector2 positionDepart) {
		this.positionDepart = positionDepart;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public int getDegats() {
		return degats;
	}

	public void setDegats(int degats) {
		this.degats = degats;
	}

	public double getVitesse() {
		return vitesse;
	}

	public void setVitesse(double vitesse) {
		this.vitesse = vitesse;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}
