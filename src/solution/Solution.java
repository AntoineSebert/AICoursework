package solution;

import java.util.ArrayList;
import java.util.Scanner;

import search.Path;

public class Solution {
	/* problem's default values */
		static public int RAFT_SIZE = 2;
		static public int RAFT_MAX_WEIGHT = 180;
		static public ArrayList<Integer> PEOPLE = new ArrayList<Integer>() {{
			add(100);
			add(90);
			add(50);
			add(30);
		}};
		static public int NUM_PEOPLE = PEOPLE.size();
		static public Bank DESTINATION = Bank.NORTH;
	/* main */
		public static void main(String[] args) {
			//runTests();
			// change these values to customize problem statement
			initializeMainValues(3, 180, new ArrayList<Integer>() {{
				add(100);
				add(80);
				add(60);
				add(40);
				add(70);
				add(50);
				add(90);
			}}, Bank.SOUTH);
			performSearch();
		}
	/* tests */
		private static void runTests() {
			testStateClass();
			testActionClass();
			testSuccessor();
		}
		@SuppressWarnings("serial")
		private static void testSuccessor() {
			State test = new State(
				new ArrayList<Integer>() {{
					add(100);
					add(80);
					add(60);
					add(40);
				}}
			);
			System.out.println(test.successor().toString());
		}
		@SuppressWarnings("serial")
		private static void testActionClass() {
			Action test = new Action(
				new ArrayList<Integer>() {{
					add(100);
				}},
				Bank.SOUTH
			);
			System.out.println(test.toString());
			State test2 = new State(
				new ArrayList<Integer>() {{
					add(100);
					add(80);
				}}
			), test3 = test2.applyAction(test);
			System.out.println(test2.toString());
			System.out.println(test3.toString());
		}
		@SuppressWarnings("serial")
		private static void testStateClass() {
			State test = new State(
				new ArrayList<Integer>() {{
					add(100);
					add(80);
				}}
			);
			State test2 = new State(
				new ArrayList<Integer>() {{
					add(100);
					add(80);
				}},
				new ArrayList<Integer>() {{
					add(100);
					add(80);
				}},
				Bank.SOUTH
			);
			System.out.println(test.toString());
			System.out.println(test2.toString());
			System.out.println(test.equals(test2));
			System.out.println(test2.equals(test));
			test2 = new State(
				new ArrayList<Integer>() {{
					add(100);
					add(-80);
				}},
				new ArrayList<Integer>() {{
					add(100);
					add(80);
				}},
				Bank.SOUTH
			);
			System.out.println(test2.isValid());
		}
	/* search */
		private static Path performSearch() {
			return new SearchProblem(new State(PEOPLE), new State(new ArrayList<Integer>() {{ }}, PEOPLE, DESTINATION)).search();
		}
	/* utility */
		public static int sum(ArrayList<Integer> data) {
			int sum = 0;
			for(int element : data)
				sum += element;
			return sum;
		}
		public static void initializeMainValues(int raftSize, int maxWeight, ArrayList<Integer> people, Bank destination) {
			RAFT_SIZE = raftSize;
			RAFT_MAX_WEIGHT = maxWeight;
			PEOPLE = people;
			NUM_PEOPLE = people.size();
			DESTINATION = destination;
		}
}