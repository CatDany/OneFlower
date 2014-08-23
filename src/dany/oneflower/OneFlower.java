package dany.oneflower;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dany.oneflower.libs.Helper;

public class OneFlower implements Runnable, KeyListener
{
	public final JFrame frame;
	public final JLabel character;
	public final JLabel flower;
	public final JLabel showInfo;
	
	public final GameMode mode;
	public int score = 0;
	public CharRotation rotation = CharRotation.DOWN;
	public int x = 0;
	public int y = 0;
	public int fx = 0;
	public int fy = 0;
	public long msFlower = 0;
	
	public OneFlower(GameMode mode)
	{
		this.mode = mode;
		
		frame = new JFrame();
		frame.setTitle("OneFlower - Playing - Score: 0");
		frame.setLocationRelativeTo(null);
		frame.setSize(390, 410);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		frame.addKeyListener(this);
		
		showInfo = new JLabel();
		showInfo.setFont(Main.font);
		showInfo.setBounds(0, frame.getHeight() - 50, frame.getWidth(), 25);
		frame.add(showInfo);
		
		character = new JLabel();
		character.setIcon(ImageInitializer.imageCharacterDown);
		character.setBounds(0, 0, 32, 32);
		frame.add(character);
		
		flower = new JLabel();
		flower.setIcon(ImageInitializer.imageFlower);
		flower.setBounds(0, 0, 32, 32);
		frame.add(flower);
		
		// Overdraw.. Oops. Totally not a bug.
		for (int i = 0; i < 16; i++)
		{
			for (int j = 0; j < 16; j++)
			{
				JLabel l = new JLabel();
				l.setBounds(i * 32, j * 32, 32, 32);
				l.setIcon(ImageInitializer.imageBackground);
				frame.add(l);
			}
		}
	}
	
	@Override
	public void run()
	{
		Helper.replaceFlower(this);
		msFlower = System.currentTimeMillis();
		
		while (Main.currentGame == this)
		{
			character.setBounds(x * 32, y * 32, 32, 32);
			character.setIcon(rotation.icon);
			
			flower.setBounds(fx * 32, fy * 32, 32, 32);
			
			int maxTimer = Helper.getMaxTimerFromScore(mode, score);
			int timer = (int)((msFlower + maxTimer - System.currentTimeMillis()) / 1000);
			showInfo.setText("Score: " + score + " | Timer: " + timer);
			frame.setTitle("One Flower - Playing - Score: " + score + " - Game Over In: " + timer);
			
			if (msFlower + maxTimer - System.currentTimeMillis() <= 0)
			{
				Main.currentGame = null;
				frame.setVisible(false);
				Main.frameMM.setVisible(true);
				JOptionPane.showMessageDialog(new JFrame(), String.format(Helper.arrayToString("\n", Refs.GAME_OVER), score), "Oops", JOptionPane.WARNING_MESSAGE);
			}
			
			frame.repaint();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			this.rotation = CharRotation.UP;
			Helper.moveIfPossible(this, x, y - 1);
			Helper.eatFlowerIfCollided(this);
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			this.rotation = CharRotation.DOWN;
			Helper.moveIfPossible(this, x, y + 1);
			Helper.eatFlowerIfCollided(this);
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			this.rotation = CharRotation.LEFT;
			Helper.moveIfPossible(this, x - 1, y);
			Helper.eatFlowerIfCollided(this);
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			this.rotation = CharRotation.RIGHT;
			Helper.moveIfPossible(this, x + 1, y);
			Helper.eatFlowerIfCollided(this);
		}
	}
	
	public void start()
	{
		new Thread(this).start();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	public static enum CharRotation
	{
		UP(ImageInitializer.imageCharacterUp),
		DOWN(ImageInitializer.imageCharacterDown),
		LEFT(ImageInitializer.imageCharacterLeft),
		RIGHT(ImageInitializer.imageCharacterRight);
		
		public final ImageIcon icon;
		
		private CharRotation(ImageIcon icon)
		{
			this.icon = icon;
		}
	}
	
	public static enum GameMode
	{
		CASUAL,
		HARD;
	}
}