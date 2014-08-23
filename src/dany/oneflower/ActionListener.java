package dany.oneflower;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dany.oneflower.OneFlower.GameMode;
import dany.oneflower.libs.Helper;

public class ActionListener implements java.awt.event.ActionListener
{
	public static final ActionListener instance = new ActionListener();
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ("bStartCasual".equals(e.getActionCommand()))
		{
			Main.frameMM.setVisible(false);
			Main.currentGame = new OneFlower(GameMode.CASUAL);
			Main.currentGame.start();
		}
		else if ("bStartHard".equals(e.getActionCommand()))
		{
			Main.frameMM.setVisible(false);
			Main.currentGame = new OneFlower(GameMode.HARD);
			Main.currentGame.start();
		}
		else if ("bAbout".equals(e.getActionCommand()))
		{
			JOptionPane.showMessageDialog(new JFrame(), Helper.arrayToString("\n", Refs.ABOUT), "About", JOptionPane.INFORMATION_MESSAGE);
		}
		else if ("bExit".equals(e.getActionCommand()))
		{
			System.exit(0);
		}
	}
}