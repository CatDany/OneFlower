package dany.oneflower.libs;

import dany.oneflower.OneFlower;
import dany.oneflower.World;

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
		return !(mX < -5000 || mX > 4999 || mY < -5000 || mY > 4999) && !World.getWorld().isFlower(mX, mY);
	}
	
	public static void moveIfPossible(OneFlower o, int mX, int mY)
	{
		if (isMovePossible(mX, mY))
		{
			o.x = mX;
			o.y = mY;
		}
	}
}