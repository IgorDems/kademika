package classTank;

public class Tank {

	/**
	 * @param args
	 */
	// public static void main(String[] args) {
	//
	// // TODO Auto-generated method stub
	//
	// }

	public String color;
	public int crew;
	public int maxSpid;

	Tank() {
		color="red";
		crew =3;
		maxSpid=70;
	}
	Tank(String color){
		this.color=color;
	}
	 Tank(String col, int i, int j) {
		this.color=col;
		this.crew=i;
		this.maxSpid=j;
	}

}
