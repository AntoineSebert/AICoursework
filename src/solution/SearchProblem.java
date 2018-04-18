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
			double sumGoal = Solution.sum(goal.getPeopleFrom(goal.getLocation())), sumState = Solution.sum(((State)state).getPeopleFrom(goal.getLocation()));
			return (sumGoal / sumState) * 100;
		}
		@Override
		public boolean isGoal(search.State state) {
			if(((State)state).equals(goal))
				System.out.println("GOAL REACHED");
			return ((State)state).equals(goal);
		}
}