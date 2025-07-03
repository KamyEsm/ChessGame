package Map;

import Models.*;

public class Board {
    private Piece[][] board;

    public Board() {
        board = new Piece[8][8];
        setupBoard();
    }

    private void setupBoard() {
//         White
//         Pawns
        board[6][0] = new Pawn(true, 6, 0);
        board[6][1] = new Pawn(true, 6, 1);
        board[6][2] = new Pawn(true, 6, 2);
        board[6][3] = new Pawn(true, 6, 3);
        board[6][4] = new Pawn(true, 6, 4);
        board[6][5] = new Pawn(true, 6, 5);
        board[6][6] = new Pawn(true, 6, 6);
        board[6][7] = new Pawn(true, 6, 7);
//        Rooks
        board[7][0] = new Rook(true, 7, 0);
        board[7][7] = new Rook(true, 7, 7);
//        Knight
        board[7][1] = new Knight(true, 7, 1);
        board[7][6] = new Knight(true, 7, 6);
        //Bishop
        board[7][2] = new Bishop(true, 7, 2);
        board[7][5] = new Bishop(true, 7, 5);
        //King
        board[7][3] = new King(true, 7, 3);
        //Queen
        board[7][4] = new Queen(true, 7, 4);




//
//        //Black
//        //Pawn
        board[1][0] = new Pawn(false, 1, 0);
        board[1][1] = new Pawn(false, 1, 1);
        board[1][2] = new Pawn(false, 1, 2);
        board[1][3] = new Pawn(false, 1, 3);
        board[1][4] = new Pawn(false, 1, 4);
        board[1][5] = new Pawn(false, 1, 5);
        board[1][6] = new Pawn(false, 1, 6);
        board[1][7] = new Pawn(false, 1, 7);
        //Rooks
        board[0][0] = new Rook(false, 0, 0);
        board[0][7] = new Rook(false, 0, 7);
        //Knight
        board[0][1] = new Knight(false, 0, 1);
        board[0][6] = new Knight(false, 0, 6);
        //Bishop
        board[0][2] = new Bishop(false, 0, 2);
        board[0][5] = new Bishop(false, 0, 5);
        //King
        board[0][3] = new King(false, 0, 3);
        //Queen
        board[0][4] = new Queen(false, 0, 4);




    }

    public void SetBoardArray(Piece[][] board) {
        this.board = board;
    }

    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    public void movePiece(int srcRow, int srcCol, int destRow, int destCol) {
        Piece piece = board[srcRow][srcCol];
        board[destRow][destCol] = piece;
        board[srcRow][srcCol] = null;
        piece.moveTo(destRow, destCol);
    }

    public Piece[][] getBoardArray() {
        return board;
    }
}