package me.dylanmullen.agar.game.ecs.components;

import org.joml.Vector2f;
import org.joml.Vector3f;

import me.dylanmullen.agar.game.ecs.systems.ControlSystem;
import me.dylanmullen.agar.window.input.InputController;

public class ControlComponent implements Component
{

	private PositionComponent positionComponent;

	public ControlComponent(PositionComponent position)
	{
		this.positionComponent = position;
	}

	@Override
	public void load()
	{
		ControlSystem.getInstance().registerComponent(this);
	}

	@Override
	public void unload()
	{
		ControlSystem.getInstance().deregisterComponent(this);
		this.positionComponent = null;
	}

	public void handleInput(InputController input)
	{
		this.positionComponent.setMoved(false);
		Vector2f direction = input.getMouse().getDirectionFromCenter();
		Vector3f movementVector = new Vector3f(direction.x, 0, direction.y);
//		if (keyboard.isPressed(GLFW.GLFW_KEY_W))
//			movementVector.add(0f, 0f, -0.25f);
//		if (keyboard.isPressed(GLFW.GLFW_KEY_A))
//			movementVector.add(-0.5f, 0f, 0f);
//		if (keyboard.isPressed(GLFW.GLFW_KEY_S))
//			movementVector.add(0f, 0f, 0.5f);
//		if (keyboard.isPressed(GLFW.GLFW_KEY_D))
//			movementVector.add(0.5f, 0f, 0f);
//

		if (movementVector.x == 0 && movementVector.y == 0 && movementVector.z == 00)
			return;
		move(movementVector);
	}

	private void move(Vector3f moveVector)
	{
		this.positionComponent.changePosition(moveVector);
	}

	public PositionComponent getPositionComponent()
	{
		return positionComponent;
	}
}
