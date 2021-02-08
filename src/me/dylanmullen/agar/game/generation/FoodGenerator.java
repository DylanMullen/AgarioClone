package me.dylanmullen.agar.game.generation;

import org.joml.Random;
import org.joml.Vector3f;

import me.dylanmullen.agar.game.GameController;
import me.dylanmullen.agar.game.ecs.Entity;
import me.dylanmullen.agar.game.ecs.EntityFactory;
import me.dylanmullen.agar.game.map.Terrain;

public class FoodGenerator
{

//	private Terrain terrain;

	private int currentFood;

	public FoodGenerator(Terrain terrain)
	{
	}

	public void generateFood()
	{
		if (currentFood >= 1)
			return;

		Random random = new Random();
		float x = 10; // -10 + random.nextFloat() * (10 - -10);
		float y = 10;
		Entity entity = EntityFactory.createFoodEntity(new Vector3f(x, 1f, y));
		GameController.getInstance().getEntityHandler().addEntity(entity);
		currentFood++;
	}

}
