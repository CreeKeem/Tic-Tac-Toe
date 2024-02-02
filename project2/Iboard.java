package project2;


public interface Iboard {
	
	 void print();
	 void reset();
	 PlayerMark getMark(int row, int col);
	 boolean makeMove(PlayerMark player, int row, int col);
	 void setSize(int row, int col);
	 int getColSize();
	 int getRowSize();
	 String getName();
	 boolean isFull();
	 PlayerMark[][] getCurrentBoard();
	 public String toString();

}

