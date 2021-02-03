package me.dylanmullen.agar.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class BufferUtil
{

	public static FloatBuffer toFloatBuffer(float[] data)
	{
		FloatBuffer buffer = ByteBuffer.allocateDirect(data.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
		buffer.put(data).flip();
		return buffer;
	}

	public static IntBuffer toIntBuffer(int[] array)
	{
		IntBuffer result = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
		result.put(array).flip();
		return result;
	}

}
