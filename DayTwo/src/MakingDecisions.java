public class MakingDecisions {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		hello("Igor");
		sum(22, 33);
		square(7.0);
		System.out.println(oldEnough(-202));
		System.out.println(oldEnough(2));
		System.out.println(oldEnough(21));
		System.out.println(min(10, 20, 30));
		System.out.println(min(10, 20, -30));
		System.out.println(minElse(10, 20, 30));
		System.out.println(minElse(10, 20, -30));
		System.out.println(ifSpring(5));
		System.out.println(ifSpring(6));
		System.out.println(ifSpring(16));

		System.out.println(getQuadrantXY("a", "8"));
		System.out.println(getQuadrant("i", "2"));

		//System.out.println(printCoordinates("i", "2"));
		//System.out.println(printCoordinates("a", "6"));
	}

	static void hello(String name) {

		System.out.println("Hi " + name);
	}

	static void sum(int num1, int num2) {

		System.out.println(num1 + num2);
	}

	static void square(double num) {

		System.out.println(num * num);
	}

	static void square(int num) {

		System.out.println(num * num);
	}

	static boolean oldEnough(int age) {
		if (age >= 21) {
			return true;
		}
		if (age < 0) {
			System.out.println("illegal result of age");

		}
		return false;

	}

	static int min(int a, int b, int c) {
		int result = a;
		if (b < result) {
			result = b;
		}
		if (c < result) {
			result = c;
		}
		return result;

	}

	static int minElse(int a, int b, int c) {
		int result = a;
		if (b <= result) {
			result = b;
		} else {
			if (c <= result)
				result = c;
		}
		return result;

	}

	static int ifSpring(int month) {

		if (month == 3 || month == 4 || month == 5) {
			return 1;
		}
		if (month >= 1 && month >= 6 && month <= 12) {
			return 0;
		} else {
			return -1;
		}
	}

	/**
	 * 
	 * @param v
	 * @param h
	 * @return
	 */
	static String getQuadrant(String v, String h) {
		int hor = Integer.valueOf(h);
		int vert = 1;
		if (v == "a") {
			vert = 0;
		}
		if (v == "b") {
			vert = 1;
		}
		if (v == "c") {
			vert = 2;
		}
		if (v == "d") {
			vert = 3;
		}
		if (v == "e") {
			vert = 4;
		}
		if (v == "f") {
			vert = 5;
		}
		if (v == "g") {
			vert = 6;
		}
		if (v == "h") {
			vert = 7;
		}
		if (v == "i") {
			vert = 8;
		}

		return vert * 64 + "_" + (hor - 1) * 64;

	}

	/**
	 * 
	 * TANKS
	 */
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
		}
		return vert * 64 + "_" + (hor - 1) * 64;
	}

	static void printCoordinates(String v, String h) {
		String coordinates = getQuadrantXY(v, h);
		String x = coordinates.substring(0, coordinates.indexOf("_"));
		String y = coordinates.substring(coordinates.indexOf("_") + 1);
		System.out.print(  x + "px" +" "+ y + "px");
	}
}
