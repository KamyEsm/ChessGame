package Map;

import Models.*;
import com.example.chessgame.ChessController;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MapLoader {
    //GUI
    private Stage MapStage;
    private Scene MapScene;
    private Canvas MapCanvas;
    private GraphicsContext gc;
    private static final int WIDTH = 640;
    private static final int HEIGHT = 640;
    private static final int Row=8;
    private static final int Col=8;
    private static final int CellSize=WIDTH/Row;
    //Controller
    private ChessController controller;


    //------------------------->>>Image
    private static final Image BlackPawn=new Image("D:\\Project\\Java\\ChessGame\\src\\main\\resources\\Image\\BlackPawn.jpg");
    private static final Image WhitePawn=new Image("D:\\Project\\Java\\ChessGame\\src\\main\\resources\\Image\\WhitePawn.jpg");
    private static final Image BlackKnight=new Image("D:\\Project\\Java\\ChessGame\\src\\main\\resources\\Image\\BlackKnight.jpg");
    private static final Image WhiteKnight=new Image("D:\\Project\\Java\\ChessGame\\src\\main\\resources\\Image\\WhiteKnight.jpg");
    private static final Image WhiteBishop=new Image("D:\\Project\\Java\\ChessGame\\src\\main\\resources\\Image\\WhiteBishop.jpg");
    private static final Image BlackBishop=new Image("D:\\Project\\Java\\ChessGame\\src\\main\\resources\\Image\\BlackBishop.jpg");
    private static final Image WhiteQueen=new Image("D:\\Project\\Java\\ChessGame\\src\\main\\resources\\Image\\WhiteQueen.jpg");
    private static final Image BlackQueen=new Image("D:\\Project\\Java\\ChessGame\\src\\main\\resources\\Image\\BlackQueen.jpg");
    private static final Image WhiteKing=new Image("D:\\Project\\Java\\ChessGame\\src\\main\\resources\\Image\\WhiteKing.jpg");
    private static final Image BlackKing=new Image("D:\\Project\\Java\\ChessGame\\src\\main\\resources\\Image\\BlackKing.jpg");
    private static final Image WhiteRook=new Image("D:\\Project\\Java\\ChessGame\\src\\main\\resources\\Image\\WhiteRook.jpg");
    private static final Image BlackRook=new Image("D:\\Project\\Java\\ChessGame\\src\\main\\resources\\Image\\BlackRook.jpg");



    public MapLoader(Stage stage){
        MapStage = stage;
    }

    public void setController(ChessController controller) {
        this.controller = controller;
    }

    public void loadMap(){
        Group root = new Group();
        MapCanvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(MapCanvas);
        MapStage.setTitle("Chess Game");
        MapScene = new Scene(root);
        MapStage.setScene(MapScene);
        MapStage.show();
        gc = MapCanvas.getGraphicsContext2D();
        DrawCanvas();
        DrawMovePiece(controller.getBoard());
        DrawPieces(controller.getBoard());
        DrawTurn();
        MapCanvas.setOnMouseClicked(e -> {
            int col = (int) (e.getX() / CellSize);
            int row = (int) (e.getY() / CellSize);

            if (controller != null) {
                controller.onCellClicked(row, col);
                if (controller.isGameOver()) {
                    Button button = new Button("Restart");
                    button.setOnMousePressed(s -> {
                        controller.getBoard().SetBoardArray(new Board().getBoardArray());
                        controller.Restart(false);
                        Update();
                    });
                    VBox overlay = new VBox(button);
                    overlay.setAlignment(Pos.CENTER);
                    ((Group) MapScene.getRoot()).getChildren().add(overlay);
                } else {
                    Update();
                }
            }
        });
    }

    public void DrawCanvas(){
        for (int i=0; i<Row; i++){
            for (int j=0; j<Col; j++){
                if((i+j)%2==0){
                    gc.setFill(Color.web("#769656"));
                }
                else{
                    gc.setFill(Color.web("#EEEED2"));
                }
                gc.fillRect(j*CellSize,i*CellSize,CellSize,CellSize);
            }
        }

    }

    public void DrawMovePiece(Board board){
        int[] a =controller.getCellSelected();
        Piece piece=null;
        if(a==null)
            return;
        if(board.getBoardArray()[a[0]][a[1]]==null){
            return;
        }
        if(board.getBoardArray()[a[0]][a[1]].getClass()==King.class){
            piece = new King(board.getBoardArray()[a[0]][a[1]].isWhite(),a[0],a[1]);
        }
        if(board.getBoardArray()[a[0]][a[1]].getClass()==Queen.class){
            piece = new Queen(board.getBoardArray()[a[0]][a[1]].isWhite(),a[0],a[1]);
        }
        if(board.getBoardArray()[a[0]][a[1]].getClass()==Bishop.class){
            piece = new Bishop(board.getBoardArray()[a[0]][a[1]].isWhite(),a[0],a[1]);
        }
        if(board.getBoardArray()[a[0]][a[1]].getClass()==Knight.class){
            piece = new Knight(board.getBoardArray()[a[0]][a[1]].isWhite(),a[0],a[1]);
        }
        if(board.getBoardArray()[a[0]][a[1]].getClass()==Pawn.class){
            piece = new Pawn(board.getBoardArray()[a[0]][a[1]].isWhite(),a[0],a[1]);
        }
        if(board.getBoardArray()[a[0]][a[1]].getClass()==Rook.class){
            piece=new Rook(board.getBoardArray()[a[0]][a[1]].isWhite(),a[0],a[1]);
        }
        gc.setFill(Color.web("#f7ec65"));
        gc.fillRect(a[1]*CellSize,a[0]*CellSize,CellSize,CellSize);

        for (int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(piece.isValidMove(i,j,board.getBoardArray())){
                    if(board.getBoardArray()[i][j]!=null && board.getBoardArray()[i][j].isWhite()!=piece.isWhite()) gc.setFill(Color.rgb(255, 0, 0, 0.4));
                    if(board.getBoardArray()[i][j]==null) gc.setFill(Color.rgb(0, 191, 255, 0.4));
                    gc.fillRect(j*CellSize,i*CellSize,CellSize,CellSize);
                }
            }
        }

        if(controller.isCheck(controller.getBoard().getBoardArray(),controller.isWhiteTurn())){
            int[] King=controller.KingLocation(controller.getBoard().getBoardArray(),controller.isWhiteTurn());
            gc.setFill(Color.web("#e07b00"));
            gc.fillRect(King[1]*CellSize,King[0]*CellSize,CellSize,CellSize);
        }

    }


    public void DrawPieces(Board board){
        Piece[][] pieces=board.getBoardArray();
        for (int i=0; i<Row; i++){
            for (int j=0; j<Col; j++){
                if(pieces[i][j]!=null){
                    if(pieces[i][j].getClass().equals(Knight.class)){
                        if(pieces[i][j].isWhite()){
                            gc.drawImage(WhiteKnight,j*CellSize,i*CellSize,CellSize,CellSize);
                        }
                        else{
                            gc.drawImage(BlackKnight,j*CellSize,i*CellSize,CellSize,CellSize);
                        }
                    }
                    else if(pieces[i][j].getClass().equals(Bishop.class)){
                        if(pieces[i][j].isWhite()){
                            gc.drawImage(WhiteBishop,j*CellSize,i*CellSize,CellSize,CellSize);
                        }
                        else{
                            gc.drawImage(BlackBishop,j*CellSize,i*CellSize,CellSize,CellSize);
                        }
                    }
                    else if(pieces[i][j].getClass().equals(Queen.class)){
                        if(pieces[i][j].isWhite()){
                            gc.drawImage(WhiteQueen,j*CellSize,i*CellSize,CellSize,CellSize);
                        }
                        else{
                            gc.drawImage(BlackQueen,j*CellSize,i*CellSize,CellSize,CellSize);
                        }
                    }
                    else if(pieces[i][j].getClass().equals(Pawn.class)){
                        if(pieces[i][j].isWhite()){
                            gc.drawImage(WhitePawn,j*CellSize,i*CellSize,CellSize,CellSize);
                        }
                        else{
                            gc.drawImage(BlackPawn,j*CellSize,i*CellSize,CellSize,CellSize);
                        }
                    } else if (pieces[i][j].getClass().equals(King.class)) {
                        if(pieces[i][j].isWhite()){
                            gc.drawImage(WhiteKing,j*CellSize,i*CellSize,CellSize,CellSize);
                        }
                        else{
                            gc.drawImage(BlackKing,j*CellSize,i*CellSize,CellSize,CellSize);
                        }
                    } else if (pieces[i][j].getClass().equals(Rook.class)) {
                        if(pieces[i][j].isWhite()){
                            gc.drawImage(WhiteRook,j*CellSize,i*CellSize,CellSize,CellSize);
                        }
                        else{
                            gc.drawImage(BlackRook,j*CellSize,i*CellSize,CellSize,CellSize);
                        }
                    }
                }

            }
        }
    }

    public void DrawTurn(){
        gc.setFill(Color.web("#4A6F8A"));
        gc.setFont(new Font("Arial", 18));
        if(controller.isWhiteTurn()) gc.fillText("White Turn", 10,45);
        else gc.fillText("Black Turn", 10,45);
        if(controller.isCheck(controller.getBoard().getBoardArray(),controller.isWhiteTurn())) {
            if(controller.isWhiteTurn()){
                gc.fillText("White is in Check", 10,65);
            }
            else{
                gc.fillText("Black is in Check", 10,65);
            }
        }
        gc.setFill(Color.RED);
        if(controller.isCheckmate(controller.getBoard().getBoardArray(),controller.isWhiteTurn())){
            if(controller.isWhiteTurn()){
                gc.fillText("White is in Checkmate", 10,85);
            }
            else{
                gc.fillText("Black is in Checkmate", 10,85);
            }
        }
    }


    public void Update(){
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        DrawCanvas();
        DrawMovePiece(controller.getBoard());
        DrawPieces(controller.getBoard());
        DrawTurn();
    }



    public void End(){
        gc.setFill(Color.BLACK);
        gc.setFont(new Font("Arial", 30));
        gc.fillText("Win : " + controller.isWhiteTurn(), WIDTH/2, HEIGHT/2);
    }

}
