package dany.oneflower.libs;

import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import dany.oneflower.Main;
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
			if (o.score == 75)
			{
				try
				{
					FileOutputStream out = new FileOutputStream(new File(System.getenv("APPDATA") + "\\OneFlower\\HardModeLock.data"));
					out.write(getSignedBytes((byte)0));
					out.close();
					Main.hardModeUnlocked = true;
					Main.updateHardModeButton();
				}
				catch (Throwable t)
				{
					t.printStackTrace();
				}
			}
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
	
	public static void createAppData()
	{
		File f = new File(System.getenv("APPDATA") + "\\OneFlower");
		if (!f.exists() || !f.isDirectory())
		{
			f.mkdirs();
		}
		
		String[] filenames = new String[]
				{
					"HardModeLock.data"
				};
		for (String i : filenames)
		{
			File j = new File(f, i);
			if (!j.exists())
			{
				try
				{
					j.createNewFile();
				}
				catch (Throwable t)
				{
					t.printStackTrace();
					System.exit(-1);
				}
			}
		}
	}
	
	public static byte[] getSignedBytes(byte first)
	{
		try
		{
			byte[] data = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
			byte[] result = new byte[data.length + 1];
			result[0] = first;
			for (int i = 0; i < data.length; i++)
			{
				result[i + 1] = data[i]; 
			}
			return result;
		}
		catch (Throwable t)
		{
			t.printStackTrace();
			System.exit(-1);
			return null;
		}
	}
}