package resources;

import libraries.Vector2;

public class DecorsInfo {
	public static Vector2 FLOOR_WALL_SIZE = RoomInfos.TILE_SIZE;
	public static Vector2 DOOR_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.8);
	public static Vector2 ROCK_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.4);
}
