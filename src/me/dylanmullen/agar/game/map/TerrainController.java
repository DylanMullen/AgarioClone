package me.dylanmullen.agar.game.map;

import me.dylanmullen.agar.game.GameController;
import me.dylanmullen.agar.game.ecs.Entity;
import me.dylanmullen.agar.game.ecs.components.PositionComponent;
import me.dylanmullen.agar.game.generation.FoodGenerator;

public class TerrainController
{

	private Terrain terrain;
	private FoodGenerator foodGenerator;

	public TerrainController()
	{
		this.terrain = new Terrain(16, 16);
		this.foodGenerator = new FoodGenerator(terrain);
	}

	public void update()
	{
		updateLoadedChunks();
		foodGenerator.generateFood();
	}

	private void updateLoadedChunks()
	{
		Entity controlledEntity = GameController.getInstance().getEntityHandler().getFocusedEntity();
		if (!checkFocusEntity(controlledEntity))
			return;

		PositionComponent position = (PositionComponent) controlledEntity.getComponent(PositionComponent.class);
		terrain.loadSurroundingChunks(position.getPosition());
//		terrain.unloadChunks(position.getPosition());
	}

	private boolean checkFocusEntity(Entity entity)
	{
		if (entity == null)
			return false;
		return entity.getComponent(PositionComponent.class) != null;
	}

	public Terrain getTerrain()
	{
		return terrain;
	}
}
