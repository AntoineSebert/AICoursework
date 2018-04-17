package solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import search.ActionStatePair;
import org.apache.commons.math3.util.*; // mention this in report

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
			public int getRaftWeight() { return Solution.sum(raft); }
		// accessors
			public boolean isValid() {
				for(int element : northPeople)
					if(element < 0)
						return false;
				for(int element : southPeople)
					if(element < 0)
						return false;
				return raft.size() <= Solution.RAFT_SIZE && getRaftWeight() <= Solution.RAFT_MAX_WEIGHT;
			}
			public List<ActionStatePair> successor() {
				List<ActionStatePair> result = new ArrayList<ActionStatePair>();
				ArrayList<Integer> PeopleOnBank = (raftLocation == Bank.NORTH ? northPeople : southPeople);
				for(int i = 0; i < Solution.RAFT_SIZE; i++) {
					Combinations comb = new Combinations(PeopleOnBank.size(), i);
					Iterator<int[]> iterator = comb.iterator();
					while(iterator.hasNext()) {
						int[] selected = iterator.next();
						int sum = 0;
						if(0 < selected.length) {
							for(int nameIndex : selected)
								sum += PeopleOnBank.get(nameIndex);
							if(sum < Solution.RAFT_MAX_WEIGHT) {
								ArrayList<Integer> selectedItems = new ArrayList<Integer>();
								for(int nameIndex : selected)
									selectedItems.add(nameIndex);
								Action action = new Action(selectedItems, oppositeBank(raftLocation));
								State newState = applyAction(action);
								if(newState.isValid())
									result.add(new ActionStatePair(action, newState));
							}
						}
					}
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
		// other
			private Bank oppositeBank(Bank current) { return (current == Bank.NORTH ? Bank.SOUTH : Bank.NORTH); }
}
