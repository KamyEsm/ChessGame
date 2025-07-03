package Models;


public class Pawn extends Piece {
    public Pawn(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    @Override
    public boolean isValidMove(int destRow, int destCol, Piece[][] board) {
        if(destRow < 0 || destRow >= 8 || destCol < 0 || destCol >= 8) return false;

        if(super.isWhite()){
            if(super.getRow() == 6){
                if(destRow == 5){
                    if(super.getCol() == destCol && board[5][super.getCol()]==null){
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                else if(destRow == 4 && col == destCol && board[5][destCol]==null && board[4][super.getCol()]==null) return true;
                else if (destRow == 5 && super.getCol()+1<8 && board[5][super.getCol()+1] != null && board[5][super.getCol()+1].isWhite() != super.isWhite()) return true;
                else if (destRow == 5 && super.getCol()-1>=0 && board[5][super.getCol()-1] != null && board[5][super.getCol()-1].isWhite() != super.isWhite()) return true;
                else return false;
            }
            else {
                if(col == destCol && destRow == super.getRow()-1 && board[destRow][super.getCol()]==null) return true;
                else if(destRow==row-1 && (destCol == col+1 || destCol == col-1) && board[destRow][destCol]!=null && board[destRow][destCol].isWhite!=super.isWhite) return true;
                else return false;
            }
        }
        else {
            if(super.getRow() == 1){
                if(destRow == 2){
                    if(super.getCol() == destCol && board[2][super.getCol()]==null){
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                else if(destRow == 3 && col==destCol && board[2][super.getCol()]==null && board[3][super.getCol()] == null  ) return true;
                else if (destRow == 2 && super.getCol()+1<8 && board[2][super.getCol()+1] != null && board[2][super.getCol()+1].isWhite() != super.isWhite()) return true;
                else if (destRow == 2 && super.getCol()-1>=0 && board[2][super.getCol()-1] != null && board[2][super.getCol()-1].isWhite() != super.isWhite()) return true;
                else return false;
            }
            else {
                if (destRow == super.getRow()+1 && col==destCol && board[destRow][super.getCol()]==null) return true;
                else if(destRow==row+1 && (destCol == col+1 || destCol == col-1) && board[destRow][destCol]!=null && board[destRow][destCol].isWhite!=super.isWhite) return true;
                else return false;
            }
        }

    }
}