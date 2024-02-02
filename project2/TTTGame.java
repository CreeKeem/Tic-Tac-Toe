package project2;

public class TTTGame implements TicTacToe {
	  private Board board;
	  public APlayer player1;
	  public APlayer player2;
	  public APlayer currentPlayer;

	  public TTTGame() {
	    this.player1 = new ComputerPlayer(PlayerMark.X);
	    this.player2 = new ComputerPlayer(PlayerMark.O);
	    this.board = new Board(3, 3);
	    this.currentPlayer = player1;
	  }
	  
	  @Override
	  public boolean move(int r, int c) {
	    if (board.makeMove(currentPlayer.getPlayerMark(), r, c)) {
	      // The move was successful, switch players
	      currentPlayer = (currentPlayer == player1) ? player2 : player1;
	      return true;
	    }
	    // The move was invalid, don't switch players
	    return false;
	  }
	  
	  
	  public boolean move() {
	    if (board.makeMove(currentPlayer.getPlayerMark(), currentPlayer.selectRowValue(3), currentPlayer.selectColValue(3))) {
	      // The move was successful, switch players
	      currentPlayer = (currentPlayer == player1) ? player2 : player1;
	      return true;
	    }
	    // The move was invalid, don't switch players
	    return false;
	  }
	  

	  @Override
	  public PlayerMark getTurn() {
	    return currentPlayer.getPlayerMark();
	  }

	  @Override
	  public boolean isGameOver() {
	    return board.isFull() || getWinner() != null;
	  }

	  @Override
	  public PlayerMark getWinner() {
	    PlayerMark[][] currentBoard = board.getCurrentBoard();
	    int rows = currentBoard.length;
	    int cols = currentBoard[0].length;

	    // Check rows
	    for (int r = 0; r < rows; r++) {
	      if (currentBoard[r][0] != PlayerMark.DASH && currentBoard[r][0] == currentBoard[r][1] && currentBoard[r][1] == currentBoard[r][2]) {
	        return currentBoard[r][0];
	      }
	    }

	    // Check columns
	    for (int c = 0; c < cols; c++) {
	      if (currentBoard[0][c] != PlayerMark.DASH && currentBoard[0][c] == currentBoard[1][c] && currentBoard[1][c] == currentBoard[2][c]) {
	        return currentBoard[0][c];
	      }
	    }

	    // Check diagonals
	    if (currentBoard[0][0] != PlayerMark.DASH && currentBoard[0][0] == currentBoard[1][1] && currentBoard[1][1] == currentBoard[2][2]) {
	      return currentBoard[0][0];
	    }
	    if (currentBoard[0][2] != PlayerMark.DASH && currentBoard[0][2] == currentBoard[1][1] && currentBoard[1][1] == currentBoard[2][0]) {
	      return currentBoard[0][2];
	    }

	    // No winner yet
	    return null;
	  }

	  @Override
	  public PlayerMark[][] getBoard() {
	    return board.getCurrentBoard();
	  }

	  @Override
	  public PlayerMark getMarkAt(int r, int c) {
	    return board.getMark(r, c);
	  }

	  
	  public void setSize(int rows, int cols) {
	    board.setSize(rows, cols);
	  }

	  
	  public int getRowSize() {
	    return board.getRowSize();
	  }

	  
	  public int getColSize() {
	    return board.getColSize();
	  }

	  
	  public String getName() {
	    return "Tic Tac Toe";
	  }

	  
	  public void print() {
	    board.print();
	  }

	  
	  public boolean isFull() {
	    return board.isFull();
	  }

	  
	  public String toString() {
	    return "Current board:\n" + board;
	  }
}
