package me.dylanmullen.agar.game.ecs;

import org.joml.Vector2f;
import org.joml.Vector3f;

import me.dylanmullen.agar.game.GameController;
import me.dylanmullen.agar.game.collision.CircleCollision;
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

		RenderComponent render = new RenderComponent(shader, new Model(VAOFactory.createSquare(), 1f),
				positionComponent);
		render.addProperty("playerColour", new Vector3f(1, 0, 1));

		entity.addComponent(render);
		entity.addComponent(new CollisionComponent(entity.getUUID(), positionComponent,
				new CircleCollision(new Vector2f(position.x, position.z), 0.5f, true)));
		entity.addComponent(new ControlComponent(positionComponent));
		entity.addComponent(new HealthComponent(30));

		for (Component component : entity.getComponents())
			component.load();

		return entity;
	}

	public static Entity createFoodEntity(Vector3f position)
	{
		Entity entity = new Entity();
		PositionComponent positionComponent = new PositionComponent(position);
		entity.addComponent(positionComponent);

		Shader shader = GameController.getInstance().getRenderSystem().getShaders().createShader("foodShader",
				"food/food.vert", "food/food.frag");

		RenderComponent render = new RenderComponent(shader, new Model(VAOFactory.createSquare(), 0.6f),
				positionComponent);
		render.addProperty("entityPosition", positionComponent.getPosition());
		render.setAttribTimeRotate(true);
		entity.addComponent(render);

		CircleCollision col = new CircleCollision(new Vector2f(position.x, position.z), 0.3f, true);

		entity.addComponent(new CollisionComponent(entity.getUUID(), positionComponent, col));
		entity.addComponent(new HealthComponent(1));

		for (Component component : entity.getComponents())
			component.load();

		return entity;
	}
}
