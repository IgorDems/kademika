
public class HomeWork1Last{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "Education is most powerful weapon which you can use to change the world.";
		String t = "An investment in knowledge pays the best interest.";

		System.out.print(s.substring(s.indexOf("use"), s.indexOf("use") + 3)
				+ " ");
		System.out.print(t.substring(t.indexOf("knowledge"),
				t.indexOf("knowledge") + "knowledge".length())
				+ " ");
		System.out.print(s.substring(s.indexOf("to"), s.length() - 1));
	}

}
