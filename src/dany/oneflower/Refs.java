package dany.oneflower;

import dany.oneflower.libs.Helper;

public class Refs
{
	public static final String VERSION = "b1.0-1";
	
	public static final String[] AUTHORS = new String[]
			{
				"CatDany"
			};
	
	public static final String[] ABOUT = new String[]
			{
				"OneFlower " + VERSION,
				"Made by " + Helper.arrayToString(", ", AUTHORS)
			};
	
	public static final String[] GAME_OVER = new String[]
			{
				"G A M E   O V E R",
				"Score: %s",
				"You didn't eat flower fast enough!"
			};
}