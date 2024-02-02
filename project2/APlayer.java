package project2;

public abstract class APlayer {

	private PlayerMark mark;
	
	public APlayer( PlayerMark mark) {
		setPlayerMark(mark);
	}

	public PlayerMark getPlayerMark() {
		return mark;
	}
	public void setPlayerMark(PlayerMark mark) {
		this.mark = mark;
	}
	
	public abstract int selectRowValue(int range);
	
	public abstract int selectColValue(int range);
	
}

