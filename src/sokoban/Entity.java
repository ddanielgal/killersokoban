package sokoban;

public abstract class Entity {

    private Cell place;
    private Entity owner;
    private Warehouse warehouse;

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public Entity getOwner() {
        return owner;
    }

    public void setOwner(Entity owner) {
        this.owner = owner;
    }

    public Cell getPlace() {
        return place;
    }

    public void setPlace(Cell place) {
        this.place = place;
    }

    public Entity() {
        place = new Cell();
    }

    Entity(Cell p)
    {
        place = p;
    }

    public abstract void addScore(int amount);

    public boolean move(Directions dir, Entity mOwner){
        if (place.getNeighbour(dir).acceptEntity(this, dir, mOwner)){
            place.removeEntity();
            place = place.getNeighbour(dir);
            return true;
        }
        return false;
    }





    public abstract boolean pressButton();

}
