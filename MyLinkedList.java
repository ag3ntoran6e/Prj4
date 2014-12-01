package pack1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.*;

/**
 * The MyLinkedList class implements a simple linked list.
 * 
 * @author Morrie Cunningham
 *
 */
public class MyLinkedList implements ILinkedList {

	private Node front;
	private Node rear;
	private Node current;
	private int size;

	/**
	 * Initializes a newly constructed MyLinkedList object.
	 */
	public MyLinkedList() {
		front = null;
		rear = null;
		current = null;
		size = 0;
	}

	/**
	 * Adds the specified element to the end of the list.
	 * 
	 * @param element the element to add to the list
	 * 
	 * @throws NullPointerException if the specified element is null
	 */
	@Override
	public void add(String element) {
		// COMPLETE THIS METHOD
		if(element.equals(null))
			throw new NullPointerException();
		if(front == null) 
			rear = front = new Node(element);
		else {
			rear.setNext(new Node(element));
			rear = rear.getNext();
		}
		size++;
	}

	/**
	 * Inserts the given element at the specified position in the list.
	 * 
	 * @param index index at which the specified element is to be inserted
	 * @param element element to be inserted
	 * 
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
	 * @throws NullPointerException if the specified element is null
	 */
	@Override
	public void add(int index, String element) {
		// COMPLETE THIS METHOD

		// parameters are invalid
		if(element == null) 
			throw new NullPointerException();
		else if(index < 0 || index > size()) 
			throw new IndexOutOfBoundsException();

		Node add;

		// list is empty
		if(size() == 0) {
			add = new Node(element);
			front = rear = add;
		}

		// target at top of list
		else if(index == 0) {
			Node temp = front;
			add = new Node(element);
			front = add;
			add.setNext(temp);
		}

		// target is at the end of list
		else if (index == size) {
			Node temp = front;

			while(temp.getNext() != null) {
				temp = temp.getNext();
			}

			add = new Node(element);
			temp.setNext(add);
			add.setNext(null);
			rear = add;
		}

		// target is in the middle of the list
		else {
			Node temp = front;

			for(int i = 0; i < index-1; i++) {
				temp = temp.getNext();
			}

			add = new Node(element);

			add.setNext(temp.getNext());
			temp.setNext(add);
		}

		current = add;
		size++;


		//		if(index < 0 || index > size) 
		//			throw new IndexOutOfBoundsException();
		//
		//		if(element.equals(null))
		//			throw new NullPointerException();
		//
		//		if(index == 0 && front == null)
		//			front = rear = add;
		//
		//		else {
		//			for(int i = 0; i < index-1; i++) {
		//				temp = temp.getNext();
		//			}
		//			if(temp == front) {
		//				front = add;
		//				add.setNext(temp);
		//			} else {
		//				add.setNext(temp.getNext());
		//				temp.setNext(add);
		//			}
		//		}
		//		current = add;
		//		size++;
	}

	/**
	 * Removes the element at the specified position in this list.
	 * 
	 * @param index the index of the element to be removed
	 * 
	 * @return the element previously at the specified position
	 * 
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
	 */
	@Override
	public String remove(int index) {
		// REPLACE THE LINE BELOW WITH YOUR CODE

		if(index < 0 || index >= size()) 
			throw new IndexOutOfBoundsException();

		Node delete;

		// no list
		if(size() ==0) 
			return null;

		// target is only element in list
		else if(size() == 1) {
			delete = front;
			front = rear = current = null;
		}

		// target in front
		else if(index == 0) {
			delete = front;
			front = front.getNext();

			if(current == delete) 
				current = delete.getNext();
		}

		// target in rear
		else if(index == (size() - 1)) {
			delete = rear;
			Node temp = front;

			while(temp.getNext().getNext() != null) {
				temp = temp.getNext();
			}

			temp.setNext(null);
			
			rear = current = temp;
		}

		// target is in list larger than 2 elements
		else {
			// finds the value
			Node temp = front;

			for (int i = 0; i < index - 1; i++) {
				temp = temp.getNext();
			}

			delete = temp.getNext();

			// deletes content on given line
			temp.setNext(delete.getNext());
			
			if(current == delete) 
				current = delete.getNext();
			
//			// finds the value
//			delete = front;
//			for(int i = 0; i < index - 1; i++) {
//				delete = delete.getNext();
//			}
//
//			// deletes content on given line
//			delete.setNext(delete.getNext());
		}

		// Decrements size
		size--;

		//returns string of deleted input
		return delete.getData();


		//		Node temp = front;
		//
		//		if(front == null)
		//			return null;
		//		
		//		if(index < 0 || index >= size()) 
		//			throw new IndexOutOfBoundsException();
		//
		//		if(index != 0) {
		//			for(int i = 0; i < index; i++) {
		//				temp = temp.getNext();
		//			}
		//			temp.setNext(temp.getNext());
		//			size--;
		//			return temp.getData().toString();
		//		} 
		//		size--;
		//		return temp.getData().toString();
	}

	/**
	 * Returns the element at the specified position in this list.
	 * Return null if the index is trout of range (index < 0 || index >= size())
	 * 
	 * @param index index of the element to return
	 * 
	 * @return the element at the specified position in this list
	 * 
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
	 */
	@Override
	public String get(int index) {
		// REPLACE THE LINE BELOW WITH YOUR CODE

		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}

		Node temp = front;
		for(int i = 0; i < index; i++) {
			temp = temp.getNext();
		}

		return temp.getData();
	}

	public int get(String s) {
		Node temp = front;

		for(int i = 0; i < size; i++) {
			if(temp.getData().equals(s)) {
				return i;
			}
			temp = temp.getNext();
		}
		return -1;
	}

	public Node getFront() {
		return this.front;
	}

	/**
	 * Returns true if this list contains no elements.
	 * 
	 * @return true if this list contains no elements and false otherwise
	 */
	@Override
	public boolean isEmpty() {
		// ALREADY DONE FOR YOU :-)
		return size == 0;
	}

	/**
	 * Returns the number of elements in this list.
	 * 
	 * @return the number of elements in this list
	 */
	@Override
	public int size() {
		// ALREADY DONE FOR YOU :-)
		return size;
	}

	/**
	 * Removes all of the elements from this list. The list will be empty after this call returns.
	 */
	@Override
	public void clear() {
		// ALREADY DONE FOR YOU :-)
		front = null;
		rear = null;
		size = 0;
	}

	public Node getCurrent() {
		return current;
	}

	public void setCurrent(Node current) {
		this.current = current;
	}

	public void reverseList() {
		if(size() > 1) {
			Stack<Node> stack = new Stack<Node>();
			Node temp = front;
			front = rear = null;
			size = 0;

			while(temp != null) {
				stack.push(temp);
				temp = temp.getNext();
			}

			front = temp = stack.pop();
			size++;

			while(!stack.empty()) {
				temp.setNext(stack.pop());
				temp = temp.getNext();
				size++;
			}
			rear = temp;
		}
	}

	public void display(int start, int end) throws EditorException {

		if(size() == 0) 
			throw new EditorException("The file is currently empty.");
		else if(start > end) 
			throw new EditorException("The first position must be " + 
					"before the second position.");

		// the finishing position falls out of the limits of the file
		else if(end > start) 
			end = size;

		String all = "";
		Node temp = front;
		int n = String.valueOf(size).length();
		for(int i = start; i <= end; i++) {
			all += String.format("%1$" + n + "s", i);

			if(temp == current)
				all += ">";
			else 
				all += " ";

			all += "| " + temp.getData();

			if(i != end)
				all += "\n";

			temp = temp.getNext();
		}
		System.out.println(all);
	}
	
	public static void main(String[] args) throws EditorException {
		MyLinkedList list = new MyLinkedList();
		list.add("one");
		list.add("two");
		list.add("three");
		System.out.println(list.get(1));
	}
}
