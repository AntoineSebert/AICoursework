package solution;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
	static public int RAFT_SIZE = 2;
	static public int RAFT_MAX_WEIGHT = 180;
	static public int NUM_PEOPLE = 4;

	@SuppressWarnings("serial")
	static public ArrayList<Integer> PEOPLE = new ArrayList<Integer>() {{
		add(100);
		add(90);
		add(50);
		add(30);
	}};

	public static void main(String[] args) {
		//promptValues();
		runTests();
	}

	private static void promptValues() {
		Scanner scan = new Scanner(System.in);

		System.out.println("RAFT_SIZE");
		RAFT_SIZE = scan.nextInt();

		System.out.println("RAFT_MAX_WEIGHT");
		RAFT_MAX_WEIGHT = scan.nextInt();

		System.out.println("NUM_PEOPLE");
		NUM_PEOPLE = scan.nextInt();
		PEOPLE.clear();
		for(int i = 0; i < NUM_PEOPLE; i++)
			PEOPLE.add(scan.nextInt());

		scan.close();
	}

	private static void runTests() {
		//testStateClass();
		//testActionClass();
		//testSuccessor();
		testSearch();
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

	@SuppressWarnings("serial")
	private static void testSearch() {
		RAFT_SIZE = 3;
		RAFT_MAX_WEIGHT = 180;
		NUM_PEOPLE = 7;
		State start = new State(
			new ArrayList<Integer>() {{
				add(100);
				add(80);
				add(60);
				add(40);
				add(70);
				add(50);
				add(90);
			}}
		);
		State goal = new State(
			new ArrayList<Integer>() {{ }},
			new ArrayList<Integer>() {{
				add(100);
				add(80);
				add(60);
				add(40);
				add(70);
				add(50);
				add(90);
			}},
			Bank.SOUTH
		);
		SearchProblem test = new SearchProblem(start, goal);
		test.search();
	}

	public static int sum(ArrayList<Integer> data) {
		int sum = 0;
		for(int element : data)
			sum += element;
		return sum;
	}
}