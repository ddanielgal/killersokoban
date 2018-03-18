package sokoban;

/**
 * A játékban a kapcsolókat megvalósító osztály.
 * @see Cell
 */
public class Switch extends Cell {

    /**
     * Az a lyuk, amit a kapcsoló kapcsol.
     * @see SwitchableHole
     */
    private SwitchableHole hole;

    /**
     * Létrehozza a kapcsolót.
     * @param h Az a lyuk, amit kapcsolnia kell majd a kapcsolónak.
     */
    public Switch(SwitchableHole h){
        hole = h;
    }

    /**
     * Befogad valamit a kapcsolóra (tolás során) és ráhelyezi.
     * @param n Amit befogad.
     * @param dir Amelyik irányban tolódik a befogadott dolog, tehát amerre a mostani tartalomnak is tolódnia kell.
     * @param mOwner Aki a tolást kezdeményezte, ezzel rátolva a kapcsolóra az új dolgot és letolva a régit.
     * @return Sikeresen befogadta-e.
     */
    @Override
    public boolean acceptEntity(Entity n, Directions dir, Entity mOwner) {
        boolean succesful = super.acceptEntity(n, dir, mOwner);
        if(succesful)
            n.stepOnSwitch(hole, mOwner);
        return succesful;
    }

    /**
     * Leveszi a kapcsolóról a rajta álló dolgot.
     */
    @Override
    public void removeEntity() {
        super.removeEntity();
        hole.setOpen(false, null);
    }
}
