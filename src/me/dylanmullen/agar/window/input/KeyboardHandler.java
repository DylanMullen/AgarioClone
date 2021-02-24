package me.dylanmullen.agar.window.input;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallbackI;

public class KeyboardHandler
{

	private long windowRef;
	private List<Key> keys;

	public KeyboardHandler(long window)
	{
		this.windowRef = window;
		this.keys = new ArrayList<Key>();
		init();
	}

	private void init()
	{
		GLFW.glfwSetKeyCallback(windowRef, new GLFWKeyCallbackI()
		{
			@Override
			public void invoke(long window, int keyCode, int scancode, int action, int mods)
			{
				Key key;
				if (getKey(keyCode) != null)
					key = getKey(keyCode);
				else
					key = createKey(keyCode);
				
				switch (action)
				{
					case GLFW.GLFW_PRESS:
						key.setPressed(true);
						break;
					case GLFW.GLFW_REPEAT:
						key.setRepeat(true);
						break;
					case GLFW.GLFW_RELEASE:
						key.setPressed(false);
						key.setRepeat(false);
						break;
				}
			}
		});
	}

	private Key createKey(int code)
	{
		Key key = new Key(code);
		keys.add(key);
		return key;
	}

	public Key getKey(int code)
	{
		try
		{
			return keys.stream().filter(e -> e.getKeycode() == code).findFirst().get();
		} catch (NullPointerException | NoSuchElementException e)
		{
			return null;
		}
	}

	public boolean wasPressed(int keyCode)
	{
		Key key = getKey(keyCode);
		return (key == null ? false : key.isPressed());
	}

	public boolean isRepeated(int keyCode)
	{
		Key key = getKey(keyCode);
		return (key == null ? false : key.isRepeat());
	}
}
