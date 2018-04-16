package solution;

import java.util.ArrayList;
import java.util.List;
import search.ActionStatePair;

public class State implements search.State {
	/* ATTRIBUTES */
		private int northPeople, southPeople;
		private Bank raftLocation;
	/* MEMBERS */
		// constructor
			public State(int np, int sp, Bank location) {
				northPeople = np;
				southPeople = sp;
				raftLocation = location;
			}
		// operators
			public String toString() {
				String result = "North: " + northPeople;
				if(raftLocation == Bank.NORTH)
					result += " Raft";
				result += "\nSouth: " + southPeople;
				if(raftLocation == Bank.SOUTH)
					result += " Raft";
				return result;
			}
			public boolean equals(State state) {
				return northPeople == state.getNorthPeople() && southPeople == state.getSouthPeople() && raftLocation == state.getLocation();
			}
		// getters
			public int hashCode() { return northPeople * 10 + southPeople; }
			public int getNorthPeople() { return northPeople; }
			public int getSouthPeople() { return southPeople; }
			public Bank getLocation() { return raftLocation; }
		// accessors
			public boolean isInvalid() { return northPeople < 0 || southPeople < 0; }
			public List<ActionStatePair> successor() {
				List<ActionStatePair> result = new ArrayList<ActionStatePair>();	// I chose to use an ArrayList object as the list will be short.

				if(this.isInvalid())	// if current state is invalid
					return result;	// return an empty set
				int numPeopleOnBank = (raftLocation == Bank.NORTH ? northPeople : southPeople);
				
				// The main loops going through all combinations of M. Note that we start from the max value of M down to 0.
				// This makes us generate actions that prefer moving more M than fewer.
				for(int m = Math.min(numPeopleOnBank, MissionariesCannibals.RAFT_SIZE); m >= 0; m--)
						// You need at least 1 person on the raft, and not more than raft size.
						// If M is within the acceptable range, create an action.
						if(m <= MissionariesCannibals.RAFT_SIZE && m > 0) {
							Action action = new Action(m, oppositeBank(this.raftLocation));
							State nextState = this.applyAction(action);
							if(!nextState.isInvalid())
								result.add(new ActionStatePair(action, nextState));
						}
				return result;
			}
		// modifiers
			private Bank oppositeBank(Bank current) { return (current == Bank.NORTH ? Bank.SOUTH : Bank.NORTH); }
	// modifier ? change the return to "this = "
	public State applyAction(Action action) {
		if(action.toBank == Bank.NORTH)
			return new State(northPeople + action.missionaries, southPeople - action.missionaries, Bank.NORTH);
		return new State(northPeople - action.missionaries, southPeople + action.missionaries, Bank.SOUTH);
	}
}
