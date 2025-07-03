package Models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    @Override
    public boolean isValidMove(int destRow, int destCol, Piece[][] board) {
        if(board[destRow][destCol]!=null && board[destRow][destCol].isWhite() == super.isWhite()) {
            return false;
        }
        if(Math.abs(destRow - super.getRow()) == Math.abs(destCol - super.getCol())){
            if(destRow > super.getRow() && destCol > super.getCol()){
                int i = super.getRow()+1;
                int j = super.getCol()+1;
                while (i < destRow && j < destCol){
                    if(board[i][j] != null){
                        return false;
                    }
                    i++;
                    j++;
                }

            }
            if(destRow < super.getRow() && destCol < super.getCol()){
                int i = super.getRow()-1;
                int j = super.getCol()-1;
                while (i > destRow && j > destCol){
                    if(board[i][j] != null){
                        return false;
                    }
                    i--;
                    j--;
                }
            }
            if(destRow>super.getRow() && destCol<super.getCol()){
                int i = super.getRow()+1;
                int j = super.getCol()-1;
                while (i < destRow && j > destCol){
                    if(board[i][j] != null){
                        return false;
                    }
                    i++;
                    j--;
                }
            }
            if(destRow<super.getRow() && destCol>super.getCol()){
                int i = super.getRow()-1;
                int j = super.getCol()+1;
                while (i > destRow && j < destCol){
                    if(board[i][j] != null){
                        return false;
                    }
                    i--;
                    j++;
                }
            }
            return true;
        }
        return false;
    }
}
