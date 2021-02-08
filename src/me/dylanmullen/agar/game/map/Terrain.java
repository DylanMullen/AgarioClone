package me.dylanmullen.agar.game.map;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		Vector3f chunkPosition = getChunkPosition(position);
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

	public Vector3f getChunkPosition(Vector3f position)
	{
		float coordX = getChunkCoordX(position.x);
		float coordZ = getChunkCoordZ(position.z);
		return new Vector3f(coordX, 0, coordZ);
	}

	public void unloadChunks(Vector3f currentPosition)
	{
		List<Chunk> toUnload = getChunksOutside(getChunkPosition(currentPosition));
		if (toUnload == null)
			return;
		if (toUnload.size() == 0)
			return;

		for (Chunk chunk : toUnload)
		{
			chunk.unload();
			chunks.remove(chunk);
		}
	}

	private List<Chunk> getChunksOutside(Vector3f vector)
	{
		Vector3f high = new Vector3f(vector.x - 16, 0, vector.z - 16);
		Vector3f low = new Vector3f(vector.x + 16, 0, vector.z + 16);

		return chunks.stream().filter(e -> !intersects(high, low, e.getChunkPosition())).map(e -> e)
				.collect(Collectors.toList());
	}

	public int getChunkCoordX(float input)
	{
		return (int) (input / 16f) * 16;
	}

	public int getChunkCoordZ(float input)
	{
		return (int) (input / 16f) * 16;
	}

	public void loadSurroundingChunks(Vector3f position)
	{
		loadChunk(position);
		loadSides(position);
		loadCorners(position);
	}

	private void loadSides(Vector3f chunkCoords)
	{
		loadChunk(new Vector3f(chunkCoords.x + 16, 0, chunkCoords.z));
		loadChunk(new Vector3f(chunkCoords.x - 16, 0, chunkCoords.z));
		loadChunk(new Vector3f(chunkCoords.x, 0, chunkCoords.z + 16));
		loadChunk(new Vector3f(chunkCoords.x, 0, chunkCoords.z - 16));
	}

	private void loadCorners(Vector3f chunkCoords)
	{
		loadChunk(new Vector3f(chunkCoords.x - 16, 0, chunkCoords.z - 16)); // Top left
		loadChunk(new Vector3f(chunkCoords.x - 16, 0, chunkCoords.z + 16)); // bottom left
		loadChunk(new Vector3f(chunkCoords.x + 16, 0, chunkCoords.z - 16)); // top right
		loadChunk(new Vector3f(chunkCoords.x + 16, 0, chunkCoords.z + 16)); // bottom right
	}

	private boolean intersects(Vector3f high, Vector3f low, Vector3f position)
	{
		return (position.x >= high.x && position.x <= low.x) && (position.z >= high.z && position.z <= low.z);
	}

}
