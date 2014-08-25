package dany.oneflower;

import java.util.ArrayList;
import java.util.Random;

public class World
{
	private static World world;
	
	public final ArrayList<Coords> flowers = new ArrayList<Coords>();
	
	public final int seed;
	public final Random random;
	public boolean isWorldGenerated = false;
	
	public World(int seed)
	{
		this.seed = seed;
		this.random = new Random(seed);
	}
	
	public static void setWorld(World world)
	{
		World.world = world;
	}
	
	public static World getWorld()
	{
		return world;
	}
	
	public boolean isFlower(int x, int y)
	{
		for (Coords i : flowers)
		{
			if (i.x == x && i.y == y)
			{
				return true;
			}
		}
		return false;
	}
	
	public Thread generateTerrain()
	{
		Thread t = new Thread("World-Generator")
		{
			@Override
			public void run()
			{
				for (int i = -5000; i < 5000; i++)
				{
					for (int j = -5000; j < 5000; j++)
					{
						if (random.nextInt(16) == 0)
						{
							flowers.add(new Coords(i, j));
						}
					}
				}
				World.getWorld().isWorldGenerated = true;
			}
		};
		t.start();
		return t;
	}
}