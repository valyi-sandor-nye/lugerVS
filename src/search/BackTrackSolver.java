package search;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defines a simple backtrack solver.
 * 
 * @author valyis
 */

public class BackTrackSolver implements Solver {
    public ArrayList<State> solve(State initialState) {
        List<State> children = new ArrayList<State>();
        Node actualNode = new Node();
        actualNode.parent = null;
        actualNode.numberOfUsedUpChildren = 0;
        actualNode.state = initialState;
        while(true) {
            if (actualNode == null) break;
            if (actualNode.state.isSolution()) break;
            children.clear();
            for (State s: actualNode.state.getPossibleMoves())
                        children.add(s);
            if (children.size() > actualNode.numberOfUsedUpChildren) {
                State nextState = children.get((actualNode.numberOfUsedUpChildren)++);
                Node newNode = new Node();
                newNode.state = nextState;
                newNode.parent = actualNode;
                newNode.numberOfUsedUpChildren=0;
                actualNode = newNode;
                }
                else actualNode = actualNode.parent;
        }
        if (actualNode != null) {
            return pathTo(actualNode);
        }
        else return null;
    }       
        
        protected ArrayList<State> pathTo (Node node) {
            ArrayList<State> solution = new ArrayList<State>();
            solution.add(node.state);
            while(node.parent != null) {
                node = node.parent;
                solution.add(0, node.state);
            }
        return solution;
        }
        
 

}
