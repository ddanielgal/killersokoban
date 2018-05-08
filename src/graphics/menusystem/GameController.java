package graphics.menusystem;

import graphics.GraphicsCollection;
import graphics.SokobanApp;
import graphics.mapelements.PlayerGraphics;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sokoban.Directions;
import sokoban.Player;
import sokoban.Warehouse;

public class GameController {

    @FXML
    private Pane canvas;

    @FXML
    private GridPane scorePane;

    private GraphicsCollection drawables;

    private Warehouse warehouse;

    public static int cellSize;

    private static final int canvasSize = 500;


    public void initialize(){
        // load settings

    }

    private void initScoreboard(int players){
        scorePane.getChildren().clear();
        Label scoreLabel = new Label("Score");
        scoreLabel.setId("scoreLabel");
        scoreLabel.setAlignment(Pos.CENTER);
        scorePane.add(scoreLabel, 0, 0, 2, 1);
        for(int i = 1; i <= players; ++i){
            Label playerString = new Label("Player " + i);
            playerString.setId("player" + i + "string");
            scorePane.add(playerString, 0, i);
            Label playerScore = new Label("0");
            playerScore.setId("player" + i + "score");
            scorePane.add(playerScore, 1, i);
        }
    }

    public void newGame(){
        warehouse = new Warehouse();
        warehouse.generateMap();
        initScoreboard(warehouse.getPlayers().size());
        cellSize = Math.min(canvasSize / warehouse.getMapWidth(), canvasSize /warehouse.getMapHeight());
        drawables = new GraphicsCollection(canvas);
        PlayerGraphics.playerCount = 1;
        canvas.setMaxSize(cellSize*warehouse.getMapWidth(),cellSize*warehouse.getMapHeight());
        drawables.initFrom(warehouse);
        drawables.drawAll();
        updateScore();
    }

    public void handleKeyPress(KeyEvent key){
        switch(key.getCode()){
            case A:
                warehouse.getPlayer(0).move(Directions.left);
                break;
            case D:
                warehouse.getPlayer(0).move(Directions.right);
                break;
            case W:
                warehouse.getPlayer(0).move(Directions.top);
                break;
            case S:
                warehouse.getPlayer(0).move(Directions.bottom);
                break;
            case LEFT:
                warehouse.getPlayer(1).move(Directions.left);
                break;
            case RIGHT:
                warehouse.getPlayer(1).move(Directions.right);
                break;
            case UP:
                warehouse.getPlayer(1).move(Directions.top);
                break;
            case DOWN:
                warehouse.getPlayer(1).move(Directions.bottom);
                break;
        }
        drawables.drawAll();
        updateScore();
    }

    private void updateScore(){
        int i = 1;
        for(Player p : warehouse.getPlayers()){
            ((Label)scorePane.lookup("#player" + i + "score")).setText(String.valueOf(p.getScore()));
            ++i;
        }
    }

    public void exitButton(ActionEvent actionEvent) {
        SokobanApp.switchScenes("main");
    }


    public void saveButton(ActionEvent actionEvent) {
    }
}
