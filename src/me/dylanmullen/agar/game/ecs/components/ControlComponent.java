package me.dylanmullen.agar.game.ecs.components;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import me.dylanmullen.agar.window.input.KeyboardHandler;

public class ControlComponent implements Component
{

	private PositionComponent positionComponent;
	private CollisionComponent collisionComponent;

	public ControlComponent(PositionComponent position)
	{
		this.positionComponent = position;
	}

	public void handleInput(KeyboardHandler keyboard)
	{
		this.positionComponent.setMoved(false);
		
		if (keyboard.isPressed(GLFW.GLFW_KEY_W))
			move(new Vector3f(0f, 0f, -0.5f));
		if (keyboard.isPressed(GLFW.GLFW_KEY_A))
			move(new Vector3f(-0.5f, 0f, 0f));
		if (keyboard.isPressed(GLFW.GLFW_KEY_S))
			move(new Vector3f(0f, 0f, 0.5f));
		if (keyboard.isPressed(GLFW.GLFW_KEY_D))
			move(new Vector3f(0.5f, 0f, 0f));
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
