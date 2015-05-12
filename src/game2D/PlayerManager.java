package game2D;

import game2D.abstracts.*;
import game2D.collisions.*;

import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class PlayerManager extends Manager {

	public PlayerManager(Porygon bounds, int maxAmount) throws SlickException {
		super(bounds, maxAmount);
		for (int i = 0; i < maxAmount; i++) {
			entities.add(new Player());
			entities.get(i).setSpeed(.1f);
		}
		hitSound = new Sound("data/sounds/player_hurt.wav");
	}

	@Override
	public void create(Vector2f loc, Vector2f dir) {
		for (Entity temp : entities) {
			Player player = (Player) temp;
			if (count < maxCount) {
				player.create();
				player.setLoc(loc);
				player.setDir(dir);
				activeNdxs.add(ndx);
				count++;
				ndx++;
				if (ndx == maxCount)
					ndx = 0;
			}
		}
	}

	public void update(int playerNum, Vector2f dir, int dt) {
		try {
			Player player = (Player) entities.get(playerNum - 1);
			if (player.getOriginY() <= 0 && dir.y < 0)
				dir.y = 0;
			else if (player.getEndY() >= 560 && dir.y > 0)
				dir.y = 0;
			if (player.getOriginX() <= 0 && dir.x < 0)
				dir.x = 0;
			else if (player.getEndX() >= gameBounds.getWidth() && dir.x > 0)
				dir.x = 0;
			if (!player.isDead()) {
				player.update(dir, dt);
				player.updateAnimation(dir);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return;
		}
	}

	public int getHealth(int player) {
		try {
			Player temp = (Player) (entities.get(player - 1));
			return temp.getHealth();
		} catch (ArrayIndexOutOfBoundsException e) {
			return -1;
		}
	}

	public int getMissileCount(int player) {
		try {
			Player temp = (Player) (entities.get(player - 1));
			return temp.getMissileCount();
		} catch (ArrayIndexOutOfBoundsException e) {
			return -1;
		}
	}

	public void setDir(Vector2f dir) {
		for (Entity entity : entities)
			entity.setDir(dir);
	}

	public void setDir(float dirX, float dirY) {
		for (Entity entity : entities)
			entity.setDir(dirX, dirY);
	}

	public void setLoc(Vector2f loc) {
		for (Entity entity : entities)
			entity.setLoc(loc);
	}

	public void setLoc(float LocX, float LocY) {
		for (Entity entity : entities)
			entity.setDir(LocX, LocY);
	}

	public Vector2f getCenter(int player) {
		try {
			Player temp = (Player) (entities.get(player - 1));
			return new Vector2f(temp.getCenterX(), temp.getCenterY());
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public float fireX(int player) {
		try {
			return entities.get(player - 1).getCenterX();
		} catch (ArrayIndexOutOfBoundsException e) {
			return -1;
		}
	}

	public float fireY(int player) {
		try {
			return entities.get(player - 1).getCenterY();
		} catch (ArrayIndexOutOfBoundsException e) {
			return -1;
		}
	}

	public Player get(int i) {
		try {
			return (Player) entities.get(i - 1);
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public boolean areDead() {
		for (Entity player : entities) {
			if (!player.isDead()) {
				return false;
			}
			return true;
		}
		return true;
	}

	@Override
	public void displace(Manager rhs, int collisionEnum) {
		Player player;
		ArrayList<Entity> secondList;
		Vector2f dis;
		secondList = rhs.getActive();
		for (Entity entity : entities) {
			if (!entity.isDead()) {
				for (Entity second : secondList) {
					dis = Collision.intersects(entity, second);
					if (dis.x != 0 || dis.y != 0) {
						if (collisionEnum == CollisionEnum.BLOCKING) {
							entity.displace(dis);
							handleCollision(entity, collisionEnum,
									second.getStatDamage());
							rhs.handleCollision(second, collisionEnum, 0);
						} else if (collisionEnum == CollisionEnum.MEDPACK) {
							player = (Player) entity;
							if (player.isDamaged()) {
								handleCollision(entity, collisionEnum, 0);
								rhs.handleCollision(second, collisionEnum, 0);
								return;
							}
						} else {
							if (second.isDying()) {
								handleCollision(entity, collisionEnum,
										second.getStatSplashDamage());
								rhs.handleCollision(second, collisionEnum, 0);
							} else {
								handleCollision(entity, collisionEnum,
										second.getStatDamage());
								rhs.handleCollision(second, collisionEnum, 0);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void handleCollision(Entity entity, int collisionEnum, int damage) {
		Player player = (Player) entity;
		if (collisionEnum == CollisionEnum.DAMAGING && !player.isDamaged()) {
			// hitSound.stop();
			hitSound.play(1, 3);
		}
		if (collisionEnum == CollisionEnum.MEDPACK) {
			player.addHealth(20);
		}
		super.handleCollision(entity, collisionEnum, damage);
	}

	public PlayerManager(Double thisIsATest, Double otherOne)
			throws SlickException {
		super(thisIsATest);
		for (int i = 0; i < thisIsATest.intValue(); i++) {
			entities.add(new Player(otherOne));
			entities.get(i).setSpeed(.1f);
		}

	}

}
