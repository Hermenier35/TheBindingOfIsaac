package resources;

import gameWorld.Room;
import libraries.Vector2;

public class HeroInfos
{
	public static Vector2 ISAAC_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.7);
	public static final double ISAAC_SPEED = 0.01;
	public static final Vector2 POSITION_ENTER_ROOM_UP = Room.positionFromTileIndex(4, 1);
	public static final Vector2 POSITION_ENTER_ROOM_DOWN = Room.positionFromTileIndex(4, 7);
	public static final Vector2 POSITION_ENTER_ROOM_LEFT = Room.positionFromTileIndex(7, 4);
	public static final Vector2 POSITION_ENTER_ROOM_RIGHT = Room.positionFromTileIndex(1, 4);  
}
