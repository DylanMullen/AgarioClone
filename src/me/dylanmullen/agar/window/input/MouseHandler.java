package me.dylanmullen.agar.window.input;

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
	private Vector2f mousePosition;
	private Vector2f lastPosition;

	public MouseHandler(long windowRef)
	{
		this.windowRef = windowRef;
		this.mousePosition = new Vector2f();
		this.lastPosition=new Vector2f();
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
				if (mousePosition.x != xpos || mousePosition.y != ypos)
				{
					if(lastPosition.x!=xpos || lastPosition.y !=ypos)
						lastPosition.set(mousePosition.x,mousePosition.x);
					mousePosition.set(xpos, ypos);
				}
			}
		});
	}
	
	public boolean isPressed(int code)
	{
		Button button = getMouseButton(code);
		if(button ==null)
			return false;
		else
			return button.isClicked();
	}

	private Button getMouseButton(int code)
	{
		try
		{
			return buttons.stream().filter(e -> e.getButtonCode() == code).findFirst().get();
		}catch(Exception e)
		{
			return null;
		}
	}

	private boolean isButton(int code)
	{
		return buttons.stream().filter(e -> e.getButtonCode() == code).findFirst().isPresent();
	}
	
	public float getXChange()
	{
		return lastPosition.x-mousePosition.x;
	}
	public float getYChange()
	{
		return lastPosition.y-mousePosition.y;
	}
}
