package search;




public abstract class AbstractState implements State {

	
	private State parent = null;
	private double distance = 0;
	
	public AbstractState() {
	}
	
	public AbstractState(State parent) {
		this.parent = parent;
		if (parent != null) this.distance = parent.getDistance() + 1;
                else distance = 0;
	}

	public State getParent() {
		return parent;
	}
	
	public double getDistance(){
		return distance;
	}

}
