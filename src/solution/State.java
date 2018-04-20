package solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import search.ActionStatePair;
import org.apache.commons.math3.util.*; // mention this in report

public class State implements search.State {
	/* ATTRIBUTES */
		private ArrayList<Integer> northPeople, southPeople;
		private Bank raftLocation;
	/* MEMBERS */
		// constructor
			public State(ArrayList<Integer> people) {
				if(Solution.DESTINATION == Bank.NORTH) {
					northPeople = (ArrayList<Integer>)people.clone();
					southPeople = new ArrayList<Integer>();
				}
				else {
					northPeople = new ArrayList<Integer>();
					southPeople = (ArrayList<Integer>)people.clone();
				}
				raftLocation = oppositeBank(Solution.DESTINATION);
			}
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
				String result = "\tNorth: " + northPeople;
				if(raftLocation == Bank.NORTH)
					result += " Raft";
				result += "\n\tSouth: " + southPeople;
				if(raftLocation == Bank.SOUTH)
					result += " Raft\n";
				return result;
			}
			public boolean equals(State state) {
				if(northPeople.size() != state.getNorthPeople().size() || southPeople.size() != state.getSouthPeople().size() || raftLocation != state.getLocation())
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
			public ArrayList<Integer> getPeopleFrom(Bank bank) { return (bank == Bank.NORTH ? northPeople : southPeople); }
			public Bank getLocation() { return raftLocation; }
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
				System.out.println("==========================================");
				String test = "\tNorth: " + northPeople;
				if(raftLocation == Bank.NORTH)
					test += " Raft";
				test += "\n\tSouth: " + southPeople;
				if(raftLocation == Bank.SOUTH)
					test += " Raft";
				System.out.println(test);
				System.out.println("------------------------------------------");
				List<ActionStatePair> result = new ArrayList<ActionStatePair>();
				ArrayList<Integer> PeopleOnBank = (raftLocation == Bank.NORTH ? northPeople : southPeople);
				for(int i = 1; i < Solution.RAFT_SIZE; i++) {
					Combinations comb = new Combinations(PeopleOnBank.size(), Math.min(i, PeopleOnBank.size()));
					Iterator<int[]> iterator = comb.iterator();
					while(iterator.hasNext()) {
						int[] selected = iterator.next();
						System.out.print("  people selected: ");
						for(int element : selected)
							System.out.print(PeopleOnBank.get(element) + " ");
						int sum = 0;
						for(int nameIndex : selected)
							sum += PeopleOnBank.get(nameIndex);
						System.out.println("\t[sum: " + sum + "]");
						if(0 < selected.length && sum <= Solution.RAFT_MAX_WEIGHT) {
							System.out.println("    candidate");
							ArrayList<Integer> selectedItems = new ArrayList<Integer>();
							for(int nameIndex : selected)
								selectedItems.add(PeopleOnBank.get(nameIndex));
							Action action = new Action(selectedItems, oppositeBank(raftLocation));
							System.out.println("      " + action.toString());
							State newState = applyAction(action);
							if(newState.isValid())
								result.add(new ActionStatePair(action, newState));
						}
					}
				}
				return result;
			}
			public State applyAction(Action action) {
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
			public int hashCode() {
				return (raftLocation == Bank.NORTH ? 0 : 1) + Solution.sum(northPeople) + (Solution.sum(southPeople) * 1000);
			}
		// other
			private Bank oppositeBank(Bank current) { return (current == Bank.NORTH ? Bank.SOUTH : Bank.NORTH); }
}
