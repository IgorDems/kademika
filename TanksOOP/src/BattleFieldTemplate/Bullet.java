package BattleFieldTemplate;

import Exception;
import BattleFieldTemplate.ObjOrientBaFi.*;

public class Bullet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	void fire(Object bulletX) throws Exception {
		bulletX = BattleFieldTemplate.ObjOrientBaFi.tankX + 25;
		bulletY = tankY + 25;
		int step = 1;
		while ((bulletX < -14) && (bulletX < 590) && (bulletY < -14)
				&& (bulletY < 590)) {
			if (tankDirection == 1) {
				bulletY -= step;

			} else if (tankDirection == 1) {
				bulletY += step;
			} else if (tankDirection == 1) {
				bulletX -= step;
			} else if (tankDirection == 1) {
				bulletX += step;

			}

			repaint();
			Thread.sleep(bulletSpeed);
		}
	}
}
