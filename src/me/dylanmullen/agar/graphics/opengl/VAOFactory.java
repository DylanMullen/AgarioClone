package me.dylanmullen.agar.graphics.opengl;

import me.dylanmullen.agar.util.BufferUtil;

public class VAOFactory
{

	public static VAO createSquare()
	{
		float[] verts =
		{
				-0.5f, 0, 0.5f, //
				0.5f, 0, 0.5f, //
				-0.5f, 0, -0.5f, //
				0.5f, 0, -0.5f
		};

		int[] indices =
		{
				0, 1, 2, 2, 1, 3
		};

		float[] textureCoords =
		{
				0, 1, //
				1, 1, //
				0, 0, //
				1, 0
		};

		VAO vao = new VAO();
		vao.bind();
		vao.storeVertices(BufferUtil.toFloatBuffer(verts), verts.length / 3);
		vao.storeIndicesBuffer(indices);
		vao.storeTextureCoords(BufferUtil.toFloatBuffer(textureCoords));
		vao.unbind();
		return vao;
	}

	public static VAO createVerticalRectangle()
	{
		float[] verts =
		{
				-0.5f, 0, 1, //
				0.5f, 0, 1, //
				-0.5f, 0, -1, //
				0.5f, 0, -1
		};
		int[] indices =
		{
				0, 1, 2, 2, 1, 3
		};
		VAO vao = new VAO();
		vao.bind();
		vao.storeVertices(BufferUtil.toFloatBuffer(verts), verts.length / 3);
		vao.storeIndicesBuffer(indices);
		vao.unbind();
		return vao;
	}

}
