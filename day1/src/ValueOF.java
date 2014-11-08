public class ValueOF {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double value = 26.6;
		String strValue = String.valueOf(value);
		int dotIndex = strValue.indexOf(".");

		System.out.println(strValue.substring(0, dotIndex));
		System.out.println(strValue.substring(dotIndex + 1));
	}

}
