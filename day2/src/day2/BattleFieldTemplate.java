/*
 * Copyright (c) 2014 kademika.com
 */
package day2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class BattleFieldTemplate extends JPanel {

	boolean COLORDED_MODE = true;

	int tankX = 0;
	int tankY = 0;

	long speed = 225;

	/**
	 * Write your code here.
	 */
	void runTheGame() throws Exception {

		/* while (true){
		long time = System.currentTimeMillis() / 1000;
		String f = String.valueOf(time);
		
*/
	
		
		moveToQuadrantXY("a","1");
		
		moveToQuadrantXY("b","3");
		
//		moveToQuadrantXY("a","2");
		
		moveToQuadrantXY("d","4");
		
		moveToQuadrantXY("i","7");
		
		moveToQuadrantXY("i","8");
		
		
//		Random r = new Random();
//		int i;
//		while (true){
//			i=r.nextInt(5);
//			if (i>0) {
//				move(i);
//				 repaint();// ����������� � fps
//	             Thread.sleep(speed);
//				
//			}
//		}
		
	}

	void move(int direction) throws InterruptedException {

		int step = 64;
//		int tankY;
//		int tankX;

		
		
		if ((direction == 1 && tankY == 0) || (direction == 2 && tankY >= 512)
				|| (direction == 3 && tankX == 0)
				|| (direction == 4 && tankX >= 512)) {
			System.out.println("illegal move direction:" + direction + " Y="
					+ tankY + " X=" + tankX);
			if (tankX>=512){
				//tankX=-64;
				direction=3;
			}
			else if (tankX<=0){
				//tankX=+64;
				direction=1;
			}
			else if (tankY<=0){
				//tankY=+64;
				direction=1;
			}
			else if (tankY>=512){
				//tankY=-64;
				direction=2;
			}
		} else if (direction == 1) {
			tankY -= step;
			System.out.println("[down move] direction:" + direction + " Y="
					+ tankY + " X=" + tankX);
		} else if (direction == 2) {
			tankY += step;
			System.out.println("[up move] direction:" + direction + " Y="
					+ tankY + " X=" + tankX);
		} else if (direction == 2) {
			tankX -= step;
			System.out.println("[left move] direction:" + direction + " Y="
					+ tankY + " X=" + tankX);
		} else if (direction == 4) {
			tankX += step;
			System.out.println("[right move] direction:" + direction + " Y="
					+ tankY + " X=" + tankX);
		}
		repaint();
		Thread.sleep(225);
		
	}
	
	static String getQuadrantXY(String v, String h) {
		int hor = Integer.valueOf(h);
		int vert = 1;
		if (v.equals("b")) {
			vert = 2;
		} else if (v.equals("c")) {
			vert = 3;
		} else if (v.equals("d")) {
			vert = 4;
		} else if (v.equals("e")) {
			vert = 5;
		} else if (v.equals("f")) {
			vert = 6;
		} else if (v.equals("g")) {
			vert = 7;
		} else if (v.equals("h")) {
			vert = 8;
		} else if (v.equals("i")) {
			vert = 9;
		}		return vert * 64 + "_" + (hor - 1) * 64;
	}
	
	void  moveToQuadrantXY(String v, String h) throws InterruptedException {
		String coordinates = getQuadrantXY(v,h);
		
		int separator = coordinates.indexOf("_");
		int y= Integer.parseInt(coordinates.substring(0, separator));
		int x= Integer.parseInt(coordinates.substring(separator+1));
		
		
		if (tankX < x) {
				while (tankX != x){
				move(4);
			}
				}
			else {	
				while (tankX != x) {
					move(3);
					}
			
				
			}
		if (tankY < y) {
			while (tankY != y){
			move(2);
		}
			}
		else {	
			while (tankY != y) {
				move(1);
				}
		
		
		}
	
	}

	// Magic bellow. Do not worry about this now, you will understand everything
	// in this course.
	// Please concentrate on your tasks only.

	final int BF_WIDTH = 576;
	final int BF_HEIGHT = 576;

	public static void main(String[] args) throws Exception {
		BattleFieldTemplate bf = new BattleFieldTemplate();
		bf.runTheGame();
	}

	public BattleFieldTemplate() throws Exception {
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
