package me.dylanmullen.agar.game.ecs;

import org.joml.Vector2f;
import org.joml.Vector3f;

import me.dylanmullen.agar.game.collision.SquareCollision;
import me.dylanmullen.agar.game.ecs.components.CollisionComponent;
import me.dylanmullen.agar.game.ecs.components.Component;
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

		SquareCollision col = getSquareCollision(position, 2f);
		
		System.out.println(col.getTopLeft().x);
		
		entity.addComponent(new RenderComponent(shader, new Model(VAOFactory.createSquare(), 2f), positionComponent));
		entity.addComponent(
				new CollisionComponent(entity.getUUID(), positionComponent, col));
		entity.addComponent(new ControlComponent(positionComponent));
		entity.addComponent(new HealthComponent(30));

		for (Component component : entity.getComponents())
			component.load();

		return entity;
	}

	private static SquareCollision getSquareCollision(Vector3f position, float scale)
	{
		Vector2f topLeft = new Vector2f(position.x - (scale / 2), position.z - (scale / 2));
		Vector2f bottomRight = new Vector2f(position.x + (scale / 2), position.z + (scale / 2));
		return new SquareCollision(topLeft, bottomRight);
	}

	public static Entity createFoodEntity(Vector3f position)
	{
		Entity entity = new Entity();
		PositionComponent positionComponent = new PositionComponent(position);
		entity.addComponent(positionComponent);

		Shader shader = new Shader("circle.vert", "circle.frag");
		entity.addComponent(new RenderComponent(shader, new Model(VAOFactory.createSquare(), 1f), positionComponent));

		SquareCollision square = getSquareCollision(position, 1f);

		entity.addComponent(new CollisionComponent(entity.getUUID(), positionComponent, square));
		entity.addComponent(new HealthComponent(10));

		for (Component component : entity.getComponents())
			component.load();

		return entity;
	}
}
