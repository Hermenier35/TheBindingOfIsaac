package gameobjects;

import java.util.LinkedList;
import java.util.List;

import gameWorld.Room;
import libraries.StdDraw;
import libraries.Vector2;
import resources.DisplaySettings;
import resources.ImagePaths;
import resources.RoomInfos;

public abstract class Monstre {
	private Vector2 position;
	private Vector2 size;
	private Vector2 direction;
	private int life;
	private String imagePath;
	private double speed;
	private boolean isProjectile;
	private List<Projectile> projectiles;
	private Room currentRoom;
	private long startTimeToCast;
	private int meleeDegat;

	
	public Monstre(Vector2 position, Vector2 size, int life, String imagePath,
					double speed, boolean isProjectile, int meleeDegat) {
		this.position = position;
		this.size = size;
		this.life = life;
		this.imagePath = imagePath;
		this.speed = speed;
		this.isProjectile = isProjectile;
		this.direction = new Vector2();
		this.meleeDegat = meleeDegat;
		this.projectiles = new LinkedList<Projectile>();
	}
	
	public abstract void randomDeplacement();
	
	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(), 0);
		if(isProjectile)
			for(Projectile projectile : projectiles)
				projectile.drawGameObject();
	}
	
	public void updateGameObject()
	{
		randomDeplacement();
		move();
		gestionDegatsMelee();
		if(isProjectile) {
			fire();
			for(Projectile projectile : projectiles)
				projectile.updateGameObject();
			updateFire();
		}
	}
	
	private void gestionDegatsMelee() {
		if(currentRoom.getHero() == null)
			System.out.println("null");
		Hero hero = currentRoom.getHero();
		if(currentRoom.collisionHero(position, size))
			if(canCast()) {
				hero.getLife().setVie(hero.getLife().getVie() - meleeDegat);
				startTimeToCast = System.nanoTime();
			}
	}
	
	public void goUpNext()
	{
		Vector2 direction = new Vector2(getDirection());
		direction.addY(1);
		if(!currentRoom.collisionWall(direction, position, size, speed) && !currentRoom.collisionHero(position, size))
			getDirection().addY(1);
	}

	public void goDownNext()
	{
		Vector2 direction = new Vector2(getDirection());
		direction.addY(-1);
		if(!currentRoom.collisionWall(direction, position, size, speed) && !currentRoom.collisionHero(position, size))
		getDirection().addY(-1);
	}

	public void goLeftNext()
	{
		Vector2 direction = new Vector2(getDirection());
		direction.addX(-1);
		if(!currentRoom.collisionWall(direction, position, size, speed) && !currentRoom.collisionHero(position, size))
			getDirection().addX(-1);
	}

	public void goRightNext()
	{
		Vector2 direction = new Vector2(getDirection());
		direction.addX(1);
		if(!currentRoom.collisionWall(direction, position, size, speed) && !currentRoom.collisionHero(position, size))
			getDirection().addX(1);
	}
	
	private void move()
	{
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		direction = new Vector2();
	}
	
	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}
	
	public boolean canCast() {
		if((System.nanoTime() - startTimeToCast) / 1000000 > DisplaySettings.MILLISECONDS_PER_FRAME_TO_MAINTAIN_FPS * 40)
			return true;
		return false;
	}
	
	private void updateFire() {
		List<Projectile> projectilesTampon = new LinkedList<Projectile>();
		for(Projectile fire : projectiles)
			if(!fire.projectileDown() && !fire.collisionWall(getCurrentRoom()) && !fire.collisionHero(getCurrentRoom().getHero()))
				projectilesTampon.add(fire);
		projectiles.clear();
		projectiles.addAll(projectilesTampon);
	}
	
	private void fire() {
		if(canCast()) 
			castProjectile();
	}
	
	private void castProjectile() {
			startTimeToCast = System.nanoTime();
			Vector2 positionDepart = new Vector2(position);
			Projectile fire = new Projectile(0.4, 1, 0.01, ImagePaths.FIRE, new Vector2(), positionDepart, RoomInfos.TILE_SIZE.scalarMultiplication(0.2), positionDepart);
			double direcX = currentRoom.getHero().getPosition().getX() - position.getX();
			double direcY = currentRoom.getHero().getPosition().getY() - position.getY();
			fire.getDirection().addY(direcY);
			fire.getDirection().addX(direcX);
			projectiles.add(fire);
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getDirection() {
		return direction;
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Vector2 getSize() {
		return size;
	}

	public String getImagePath() {
		return imagePath;
	}

	public boolean isProjectile() {
		return isProjectile;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	public void setProjectiles(List<Projectile> projectiles) {
		this.projectiles = projectiles;
	}
}
