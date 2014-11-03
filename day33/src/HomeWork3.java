
public class HomeWork3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// TODO Auto-generated method stub
System.out.println(findElement(new double[] {1.0,2,3,5,6,7}, 1.0));
System.out.println(findElement(new double[] {4,2.0,3,1.0,6,7}, 1.0));
	}

	
	static int findElement (double [] numbers, double el ){
		if (numbers !=null) {
			for (int i = 0;i<numbers.length;i++)
				if (numbers[i]==el){
					return i;
				}
				}
		return -1;
		
	}

}
