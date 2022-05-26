package gameobjects;
import java.util.Random;

import libraries.Vector2;
import resources.MonstreInfos;

public class Spider extends Monstre {
	private long startTimeToMove;
	private long startTimeToPause;
	private int randomMove;
	private int randomPause;

	public Spider(Vector2 position, Vector2 size, int life, String imagePath, double speed,
		boolean projectile, int meleeDegat) {
		super(position, size, life, imagePath, speed, projectile, meleeDegat);
		// TODO Auto-generated constructor stub
		this.startTimeToMove = System.nanoTime() / 1000000;
		this.startTimeToPause = System.nanoTime() / 1000000;
		this.randomMove = randomDirection();
		this.randomPause = randomPause();
	}
	
	public void randomDeplacement() {
		long timePause = System.nanoTime() / 1000000 - startTimeToPause;
		long timeMove = System.nanoTime() / 1000000 - startTimeToMove;
		if(timePause > randomPause) {
			if(timeMove < 500) {
				processMovement(randomMove);
			}
			else {
				startTimeToPause = System.nanoTime() / 1000000;
				randomMove = randomDirection();
				randomPause = randomPause();
			}
		}else
			startTimeToMove = System.nanoTime() / 1000000;
	}
	
	private int randomDirection() {
		Random random = new Random();
		return random.nextInt(8);
	}
	
	private int randomPause() {
		Random random = new Random();
		return random.nextInt(1000) + 1000;
	}
	
	private void processMovement(int random) {
		switch(random) {
		case MonstreInfos.TOP_MOVE:
			goUpNext();
			break;
		case MonstreInfos.DOWN_MOVE:
			goDownNext();
			break;
		case MonstreInfos.LEFT_MOVE:
			goLeftNext();
			break;
		case MonstreInfos.RIGHT_MOVE:
			goRightNext();
			break;
		case MonstreInfos.TOP_LEFT_MOVE:
			goUpNext();
			goLeftNext();
			break;
		case MonstreInfos.TOP_RIGHT_MOVE:
			goUpNext();
			goRightNext();
			break;
		case MonstreInfos.DOWN_LEFT_MOVE:
			goDownNext();
			goLeftNext();
			break;
		case MonstreInfos.DOWN_RIGHT_MOVE:
			goDownNext();
			goRightNext();
			break;
		}
	}
	
}
