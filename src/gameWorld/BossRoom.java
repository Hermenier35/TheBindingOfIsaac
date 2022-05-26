package gameWorld;

import gameobjects.Boss;
import gameobjects.Hero;
import libraries.Physics;
import libraries.Vector2;

public class BossRoom extends MonsterRoom {
	private Boss boss;

	public BossRoom(Hero hero) {
		super(hero);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean collision(Vector2 direction, Vector2 position, Vector2 size, double speed) {
		// TODO Auto-generated method stub
		Vector2 normalVector = new Vector2(direction);
		normalVector.euclidianNormalize(speed);
		Vector2 positionTest = position.addVector(normalVector);
		if(Physics.rectangleCollision(positionTest, size, boss.getPosition(), boss.getSize().scalarMultiplication(0.8)))
			return true;
		return false;
	}

	public Boss getBoss() {
		return boss;
	}

	public void setBoss(Boss boss) {
		this.boss = boss;
	}
}
