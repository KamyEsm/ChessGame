package Models;

public class King extends Piece {
    public King(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    @Override
    public boolean isValidMove(int destRow, int destCol, Piece[][] board) {
        if(destRow >= 8 || destRow < 0 || destCol >= 8 || destCol < 0)
            return false;
        int RowNum = Math.abs(destRow-super.getRow());
        int ColNum = Math.abs(destCol-super.getCol());
        if(RowNum>1 || ColNum>1){
            return false;
        }
        if(board[destRow][destCol]!=null && board[destRow][destCol].isWhite()==super.isWhite()){
            return false;
        }
        return true;
    }
}
