package gameobjects;

import java.util.Random;

import libraries.Vector2;
import resources.MonstreInfos;

public class Boss extends Monstre {
	private long startTimeToMove;
	private long startTimeToPause;
	private long startMovement;
	private int randomMove;
	private int randomPause;
	private int randomMovement;
	

	public Boss(Vector2 position, Vector2 size, Vector2 direction, int life, String imagePath, double speed,
			boolean projectile, int meleeDegat) {
		super(position, size, life, imagePath, speed, projectile, meleeDegat);
		// TODO Auto-generated constructor stub
		startMovement = System.nanoTime() / 1000000;
		this.randomMovement = randomMovement();
	}
	
	
	@Override
	public void randomDeplacement() {
		// TODO Auto-generated method stub
		if(System.nanoTime() / 1000000 - startMovement > 1500) {
			this.randomMovement = randomMovement();
			this.startMovement = System.nanoTime() / 1000000;
			if(randomMovement==0)
				setSpeed(0.005);
			else
				setSpeed(0.02);
		}
			
		//FlyMovement
		if(randomMovement == 0) {
			processMovement();
		}
		//SpiderMovement;
		else {
			long timePause = System.nanoTime() / 1000000 - startTimeToPause;
			long timeMove = System.nanoTime() / 1000000 - startTimeToMove;
			if(timePause > randomPause) {
				if(timeMove < 500) {
					processMovement2(randomMove);
				}
				else {
					startTimeToPause = System.nanoTime() / 1000000;
					randomMove = randomDirection();
					randomPause = randomPause();
				}
			}else
				startTimeToMove = System.nanoTime() / 1000000;
		}
	}	
	
	private void processMovement() {
		if(getCurrentRoom().getHero().getPosition().getY() < getPosition().getY() &&
		   getCurrentRoom().getHero().getPosition().getX() < getPosition().getX()) {
			goDownNext();
			goLeftNext();
		}
		if(getCurrentRoom().getHero().getPosition().getY() > getPosition().getY() &&
		   getCurrentRoom().getHero().getPosition().getX() < getPosition().getX()) {
			goUpNext();
			goLeftNext();
		}
		if(getCurrentRoom().getHero().getPosition().getY() > getPosition().getY() &&
		   getCurrentRoom().getHero().getPosition().getX() > getPosition().getX()) {
			goUpNext();
			goRightNext();
		}
		if(getCurrentRoom().getHero().getPosition().getY() < getPosition().getY() &&
		   getCurrentRoom().getHero().getPosition().getX() > getPosition().getX()) {
			goDownNext();
			goRightNext();
		}
		if(getCurrentRoom().getHero().getPosition().getY() == getPosition().getY() &&
		   getCurrentRoom().getHero().getPosition().getX() > getPosition().getX()) {
			goRightNext();
		}
		if(getCurrentRoom().getHero().getPosition().getY() == getPosition().getY() &&
		   getCurrentRoom().getHero().getPosition().getX() < getPosition().getX()) {
			goLeftNext();
		}
		if(getCurrentRoom().getHero().getPosition().getY() < getPosition().getY() &&
		   getCurrentRoom().getHero().getPosition().getX() == getPosition().getX()) {
			goDownNext();
		}
		if(getCurrentRoom().getHero().getPosition().getY() > getPosition().getY() &&
		   getCurrentRoom().getHero().getPosition().getX() == getPosition().getX()) {
			goUpNext();
		}
	}
		
		private int randomDirection() {
			Random random = new Random();
			return random.nextInt(8);
		}
		
		private int randomPause() {
			Random random = new Random();
			return random.nextInt(1000) + 1000;
		}
		
		private int randomMovement() {
			Random random = new Random();
			return random.nextInt(2);
		}
		
		private void processMovement2(int random) {
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
