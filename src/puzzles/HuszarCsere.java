package puzzles;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.lang.Math.*;

import search.AbstractState;
import search.State;

public class HuszarCsere extends AbstractState {

	char[][] tabla = {{'w','_','w'},
                      {'_','_','_'},
                      {'b','_','b'}};
    int utolsoSorIndexe = tabla.length-1;
    int utolsoOszlopIndexe = tabla[0].length-1;

    /**
     * Constructs a new default state.  Everyone is on the east side.
     */
    public HuszarCsere() {
    }

    /**
     * Constructs a move state from a parent state
     */
    public HuszarCsere(HuszarCsere parent,
    		char[][] ujtabla) {
        super(parent);
        //this.tabla = ujtabla.clone();
        utolsoSorIndexe = ujtabla.length-1;
        utolsoOszlopIndexe=ujtabla[0].length-1;
        tabla = new char[utolsoSorIndexe+1][utolsoOszlopIndexe+1];
        for (int i=0;i<=utolsoSorIndexe;i++)
           for (int j=0;j<=utolsoOszlopIndexe;j++)
              tabla[i][j]=ujtabla[i][j];
    }



    /**
     * Returns a set of all possible moves from this state.
     */
    public Iterable<State> getPossibleMoves() {
        Set<State> moves = new HashSet<State>();
        char [][] ujtabla = new char[utolsoSorIndexe+1][utolsoOszlopIndexe+1];
        for (int regix=0;regix<=utolsoSorIndexe;regix++)
           for (int regiy=0;regiy<=utolsoOszlopIndexe;regiy++)
            for (int ujx=0;ujx<=utolsoSorIndexe;ujx++)
               for (int ujy=0;ujy<=utolsoOszlopIndexe;ujy++)
                   if (java.lang.Math.abs(ujx-regix)*java.lang.Math.abs(ujy-regiy)==2 &&
                       tabla[regix][regiy] != '_' &&
                       tabla[ujx][ujy] == '_')
                   {
                    for (int i=0;i<=utolsoSorIndexe;i++)
                     for (int j=0;j<=utolsoOszlopIndexe;j++)
                             ujtabla[i][j] = tabla[i][j];
                   ujtabla[ujx][ujy] = tabla[regix][regiy];
                   ujtabla[regix][regiy] = '_';
                   

                   HuszarCsere ujAllapot = new HuszarCsere(this,ujtabla);
                   moves.add(ujAllapot);
                   }
        return moves;
    }


    /**
     * The solution is specified as everyone being on the west side
     * @return true if this state is a solution
     */
    public boolean isSolution() {
        return
           tabla[0][0] == 'b' &&
           tabla[0][utolsoOszlopIndexe] == 'b' &&
           tabla[utolsoSorIndexe][0] == 'w' &&
           tabla[utolsoSorIndexe][utolsoOszlopIndexe] == 'w';
    }


 
    public double getHeuristic() {
        return 1.0;
    }
    @Override
    public boolean equals(Object o) {
        if (o==null || !(o instanceof HuszarCsere))
            return false;
        HuszarCsere h = (HuszarCsere)o;
        boolean talaltElterest = false;
        for (int i=0;i<=utolsoSorIndexe;i++)
           for (int j=0;j<=utolsoOszlopIndexe;j++)
              if(tabla[i][j]!=h.tabla[i][j]) talaltElterest = true;

        return !talaltElterest;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Arrays.deepHashCode(this.tabla);
        hash = 67 * hash + this.utolsoSorIndexe;
        hash = 67 * hash + this.utolsoOszlopIndexe;
        return hash;
    }

  
   @Override
    public String toString() {
       java.lang.StringBuilder s = new StringBuilder();
        for (int i=0;i<=utolsoSorIndexe;i++) {
             s.append("\n");
            for (int j=0; j<=utolsoOszlopIndexe; j++) s.append(tabla[i][j]);
        }
       s.append("\n-------------");
       return s.toString();
   }
}
