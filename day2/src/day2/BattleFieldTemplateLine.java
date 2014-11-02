package day2;
/*
 * Copyright (c) 2014 kademika.com
 */


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class BattleFieldTemplateLine extends JPanel {

	boolean COLORDED_MODE = true;
	
	int tankX = 0;
	int tankY = 0;
	
	long speed = 225;
	
	/**
	 * Write your code here.
	 */
	void runTheGame() throws Exception {
		int direction = 0;
		
		 
        while (direction == 0) {
               tankX = tankX + 64;
               repaint();// перерисовка в fps
               Thread.sleep(speed);
               if (tankX == 512) {
                      direction = 1;
                      while (direction == 1) {
                             tankX = tankX - 64;
                             repaint();// перерисовка в fps
                             Thread.sleep(speed);
                             if (tankX == 0) {
                                   direction = 0;
                             }
                      }
               }
        }
	}
	
	void move(int direction) {
		
	}

	
	// Magic bellow. Do not worry about this now, you will understand everything in this course.
	// Please concentrate on your tasks only.

	final int BF_WIDTH = 576;
	final int BF_HEIGHT = 576;
	
	public static void main(String[] args) throws Exception {
		BattleFieldTemplateLine bf = new BattleFieldTemplateLine();
		bf.runTheGame();
	}

	public BattleFieldTemplateLine() throws Exception {
		JFrame frame = new JFrame("BATTLE FIELD, DAY 2");
		frame.setLocation(500, 150);
		frame.setMinimumSize(new Dimension(BF_WIDTH, BF_HEIGHT + 22));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int i = 0;
		Color cc;
		for (int v = 0; v < 9; v++) {
			for (int h = 0; h < 9; h++) {
				if (COLORDED_MODE) {
					if (i % 2 == 0) {
						cc = new Color(252, 241, 177);
					} else {
						cc = new Color(233, 243, 255);
					}
				} else {
					cc = new Color(180, 180, 180);
				}
				i++;
				g.setColor(cc);
				g.fillRect(h * 64, v * 64, 64, 64);
			}
		}
		
		g.setColor(new Color(255, 0, 0));
		g.fillRect(tankX, tankY, 64, 64);
	}

}
