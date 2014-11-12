package TanksObj;

public class BattleField {
	final int BF_WIDTH = 576;
	final int BF_HEIGHT = 576;

	// 1 - top, 2 - bottom, 3 - left, 4 - right
	

	String[][] battleField = { { " ", "B", "B", "B", "B", "B", "B", "B", " " },
			{ " ", " ", " ", " ", " ", " ", " ", " ", " " },
			{ " ", "B", "B", " ", "B", " ", "B", "B", " " },
			{ " ", "B", "B", " ", " ", " ", "B", "B", " " },
			{ " ", "B", "B", " ", "B", " ", "B", "B", " " },
			{ " ", "B", " ", " ", "B", "B", " ", "B", " " },
			{ " ", "B", " ", " ", " ", " ", " ", "B", " " },
			{ " ", " ", " ", "B", "B", "B", " ", " ", " " },
			{ " ", " ", " ", "B", "B", "B", " ", " ", " " } };

}
