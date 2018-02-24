package sokoban;

/**
 *
 */

public class Cell {

    public enum Directions{
        top, right, bottom, left;
    }

    private Cell[] neighbours;

    private Entity holding;

    public Cell(){

    }


    public Entity getHolding() {
        return holding;
    }

    public void setHolding(Entity holding) {
        this.holding = holding;
    }

    public Cell getNeighbour(Directions d) {
        return neighbours[d.ordinal()];
    }

    public void setNeighbours(Cell neighbours, Directions dir ) {
        this.neighbours[dir.ordinal()] = neighbours;
    }

    public void acceptEntity(Entity n){

    }

    public boolean canMoveHere(Directions dir){
        if (holding == null)
            return true;
        return holding.isMovable(dir);
    }
}
