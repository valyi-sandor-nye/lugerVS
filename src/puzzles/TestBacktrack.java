/**
 * 
 */
package puzzles;

import java.util.List;

import search.Solver;
import search.State;
import search.BackTrackSolver;
import search.BestFirstSolver;
import search.DepthFirstSolver;
import search.BreadthFirstSolver;
/**
 * @author valyis@nyf.hu
 */
public class TestBacktrack {
	
	private static void trySolver(State initialState, Solver solver) {
		System.out.println("Megoldó program: "+solver.getClass());
		List<State> solution = solver.solve(initialState);
		System.out.println("A megoldás:");
		if (solution == null) {
			System.out.println(" Nem talált.");
		} else {
			for (State s : solution)
				System.out.println("    "+s);
			System.out.println("   "+solution.size()+" állapotot érint a megoldó út.");
		}
	}
	private static void trySolvers(State initialState) {
		trySolver(initialState, new BackTrackSolver());
		trySolver(initialState, new BestFirstSolver());
		trySolver(initialState, new DepthFirstSolver());
		trySolver(initialState, new BreadthFirstSolver());
                
        }
	public static void main(String[] args) {
            
            
		System.out.println("8-királynő probléma\n");
		trySolvers(new Kiralyno8());
	}
}
