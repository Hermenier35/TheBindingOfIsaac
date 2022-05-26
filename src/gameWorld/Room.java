package gameWorld;

import java.util.ArrayList;
import java.util.List;
import gameobjects.Hero;
import libraries.Physics;
import libraries.Vector2;
import resources.HeroInfos;
import resources.RoomInfos;


public abstract class Room
{
	private Hero hero;
	private List<Wall> walls;
	private List<Floor> floors;
	private List<Acces> acces;


	public Room(Hero hero)
	{
		this.hero = hero;
		this.walls = new ArrayList<Wall>();
		this.floors = new ArrayList<Floor>();
		this.acces = new ArrayList<Acces>();
	}


	/*
	 * Make every entity that compose a room process one step
	 */
	public void updateRoom()
	{
		makeHeroPlay();
	}


	private void makeHeroPlay()
	{
		hero.updateGameObject();
	}
	
	public abstract boolean collision(Vector2 direction, Vector2 position, Vector2 size, double speed);
	public abstract void drawGameObjects();
	
	public boolean collisionWall(Vector2 direction, Vector2 position, Vector2 size, double speed) {
		Vector2 normalVector = new Vector2(direction);
		normalVector.euclidianNormalize(speed);
		Vector2 positionTest = position.addVector(normalVector);
		for(Wall wall : walls) {
			if(Physics.rectangleCollision(positionTest, size, wall.getPosition(), wall.getSize()))
				if(collisionOpenDoor(wall))
					return false;
				else
					return true;
		}
		return false;		
	}
	
	private boolean collisionOpenDoor(Wall wall) {
		for(Acces acces : acces) {
			if(wall.getPosition().equals(acces.getPorte().getPosition()) && acces.getPorte().isOpen())
				return true;
		}
		return false;
	}
	
	public boolean collisionHero(Vector2 position, Vector2 size) {			
		if(Physics.rectangleCollision(hero.getPosition(), hero.getSize(), position, size)) {
			return true;
		}
		return false;
	}
	
	public boolean isSpwanable(Vector2 position) {
		for(Acces acces : acces)
			if(acces.getPorte().getPosition().equals(RoomInfos.POSITION_DOOR_UP)) {
				if(position.equals(HeroInfos.POSITION_ENTER_ROOM_DOWN))
					return false;
			}else if(acces.getPorte().getPosition().equals(RoomInfos.POSITION_DOOR_DOWN)) {
				if(position.equals(HeroInfos.POSITION_ENTER_ROOM_UP))
					return false;
			}else if(acces.getPorte().getPosition().equals(RoomInfos.POSITION_DOOR_LEFT)) {
				if(position.equals(HeroInfos.POSITION_ENTER_ROOM_RIGHT))
					return false;
			}else if(acces.getPorte().getPosition().equals(RoomInfos.POSITION_DOOR_RIGHT)) {
				if(position.equals(HeroInfos.POSITION_ENTER_ROOM_LEFT))
					return false;
			}
		return true;
	}
	

	/*
	 * Drawing
	 */
	public void drawRoom()
	{
		drawFloor();
		drawWall();
		drawDoor();
	}
	
	private void drawFloor() {
		for(Floor floor : floors)
			floor.drawGameObject();
	}
	
	
	private void drawWall() {
		for(Wall wall : walls) {
			wall.drawGameObject();
		}
	}
	
	private void drawDoor() {
		for(Acces acce : acces) {
			acce.getPorte().drawGameObject();
		}
	}
	
	
	
	public List<Acces> getAcces() {
		return acces;
	}


	public void setAcces(List<Acces> acces) {
		this.acces = acces;
	}

	public Hero getHero() {
		return hero;
	}


	public void setHero(Hero hero) {
		this.hero = hero;
	}
	

	public List<Wall> getWalls() {
		return walls;
	}


	public void setWalls(List<Wall> walls) {
		this.walls = walls;
	}
	
	

	public List<Floor> getFloor() {
		return floors;
	}


	public void setFloor(List<Floor> floors) {
		this.floors = floors;
	}


	/**
	 * Convert a tile index to a 0-1 position.
	 * 
	 * @param indexX
	 * @param indexY
	 * @return
	 */
	public static Vector2 positionFromTileIndex(int indexX, int indexY)
	{
		return new Vector2(indexX * RoomInfos.TILE_WIDTH + RoomInfos.HALF_TILE_SIZE.getX(),
				indexY * RoomInfos.TILE_HEIGHT + RoomInfos.HALF_TILE_SIZE.getY());
	}
}
