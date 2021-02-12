package me.dylanmullen.agar.graphics.opengl;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import me.dylanmullen.agar.game.ecs.Entity;
import me.dylanmullen.agar.game.ecs.components.PositionComponent;
import me.dylanmullen.agar.window.input.InputController;
import me.dylanmullen.agar.window.input.KeyboardHandler;
import me.dylanmullen.agar.window.input.MouseHandler;

public class Camera
{
	private Vector3f position;

	private Vector2f pitchYaw;
	private KeyboardHandler keyboard;
	private MouseHandler mouse;

	private Entity focusedEntity;
	private boolean focus;

	private boolean moved;
	private boolean movedChunk;

	public Camera(InputController input)
	{
		this.position = new Vector3f(0, 0, 0);
		this.pitchYaw = new Vector2f(0, 90);
		this.keyboard = input.getKeyboard();
		this.mouse = input.getMouse();
	}

	public void move(Vector3f vec)
	{
		position.add(vec);
		moved = true;
	}

	public void rotate()
	{
		this.pitchYaw.add(0.5f, 0);
	}

	public void followEntity()
	{
		PositionComponent entityPosition = (PositionComponent) focusedEntity.getComponent(PositionComponent.class);
		if (entityPosition == null)
		{
			focus = false;
			focusedEntity = null;
			return;
		}
		position.set(entityPosition.getPosition().x, entityPosition.getPosition().y, entityPosition.getPosition().z);
	}

	public void focusEntity(Entity entity)
	{
		this.focusedEntity = entity;
		this.focus = true;
	}

	public void unfocusEntity()
	{
		this.focus = false;
		this.focusedEntity = null;
	}

	public void update()
	{
		moved = false;
		movedChunk = false;
		if (focus)
			followEntity();
		else
			handleInputs();
	}

	private void handleInputs()
	{
		Vector3f movementVector = new Vector3f();

		if (keyboard.isPressed(GLFW.GLFW_KEY_D))
			movementVector.add(0.25f, 0f, 0f);
		if (keyboard.isPressed(GLFW.GLFW_KEY_A))
			movementVector.add(-0.5f, 0f, 0f);
		if (keyboard.isPressed(GLFW.GLFW_KEY_S))
			movementVector.add(0f, 0f, 0.5f);
		if (keyboard.isPressed(GLFW.GLFW_KEY_W))
			movementVector.add(0f, 0f, -0.5f);

		move(movementVector);
	}

	public Matrix4f getViewMatrix()
	{
		Matrix4f matrix = new Matrix4f();
		matrix.identity();
		matrix.rotate((float) Math.toRadians(pitchYaw.y), new Vector3f(1, 0, 0));
		matrix.rotate((float) Math.toRadians(pitchYaw.x), new Vector3f(0, 1, 0));
//		matrix.scale(10, 1, 10);
		matrix.translate(-position.x, -position.y, -position.z);
		return matrix;
	}

	public Vector3f getPosition()
	{
		return position;
	}

	public boolean hasMoved()
	{
		return moved;
	}

	public boolean hasMovedChunk()
	{
		return movedChunk;
	}

	public boolean isFocused()
	{
		return focus;
	}

}
