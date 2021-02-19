package me.dylanmullen.agar.game.generation;

import java.util.Random;

import org.joml.Vector3f;

import me.dylanmullen.agar.game.GameController;
import me.dylanmullen.agar.game.ecs.Entity;
import me.dylanmullen.agar.game.ecs.EntityFactory;
import me.dylanmullen.agar.game.map.Terrain;

public class FoodGenerator
{

	private Terrain terrain;

	private Random random;
	private int currentFood;

	public FoodGenerator(Terrain terrain)
	{
		this.terrain = terrain;
		this.random = new Random();
	}

	private void spawnFood()
	{
		float x = -terrain.getUsableWidth()
				+ random.nextFloat() * (terrain.getUsableWidth() - -terrain.getUsableWidth());
		float y = -terrain.getUsableWidth()
				+ random.nextFloat() * (terrain.getUsableWidth() - -terrain.getUsableWidth());

		Entity entity = EntityFactory.createFoodEntity(new Vector3f(x, 0.5f,y));
		GameController.getInstance().getEntityHandler().addEntity(entity);
		currentFood++;
	}

	public void generateFood()
	{
		if (currentFood >= 1000)
			return;

		spawnFood();
	}

}
