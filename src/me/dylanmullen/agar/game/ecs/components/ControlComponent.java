package me.dylanmullen.agar.game.ecs.components;

import java.util.UUID;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import me.dylanmullen.agar.game.ecs.systems.ControlSystem;
import me.dylanmullen.agar.game.events.api.EventHandler;
import me.dylanmullen.agar.game.events.api.event.player.PlayerSplitEvent;
import me.dylanmullen.agar.window.input.InputController;
import me.dylanmullen.agar.window.input.MouseHandler;

public class ControlComponent implements Component
{

	private UUID entity;
	private PositionComponent positionComponent;

	private Vector3f velocity;

	private long lastSplit;

	public ControlComponent(UUID entity, PositionComponent position)
	{
		this.entity = entity;
		this.positionComponent = position;
		this.lastSplit = System.currentTimeMillis();

		this.velocity = new Vector3f();
	}

	@Override
	public void load()
	{
		ControlSystem.getInstance().registerComponent(this);
	}

	@Override
	public void unload()
	{
		this.positionComponent = null;
		ControlSystem.getInstance().deregisterComponent(this);
	}

	public void handleInput(InputController input)
	{
		handleMovement(input.getMouse());
		handleControls(input);
	}

	private void handleMovement(MouseHandler mouse)
	{
		this.positionComponent.setMoved(false);
		Vector2f direction = mouse.getDirectionFromCenter();
		if (direction == null)
			return;

		Vector3f movementVector = new Vector3f(direction.x, 0, direction.y);
		if (movementVector.x == 0 && movementVector.z == 0)
			return;

		this.velocity.add(movementVector.x, 0, movementVector.z);

		float x = clamp(mouse.distanceFromCenter() / 100, 1) * 0.2f;
		this.velocity.mul(x);
		move(movementVector);
	}

	private void handleControls(InputController input)
	{
		if (input.getKeyboard().wasPressed(GLFW.GLFW_KEY_SPACE) && System.currentTimeMillis() - lastSplit >= 200)
		{
			lastSplit = System.currentTimeMillis();
			EventHandler.getInstance()
					.fireEvent(new PlayerSplitEvent(entity, input.getMouse().getDirectionFromCenter()));
		}
	}

	private void move(Vector3f moveVector)
	{
		this.positionComponent.changePosition(velocity);
	}

	private float clamp(float value, float maximum)
	{
		return (value < maximum ? value : maximum);
	}

	public PositionComponent getPositionComponent()
	{
		return positionComponent;
	}
}
