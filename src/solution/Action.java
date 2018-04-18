package solution;

import java.util.ArrayList;

public class Action extends search.Action {
	/* ATTRIBUTES */
		// The number of persons moving in this action
		public ArrayList<Integer> people;
		// We are moving the raft to this bank
		public Bank toBank;
	/* MEMBERS */
		// constructor
			/*
			 * Create an Action object.
			 * @param newPeople The number of people to move.
			 * @param to The destination of the raft.
			 */
			public Action(ArrayList<Integer> newPeople, Bank to) {
				people = (ArrayList<Integer>)newPeople.clone();
				toBank = to;
			}
		// getters
			public ArrayList<Integer> getPeople() { return people; }
			public Bank getBank() { return toBank; }
		// operators
			public String toString() {
				String result = (this.toBank == Bank.NORTH ? "South->North " : "North->South:");
				for(int element : people)
					result += " " + element;
				return result;
			}
}