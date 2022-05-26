package gameWorld;

import gameobjects.Hero;
import libraries.Physics;
import libraries.StdDraw;
import resources.Controls;
import resources.ImagePaths;
import resources.RoomInfos;

public class GameWorld
{
	private Room currentRoom;
	private Hero hero;
	private Dungeon dungeon;

	// A world needs a hero
	public GameWorld(Hero hero)
	{
		this.hero = hero;
		dungeon = new Dungeon(3);
		currentRoom = dungeon.getEtages().get(0).getPieces().get(1);
	}

	public void processUserInput()
	{
		processKeysForMovement();
		processKeysForWeapon();
	}

	public boolean gameOver()
	{
		if(hero.getLife().getVie()<1) {
			StdDraw.picture(RoomInfos.POSITION_CENTER_OF_ROOM.getX(), RoomInfos.POSITION_CENTER_OF_ROOM.getY(), ImagePaths.LOSE_SCREEN, 1, 1);
			return true;
		}
		return false;
	}

	public void updateGameObjects()
	{
		hero.setCurrentRoom(currentRoom);
		enterRoom();
		currentRoom.setHero(hero);
		currentRoom.updateRoom();
		openDoorBoss();
			
	}

	public void drawGameObjects()
	{
		if(!gameOver()) {
			currentRoom.drawRoom();
			currentRoom.drawGameObjects();
			hero.drawGameObject();
		}
	}
	
	private boolean openDoorBoss() {
		for(Room room : dungeon.getEtages().get(0).getPieces())
			if(room instanceof MonsterRoom && !(room instanceof BossRoom)) {
				if(((MonsterRoom) room).getMontres().size() > 0)
					return false;
			}
		for(Room room : dungeon.getEtages().get(0).getPieces())
			for(Acces acces : room.getAcces())
				if(acces.getPiece() instanceof BossRoom) {
					acces.getPorte().setOpen(true);
				}
		
		return true;
	}
	
	private void enterRoom() {
		for(Acces acces : currentRoom.getAcces()) {
			if(Physics.rectangleCollision(hero.getPosition(), hero.getSize(), acces.getPorte().getPosition(), acces.getPorte().getSize()) && acces.getPorte().isOpen()) {
				currentRoom = acces.getPiece();
				hero.setPosition(acces.getPosition());
			}
		}
	}
	


	/*
	 * Keys processing
	 */

	private void processKeysForMovement()
	{
		if (StdDraw.isKeyPressed(Controls.goUp))
		{
			hero.goUpNext();
		}
		if (StdDraw.isKeyPressed(Controls.goDown))
		{
			hero.goDownNext();
		}
		if (StdDraw.isKeyPressed(Controls.goRight))
		{
			hero.goRightNext();
		}
		if (StdDraw.isKeyPressed(Controls.goLeft))
		{
			hero.goLeftNext();
		}
	}
	
	private void processKeysForWeapon() {
		if (StdDraw.isKeyPressed(Controls.haut))
		{
			hero.castTearUp();
		}
		if (StdDraw.isKeyPressed(Controls.bas))
		{
			hero.castTearDown();
		}
		if (StdDraw.isKeyPressed(Controls.droite))
		{
			hero.castTearRight();
		}
		if (StdDraw.isKeyPressed(Controls.gauche))
		{
			hero.castTearLeft();
		}
	}
}
