package me.dylanmullen.agar.game.generation;

import org.joml.Random;
import org.joml.Vector3f;

import me.dylanmullen.agar.game.ecs.Entity;
import me.dylanmullen.agar.game.ecs.EntityFactory;
import me.dylanmullen.agar.game.ecs.components.RenderComponent;
import me.dylanmullen.agar.game.ecs.systems.RenderSystem;
import me.dylanmullen.agar.game.map.Terrain;

public class FoodGenerator
{

	private Terrain terrain;

	public FoodGenerator(Terrain terrain)
	{
	}

	public void generateFood()
	{
		Random random = new Random();
		float x = -10 + random.nextFloat() * (10 - -10);
		float y = -10 + random.nextFloat() * (10 - -10);
		System.out.println(x + ":" + y);
		Entity entity = EntityFactory.createFoodEntity(new Vector3f(x, 1f, y));
		RenderSystem.getRenderComponents().add((RenderComponent) entity.getComponent(RenderComponent.class));
		System.out.println("test");
	}

}
