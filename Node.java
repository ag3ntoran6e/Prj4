package pack1;

/**
 * The Node class represents a node in a linked list.
 * 
 * @author Morrie Cunningham
 *
 * THIS CLASS IS ALL DONE FOR YOU. DO NOT MAKE ANY CHANGES.
 */
public class Node {

	private String data;
	private Node next;
	
	/**
	 * Initializes a newly constructed Node object.
	 * 
	 * @param data data that this node contains.
	 */
	public Node(String data) {
		this.data = data;
		this.next = null;
	}
	
	/**
	 * Returns the data at this node.
	 * 
	 * @return data at this node
	 */
	public String getData() {
		return data;
	}

	/**
	 * Sets the data value of this node to the specified argument.
	 * 
	 * @param data data stored in this node
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * Returns the reference to the next node (could be null).
	 * 
	 * @return reference to the next node
	 */
	public Node getNext() {
		return next;
	}

	/**
	 * Sets the next node that this node refers to.
	 * 
	 * @param INode next node that this node refers to
	 */
	public void setNext(Node next) {
		this.next = next;
	}
	
	/**
	 * Returns a string representation of the value at this object.
	 * 
	 * @return a string representation of the value of this object
	 */
	public String toString() {
		return data;
	}

}
