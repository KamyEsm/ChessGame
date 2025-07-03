package com.example.chessgame;

import Map.Board;
import Map.MapLoader;
import Models.*;

import java.util.List;

public class ChessController {
    private Board board;
    private boolean whiteTurn = true;
    private boolean PieceSelected = false;
    private int[] CellSelected= {-1,-1};



    public ChessController(MapLoader mapLoader) {
        this.board = new Board();
        mapLoader.setController(this);
    }


    public void onCellClicked(int row, int col) {

        if(!PieceSelected && board.getBoardArray()[row][col]==null) return;
        if(board.getBoardArray()[row][col]!=null&& !PieceSelected && board.getBoardArray()[row][col].isWhite()!=whiteTurn) return;
        if(PieceSelected && board.getBoardArray()[row][col]!=null && board.getBoardArray()[row][col].isWhite()==whiteTurn) {
            CellSelected[0]=row;
            CellSelected[1]=col;
            return;
        }

        if(PieceSelected) {
            if(board.getBoardArray()[CellSelected[0]][CellSelected[1]].isValidMove(row,col,board.getBoardArray())) {
                Piece TempBoardPiece[][] =new Piece[8][8];
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if(board.getBoardArray()[i][j]!=null && board.getBoardArray()[i][j].getClass() == King.class) {
                            TempBoardPiece[i][j] = new King(board.getBoardArray()[i][j].isWhite(),i,j);
                            continue;
                        }
                        if(board.getBoardArray()[i][j]!=null && board.getBoardArray()[i][j].getClass() == Queen.class) {
                            TempBoardPiece[i][j] = new Queen(board.getBoardArray()[i][j].isWhite(),i,j);
                            continue;
                        }
                        if(board.getBoardArray()[i][j]!=null && board.getBoardArray()[i][j].getClass() == Pawn.class) {
                            TempBoardPiece[i][j] = new Pawn(board.getBoardArray()[i][j].isWhite(),i,j);
                            continue;
                        }
                        if(board.getBoardArray()[i][j]!=null && board.getBoardArray()[i][j].getClass() == Knight.class) {
                            TempBoardPiece[i][j] = new Knight(board.getBoardArray()[i][j].isWhite(),i,j);
                            continue;
                        }
                        if(board.getBoardArray()[i][j]!=null && board.getBoardArray()[i][j].getClass() == Rook.class) {
                            TempBoardPiece[i][j] = new Rook(board.getBoardArray()[i][j].isWhite(),i,j);
                            continue;
                        }
                        if(board.getBoardArray()[i][j]!=null && board.getBoardArray()[i][j].getClass() == Bishop.class) {
                            TempBoardPiece[i][j] = new Bishop(board.getBoardArray()[i][j].isWhite(),i,j);
                            continue;
                        }
                        TempBoardPiece[i][j] = null;
                    }
                }
                board.movePiece(CellSelected[0],CellSelected[1],row,col);
                if(isCheck(board.getBoardArray(),whiteTurn)) {
                    board.SetBoardArray(TempBoardPiece);
                    if(whiteTurn) System.out.println("Check White");
                    else System.out.println("Check Black");
                    return;
                }
                CellSelected[0]=-1;
                CellSelected[1]=-1;
                PieceSelected = false;
                switchTurn();
            }
        }
        else {
            CellSelected[0]=row;
            CellSelected[1]=col;
            PieceSelected = true;
        }


        // این متد رو از MapLoader صدا بزن
        // بررسی حرکت، نوبت، و اعمال حرکت اینجا انجام می‌شه
    }




    public int[] KingLocation(Piece piece[][],boolean white) {
        int[] KingLocation = new int[2];
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length; j++) {
                if(piece[i][j]!=null && piece[i][j].getClass()== King.class && piece[i][j].isWhite()==white) {
                    KingLocation[0]=i;
                    KingLocation[1]=j;
                    return KingLocation;
                }
            }
        }
        KingLocation[0]=-1;
        KingLocation[1]=-1;
        return KingLocation;
    }



    public boolean isCheck(Piece piece[][],boolean White){
        int[] KingLocation = KingLocation(piece,White);
        if(KingLocation[0]==-1 || KingLocation[1]==-1) return false;
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length; j++) {
                if(piece[i][j]!=null && piece[i][j].isWhite()!=White) {
                    if(piece[i][j].isValidMove(KingLocation[0],KingLocation[1],piece)) return true;
                }
            }
        }
        return false;
    }


    public int[] getCellSelected(){
        if(PieceSelected)
            return CellSelected;
        return null;
    }
    public Board getBoard() {
        return board;
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void switchTurn() {
        whiteTurn = !whiteTurn;
    }
}
