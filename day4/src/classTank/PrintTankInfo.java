package classTank;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class PrintTankInfo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tank[] tanks = new Tank[5];

		tanks[1] = new Tank("gold", 3, 100);
		tanks[2] = new Tank("silver", 2, 110);
		tanks[3] = new Tank("green", 1, 120);
		tanks[4] = new Tank("purpl", 4, 80);
		tanks[0] = new Tank("red", 1, 100);

		// classTank.Tank t = new Tank(); ==
		Tank t = new Tank();
		Tank t2 = new Tank("gold");

		// t.color="green";
		System.out.println(t.color);
		System.out.println(t.crew);
		System.out.println(t.maxSpid);
		System.out.println(t2.color);
		
		System.out.println(tanks[1]);
	
		int i = 0;
		
		while (i <= 4) {
			System.out.println(tanks[i]);//������ �� ������� �������
			
			
			i++;
		}

	}

}
