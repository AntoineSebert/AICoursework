package search;

/**
 * This class defines a node in the search algorithm.
 * Remember that a node is a book-keeping structure.
 * Apart from the state of the problem it has other attributes.
 * e.g. The parent pointer points back to the parent node so that we can trace the path when a goal is found.
 * The cost attribute is needed if we want to optimize on the cost instead of just depth of the solution.
 * The action attribute keeps track of the action which brings us to this state/node.
 * The depth attribute tells us the level/depth of this node in the search tree.
 * This is helpful if we want to limit the depth of the search (e.g. in a depth-limited search).
 */

public class Node {
	/**
	 * This attribute stores/points to the state of the problem this node represents.
	 */
	public State state;
	/**
	 * This point to the parent node. We need this to trace back the whole path (from to goal) when we find a goal.
	 */
	public Node parent;	//pointing to parent node of path
	
	/*
	 * ***Update on 2015/12/03:
	 * 		No longer storing cost of path in a node as it leads to a problem when another shorter path is found to the same state.
	 * 		Instead of storing the cost from root to node, now we use a getCost() method to calculate the cost of the path.
	 * 		This ensures that the cost in nodes are correct all the time.
	 * 
	 * 		Also no longer storing the depth of a node as an attribute but calculate it by a recursive method.
	 */
	
	/**
	 * This is the action that brings us from the parent to this node.
	 * In the root node of the tree, this attribute must be null as there is no parent node.
	 */
	public Action action;	//the action that takes us here
	
	/**
	 * Creates a {@link Node} object.
	 * @param state The state of the problem this node represents.
	 * @param parent The parent node leading to this node.
	 * @param action The action that leads us from the parent to this node.
	 */
	public Node(State state,Node parent,Action action) {
		this.state = state;
		this.parent = parent;
		this.action = action;
	} //end method
	
	/**
	 * Return the cost of the path from the root node to this node.
	 * @return	The cost of the path (i.e. sum of all actions) from the root to this node.
	 */
	public double getCost() {
		if (this.parent == null)	//this is the root node
			return 0.0;			//0 cost to here
		return this.action.cost + this.parent.getCost();	//otherwise recursively calculate the cost up to the root
	} //end method
	
	/**
	 * Return the depth of this node.
	 * @return	The depth of the node from root. The root node has a depth of 0.
	 */
	public int getDepth() {
		if (this.parent == null)	//this is the root node
			return 0;			//depth is 0
		return this.parent.getDepth() + 1;
	} //end method
} //end class
