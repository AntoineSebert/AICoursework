package solution;

import java.util.Scanner;

import search.*;
import search.informed.*;
//import org.apache.commons.math3.util.*;

public class Solution {
	static public int RAFT_SIZE = 2;
	static public int RAFT_MAX_WEIGHT = 180;
	static public int NUM_PEOPLE = 4;
	static public int PEOPLE[] = { /* Adam (100.0) Betty (90.0) Claire (50.0) Dave (30.0) */ };
	
	public static void main(String[] args) {
		promptValues();
	}
	
	private static void promptValues() {
		Scanner scan = new Scanner(System.in);

		System.out.println("RAFT_SIZE");
		RAFT_SIZE = scan.nextInt();

		System.out.println("RAFT_MAX_WEIGHT");
		RAFT_MAX_WEIGHT = scan.nextInt();

		System.out.println("NUM_PEOPLE");
		NUM_PEOPLE = scan.nextInt();
		for(int i = 0; i < NUM_PEOPLE; i++)
			PEOPLE[i] = new Person(scan.nextInt());

		scan.close();
	}
}
