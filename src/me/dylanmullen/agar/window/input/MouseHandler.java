package me.dylanmullen.agar.window.input;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;
import org.lwjgl.glfw.GLFWMouseButtonCallbackI;

public class MouseHandler
{

	private long windowRef;

	private List<Button> buttons;
	private Vector2f center;
	private Vector2f mousePosition;
	private Vector2f lastPosition;
	private Dimension windowDimensions;

	public MouseHandler(Dimension windowDimensions, long windowRef)
	{
		this.windowDimensions = windowDimensions;
		this.windowRef = windowRef;
		this.center = new Vector2f(windowDimensions.width / 2, windowDimensions.height / 2);
		this.mousePosition = new Vector2f();
		this.lastPosition = new Vector2f();
		this.buttons = new ArrayList<Button>();
		init();
	}

	private void init()
	{
		GLFW.glfwSetMouseButtonCallback(windowRef, new GLFWMouseButtonCallbackI()
		{

			@Override
			public void invoke(long window, int button, int action, int mods)
			{
				if (!isButton(button))
				{
					buttons.add(new Button(button));
				}
				getMouseButton(button).setClicked(action);
			}
		});

		GLFW.glfwSetCursorPosCallback(windowRef, new GLFWCursorPosCallbackI()
		{
			@Override
			public void invoke(long window, double xpos, double ypos)
			{
				if (lastPosition.x != xpos || lastPosition.y != ypos)
					lastPosition.set(mousePosition.x, mousePosition.x);
				mousePosition = mousePosition.set(xpos, ypos);
			}
		});
	}

	public Vector2f convertToOpenGLCoords()
	{
		float oX = (float) (mousePosition.x * 2) / windowDimensions.width - 1;
		float oY = (float) (mousePosition.y * 2) / windowDimensions.height - 1;
		return new Vector2f(oX, oY);
	}

	public boolean isPressed(int code)
	{
		Button button = getMouseButton(code);
		if (button == null)
			return false;
		else
			return button.isClicked();
	}

	private Button getMouseButton(int code)
	{
		try
		{
			return buttons.stream().filter(e -> e.getButtonCode() == code).findFirst().get();
		} catch (Exception e)
		{
			return null;
		}
	}

	private boolean isButton(int code)
	{
		return buttons.stream().filter(e -> e.getButtonCode() == code).findFirst().isPresent();
	}

	public Vector2f getLastPosition()
	{
		return lastPosition;
	}

	public Vector2f getDirectionFromCenter()
	{
		Vector2f position = new Vector2f(mousePosition);
		position.sub(center);
		position.normalize();
		return position;
	}

	public Vector2f getDirectionFrom(Vector2f vector)
	{
		Vector2f position = new Vector2f(mousePosition);
		position.sub(vector);
		position.normalize();
		return position;
	}

	public float distanceFromCenter()
	{
		return center.distance(mousePosition);
	}

	public Vector2f center()
	{
		return new Vector2f(0, -0);
	}

	public float getXChange()
	{
		return lastPosition.x - mousePosition.x;
	}

	public float getYChange()
	{
		return lastPosition.y - mousePosition.y;
	}

	public Vector2f getMousePosition()
	{
		return mousePosition;
	}
}
