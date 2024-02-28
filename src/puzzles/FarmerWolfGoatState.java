package puzzles;

import java.util.Set;
import java.util.HashSet;

import search.AbstractState;
import search.State;

/* Ez az osztály megvalósítja az AbstractState absztrakt osztályt,
 * megfelelvén a farmer-farkas ... probléma tartaémának.
 */
public class FarmerWolfGoatState extends AbstractState {
	
    private FarmerWolfGoatSide farmer = FarmerWolfGoatSide.EAST;
    private FarmerWolfGoatSide wolf = FarmerWolfGoatSide.EAST;
    private FarmerWolfGoatSide goat = FarmerWolfGoatSide.EAST;
    private FarmerWolfGoatSide cabbage = FarmerWolfGoatSide.EAST;
    

    /**
     * Új állapotot konstruál. Mindenki keleten.
     */
    public FarmerWolfGoatState() {
    }

    /**
     * Az adott @param szülőhöz gyereket konstruál, a megadott pozíciók alapján.
     * @param parent a szülő 
     */
    public FarmerWolfGoatState(FarmerWolfGoatState parent, 
    		FarmerWolfGoatSide farmer, FarmerWolfGoatSide wolf, FarmerWolfGoatSide goat, FarmerWolfGoatSide cabbage) {
        super(parent);
        
        this.farmer = farmer;
        this.wolf = wolf;
        this.goat = goat;
        this.cabbage = cabbage;
    }
    
   

    /**
     * Adott állapotból elérhető többi állapot listáját adja vissza.
     */
    public Iterable<State> getPossibleMoves() {
        Set<State> moves = new HashSet<State>();
        // Move wolf
        if (farmer==wolf)
            new FarmerWolfGoatState(this,farmer.getOpposite(),
                                         wolf.getOpposite(),
                                         goat,
                                         cabbage).addIfSafe(moves);
        // Move goat
        if (farmer==goat)
            new FarmerWolfGoatState(this,farmer.getOpposite(),
                                         wolf,
                                         goat.getOpposite(),
                                         cabbage).addIfSafe(moves);
        // Move cabbage
        if (farmer==cabbage)
            new FarmerWolfGoatState(this,farmer.getOpposite(),
                                         wolf,
                                         goat,
                                         cabbage.getOpposite()).addIfSafe(moves);
        // Move just farmer
        new FarmerWolfGoatState(this,farmer.getOpposite(),
                                     wolf,
                                     goat,
                                     cabbage).addIfSafe(moves);

        return moves;
    }
    
    private final void addIfSafe(Set<State> moves) {
        boolean unsafe = (farmer != wolf && farmer != goat) ||
                         (farmer != goat && farmer != cabbage);
        if (!unsafe)
            moves.add(this);
    }

    /**
     * Azt számítja ki, hogy megoldás-e az adott állapot
     * @return true akcsakkor ha az állapot célállapot
     */
    public boolean isSolution() {
        return farmer==FarmerWolfGoatSide.WEST && 
               wolf==FarmerWolfGoatSide.WEST &&
               goat==FarmerWolfGoatSide.WEST && 
               cabbage==FarmerWolfGoatSide.WEST;
    }



    /**
     * A heurisztika annyit jelent, hogy hány dolog van a EAST (KELETI) oldalon.
     */
    public double getHeuristic() {
        int sum = 0;
        if (farmer  == FarmerWolfGoatSide.EAST) sum++;
        if (wolf    == FarmerWolfGoatSide.EAST) sum++;
        if (cabbage == FarmerWolfGoatSide.EAST) sum++;
        if (goat    == FarmerWolfGoatSide.EAST) sum++;
        return sum;
    }
    /**
     * Mint szokik lenni, összehasonlítja két FWGS objektumot tartalomra.
     * @returns true, ha az adott objektum megfelelő típusú és egyenlő a példányunkkal.
     */
    public boolean equals(Object o) {
        if (o==null || !(o instanceof FarmerWolfGoatState))
            return false;
        FarmerWolfGoatState fwgs = (FarmerWolfGoatState)o;
        return farmer  == fwgs.farmer && 
               wolf    == fwgs.wolf && 
               cabbage == fwgs.cabbage &&
               goat    == fwgs.goat;
    }
    /**
     * hashCode, egyedi azonosító a példányokhoz. 
     * Akcsakkor egyenlőek két példányra, ha a két példány emgegyezik.
     */
    public int hashCode() {
        return (farmer  == FarmerWolfGoatSide.EAST ? 1 : 0)+
               (wolf    == FarmerWolfGoatSide.EAST ? 2 : 0)+
               (cabbage == FarmerWolfGoatSide.EAST ? 4 : 0)+
               (goat    == FarmerWolfGoatSide.EAST ? 8 : 0);
    }
    /**
     * Kiír egy állapotot.
     */
    public String toString() {
        return (farmer  == FarmerWolfGoatSide.EAST ? "F" : " ")+
               (wolf    == FarmerWolfGoatSide.EAST ? "W" : " ")+
               (cabbage == FarmerWolfGoatSide.EAST ? "C" : " ")+
               (goat    == FarmerWolfGoatSide.EAST ? "G" : " ")+
               " | ~~~~~ | "+
               (farmer  == FarmerWolfGoatSide.WEST ? "F" : " ")+
               (wolf    == FarmerWolfGoatSide.WEST ? "W" : " ")+
               (cabbage == FarmerWolfGoatSide.WEST ? "C" : " ")+
               (goat    == FarmerWolfGoatSide.WEST ? "G" : " ")+
               " (heuristic: "+getHeuristic()+")"+"\n";
    }

}
