import java.util.Arrays;

public class Dim {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] data0 = { 1, 2, 3, 4, 5, 6, 7, 8 };
		int[] data1 = { 0 };
		int[] data2 = {};
		int[] data3 = { 22, 33, 44, 55, 66 };
		int[] data4 = { 11, 33 };
		int[] data5 = { 22, 33, 44, 55, 66 };

//		System.out.println(getLast(data0));
//		System.out.println(getLast(data1));
//		System.out.println(getLast(data2));
//		System.out.println(getLast(data3));
//		System.out.println(getLast(data4));
//		System.out.println(getLast(data5));
//
//		System.out.println(swap1(data0, 3, 4));
//		System.out.println(swap1(data1, 3, 4));
//		System.out.println(swap1(data2, 6, 5));
//		System.out.println(swap1(data3, 6, 5));
//		System.out.println(swap1(data4, 1, 2));
//		System.out.println(swap1(data5, 1, 5));
//		//
//		System.out.println(printArray(data0));
//		System.out.println(printArray(data1));
//		System.out.println(printArray(data2));
//		System.out.println(printArray(data3));
//		System.out.println(printArray(data4));
//		System.out.println(printArray(data5));
//
//		System.out.println(oddElementSum(data0));
//		System.out.println(oddElementSum(data1));
//		System.out.println(oddElementSum(data2));
//		System.out.println(oddElementSum(data3));
//		System.out.println(oddElementSum(data4));
//		System.out.println(oddElementSum(data5));
//
//		System.out.println(awerage(data0));
//		System.out.println(awerage(data1));
//		System.out.println(awerage(data2));
//		System.out.println(awerage(data3));
//		System.out.println(awerage(data4));
//		System.out.println(awerage(data5));
		
		System.out.println(Arrays.toString(data0));
		System.out.println(Arrays.toString(data1));
		System.out.println(Arrays.toString(data2));
		System.out.println(Arrays.toString(data3));
		System.out.println(Arrays.toString(data4));
		System.out.println(Arrays.toString(data5));

	}

	static int getLast(int[] data) {
		System.out.println("[----getLast------------------------]");
		if (data.length > 0) {
			return data[data.length - 1];
		}

		return -1;
	}

	static int swap1(int[] data, int i, int k) {
		System.out.println("[--------swap1------------------------]");
		int j = data.length;
		int s = 0;
		if (i <= j && k <= j) {

			int temp1 = data[i - 1];
			int temp2 = data[k - 1];
			System.out.println(data[i - 1]);
			System.out.println(data[k - 1]);
			data[i - 1] = temp2;
			data[k - 1] = temp1;
			System.out.print("[");
			while (s <= (j - 1)) {

				System.out.print(data[s] + ",");
				s += 1;
			}
			System.out.print("]");
			System.out.println("");
		}

		return -1;
	}

	static int printArray(int[] data) {
		System.out.println("[-------printArray------------------------]");
		// int j = data.length;
		// int s = 0;
		if (data != null) {
			System.out.println(Arrays.toString(data));
			// return ;
		} else
			System.out.println("[]");
		// return -1;

		return 0;
	}

	static long oddElementSum(int[] data) {
		System.out.println("[----oddElementSum------------------------]");
		long sum = 0;
		// int s = 0;
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				if (i % 2 < 1) {
					sum += data[i];
				}
			}

		}

		return sum;

	}

	static double awerage(int[] data) {
		System.out
				.println("[---------------------awerage------------------------]");

		// int s = 0;
		if (data == null || data.length == 0) {
			return -1;
		}
		double sum1 = 0;
		for (int i : data) {
			sum1 += i;
		}
		return sum1 / data.length;
	}

	static void swap(int[] data) {
		System.out.println("[---------------------swap------------------------]");
		int tmp;

		if (data != null) {

			for (int i = 0; i < data.length - 1; i++) {
				tmp = data[i];
				data[i] = data[i + 1];
				data[i + 1] = tmp;
			}

		}
	}
}
