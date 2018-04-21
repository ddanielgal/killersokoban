package sokoban;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A játékban a raktárat megvalósító osztály.
 */
public class Warehouse {

    /**
     * Azok a cellák, amik ebben a raktárban vannak.
     */
    private List<Cell> map;

    /**
     * A raktárban található dobozok.
     */
    private List<Box> boxes;

    /**
     * A raktárban található játékosok.
     */
    private List<Player> players;

    /**
     * Létrehozza a raktárat.
     */
    public Warehouse(){
        players = new ArrayList<>();
        boxes = new ArrayList<>();
    }

    /**
     * Létrehozza a raktárat adott számú játékossal.
     * @param playerNum Ahány játékos legyen.
     */
    public Warehouse(int playerNum){
        players = new ArrayList<>();
        for (int i=0; i<playerNum; i++)
            players.add(new Player());
        boxes = new ArrayList<>();
    }

    /**
     * A raktárban valamelyik játékost adja vissza.
     * @param a A játékos sorszáma.
     * @return A kért játékos.
     */
    public Player getPlayers(int a) {
        return players.get(a);
    }

    /**
     * Hozzáad egy dobozt a raktárhoz.
     * @param b A hozzáadandó doboz.
     */
    public void addBox(Box b){
        boxes.add(b);
    }


    /**
     * Kivesz egy entitást a raktárból.
     * @param e A kivevendő entitás.
     * @see Entity
     * @see Box
     * @see Player
     */
    public void removeEntity(Entity e){
        if (boxes.contains(e)) boxes.remove(e);
        else if(players.contains(e)) e.die();
    }

    /**
     * Kirajzolja a map-et a kimenetre
     * Cell : "-"
     * Wall : "W"
     * Goal : "G"
     * Hole : "H"
     * Switch : "S"
     * SwichableHole : "H" vagy "-" állapotfüggő
     * Box : "B"
     * Player : "P"
     *
     */
    public void draw(){
        for (Cell c: map
             ) {
            c.draw();
            if (c.getNeighbour(Directions.right) == null)
                System.out.println("");
        }
    }

    public void generateMap() {
        int width = new Random().nextInt() % 15 + 5;
        int height = new Random().nextInt() % 15 + 5;
        generateMap(width, height);
        int playerNum = new Random().nextInt() % 5 + 2;
        for(int i = 0; i < playerNum; i++){
            int x = new Random().nextInt() % (height-1) + 1;
            int y = new Random().nextInt() % (width-1) + 1;
            while(map.get(x + y * width).getHolding() != null) {
                x = new Random().nextInt() % (height-1) + 1;
                y = new Random().nextInt() % (width-1) + 1;
            }
            players.add(new Player());
            players.get(i).setPlace(map.get(x + y*width));
            map.get(x + y * width).setHolding(players.get(i));
        }

        int boxNum = new Random().nextInt() % 10 + 2;
        for(int i = 0; i < boxNum; i++){
            int x = new Random().nextInt() % (height-1) + 1;
            int y = new Random().nextInt() % (width-1) + 1;
            while(map.get(x + y * width).getHolding() != null) {
                x = new Random().nextInt() % (height-1) + 1;
                y = new Random().nextInt() % (width-1) + 1;
            }
            boxes.add(new Box());
            boxes.get(i).setPlace(map.get(x + y*width));
            map.get(x + y * width).setHolding(boxes.get(i));
        }
        for(int i = 0; i < boxNum; i++){
            int x = new Random().nextInt() % (height-1) + 1;
            int y = new Random().nextInt() % (width-1) + 1;
            while(map.get(x + y * width).getHolding() != null) {
                x = new Random().nextInt() % (height-1) + 1;
                y = new Random().nextInt() % (width-1) + 1;
            }
            map.remove(x + y * width);
            map.add(x + y * width, new Goal());
        }
    }

    public void generateMap(int dim) {
        generateMap(dim, dim);
    }

    public void generateMap(int width, int height){
        map = new ArrayList<Cell>();
        for (int i=0; i<width; i++) {
            for (int j = 0; j < height; j++) {
                if (i == 0 || j == 0) map.add(new Wall());
                else if (i == width-1 || j == height-1) map.add(new Wall());
                else map.add(new Cell());
            }
        }

        for (int i = 0; i<width; i++){
            for(int j = 0; j<height; j++){
                if(i!=0) map.get(i + j * width).setNeighbour(map.get(i + (j-1) * width), Directions.top);
                if(i!=width-1) map.get(i + j * width).setNeighbour(map.get(i + (j+1) * width), Directions.bottom);
                if(j!=0) map.get(i + j * width).setNeighbour(map.get(i - 1 + j * width), Directions.left);
                if(j!=height-1) map.get(i + j * width).setNeighbour(map.get(i +1 + j * width), Directions.right);
            }
        }
    }

    public int getMapWidth() {
        int i = 0;
        while (map.get(i).getNeighbour(Directions.right) != null)
            i++;
        return ++i;
    }

    public int getMapHeight(){
        int i=1;
        Cell c = map.get(0);
        while (c.getNeighbour(Directions.bottom) != null){
            i++;
            c = c.getNeighbour(Directions.bottom);
        }
        return i;
    }
}
