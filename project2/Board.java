package project2;

public class Board implements Iboard{

    private Box[][] boxes;
    private int rows;
    private int cols;

    public Board(int rows, int cols) {
        setSize(rows, cols);
        reset();
    }

    @Override
    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boxes[i][j].print();
            }
            System.out.println();
        }
    }

    @Override
    public void reset() {
        boxes = new Box[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boxes[i][j] = new Box(i, j);
            }
        }
    }

    @Override
    public PlayerMark getMark(int row, int col) {
        return boxes[row][col].getPlaceHolder();
    }

    @Override
    public boolean makeMove(PlayerMark player, int row, int col) {
        return boxes[row][col].setPlaceHolder(player);
    }

    @Override
    public void setSize(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    @Override
    public int getColSize() {
        return cols;
    }

    @Override
    public int getRowSize() {
        return rows;
    }

    @Override
    public String getName() {
        return "Tic Tac Toe Board";
    }

    @Override
    public boolean isFull() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (boxes[i][j].isAvailable()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public PlayerMark[][] getCurrentBoard() {
        PlayerMark[][] currentBoard = new PlayerMark[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                currentBoard[i][j] = boxes[i][j].getPlaceHolder();
            }
        }
        return currentBoard;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append("\n");
        sb.append("Size: ").append(rows).append("x").append(cols).append("\n");
        sb.append("Current state:").append("\n");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(boxes[i][j].getPlaceHolder().getMark()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

