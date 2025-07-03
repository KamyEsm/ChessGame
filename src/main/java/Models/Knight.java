package Models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Knight extends Piece {
    public Knight(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    @Override
    public boolean isValidMove(int destRow, int destCol, Piece[][] board) {
        List <int[]> list = new ArrayList<>();
        //Up
        list.add(new int[]{super.getRow()+2, super.getCol()+1});
        list.add(new int[]{super.getRow()+2, super.getCol()-1});
        list.add(new int[]{super.getRow()+1, super.getCol()+2});
        list.add(new int[]{super.getRow()+1, super.getCol()-2});
        //Down
        list.add(new int[]{super.getRow()-2, super.getCol()+1});
        list.add(new int[]{super.getRow()-2, super.getCol()-1});
        list.add(new int[]{super.getRow()-1, super.getCol()+2});
        list.add(new int[]{super.getRow()-1, super.getCol()-2});
        Iterator<int[]> iterator = list.iterator();
        while (iterator.hasNext()) {
            int[] next = iterator.next();
            if(next[0]<0 || next[0]>7 || next[1]<0 || next[1]>7) {
                iterator.remove();
                continue;
            }
            if(board[next[0]][next[1]]!=null && board[next[0]][next[1]].isWhite()==super.isWhite())
                iterator.remove();
        }
        for (int[] next : list) {
            if(next[0]==destRow && next[1]==destCol)
                return true;
        }
        return false;
    }
}
