package dany.oneflower;

import dany.oneflower.libs.Helper;

public class Refs
{
	public static final String VERSION_STD = "b1.0";
	public static final int VERSION_BUILD = 5;
	public static final String VERSION = VERSION_STD + "-" + VERSION_BUILD;
	
	public static final String VERSION_CHECKER_URL = "https://raw.githubusercontent.com/CatDany/OneFlower/master/LATEST";
	public static final String UPDATE_URL = "https://github.com/CatDany/OneFlower/releases";
	
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
	
	public static final String[] OUTDATED = new String[]
			{
				"New version of this game is available!",
				"Do you want to update right now?"
			};
}