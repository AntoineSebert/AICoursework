package solution;

import java.util.ArrayList;
import java.util.Scanner;

import search.Path;

public class Solution {
	/* problem's default values */
		static public int RAFT_SIZE = 2;
		static public int RAFT_MAX_WEIGHT = 180;
		static public ArrayList<Integer> PEOPLE = createArrayList(100, 90, 50, 30);
		static public int NUM_PEOPLE = PEOPLE.size();
		static public Bank DESTINATION = Bank.NORTH;
	/* main */
		public static void main(String[] args) {
			//runTests();
			// change these values to customize problem statement
			initializeMainValues(3, 180, createArrayList(100, 80, 60, 40, 70, 50, 90), Bank.SOUTH);
			performSearch();
		}
	/* tests */
		private static void runTests() {
			testStateClass();
			testActionClass();
			testSuccessor();
		}
		private static void testSuccessor() {
			State test = new State(createArrayList(100, 80, 60, 40));
			System.out.println(test.successor().toString());
		}
		private static void testActionClass() {
			Action test = new Action(createArrayList(100), Bank.SOUTH);
			System.out.println(test.toString());
			State test2 = new State(createArrayList(100, 80)), test3 = test2.applyAction(test);
			System.out.println(test2.toString());
			System.out.println(test3.toString());
		}
		private static void testStateClass() {
			State test = new State(createArrayList(100, 80)),
				test2 = new State(createArrayList(100, 80), createArrayList(100, 80), Bank.SOUTH),
				test3 = new State(createArrayList(100, 80), createArrayList(100, 80), Bank.NORTH);
			System.out.println(test.equals(test2));
			System.out.println(test2.equals(test));
			System.out.println(test3.equals(test2));
			System.out.println(test3.equals(test3));
			test2 = new State(createArrayList(100, -80), createArrayList(100, 80), Bank.SOUTH);
			System.out.println(test2.isValid());
		}
	/* search */
		private static Path performSearch() {
			return new SearchProblem(new State(PEOPLE), new State(createArrayList(), PEOPLE, DESTINATION)).search();
		}
	/* utility */
		public static int sum(ArrayList<Integer> data) {
			int sum = 0;
			for(int element : data)
				sum += element;
			return sum;
		}
		public static void initializeMainValues(int raftSize, int maxWeight, ArrayList<Integer> people, Bank destination) {
			assert(0 < people.size());
			RAFT_SIZE = raftSize;
			RAFT_MAX_WEIGHT = maxWeight;
			PEOPLE = people;
			NUM_PEOPLE = people.size();
			DESTINATION = destination;
		}
		public static <T> ArrayList<T> createArrayList(T... data) {
			ArrayList<T> container = new ArrayList<T>();
			for(T element : data)
				container.add(element);
			return container;
		}
}