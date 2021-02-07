package me.dylanmullen.agar.game.ecs;

import org.joml.Vector2f;
import org.joml.Vector3f;

import me.dylanmullen.agar.game.collision.SquareCollision;
import me.dylanmullen.agar.game.ecs.components.CollisionComponent;
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

		Shader shader = new Shader("circle.vert", "circle.frag");

		entity.addComponent(new RenderComponent(shader, new Model(VAOFactory.createSquare(), 2f), positionComponent));
		entity.addComponent(
				new CollisionComponent(positionComponent, new SquareCollision(new Vector2f(0, 1), new Vector2f(1, 0))));
		entity.addComponent(new ControlComponent(positionComponent));
		entity.addComponent(new HealthComponent(30));
		return entity;
	}

	public static Entity createFoodEntity(Vector3f position)
	{
		Entity entity = new Entity();
		PositionComponent positionComponent = new PositionComponent(position);
		entity.addComponent(positionComponent);

		Shader shader = new Shader("circle.vert", "circle.frag");
		entity.addComponent(
				new RenderComponent(shader, new Model(VAOFactory.createVerticalRectangle(), 1f), positionComponent));

		return entity;
	}
}
