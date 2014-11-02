public class Countdown {

	public static void main(String[] args) {

		start(20);
		start(-20);

	}

	static void start(int number) {

		if (number < 0) {
			System.out.println("Start filed");
		} else {
			while (number > 0) {

				System.out.println(number);
				number -= 1;
			}
		}

		if (number == 0) {
			System.out.println("Start");
		}
		// return number
	}

}
