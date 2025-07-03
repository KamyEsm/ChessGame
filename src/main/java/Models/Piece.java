package Models;

public abstract class Piece {
    protected boolean isWhite;
    protected int row, col;

    public Piece(boolean isWhite, int row, int col) {
        this.isWhite = isWhite;
        this.row = row;
        this.col = col;
    }

    public abstract boolean isValidMove(int destRow, int destCol, Piece[][] board);

    public boolean isWhite() {
        return isWhite;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }

    public void moveTo(int newRow, int newCol) {
        this.row = newRow;
        this.col = newCol;
    }
}