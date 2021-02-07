package me.dylanmullen.agar.game.collision;

import org.joml.Vector2f;

public interface Collision
{

	public boolean collide(Object target);

	public void updatePositions(Vector2f movementVector);
}
