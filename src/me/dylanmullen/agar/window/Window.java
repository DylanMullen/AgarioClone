package me.dylanmullen.agar.window;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.stackPush;

import java.awt.Dimension;
import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import me.dylanmullen.agar.window.input.InputController;

public class Window
{

	private long windowReference;

	private String title;
	private Dimension dimensions;
	
	private InputController input;

	public Window(String title, Dimension dim)
	{
		this.title = title;
		this.dimensions = dim;
	}

	private void setup()
	{
		GLFWErrorCallback.createPrint(System.err).set();
		if (!GLFW.glfwInit())
			return; // TODO throw error

		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_TRUE);
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);

		this.windowReference = GLFW.glfwCreateWindow(dimensions.width, dimensions.height, title, MemoryUtil.NULL,
				MemoryUtil.NULL);

		try (MemoryStack stack = stackPush())
		{
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			glfwGetWindowSize(this.windowReference, pWidth, pHeight);

			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			glfwSetWindowPos(this.windowReference, (vidmode.width() - pWidth.get(0)) / 2,
					(vidmode.height() - pHeight.get(0)) / 2);
		}
		
		this.input=new InputController(windowReference);
	}

	public void createWindow()
	{
		setup();
		GLFW.glfwMakeContextCurrent(this.windowReference);
		glfwShowWindow(this.windowReference);
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public Dimension getDimensions()
	{
		return dimensions;
	}
	
	public long getWindowReference()
	{
		return windowReference;
	}
	
	public InputController getInputController()
	{
		return input;
	}
	
}
