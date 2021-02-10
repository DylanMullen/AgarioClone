package me.dylanmullen.agar.game.generation;

import org.joml.Random;
import org.joml.Vector2f;
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
	
	private void test(Vector2f pos)
	{
		Entity entity = EntityFactory.createFoodEntity(new Vector3f(pos.x, 1f, pos.y));
		GameController.getInstance().getEntityHandler().addEntity(entity);
		currentFood++;
	}

	public void generateFood()
	{
		if (currentFood >= 2)
			return;
		test(new Vector2f(10,0));
	}

}
