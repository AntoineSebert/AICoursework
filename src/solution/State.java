package solution;

import java.util.ArrayList;
import java.util.List;

import lab02.mc.McAction;
import lab02.mc.MissionariesCannibals;
import search.ActionStatePair;

public class State implements search.State {
	/* ATTRIBUTES */
		private int northPeople, southPeople, totalPeople;
		private Bank raftLocation;
	/* MEMBERS */
		// constructor
			public State(int np, int sp, Bank location) {
				northPeople = np;
				southPeople = sp;
				totalPeople = np = sp;
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
				return northPeople == State.northPeople && southPeople == State.southPeople && raftLocation == State.raftLocation;
			}
		// getters
			public int hashCode() { return northPeople * 10 + southPeople; }
		// accessors
			public boolean isInvalid() { return northPeople < 0 || southPeople < 0; }
			public List<ActionStatePair> successor() {
				List<ActionStatePair> result = new ArrayList<ActionStatePair>();	//I chose to use an ArrayList object as the list will be short.

				// This should not happen but just to be safe
				if (this.isInvalid())	// if current state is invalid
					return result;	// return an empty set
				
				// I changed the code here a bit to make it easier to understand.
				int numPeopleOnBank = 0;	//the number of M on the current bank
				int numCannibalsOnBank = 0;		//the number of C
				
				// set up values of these 2 variables properly depending on where the raft is
				numPeopleOnBank = (raftLocation == Bank.NORTH ? northPeople : southPeople);
				
				//
				// The main loops going through all combinations of M and C.
				// Note that we start from the max value of M and C down to 0.
				// This makes us generate actions that prefer moving more M and C than fewer.
				//
				// For either M or C, the max number we can move is the minimum between the no. of M/C on the current bank and the raft size.
				//
				for (int m = Math.min(numPeopleOnBank, MissionariesCannibals.RAFT_SIZE); m >= 0; m--)
					for (int c = Math.min(numCannibalsOnBank, MissionariesCannibals.RAFT_SIZE); c >= 0; c--) {
						//
						// You need at least 1 person on the raft, and not more than raft size.
						// If M+C is within the acceptable range, create an action.
						//
						if (m + c <= MissionariesCannibals.RAFT_SIZE && m + c > 0) {
							Action action = new Action(m, c, oppositeBank(this.raftLocation));
							State nextState = this.applyAction(action);
							if (!nextState.isInvalid())	//check for valid combination of M&C on both sides
								result.add(new ActionStatePair(action, nextState));
						}
					}
				return result;
			}
		// modifiers
			private Bank oppositeBank(Bank current) { return (current == Bank.NORTH ? Bank.SOUTH : Bank.NORTH); }
	// modifier ? change the return to "this = "
	public State applyAction(Action action) {
		if (action.toBank == Bank.NORTH)
			return new State(northPeople + action.missionaries, southPeople - action.missionaries, Bank.NORTH);
		return new State(northPeople - action.missionaries, southPeople + action.missionaries, Bank.SOUTH);
	}
}
