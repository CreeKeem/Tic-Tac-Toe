package project2;


class Box {

	private int row;
	private int col;
	private PlayerMark placeHolder = PlayerMark.DASH;
	
	
	Box(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	
	PlayerMark getPlaceHolder() {
		return placeHolder;
	}
	
	boolean setPlaceHolder(PlayerMark placeHolder) {
		if(isAvailable()) {
			this.placeHolder = placeHolder;
			return true;
		}
		return false;
		
	}


	boolean isAvailable() {
		return this.placeHolder.equals(PlayerMark.DASH);
	}
	
	void print() {
		System.out.print(placeHolder.getMark() + " ");
	}
	
	 String getInfo() {
		return placeHolder.getMark() + " ";
	}
	

}
