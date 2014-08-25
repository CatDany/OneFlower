package dany.oneflower;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class Main
{
	public static final Font font = new Font("Georgia", Font.PLAIN, 20);
	
	public static OneFlower currentGame;
	public static JFrame frameMM;
	public static JFrame frameLoading;
	
	public static void main(String[] args)
	{
		frameMM = new JFrame();
		frameMM.setTitle("OneFlower [Exploration!]");
		frameMM.setLocationRelativeTo(null);
		frameMM.setSize(384, 384);
		frameMM.setResizable(false);
		frameMM.setLayout(null);
		frameMM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMM.setVisible(true);
		
		JButton bStart = new JButton();
		bStart.setText("Start Exploring");
		bStart.setToolTipText("Start a new world for exploring!");
		bStart.setBounds(frameMM.getWidth() / 2 - 90, 70, 89, 40);
		bStart.setActionCommand("bStart");
		bStart.addActionListener(ActionListener.instance);
		frameMM.add(bStart);
		
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
}