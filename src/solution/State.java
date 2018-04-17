package solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import search.ActionStatePair;

public class State implements search.State {
	/* ATTRIBUTES */
		private ArrayList<Integer> northPeople, southPeople, raft;
		private Bank raftLocation;
	/* MEMBERS */
		// constructor
			@SuppressWarnings("unchecked")
			public State(ArrayList<Integer> people) {
				northPeople = (ArrayList<Integer>)people.clone();
				southPeople = new ArrayList<Integer>();
				raftLocation = Bank.NORTH;
			}
			@SuppressWarnings("unchecked")
			public State(ArrayList<Integer> np, ArrayList<Integer> sp, Bank location) {
				for(int element : np)
					assert(0 < element);
				for(int element : sp)
					assert(0 < element);
				northPeople = (ArrayList<Integer>)np.clone();
				southPeople = (ArrayList<Integer>)sp.clone();
				raftLocation = location;
			}
		// operators
			public String toString() {
				String result = "North: " + northPeople;
				if(raftLocation == Bank.NORTH)
					result += " Raft";
				result += "\tSouth: " + southPeople;
				if(raftLocation == Bank.SOUTH)
					result += " Raft";
				return result;
			}
			public boolean equals(State state) {
				if(northPeople.size() != state.getNorthPeople().size() || southPeople.size() != state.getSouthPeople().size())
					return false;
				Collections.sort(northPeople);
				Collections.sort(state.getNorthPeople());
				Collections.sort(southPeople);
				Collections.sort(state.getSouthPeople());

				for(int i = 0; i < northPeople.size(); i++)
					if(northPeople.get(i) != state.getNorthPeople().get(i))
						return false;
				for(int i = 0; i < southPeople.size(); i++)
					if(southPeople.get(i) != state.getSouthPeople().get(i))
						return false;
				return true;
			}
		// getters
			public ArrayList<Integer> getNorthPeople() { return northPeople; }
			public ArrayList<Integer> getSouthPeople() { return southPeople; }
			public Bank getLocation() { return raftLocation; }
			public int getRaftWeight() {
				int sum = 0;
				for(int d : raft)
					sum += d;
				return sum;
			}
		// accessors
			public boolean isValid() {
				for(int element : northPeople)
					if(element < 0)
						return false;
				for(int element : southPeople)
					if(element < 0)
						return false;
				return true;
			}
			public List<ActionStatePair> successor() {
				List<ActionStatePair> result = new ArrayList<ActionStatePair>();
				ArrayList<Integer> PeopleOnBank = (raftLocation == Bank.NORTH ? northPeople : southPeople);
				
				// The main loops going through all combinations of M. Note that we start from the max value of M down to 0.
				// This makes us generate actions that prefer moving more M than fewer.
				for(int m = Math.min(PeopleOnBank.size(), Solution.RAFT_SIZE); m >= 0; m--)
					// You need at least 1 person on the raft, and not more than raft size. If M is within the acceptable range, create an action.
					if(0 < m && m <= Solution.RAFT_SIZE && getRaftWeight() < Solution.RAFT_MAX_WEIGHT) {
						/*
						Action action = new Action(m, oppositeBank(raftLocation));
						State newState = applyAction(action);
						if(newState.isValid())
							result.add(new ActionStatePair(action, newState));
						*/
					}
				return result;
			}
			public State applyAction(Action action) {
				@SuppressWarnings("unchecked")
				ArrayList<Integer> newNorthPeople = (ArrayList<Integer>)northPeople.clone(), newSouthPeople = (ArrayList<Integer>)southPeople.clone();
				if(action.getBank() == Bank.NORTH) {
					newNorthPeople.addAll(action.getPeople());
					newSouthPeople.removeAll(action.getPeople());
				}
				else {
					newNorthPeople.removeAll(action.getPeople());
					newSouthPeople.addAll(action.getPeople());
				}
				return new State(newNorthPeople, newSouthPeople, action.getBank());
			}
		// modifiers
			private Bank oppositeBank(Bank current) { return (current == Bank.NORTH ? Bank.SOUTH : Bank.NORTH); }
}
