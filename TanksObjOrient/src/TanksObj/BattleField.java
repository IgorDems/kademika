package TanksObj;

public class BattleField {
	final int bfWidth = 576;
	final int bfHeight = 576;

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

	public BattleField(String[][] battleField) {
		this.battleField = battleField;
	}

	public BattleField() {
		// TODO Auto-generated constructor stub
	}

	public void updateQuadrant(int v, int h, String obgact) {

		battleField[v][h] = obgact;

	}

	public String scanQuadrant(int v, int h) {
		return battleField[v][h];
	}

	public int getDimantionX() {
		return battleField.length;
	}

	public int getDimantionY() {
		return battleField.length;
	}

	public int getBFWidth() {
		return bfWidth;
	}

	public int getBFHeight() {
		return bfHeight;
	}
}
