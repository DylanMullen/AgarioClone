package me.dylanmullen.agar.graphics.opengl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryStack;

public class Shader
{

	private String vert;
	private String frag;

	private int shaderID;

	public Shader(String vertPath, String fragPath)
	{
		this.vert = load(vertPath);
		this.frag = load(fragPath);

		create();
	}

	private String load(String path)
	{
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		try
		{
			reader = new BufferedReader(
					new FileReader(new File(getClass().getClassLoader().getResource(path).toURI())));
			String len = "";
			while ((len = reader.readLine()) != null)
			{
				builder.append(len + "\n");
			}
		} catch (IOException | URISyntaxException e)
		{
			e.printStackTrace();
		} finally
		{
			if (reader != null)
				try
				{
					reader.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
		}
		return builder.toString();
	}

	public void create()
	{
		this.shaderID = GL20.glCreateProgram();
		int vertID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
		int fragID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);

		GL20.glShaderSource(vertID, vert);
		GL20.glShaderSource(fragID, frag);
		GL20.glCompileShader(vertID);
		GL20.glCompileShader(fragID);

		GL20.glAttachShader(shaderID, vertID);
		GL20.glAttachShader(shaderID, fragID);
		GL20.glLinkProgram(shaderID);
		GL20.glValidateProgram(shaderID);

		GL20.glDeleteShader(vertID);
		GL20.glDeleteShader(fragID);
	}

	public void start()
	{
		GL20.glUseProgram(shaderID);
	}

	public void stop()
	{
		GL20.glUseProgram(0);
	}

	public void bindAttrib(int location, String variable)
	{
		GL20.glBindAttribLocation(shaderID, location, variable);
	}

	public int getUniformVariable(String name)
	{
		return GL20.glGetUniformLocation(shaderID, name);
	}

	public void setVector3f(String uniform, Vector3f vec)
	{
		GL20.glUniform3f(getUniformVariable(uniform), vec.x, vec.y, vec.z);
	}

	public void setProjectionMatrix(Matrix4f proj)
	{
		try (MemoryStack stack = MemoryStack.stackPush())
		{
			FloatBuffer fb = proj.get(stack.mallocFloat(16));
			GL20.glUniformMatrix4fv(getUniformVariable("projectionMatrix"), false, fb);
		}
	}

	public void setTransformationMatrix(Matrix4f trans)
	{
		try (MemoryStack stack = MemoryStack.stackPush())
		{
			FloatBuffer fb = trans.get(stack.mallocFloat(16));
			GL20.glUniformMatrix4fv(getUniformVariable("modelMat"), false, fb);
		}
	}

	public void setViewMatrix(Matrix4f trans)
	{
		try (MemoryStack stack = MemoryStack.stackPush())
		{
			FloatBuffer fb = trans.get(stack.mallocFloat(16));
			GL20.glUniformMatrix4fv(getUniformVariable("viewMat"), false, fb);
		}
	}

}
