package Models;

public class Rook extends Piece {

    public Rook(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    @Override
    public boolean isValidMove(int destRow, int destCol, Piece[][] board) {
        if(destRow <0 || destRow > 7 || destCol <0 || destCol > 7)
            return false;

        if(destRow != super.getRow() && destCol != super.getCol())
            return false;

        if(board[destRow][destCol]!=null && board[destRow][destCol].isWhite()==super.isWhite())
            return false;


        if (super.getRow() == destRow){
            if(destCol > super.getCol()){
                for (int i = super.getCol()+1 ; i<destCol ; i++){
                    if(board[destRow][i]!=null)
                        return false;
                }
            }
            if(destCol < super.getCol()){
                for (int i = super.getCol()-1 ; i>destCol ; i--){
                    if(board[destRow][i]!=null)
                        return false;
                }
            }
        }
        if(super.getCol() == destCol){
            if(destRow > super.getRow()){
                for (int i = super.getRow()+1 ; i<destRow ; i++){
                    if(board[i][destCol]!=null)
                        return false;
                }
            }
            if(destRow < super.getRow()){
                for (int i = super.getRow()-1 ; i>destRow ; i--){
                    if(board[i][destCol]!=null)
                        return false;
                }
            }
        }
        return true;
    }
}
