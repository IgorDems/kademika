public class Move {

	/**
	 * @param args
	 */
	 static void main(String[] args) {
		 
		 move(4);
	 }
		
		static void move( int direction) {
			
			
			
	        int step = 64;
	         int tankY=512;
			 int tankX=0;
			
			 
			if ((direction == 1 && tankY == 0) || (direction == 2 && tankY >= 512)
	                      || (direction == 3 && tankX == 0)
	                      || (direction == 4 && tankX >= 512)) {
	               System.out.println("illegal move direction:" + direction + " Y="
	                             + tankY + " X=" + tankX);
	        }
	        if (direction == 1) {
	               tankY -= step;
	               System.out.println("down move direction:" + direction + " Y="
	                             + tankY + " X=" + tankX);
	        } else if (direction == 2) {
	               tankY += step;
	               System.out.println("up move direction:" + direction + " Y=" + tankY
	                             + " X=" + tankX);
	        } else if (direction == 2) {
	               tankX -= step;
	               System.out.println("left move direction:" + direction + " Y="
	                             + tankY + " X=" + tankX);
	        } else if (direction == 4) {
	               tankX += step;
	               System.out.println("right move direction:" + direction + " Y="
	                             + tankY + " X=" + tankX);
	        }
	  }
		
		// TODO Auto-generated method stub

	}
	
	

