package me.dylanmullen.agar.game.map;

import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import me.dylanmullen.agar.game.ecs.components.PositionComponent;
import me.dylanmullen.agar.game.ecs.components.RenderComponent;
import me.dylanmullen.agar.game.ecs.systems.RenderSystem;
import me.dylanmullen.agar.graphics.opengl.Model;
import me.dylanmullen.agar.graphics.opengl.VAO;
import me.dylanmullen.agar.util.BufferUtil;

public class Chunk
{

	private Vector3f chunkPosition;
	private Vector3f colour;
	private VAO chunkVAO;
	private float scale;

	private RenderComponent renderingComponent;

	public Chunk(Vector3f chunkPosition, float scale)
	{
		this.chunkPosition = chunkPosition;
		this.colour = new Vector3f((float) Math.random(), (float) Math.random(), (float) Math.random());
		this.chunkVAO = new VAO();
		this.scale = scale;
		init();
	}

	private void init()
	{
		float[] verts =
		{
				0, 0, 0, //
				1, 0, 0, //
				0, 0, -1, //
				1, 0, -1
		};

		int[] indices =
		{
				0, 1, 2, 2, 1, 3
		};

		chunkVAO.bind();
		chunkVAO.storeVertices(BufferUtil.toFloatBuffer(verts), verts.length / 3);
		chunkVAO.storeIndicesBuffer(indices);
		chunkVAO.unbind();

		this.renderingComponent = new RenderComponent(RenderSystem.shader, new Model(chunkVAO, scale),
				new PositionComponent(chunkPosition));

		RenderSystem.getRenderComponents().add(renderingComponent);
	}

	public void unload()
	{
		RenderSystem.getRenderComponents().remove(renderingComponent);
		this.chunkVAO.delete();
	}

	public Matrix4f getModelMatrix()
	{
		Matrix4f matrix = new Matrix4f();
		matrix.translate(chunkPosition);
		matrix.scale(scale, 0, scale);
		return matrix;
	}

	public Vector3f getChunkPosition()
	{
		return chunkPosition;
	}

	public VAO getChunkVAO()
	{
		return chunkVAO;
	}

	public Vector3f getColour()
	{
		return colour;
	}

}
