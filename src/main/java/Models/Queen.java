package Models;

public class Queen extends Piece {

    public Queen(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    @Override
    public boolean isValidMove(int destRow, int destCol, Piece[][] board) {
        Rook rook = new Rook(super.isWhite(), super.getRow(), super.getCol());
        Bishop bishop = new Bishop(super.isWhite(), super.getRow(), super.getCol());
        if(rook.isValidMove(destRow, destCol, board) || bishop.isValidMove(destRow, destCol, board)) {
            return true;
        }
        return false;
    }
}
