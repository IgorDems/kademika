package day2;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class BattleFieldTemplate2Goal extends JPanel {

	boolean COLORDED_MODE = true;
	
	int tankX = 0;
	int tankY = 0;
	
	long speed = 225;
	
	/**
	 * Write your code here.
	 */
	void runTheGame() throws Exception {

		
		moveToQuadrantXY("a","1");
		repaint();
		Thread.sleep(2000);
		moveToQuadrantXY("b","3");
		repaint();
		Thread.sleep(2000);
		moveToQuadrantXY("a","2");
		repaint();
		Thread.sleep(2000);
		moveToQuadrantXY("d","8");
		repaint();
		Thread.sleep(2000);
		moveToQuadrantXY("i","4");
		repaint();
		Thread.sleep(2000);
		moveToQuadrantXY("c","8");
		repaint();
		Thread.sleep(2000);
		
// by line thrue batle field		
//		int direction = 0;		 
//        while (direction == 0) {
//        	
//               tankX = tankX + 64;
//               repaint();// перерисовка в fps
//               Thread.sleep(speed);
//               if (tankX == 512) {
//                      direction = 1;
//                      tankY = tankY + 64;
//                      repaint();// перерисовка в fps
//                      Thread.sleep(speed);
//                      while (direction == 1) {
//                             tankX = tankX - 64;
//                             repaint();// перерисовка в fps
//                             Thread.sleep(speed);
//                             if (tankX == 0) {
//                                   direction = 0;
//                                   tankY = tankY + 64;
//                                   repaint();// перерисовка в fps
//                                   Thread.sleep(speed);
//                                   if (tankY==512&&tankX==512) { break; }
//                             }
//                      }
//               }
//        }
	}
	
	void move(int direction) {
		int step = 64;
//		int tankY;
//		int tankX;

		
		
		if ((direction == 1 && tankY == 0) || (direction == 2 && tankY >= 512)
				|| (direction == 3 && tankX == 0)
				|| (direction == 4 && tankX >= 512)) {
			System.out.println("illegal move direction:" + direction + " Y="
					+ tankY + " X=" + tankX);
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
	
	void  moveToQuadrantXY(String v, String h) {
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
	
	// Magic bellow. Do not worry about this now, you will understand everything in this course.
	// Please concentrate on your tasks only.

	final int BF_WIDTH = 576;
	final int BF_HEIGHT = 576;
	
	public static void main(String[] args) throws Exception {
		BattleFieldTemplate2Goal bf = new BattleFieldTemplate2Goal();
		bf.runTheGame();
	}

	public BattleFieldTemplate2Goal() throws Exception {
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
