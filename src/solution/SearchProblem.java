package solution;

import search.Node;
import search.informed.BestFirstSearchProblem;

public class SearchProblem extends BestFirstSearchProblem {
	/* ATTRIBUTES */
		State start, goal;
	/* MEMBERS */
		public SearchProblem(State newStart, State newGoal) {
			super(newStart, newGoal);
			start = newStart;
			goal = newGoal;
		}
		@Override
		public double evaluation(Node node) {
			return heuristic(node.state);
		}
		/**
		 * This heuristic function estimate how far this state is from a goal.
		 * @return The remaining distance/cost of the current state to a goal.
		*/
		public double heuristic(search.State state) {
			int maxScore = Solution.sum(goal.getSouthPeople()), currentScore = Solution.sum(((State)state).getSouthPeople());
			return (double)currentScore / (double)maxScore;
		}
		@Override
		public boolean isGoal(search.State state) { return ((State)state).equals(goal); }
}