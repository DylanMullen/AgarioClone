package me.dylanmullen.agar.core;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import me.dylanmullen.agar.graphics.opengl.Shader;
import me.dylanmullen.agar.graphics.opengl.VAO;
import me.dylanmullen.agar.util.BufferUtil;

public class Circle
{

	VAO vao = new VAO();

	public Circle()
	{
		float verts[] =
		{
				-0.5f, 0.5f, 0, // Top Left
				0.5f, 0.5f, 0, // TOPrIGHT
				-0.5f, -0.5f, 0, // Bottom Left
				0.5f, -0.5f, 0,// Bottom Right
		};
		int index[] =
		{
				0, 1, 2, 1, 2, 3
		};
		vao.bind();
		vao.storeVertices(BufferUtil.toFloatBuffer(verts), verts.length / 3);
		vao.storeIndicesBuffer(index);
		vao.unbind();
	}

	// projectionMatrix * viewMat * modelMat *

	public void renderCircle()
	{
		GL30.glBindVertexArray(vao.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL11.glDrawElements(GL11.GL_TRIANGLES, vao.getCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}

}
