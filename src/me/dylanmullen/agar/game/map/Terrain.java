package me.dylanmullen.agar.game.map;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector3f;

public class Terrain
{

	private float terrainWidth;
	private float chunkWidth;

	private List<Chunk> chunks;

	public Terrain(float width, float chunkWidth)
	{
		this.terrainWidth = width;
		this.chunkWidth = chunkWidth;
		this.chunks = new ArrayList<Chunk>();
	}

	public Chunk loadChunk(Vector3f position)
	{
		float coordX = getChunkCoord(position.x);
		float coordZ = getChunkCoord(position.z);
		Vector3f chunkPosition = new Vector3f(coordX, 0, coordZ);

		Chunk chunk = getChunkFromPosition(chunkPosition);
		if (chunk != null)
			return chunk;
		
		chunk = new Chunk(chunkPosition, chunkWidth);
		chunks.add(chunk);
		return chunk;
	}

	public Chunk getChunkFromPosition(Vector3f position)
	{
		for (int i = 0; i < chunks.size(); i++)
		{
			Chunk chunk = chunks.get(i);
			if (chunk.getChunkPosition().equals(position))
				return chunk;
		}
		return null;
	}

	public float getChunkCoord(float input)
	{
		return (int) (input / chunkWidth) * chunkWidth - (input < 0 ? chunkWidth : 0);
	}

}
