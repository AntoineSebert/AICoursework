package solution;

import java.util.ArrayList;
import java.util.Scanner;

import search.*;
import search.informed.*;
//import org.apache.commons.math3.util.*;

public class Solution {
	static public int RAFT_SIZE = 2;
	static public int RAFT_MAX_WEIGHT = 180;
	static public int NUM_PEOPLE = 4;
	static public ArrayList<Integer> PEOPLE = new ArrayList<Integer>() {{
		add(100);
		add(90);
		add(50);
		add(30);
	}};

	public static void main(String[] args) {
		//promptValues();
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
}
