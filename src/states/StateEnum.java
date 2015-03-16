package states;

public enum StateEnum {
	MAINMENU(0), GAME(1), GAMEOVER(2), PAUSE(3);

	private final int id;

	private StateEnum(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}
}
