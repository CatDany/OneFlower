package dany.oneflower;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dany.oneflower.libs.Helper;

public class ActionListener implements java.awt.event.ActionListener
{
	public static final ActionListener instance = new ActionListener();
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ("bStart".equals(e.getActionCommand()))
		{
			Main.frameMM.setVisible(false);
			String seedStr = null;
			while (seedStr == null)
			{
				seedStr = JOptionPane.showInputDialog(new JFrame(), "Enter a seed for your world", "Seed Selection", JOptionPane.QUESTION_MESSAGE);
			}
			int seedInt = seedStr.hashCode();
			
			Main.currentGame = new OneFlower();
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