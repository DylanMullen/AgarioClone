package me.dylanmullen.agar.game.ecs;

import org.joml.Vector3f;

import me.dylanmullen.agar.game.ecs.components.ControlComponent;
import me.dylanmullen.agar.game.ecs.components.HealthComponent;
import me.dylanmullen.agar.game.ecs.components.PositionComponent;
import me.dylanmullen.agar.game.ecs.components.RenderComponent;
import me.dylanmullen.agar.graphics.opengl.Model;
import me.dylanmullen.agar.graphics.opengl.Shader;
import me.dylanmullen.agar.graphics.opengl.VAOFactory;

public class EntityFactory
{

	private EntityFactory()
	{
	}

	public static Entity createPlayerEntity(Vector3f position)
	{
		Entity entity = new Entity();
		PositionComponent positionComponent = new PositionComponent(position);
		entity.addComponent(positionComponent);
		
		Shader shader = new Shader("terrain.vert", "terrain.frag");
		
		entity.addComponent(new RenderComponent(shader, new Model(VAOFactory.createSquare(), 2f), positionComponent));
		entity.addComponent(new ControlComponent(positionComponent));
		entity.addComponent(new HealthComponent(30));
		return entity;
	}

}
