package me.dylanmullen.agar.game.events;

import org.joml.Vector2f;
import org.joml.Vector3f;

import me.dylanmullen.agar.game.GameController;
import me.dylanmullen.agar.game.ecs.Entity;
import me.dylanmullen.agar.game.ecs.EntityFactory;
import me.dylanmullen.agar.game.ecs.components.HealthComponent;
import me.dylanmullen.agar.game.ecs.components.PositionComponent;
import me.dylanmullen.agar.game.ecs.components.RenderComponent;
import me.dylanmullen.agar.game.events.api.EventHandler;
import me.dylanmullen.agar.game.events.api.EventListener;
import me.dylanmullen.agar.game.events.api.Listener;
import me.dylanmullen.agar.game.events.api.event.collision.EntityCollideEvent;
import me.dylanmullen.agar.game.events.api.event.player.PlayerEatEvent;
import me.dylanmullen.agar.game.events.api.event.player.PlayerSplitEvent;

public class PlayerListener implements Listener
{

	private Entity player;

	public PlayerListener(Entity player)
	{
		this.player = player;
	}

	@EventListener
	public void onCollision(EntityCollideEvent event)
	{
		if (event.getOwner() == null)
			return;
		if (event.getInterceptor() == null)
			return;

		Entity interceptor = GameController.getInstance().getEntityHandler().getEntity(event.getInterceptor());
		if (interceptor == null)
			return;

		if (interceptor.hasComponent(HealthComponent.class))
			EventHandler.getInstance().fireEvent(new PlayerEatEvent(player, interceptor));
	}

	@EventListener
	public void onPlayerEat(PlayerEatEvent event)
	{
		float nutrition = clamp(event.getEatableHealth().getHealth() * 0.4f, 1f);
		event.getPlayerHealth().incrementHealth(nutrition);

		((RenderComponent) event.getPlayer().getComponent(RenderComponent.class)).getModel()
				.incrementScale(nutrition / 50f);

		event.getToEat().kill();
	}

	@EventListener
	public void onPlayerSplit(PlayerSplitEvent event)
	{
		if (event.getEntity() == null)
			return;

		PositionComponent component = (PositionComponent) event.getEntity().getComponent(PositionComponent.class);
		Vector2f direction = event.getDirection().mul(5);
		Vector3f acceleration = new Vector3f(component.getPosition());
		acceleration.add(direction.x, 0, direction.y);

		Entity entity = EntityFactory.createPlayerSplit(
				new Vector3f(component.getPosition().x + 2, 0, component.getPosition().z + 2), acceleration, 10f);

		GameController.getInstance().getEntityHandler().addEntity(entity);
	}

	private float clamp(float value, float minimum)
	{
		return (value > minimum ? value : minimum);
	}

}
