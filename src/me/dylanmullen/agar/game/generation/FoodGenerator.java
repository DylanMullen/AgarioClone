package me.dylanmullen.agar.game.generation;

import org.joml.Random;
import org.joml.Vector3f;

import me.dylanmullen.agar.game.ecs.EntityFactory;
import me.dylanmullen.agar.game.map.Terrain;

public class FoodGenerator
{

//	private Terrain terrain;

	public FoodGenerator(Terrain terrain)
	{
	}

	public void generateFood()
	{
		Random random = new Random();
		float x = -10 + random.nextFloat() * (10 - -10);
		float y = -10 + random.nextFloat() * (10 - -10);
		EntityFactory.createFoodEntity(new Vector3f(x, 1f, y));
	}

}
