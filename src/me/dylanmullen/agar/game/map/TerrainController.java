package me.dylanmullen.agar.game.map;

import me.dylanmullen.agar.game.ecs.Entity;
import me.dylanmullen.agar.game.ecs.components.PositionComponent;

public class TerrainController
{

	private Terrain terrain;

	public TerrainController()
	{
		this.terrain = new Terrain(128, 16);
	}

	public void handleControlledEntity(Entity controlledEntity)
	{
		if (controlledEntity.getComponent(PositionComponent.class) == null)
			return;

		PositionComponent position = (PositionComponent) controlledEntity.getComponent(PositionComponent.class);
		terrain.loadChunk(position.getPosition());
	}
	
	public Terrain getTerrain()
	{
		return terrain;
	}
}
