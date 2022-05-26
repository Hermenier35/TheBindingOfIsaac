package gameobjects;

import java.util.LinkedList;
import java.util.List;

import gameWorld.Room;
import libraries.StdDraw;
import libraries.Vector2;
import resources.DisplaySettings;
import resources.ImagePaths;
import resources.RoomInfos;

public class Hero
{
	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private double speed;
	private Vector2 direction;
	private Life life;
	private Inventaire inventaire;
	private Room currentRoom;
	private List<Projectile> projectile;
	private long startTimeToCast;


	public Hero(Vector2 position, Vector2 size, double speed, String imagePath, Life life)
	{
		this.position = position;
		this.size = size;
		this.speed = speed;
		this.imagePath = imagePath;
		this.direction = new Vector2();
		this.projectile = new LinkedList<Projectile>();
		this.life = life;
		this.inventaire = new Inventaire();
	}

	public void updateGameObject()
	{
		move();
		updateWeapon();
		inventaire.setPiece();
	}
	
	private void updateWeapon() {
		List<Projectile> projectileTampon = new LinkedList<Projectile>();
		for(Projectile pro : projectile)
			if(!pro.projectileDown() && !pro.collisionWall(currentRoom) && !pro.collisionMonstre(currentRoom)) {
				pro.updateGameObject();
				projectileTampon.add(pro);
			}
		projectile.clear();
		projectile.addAll(projectileTampon);
	}			

	private void move()
	{
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		direction = new Vector2();
	}

	public void drawGameObject()
	{
		inventaire.drawGameObject();
		life.drawGameObject();
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(), 0);
		for(Projectile pro : projectile)
			pro.drawGameObject();
	}

	/*
	 * Moving from key inputs. Direction vector is later normalised.
	 */
	public void goUpNext()
	{
		Vector2 direction = new Vector2(getDirection());
		direction.addY(1);
		if(!currentRoom.collisionWall(direction, position, size, speed) && !currentRoom.collision(direction, position, size, speed))
			getDirection().addY(1);
	}

	public void goDownNext()
	{
		Vector2 direction = new Vector2(getDirection());
		direction.addY(-1);
		if(!currentRoom.collisionWall(direction, position, size, speed) && !currentRoom.collision(direction, position, size, speed))
			getDirection().addY(-1);
	}

	public void goLeftNext()
	{
		Vector2 direction = new Vector2(getDirection());
		direction.addX(-1);
		if(!currentRoom.collisionWall(direction, position, size, speed) && !currentRoom.collision(direction, position, size, speed))
			getDirection().addX(-1);
	}

	public void goRightNext()
	{
		Vector2 direction = new Vector2(getDirection());
		direction.addX(1);
		if(!currentRoom.collisionWall(direction, position, size, speed) && !currentRoom.collision(direction, position, size, speed))
			getDirection().addX(1);
	}

	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}
	
	
	public boolean canCast() {
		if((System.nanoTime() - startTimeToCast) / 1000000 > DisplaySettings.MILLISECONDS_PER_FRAME_TO_MAINTAIN_FPS * 20)
			return true;
		return false;
	}
	
	public void castTearUp() {
		if(canCast()) {
			startTimeToCast = System.nanoTime();
			Vector2 positionDepart = new Vector2(position);
			Projectile larme = new Projectile(0.4, 1 + inventaire.getBotm(), 0.03, ImagePaths.TEAR, new Vector2(), positionDepart, RoomInfos.TILE_SIZE.scalarMultiplication(0.2), positionDepart);
			larme.getDirection().addY(1);
			projectile.add(larme);
		}
	}
	
	public void castTearDown() {
		if(canCast()) {
			startTimeToCast = System.nanoTime();
			Vector2 positionDepart = new Vector2(position);
			Projectile larme = new Projectile(0.4, 1 + inventaire.getBotm(), 0.03, ImagePaths.TEAR, new Vector2(), positionDepart, RoomInfos.TILE_SIZE.scalarMultiplication(0.2), positionDepart);
			larme.getDirection().addY(-1);
			projectile.add(larme);
		}
	}
	
	public void castTearLeft() {
		if(canCast()) {
			startTimeToCast = System.nanoTime();
			Vector2 positionDepart = new Vector2(position);
			Projectile larme = new Projectile(0.4, 1 + inventaire.getBotm(), 0.03, ImagePaths.TEAR, new Vector2(), positionDepart, RoomInfos.TILE_SIZE.scalarMultiplication(0.2), positionDepart);
			larme.getDirection().addX(-1);
			projectile.add(larme);
		}
	}
	
	public void castTearRight() {
		if(canCast()) {
			startTimeToCast = System.nanoTime();
			Vector2 positionDepart = new Vector2(position);
			Projectile larme = new Projectile(0.4, 1 + inventaire.getBotm(), 0.03, ImagePaths.TEAR, new Vector2(), positionDepart, RoomInfos.TILE_SIZE.scalarMultiplication(0.2), positionDepart);
			larme.getDirection().addX(1);
			projectile.add(larme);
		}
	}


	/*
	 * Getters and Setters
	 */
	public Vector2 getPosition()
	{
		return position;
	}

	public void setPosition(Vector2 position)
	{
		this.position = position;
	}

	public Vector2 getSize()
	{
		return size;
	}

	public void setSize(Vector2 size)
	{
		this.size = size;
	}

	public String getImagePath()
	{
		return imagePath;
	}

	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}

	public double getSpeed()
	{
		return speed;
	}

	public void setSpeed(double speed)
	{
		this.speed = speed;
	}

	public Vector2 getDirection()
	{
		return direction;
	}

	public void setDirection(Vector2 direction)
	{
		this.direction = direction;
	}

	public Life getLife() {
		return life;
	}

	public void setLife(Life life) {
		this.life = life;
	}

	public Inventaire getInventaire() {
		return inventaire;
	}

	public void setInventaire(Inventaire inventaire) {
		this.inventaire = inventaire;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
}
