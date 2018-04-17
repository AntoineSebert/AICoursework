package solution;

import search.Node;
import search.State;
import search.informed.BestFirstSearchProblem;

public class SearchProblem extends BestFirstSearchProblem {

	public SearchProblem(State start, State goal) {
		super(start, goal);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double evaluation(Node node) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isGoal(State state) {
		// TODO Auto-generated method stub
		return false;
	}

}
