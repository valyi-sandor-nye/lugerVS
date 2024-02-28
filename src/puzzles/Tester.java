/**
 * 
 */
package puzzles;

import java.util.List;

import puzzles.MissionaryState;
import puzzles.SlidingTileState;
import puzzles.WaterJugState;
import puzzles.FarmerWolfGoatState;

import search.AbstractSolver;
import search.BreadthFirstSolver;
import search.DepthFirstSolver;
import search.BestFirstSolver;
import search.State;


/**
 * This class tests the various problems with Depth, Breadth, and Best-first
 * search, calculating the runtime and printing out the solution along with some
 * statistics (time spent, number of states visited, and length of solution).
 * 
 * This can be run from the java commandline with:
 * 	java cello.unm.cs427.puzzles.Tester
 * 
 * @author Marcello
 */
public class Tester {
	
	private static void trySolver(State initialState, AbstractSolver solver) {
		System.out.println("Solving with "+solver);
		List<State> solution = solver.solve(initialState);
		System.out.println("  States visited: "+solver.getVisitedStateCount());
		System.out.println("  Solution:");
		if (solution == null) {
			System.out.println("    None found.");
		} else {
			for (State s : solution)
				System.out.println("    "+s);
			System.out.println("   "+solution.size()+" states(s)");
		}
	}
	private static void trySolvers(State initialState) {
		trySolver(initialState, new DepthFirstSolver());
		trySolver(initialState, new BreadthFirstSolver());
		trySolver(initialState, new BestFirstSolver());
	}
	public static void main(String[] args) {
		trySolvers(new Kiralyno8());
		System.out.println("---------------------------------------------------------------");
		System.out.println("Farmer Wolf Goat Cabbage Puzzle");
		System.out.println();
		trySolvers(new FarmerWolfGoatState());
		System.out.println("---------------------------------------------------------------");
		System.out.println("Sliding Tile Puzzle");
		System.out.println();
		trySolvers(new SlidingTileState());
		System.out.println("---------------------------------------------------------------");
		System.out.println("Missionary and Cannibal Problem");
		System.out.println();
		trySolvers(new MissionaryState());
		System.out.println("---------------------------------------------------------------");
		System.out.println("Water Jugs Problem");
		System.out.println();
		trySolvers(new WaterJugState());
		System.out.println("---------------------------------------------------------------");
	}
}
