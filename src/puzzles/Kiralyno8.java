package puzzles;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.lang.Math.*;

import search.AbstractState;
import search.State;


/** A 8-királynő probléma állapotteres reprezentációja a Luger-féle keretben
 *
 * @author valyis
 */
public class Kiralyno8 extends AbstractState {

	int[] felpakolas = {0,0,0,0,0,0,0,0};
        int meret = felpakolas.length;
        int utolso=0;

    /**
     * Constructs a new default state.  Everyone is on the east side.
     */
    public Kiralyno8() {
    }

    /**
     * Constructs a move state from a parent state
     */
    public Kiralyno8(Kiralyno8 parent, int[] pakolas ) {
        super(parent);
        meret = pakolas.length;
        felpakolas = Arrays.copyOf(pakolas, pakolas.length);
        int i=0; while (i<meret && pakolas[i]>0) i++;
        utolso = i;
    }



    /**
     * Returns a set of all possible moves from this state.
     */
    public Iterable<State> getPossibleMoves() {
        Set<State> moves = new HashSet<State>();
        for (int i=1;i<=meret;i++)
            if (lehetsegesLepes(i)) {
                int [] ujfelpakolas = Arrays.copyOf(felpakolas, meret);
                ujfelpakolas[utolso]=i;
                Kiralyno8 ujTabla =  new Kiralyno8(this, ujfelpakolas);
                moves.add(ujTabla);
            }
        return moves;
    }

    public boolean lehetsegesLepes(int i) {
        boolean nincsUtkozes = true;
        for (int j=0;j<utolso;j++)
            if (felpakolas[j]==i || java.lang.Math.abs(felpakolas[j]-i)== utolso-j )
                nincsUtkozes = false;
        return nincsUtkozes;
    }

    /**
     * The solution is specified as a table where there is a row for each column
     * every row that its joint position is populated by a queen.
     * @return true if this state is a solution
     */
    public boolean isSolution() {
        return utolso == meret;

    }


    /**
     * Returns a heuristic approximation of the number of moves required
     * to solve this problem from this state.  This is implemented as
     * the number of queen collisons.
     */
    public double getHeuristic() {
        int sum = 0;
        for (int i=0;i<utolso;i++)
           for (int j=i+1;j<utolso;j++)
              sum+= (felpakolas[j]==felpakolas[i] ||
                      java.lang.Math.abs(felpakolas[j]-felpakolas[i])== (j-i) )
                      ?1:0;

        return (double)sum;
    }
    /**
     * Compares whether two states are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (o==null || !(o instanceof HuszarCsere))
            return false;
        Kiralyno8 k8 = (Kiralyno8)o;
        boolean talaltElterest = false;
        for (int i=0;i<utolso;i++)
              if(felpakolas[i]!=k8.felpakolas[i]) talaltElterest = true;

        return !talaltElterest;
    }

@Override
    public int hashCode() {
        int sum = 0;
        int hatvany = 1;
        for (int i= 1; i <= 8; i++) {
            sum += hatvany;
            hatvany *= 10;
        }
        return sum;
    }


    /**
     * Returns a string representation of this state
     */
   @Override
    public String toString() {
       java.lang.StringBuilder s = new StringBuilder("\n[");
        for (int i=0;i<meret;i++) {
             s.append(felpakolas[i]+(i+1<meret?",":""));
        }
       s.append("]\n-------------");
       return s.toString();
   }
}
