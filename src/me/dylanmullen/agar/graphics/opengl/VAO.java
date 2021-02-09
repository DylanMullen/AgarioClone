package me.dylanmullen.agar.graphics.opengl;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import me.dylanmullen.agar.util.BufferUtil;

public class VAO
{

	private int vaoID;
	private int count;

	private List<Integer> vbos;

	public VAO()
	{
		this.vaoID = glGenVertexArrays();
		this.vbos = new ArrayList<Integer>();
	}

	public void delete()
	{
		GL30.glDeleteVertexArrays(vaoID);

		vbos.stream().forEach(e -> GL30.glDeleteBuffers(e));
	}

	public void bind()
	{
		glBindVertexArray(vaoID);
	}

	public void unbind()
	{
		glBindVertexArray(0);
	}

	public void storeData(int attrib, int size, FloatBuffer buffer)
	{
		int vboID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attrib, size, GL11.GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		vbos.add(vboID);
	}

	public void storeVertices(FloatBuffer buffer, int count)
	{
		storeData(0, 3, buffer);
		this.count = count;
	}

	public void storeTextureCoords(FloatBuffer buffer)
	{
		storeData(1, 2, buffer);
	}

	public void storeIndicesBuffer(int[] indices)
	{
		int vboID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = BufferUtil.toIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		this.count = indices.length;
		vbos.add(vboID);
	}

	public int getVaoID()
	{
		return vaoID;
	}

	public int getCount()
	{
		return count;
	}
}
