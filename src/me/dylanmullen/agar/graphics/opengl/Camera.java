package me.dylanmullen.agar.graphics.opengl;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import me.dylanmullen.agar.window.input.InputController;
import me.dylanmullen.agar.window.input.KeyboardHandler;
import me.dylanmullen.agar.window.input.MouseHandler;

public class Camera
{
	private Vector3f position;

	private Vector2f pitchYaw;
	private KeyboardHandler keyboard;
	private MouseHandler mouse;

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

	public void update()
	{
		moved = false;
		movedChunk = false;
		handleInputs();
	}

	private void handleInputs()
	{
//		if (keyboard.isPressed(GLFW.GLFW_KEY_SPACE))
//			move(new Vector3f(0f, 0.5f, 0f));
//		if (keyboard.isPressed(GLFW.GLFW_KEY_LEFT_SHIFT))
//			move(new Vector3f(0f, -0.5f, 0f));
		if (keyboard.isPressed(GLFW.GLFW_KEY_D))
			move(new Vector3f(0.5f, 0f, 0f));
		if (keyboard.isPressed(GLFW.GLFW_KEY_A))
			move(new Vector3f(-0.5f, 0f, 0f));
		if (keyboard.isPressed(GLFW.GLFW_KEY_S))
			move(new Vector3f(0f, 0f, 0.5f));
		if (keyboard.isPressed(GLFW.GLFW_KEY_W))
			move(new Vector3f(0f, 0f, -0.5f));
	}

	public Matrix4f getViewMatrix()
	{
		Matrix4f matrix = new Matrix4f();
		matrix.identity();
		matrix.rotate((float) Math.toRadians(pitchYaw.y), new Vector3f(1, 0, 0));
		matrix.rotate((float) Math.toRadians(pitchYaw.x), new Vector3f(0, 1, 0));
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

}
