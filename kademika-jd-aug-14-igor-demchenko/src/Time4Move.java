
public class Time4Move {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long time = System.currentTimeMillis() / 4000;
		  
		  String f = String.valueOf(time);
		  int v = f.charAt(f.length()-1);
		  while (time!=0){
			  System.out.println("time="+time);
			  System.out.println("v="+v);
			  System.out.println("f="+f);
		  }
	}

}
