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
    private boolean GameOver = false;



    public ChessController(MapLoader mapLoader) {
        this.board = new Board();
        mapLoader.setController(this);
    }


    public void onCellClicked(int row, int col) {
        if(isCheckmate(board.getBoardArray(),whiteTurn)) {
            GameOver = true;
            return;
        }
        if(!PieceSelected && board.getBoardArray()[row][col]==null) return;
        if(board.getBoardArray()[row][col]!=null&& !PieceSelected && board.getBoardArray()[row][col].isWhite()!=whiteTurn) return;
        if(PieceSelected && board.getBoardArray()[row][col]!=null && board.getBoardArray()[row][col].isWhite()==whiteTurn) {
            CellSelected[0]=row;
            CellSelected[1]=col;
            return;
        }

        if(PieceSelected) {
            if(board.getBoardArray()[CellSelected[0]][CellSelected[1]].isValidMove(row,col,board.getBoardArray())) {
                Piece TempBoardPiece[][] = CloneBoard(board.getBoardArray());
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



    public boolean isCheckmate(Piece board[][],boolean white) {
        if(!isCheck(board,white)) return false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j]==null || board[i][j].isWhite()!=white) continue;
                for (int x = 0; x < board.length; x++) {
                    for (int y = 0; y < board[x].length; y++) {
                        if(board[i][j].isValidMove(x,y,board)){
                            Piece piece[][] = CloneBoard(board);
                            Board tempBoard = new Board();
                            tempBoard.SetBoardArray(piece);
                            tempBoard.movePiece(i,j,x,y);
                            if(!isCheck(tempBoard.getBoardArray(),white)) return false;
                        }
                    }
                }
            }
        }
        return true;
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

    public boolean isGameOver() {
        return GameOver;
    }

    public void Restart(boolean isGameOver) {
        this.GameOver = isGameOver;
    }

    public void switchTurn() {
        whiteTurn = !whiteTurn;
    }

    public Piece[][] CloneBoard(Piece board[][]) {
        Piece TempBoardPiece[][] = new Piece[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j]!=null && board[i][j].getClass() == King.class) {
                    TempBoardPiece[i][j] = new King(board[i][j].isWhite(),i,j);
                    continue;
                }
                if(board[i][j]!=null && board[i][j].getClass() == Queen.class) {
                    TempBoardPiece[i][j] = new Queen(board[i][j].isWhite(),i,j);
                    continue;
                }
                if(board[i][j]!=null && board[i][j].getClass() == Pawn.class) {
                    TempBoardPiece[i][j] = new Pawn(board[i][j].isWhite(),i,j);
                    continue;
                }
                if(board[i][j]!=null && board[i][j].getClass() == Knight.class) {
                    TempBoardPiece[i][j] = new Knight(board[i][j].isWhite(),i,j);
                    continue;
                }
                if(board[i][j]!=null && board[i][j].getClass() == Rook.class) {
                    TempBoardPiece[i][j] = new Rook(board[i][j].isWhite(),i,j);
                    continue;
                }
                if(board[i][j]!=null && board[i][j].getClass() == Bishop.class) {
                    TempBoardPiece[i][j] = new Bishop(board[i][j].isWhite(),i,j);
                    continue;
                }
                TempBoardPiece[i][j] = null;
            }
        }
        return TempBoardPiece;
    }
}
