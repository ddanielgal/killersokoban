package sokoban;

public class Player extends Entity{

    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Player(){
        super();
        score = 0;
        setOwner(this);
    }

    @Override
    public boolean move(Directions dir, Entity mOwner) {
        if (getPlace().getNeighbour(dir).acceptEntity(this, dir, mOwner)){
            getPlace().removeEntity();
            setPlace(getPlace().getNeighbour(dir));
        } else {
            if(!mOwner.equals(this)){
                die();
                mOwner.addScore(1);
            }
        }
        return true;
    }

    public void addScore(int amount){
        score+=amount;
    }

    @Override
    public void stepOnGoal(Entity mOwner) {

    }

    public void die(){
        getPlace().setHolding(null);
        setPlace(null);
    }

    @Override
    public void stepOnHole(Entity mOwner) {
        if(!mOwner.equals(this))
            mOwner.addScore(1);
        getWarehouse().removeEntity(this);
    }

    @Override
    public void stepOnSwitch(SwitchableHole aSwitch, Entity mOwner) {

    }
}
