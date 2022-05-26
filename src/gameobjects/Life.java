package gameobjects;

import java.util.ArrayList;
import java.util.List;

import gameWorld.Room;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Life {
	private int vieMax;
	private int vie;
	private String imagePathEmpty;
	private String imagePathFull;
	private String imagePathHalf;
	private Vector2 size;
	private Vector2 position;
	private Vector2 position2;
	private Vector2 position3;
	
	public Life(int vieMax) {
		this.vie = vieMax;
		this.vieMax = vieMax ;
		this.imagePathFull = ImagePaths.HEART_HUD;
		this.imagePathEmpty = ImagePaths.EMPTY_HEART_HUD;
		this.imagePathHalf = ImagePaths.HALF_HEART_HUD;
		this.size = RoomInfos.TILE_SIZE.scalarMultiplication(0.4);
		this.position = Room.positionFromTileIndex(0, 8);
		this.position2 = Room.positionFromTileIndex(0, 8);
		this.position3 = Room.positionFromTileIndex(0, 8);
		position.addX(-0.035);
		position3.addX(0.035);
	}
	
	public void drawGameObject()
	{
		int entier = vie / 2;
		int half = vie % 2;
		List<Vector2> positions = new ArrayList<Vector2>();
		positions.add(position);
		positions.add(position2);
		positions.add(position3);
		for(Vector2 position : positions)
			if(entier > 0) {
				StdDraw.picture(position.getX(), position.getY(), imagePathFull, size.getX(), size.getY(), 0);
				entier--;
			}else if(half > 0) {
				StdDraw.picture(position.getX(), position.getY(), imagePathHalf, size.getX(), size.getY(), 0);
				half--;
			}else
				StdDraw.picture(position.getX(), position.getY(), imagePathEmpty, size.getX(), size.getY(), 0);
				
		
	}

	public int getVie() {
		return vie;
	}

	public void setVie(int vie) {
		if(vie > this.vieMax)
			this.vie = this.vieMax;
		else
			this.vie = vie;
	}

	public int getVieMax() {
		return vieMax;
	}

	public void setVieMax(int vieMax) {
		this.vieMax = vieMax;
	}
	
}
