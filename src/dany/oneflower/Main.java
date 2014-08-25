package dany.oneflower;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import dany.oneflower.libs.Helper;

public class Main
{
	public static final Font font = new Font("Georgia", Font.PLAIN, 20);
	
	public static OneFlower currentGame;
	public static JFrame frameMM;
	
	public static boolean hardModeUnlocked = false;
	public static JButton bStartCasual;
	public static JButton bStartHard;
	
	public static void main(String[] args)
	{
		Helper.createAppData();
		try
		{
			if (Arrays.equals(Files.readAllBytes(new File(System.getenv("APPDATA") + "\\OneFlower\\HardModeLock.data").toPath()), Helper.getSignedBytes((byte)0)))
			{
				hardModeUnlocked = true;
			}
		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
		
		frameMM = new JFrame();
		frameMM.setTitle("OneFlower");
		frameMM.setLocationRelativeTo(null);
		frameMM.setSize(384, 384);
		frameMM.setResizable(false);
		frameMM.setLayout(null);
		frameMM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMM.setVisible(true);
		
		bStartCasual = new JButton();
		bStartCasual.setText("Casual");
		bStartCasual.setToolTipText("Timer is always 10 seconds");
		bStartCasual.setBounds(frameMM.getWidth() / 2 - 90, 70, 89, 40);
		bStartCasual.setActionCommand("bStartCasual");
		bStartCasual.addActionListener(ActionListener.instance);
		frameMM.add(bStartCasual);
		
		bStartHard = new JButton();
		bStartHard.setText("Hard");
		bStartHard.setBounds(frameMM.getWidth() / 2 + 1, 70, 89, 40);
		bStartHard.setActionCommand("bStartHard");
		bStartHard.addActionListener(ActionListener.instance);
		updateHardModeButton();
		frameMM.add(bStartHard);
		
		JButton bAbout = new JButton();
		bAbout.setText("About");
		bAbout.setBounds(frameMM.getWidth() / 2 - 90, 130, 180, 40);
		bAbout.setActionCommand("bAbout");
		bAbout.addActionListener(ActionListener.instance);
		frameMM.add(bAbout);
		
		JButton bExit = new JButton();
		bExit.setText("Exit");
		bExit.setBounds(frameMM.getWidth() / 2 - 90, 190, 180, 40);
		bExit.setActionCommand("bExit");
		bExit.addActionListener(ActionListener.instance);
		frameMM.add(bExit);
		
		for (int i = 0; i < 12; i++)
		{
			for (int j = 0; j < 12; j++)
			{
				JLabel l = new JLabel();
				l.setBounds(i * 32, j * 32, 32, 32);
				l.setIcon(ImageInitializer.imageBackground);
				frameMM.add(l);
			}
		}
		frameMM.repaint();
	}
	
	public static void updateHardModeButton()
	{
		if (hardModeUnlocked)
		{
			bStartCasual.setBorder(new LineBorder(Color.GRAY, 1));
			bStartHard.setEnabled(true);
			bStartHard.setToolTipText("Timer starts with 10 seconds and decreasing over time!");
		}
		else
		{
			bStartCasual.setBorder(new LineBorder(new Color(0x3366ff), 4));
			bStartHard.setEnabled(false);
			bStartHard.setToolTipText("Get 75 points of score in Casual Mode to unlock Hard Mode!");
		}
	}
}