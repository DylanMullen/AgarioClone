package me.dylanmullen.agar.game.ecs;

import org.joml.Vector2f;
import org.joml.Vector3f;

import me.dylanmullen.agar.game.GameController;
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

		Shader shader = GameController.getInstance().getRenderSystem().getShaders().createShader("playerShader",
				"player/player.vert", "player/player.frag");

		SquareCollision col = getSquareCollision(position, 1f);

		RenderComponent render = new RenderComponent(shader, new Model(VAOFactory.createSquare(), 4f),
				positionComponent);
		render.addProperty("playerColour", new Vector3f(1, 0, 1));

		entity.addComponent(render);
		entity.addComponent(new CollisionComponent(entity.getUUID(), positionComponent, col));
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

		Shader shader = GameController.getInstance().getRenderSystem().getShaders().createShader("foodShader",
				"food/food.vert", "food/food.frag");
		
		RenderComponent render = new RenderComponent(shader, new Model(VAOFactory.createSquare(), 0.6f), positionComponent);
		render.addProperty("entityPosition",positionComponent.getPosition());
		entity.addComponent(render);

		SquareCollision square = getSquareCollision(position, 0.75f);

		entity.addComponent(new CollisionComponent(entity.getUUID(), positionComponent, square));
		entity.addComponent(new HealthComponent(1));

		for (Component component : entity.getComponents())
			component.load();

		return entity;
	}
}
