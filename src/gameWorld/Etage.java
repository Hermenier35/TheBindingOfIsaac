package gameWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gameobjects.Bombe;
import gameobjects.Boss;
import gameobjects.Botm;
import gameobjects.Coeur;
import gameobjects.Fly;
import gameobjects.Monstre;
import gameobjects.Spider;
import gameobjects.Objet;
import libraries.Vector2;
import resources.DecorsInfo;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.RoomInfos;

public class Etage {
	private int niveau;
	private List<Room> pieces;

	
	public Etage(int niveau) {
		this.niveau = niveau;
		this.pieces = new ArrayList<Room>();
		construction();
		buildAcces();
		addObjet();
	}
	
	public void construction() {
		if(niveau == 0) {
			Magasin magasin = new Magasin(null);
				magasin.setWalls(buildSetWall(magasin));
				magasin.setFloor(buildSetFloor(magasin));
			SpawnRoom spawnRoom = new SpawnRoom(null);
				spawnRoom.setWalls(buildSetWall(spawnRoom));
				spawnRoom.setFloor(buildSetFloor(spawnRoom));
			MonsterRoom monsterRoom1 = new MonsterRoom(null);
				monsterRoom1.setWalls(buildSetWall(monsterRoom1));
				monsterRoom1.setFloor(buildSetFloor(monsterRoom1));
			MonsterRoom monsterRoom2 = new MonsterRoom(null);
				monsterRoom2.setWalls(buildSetWall(monsterRoom2));
				monsterRoom2.setFloor(buildSetFloor(monsterRoom2));
			BossRoom bossRoom = new BossRoom(null);
				bossRoom.setWalls(buildSetWall(bossRoom));
				bossRoom.setFloor(buildSetFloor(bossRoom));
				
			pieces.add(magasin);
			pieces.add(spawnRoom);
			pieces.add(monsterRoom1);
			pieces.add(monsterRoom2);
			pieces.add(bossRoom);
			
		}else {
			
			MonsterRoom monsterRoom1 = new MonsterRoom(null);
			MonsterRoom monsterRoom2 = new MonsterRoom(null);
			MonsterRoom monsterRoom3 = new MonsterRoom(null);
			MonsterRoom monsterRoom4 = new MonsterRoom(null);
			BossRoom bossRoom = new BossRoom(null);
			pieces.add(monsterRoom1);
			pieces.add(monsterRoom2);
			pieces.add(monsterRoom3);
			pieces.add(monsterRoom4);
			pieces.add(bossRoom);
		}
	}
	
	private void buildAcces() {
		for(Room room : pieces) 
				if(room instanceof SpawnRoom) {
				Door doorMag = new Door(ImagePaths.OPENED_DOOR, RoomInfos.POSITION_DOOR_LEFT,RoomInfos.TILE_SIZE.scalarMultiplication(0.9), true, 90);
				Door doorSpawn = new Door(ImagePaths.OPENED_DOOR, RoomInfos.POSITION_DOOR_RIGHT, RoomInfos.TILE_SIZE.scalarMultiplication(0.9), true, 270);
				room.getAcces().add(new Acces(doorMag, pieces.get(0), HeroInfos.POSITION_ENTER_ROOM_LEFT));
				pieces.get(0).getAcces().add(new Acces(doorSpawn, pieces.get(1), HeroInfos.POSITION_ENTER_ROOM_RIGHT));
			}else if(room instanceof BossRoom && niveau == 0) {
				Vector2 position = freePositionDoor(pieces.get(1)).get(0);
				Door doorBoss = new Door(ImagePaths.CLOSED_DOOR, position ,RoomInfos.TILE_SIZE.scalarMultiplication(0.9), false, degreeDoor(position));
				Door doorSpawn = new Door(ImagePaths.CLOSED_DOOR, oppositePositionDoor(position) ,RoomInfos.TILE_SIZE.scalarMultiplication(0.9), false, degreeDoor(oppositePositionDoor(position)));
				room.getAcces().add(new Acces(doorSpawn, pieces.get(1), positionHero(doorSpawn.getPosition())));
				pieces.get(1).getAcces().add(new Acces(doorBoss, room, positionHero(doorBoss.getPosition())));
			}else if(room instanceof MonsterRoom && niveau == 0) {
				Vector2 position = freePositionDoor(pieces.get(1)).get(0);
				Door doorMonster = new Door(ImagePaths.OPENED_DOOR, position, RoomInfos.TILE_SIZE.scalarMultiplication(0.9), true, degreeDoor(position));
				Door doorMonsterExit = new Door(ImagePaths.OPENED_DOOR, oppositePositionDoor(position), RoomInfos.TILE_SIZE.scalarMultiplication(0.9), true, degreeDoor(oppositePositionDoor(position)));
				room.getAcces().add(new Acces(doorMonsterExit, pieces.get(1), positionHero(doorMonsterExit.getPosition())));
				pieces.get(1).getAcces().add(new Acces(doorMonster, room, positionHero(doorMonster.getPosition())));
			}
	}
	
	private void addObjet() {
		for(Room room : pieces)
			if(room instanceof BossRoom)
				((BossRoom) room).setBoss(setBoss(room));
			else if(room instanceof MonsterRoom) 
				listMonster(room);
			else if (room instanceof Magasin)
				setObjetMagasin(room);
	}
	
	private Vector2 oppositePositionDoor(Vector2 positionDoor) {
		if(positionDoor.equals(RoomInfos.POSITION_DOOR_LEFT))
			return RoomInfos.POSITION_DOOR_RIGHT;
		else if(positionDoor.equals(RoomInfos.POSITION_DOOR_RIGHT))
			return RoomInfos.POSITION_DOOR_LEFT;
		else if(positionDoor.equals(RoomInfos.POSITION_DOOR_UP))
			return RoomInfos.POSITION_DOOR_DOWN;
		else 
			return RoomInfos.POSITION_DOOR_UP;
	}
	
	private int degreeDoor(Vector2 positionDoor) {
		if (positionDoor.equals(RoomInfos.POSITION_DOOR_DOWN))
			return 180;
		else if (positionDoor.equals(RoomInfos.POSITION_DOOR_LEFT))
			return 90;
		else if (positionDoor.equals(RoomInfos.POSITION_DOOR_RIGHT))
			return 270;
		else
			return 0;
	}
	
	private List<Vector2> freePositionDoor(Room room) {
		List<Vector2> positions = new ArrayList<>();
		List<Vector2> freePosition = new ArrayList<>();
		boolean notFree = false;
		positions.add(RoomInfos.POSITION_DOOR_DOWN);
		positions.add(RoomInfos.POSITION_DOOR_LEFT);
		positions.add(RoomInfos.POSITION_DOOR_RIGHT);
		positions.add(RoomInfos.POSITION_DOOR_UP);
		for(Vector2 pos : positions) { 
			for(Acces acces : room.getAcces()) {
				if(acces.getPorte().getPosition().equals(pos))
					notFree = true;
			}
			if(!notFree)
				freePosition.add(pos);
			notFree = false;
		}
		return freePosition;
	}
	
	private Vector2 positionHero(Vector2 positionDoor) {
		if (positionDoor.equals(RoomInfos.POSITION_DOOR_DOWN))
			return HeroInfos.POSITION_ENTER_ROOM_DOWN;
		else if (positionDoor.equals(RoomInfos.POSITION_DOOR_LEFT))
			return HeroInfos.POSITION_ENTER_ROOM_LEFT;
		else if (positionDoor.equals(RoomInfos.POSITION_DOOR_RIGHT))
			return HeroInfos.POSITION_ENTER_ROOM_RIGHT;
		else
			return HeroInfos.POSITION_ENTER_ROOM_UP;
	}
	
	private List<Wall> buildSetWall(Room room){
		List<Wall> walls = new ArrayList<Wall>();
		int degree = 0;
		String imagePath = "";
		Vector2 imageSize = null;
		if(room instanceof SpawnRoom) {
			imagePath = ImagePaths.WALL;
			imageSize = DecorsInfo.FLOOR_WALL_SIZE;

		}else if(room instanceof Magasin) {
			imagePath = ImagePaths.WALL_WOOD;
			imageSize = DecorsInfo.FLOOR_WALL_SIZE;
		}else if(room instanceof BossRoom) {
			imagePath = ImagePaths.WALL_BLACK;
			imageSize = DecorsInfo.FLOOR_WALL_SIZE;
		}else {
			imagePath = ImagePaths.WALL;
			imageSize = DecorsInfo.FLOOR_WALL_SIZE;
		}
		
		for (int i = 0; i < RoomInfos.NB_TILES; i++)
		{
			for (int j = 0; j < RoomInfos.NB_TILES; j++)
			{
				Vector2 position = Room.positionFromTileIndex(i, j);
				
				if(i == 0) 
					degree = 90;
				else if(j == 0)
					degree = 180;
				else if(j == RoomInfos.NB_TILES - 1)
					degree = 0;
				else if(i == RoomInfos.NB_TILES - 1)
					degree = 270; 
				if(i == 0 || j == 0 || j == RoomInfos.NB_TILES - 1 || i == RoomInfos.NB_TILES - 1) {
					Wall wall = new Wall(imagePath, position, imageSize, degree);
					walls.add(wall);
				}
			}
		}
		return walls;
	}
	
	private List<Floor> buildSetFloor(Room room){
		List<Floor> floors = new ArrayList<Floor>();
		String imagePath = "";
		Vector2 imageSize = null;
		if(room instanceof SpawnRoom) {
			imagePath = ImagePaths.FLOOR_ROCK;
			imageSize = DecorsInfo.FLOOR_WALL_SIZE;

		}else if(room instanceof Magasin) {
			imagePath = ImagePaths.FLOOR_WOOD;
			imageSize = DecorsInfo.FLOOR_WALL_SIZE;
		}else if(room instanceof BossRoom) {
			imagePath = ImagePaths.FLOOR_ROCK;
			imageSize = DecorsInfo.FLOOR_WALL_SIZE;
		}else {
			imagePath = ImagePaths.FLOOR_WOOD;
			imageSize = DecorsInfo.FLOOR_WALL_SIZE;
		}
		for (int i = 1; i < RoomInfos.NB_TILES - 1; i++)
		{
			for (int j = 1; j < RoomInfos.NB_TILES - 1; j++)
			{
				Vector2 position = Room.positionFromTileIndex(i, j);
				Floor floor = new Floor(imagePath, position, imageSize, 0);
				floors.add(floor);
			}
		}
		return floors;
	}
	
	private Spider createSpider(Room room) {
		Spider spider = new Spider(randomPosition(room), RoomInfos.TILE_SIZE.scalarMultiplication(0.4), 5, ImagePaths.SPIDER, 0.01, false, 1);
		return spider;
	}
	
	private Fly createFly(Room room) {
		Fly fly = new Fly(randomPosition(room), RoomInfos.TILE_SIZE.scalarMultiplication(0.4), 3, ImagePaths.FLY, HeroInfos.ISAAC_SPEED/8, true, 1);
		return fly;
	}
	
	private Vector2 randomPosition(Room room) {
		Random r = new Random();
		Vector2 position = Room.positionFromTileIndex(r.nextInt(7) + 1, r.nextInt(7) + 1);
		while(!room.isSpwanable(position)) {
			position = Room.positionFromTileIndex(r.nextInt(7) + 1, r.nextInt(7) + 1);
		}
		return position;
	}
	
	private void listMonster(Room room) {
		if(room instanceof MonsterRoom)
			for(int i = 0; i < 2; i++) {
				Monstre spider = createSpider(room);
				Monstre fly = createFly(room);
				spider.setCurrentRoom(room);
				fly.setCurrentRoom(room);
				((MonsterRoom) room).getMontres().add(spider);
				((MonsterRoom) room).getMontres().add(fly);
			}
	}
	
	private void setObjetMagasin(Room room){
		if(room instanceof Magasin) {
			Objet coeur = new Coeur(null, randomPosition(room), RoomInfos.TILE_SIZE.scalarMultiplication(0.6), 2, 10);
			((Magasin) room).getObjets().add(coeur);
			Objet halfCoeur = new Coeur(null, randomPosition(room), RoomInfos.TILE_SIZE.scalarMultiplication(0.6), 1, 5);
			((Magasin) room).getObjets().add(halfCoeur);
			Objet botm = new Botm(null, randomPosition(room), RoomInfos.TILE_SIZE.scalarMultiplication(0.6), 15);
			((Magasin) room).getObjets().add(botm);
			Objet bombe = new Bombe(null, randomPosition(room), RoomInfos.TILE_SIZE.scalarMultiplication(0.6), 20);
			((Magasin) room).getObjets().add(bombe);
		}
	}
	
	private Boss setBoss(Room room) {
		Boss boss = new Boss(RoomInfos.POSITION_CENTER_OF_ROOM, RoomInfos.TILE_SIZE.scalarMultiplication(1.2), new Vector2(), 15, ImagePaths.BOSS, 0.01, true, 2);
		boss.setCurrentRoom(room);
		if(room instanceof MonsterRoom)
			((MonsterRoom) room).getMontres().add(boss);
		return boss;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public List<Room> getPieces() {
		return pieces;
	}

	public void setPieces(List<Room> pieces) {
		this.pieces = pieces;
	}

}
