package me.dylanmullen.agar.game.ecs.components;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import me.dylanmullen.agar.game.ecs.systems.ControlSystem;
import me.dylanmullen.agar.window.input.InputController;
import me.dylanmullen.agar.window.input.MouseHandler;

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
		movementVector.mul(0.05f);
		if (movementVector.x == 0 && movementVector.y == 0 && movementVector.z == 00)
			return;
		move(movementVector);
	}

	private void handleControls(InputController input)
	{
		if (input.getKeyboard().isPressed(GLFW.GLFW_KEY_SPACE))
			System.out.println("pressed space");
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
