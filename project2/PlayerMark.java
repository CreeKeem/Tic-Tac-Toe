package project2;

public enum PlayerMark {
	X("X"), 
	O("O"), 
	DASH("-");
	
	private String mark;
	PlayerMark(String mark){
		this.mark = mark;
	}
	public String getMark(){
		return mark;
	}
}

