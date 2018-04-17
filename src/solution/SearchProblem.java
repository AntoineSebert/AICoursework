package solution;

import search.Node;
import search.State;
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
		public double heuristic(State currentState) {
			double result = 0.0;
			
			// Explore different ways to estimate the distance of the current state to the goal state.
			// Note: You can always access the goal state by "this.goalState" as it is stored as an attribute.
			/*
			int tiles[][] = ((State)currentState).tiles;
			int goalTiles[][] = ((State)goalState).tiles;
			*/
			// The current implementation is "counting the number of misplaced tiles".
			// Note that we only look for tiles in counting mismatches, not the space.
			// Including the space in counting mismatches will over-estimate.
			/*
			for (int row = 0; row < 3; row++)
				for (int col = 0; col < 3; col++)
					if (tiles[row][col] != 0 && 					//if current tile is not a space
						tiles[row][col] != goalTiles[row][col])		//and it does not match the one in goal
						result++;									//increment count by 1
			*/
			return result;
		}
		@Override
		public boolean isGoal(State state) { return state.equals(goal); }
}