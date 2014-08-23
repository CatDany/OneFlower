package dany.oneflower.libs;

import java.util.Random;

import dany.oneflower.OneFlower;
import dany.oneflower.OneFlower.GameMode;

public class Helper
{
	public static String arrayToString(String separator, String[] array)
	{
		String str = "";
		for (String i : array)
		{
			str += i + separator;
		}
		str = str.substring(0, str.length() - separator.length());
		return str;
	}
	
	// mX, mY = "Moved to X", "Moved to Y" or "Motion X", "Motion Y"
	
	public static boolean isMovePossible(int mX, int mY)
	{
		return !(mX < 0 || mX > 11 || mY < 0 || mY > 11);
	}
	
	public static void moveIfPossible(OneFlower o, int mX, int mY)
	{
		if (isMovePossible(mX, mY))
		{
			o.x = mX;
			o.y = mY;
		}
	}
	
	public static void replaceFlower(OneFlower o)
	{
		Random random = new Random();
		int x = random.nextInt(12);
		int y = random.nextInt(12);
		if ((x == o.x && y == o.y) || (x == o.fx && y == o.fy))
		{
			replaceFlower(o);
			return;
		}
		o.fx = x;
		o.fy = y;
		
		o.msFlower = System.currentTimeMillis();
	}
	
	public static void eatFlowerIfCollided(OneFlower o)
	{
		if (o.x == o.fx && o.y == o.fy)
		{
			o.score++;
			replaceFlower(o);
		}
	}
	
	public static int getMaxTimerFromScore(GameMode mode, int score)
	{
		if (mode == GameMode.CASUAL)
		{
			return 10000;
		}
		else
		{
			if (score < 100)
			{
				return 10000;
			}
			else if (score < 150)
			{
				return 7000;
			}
			else if (score < 200)
			{
				return 5000;
			}
			else if (score < 250)
			{
				return 4000;
			}
			else if (score < 300)
			{
				return 3000;
			}
			else
			{
				return 2000;
			}
		}
	}
}