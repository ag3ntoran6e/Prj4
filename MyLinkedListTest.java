package pack1;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * MyLinkedListTest class contains unit tests for MyLinkedList class.
 * 
 * @author Morrie Cunningham
 *
 * THIS CLASS IS ALL DONE FOR YOU. DO NOT MAKE ANY CHANGES.
 */
public class MyLinkedListTest {
	
	@Test
	public void test_get_ValidIndex() {
		MyLinkedList list = new MyLinkedList();
		list.add("Line 1");
		list.add("Line 2");
		list.add("Line 3");
		assertEquals(3, list.size());
		assertEquals("Line 1", list.get(0));
		assertEquals("Line 2", list.get(1));
		assertEquals("Line 3", list.get(2));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void test_get_InvalidIndex_1() {
		MyLinkedList list = new MyLinkedList();
		list.add("Line 1");
		list.add("Line 2");
		list.add("Line 3");
		list.get(-1);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void test_get_InvalidIndex_2() {
		MyLinkedList list = new MyLinkedList();
		list.add("Line 1");
		list.add("Line 2");
		list.add("Line 3");
		list.get(3);
	}
	
	@Test
	public void test_Add_NonNullValue() {
		MyLinkedList list = new MyLinkedList();
		assertTrue(list.isEmpty());
		list.add("Line 1");
		list.add("Line 2");
		list.add("Line 3");
		assertFalse(list.isEmpty());
		assertEquals(3, list.size());
		assertEquals("Line 1", list.get(0));
		assertEquals("Line 2", list.get(1));
		assertEquals("Line 3", list.get(2));
	}
	
	@Test(expected=NullPointerException.class)
	public void test_Add_NullValue() {
		MyLinkedList list = new MyLinkedList();
		assertTrue(list.isEmpty());
		list.add(null);
	}

	@Test
	public void test_Add_ByPositon_Front() {
		MyLinkedList list = new MyLinkedList();
		assertTrue(list.isEmpty());
		list.add("Line 2");
		list.add("Line 3");
		list.add("Line 4");
		list.add(0, "Line 1");
		assertFalse(list.isEmpty());
		assertEquals(4, list.size());
		assertEquals("Line 1", list.get(0));
		assertEquals("Line 2", list.get(1));
		assertEquals("Line 3", list.get(2));
		assertEquals("Line 4", list.get(3));
	}
	
	@Test
	public void test_Add_ByPositon_End() {
		MyLinkedList list = new MyLinkedList();
		assertTrue(list.isEmpty());
		list.add("Line 1");
		list.add("Line 2");
		list.add("Line 3");
		list.add(3, "Line 4");
		assertFalse(list.isEmpty());
		assertEquals(4, list.size());
		assertEquals("Line 1", list.get(0));
		assertEquals("Line 2", list.get(1));
		assertEquals("Line 3", list.get(2));
		assertEquals("Line 4", list.get(3));
	}
	
	@Test
	public void test_Add_ByPositon_Middle() {
		MyLinkedList list = new MyLinkedList();
		assertTrue(list.isEmpty());
		list.add("Line 1");
		list.add("Line 2");
		list.add("Line 4");
		list.add(2, "Line 3");
		assertFalse(list.isEmpty());
		assertEquals(4, list.size());
		assertEquals("Line 1", list.get(0));
		assertEquals("Line 2", list.get(1));
		assertEquals("Line 3", list.get(2));
		assertEquals("Line 4", list.get(3));
	}
	
	@Test(expected=NullPointerException.class)
	public void test_Add_ByPosition_NullValue() {
		MyLinkedList list = new MyLinkedList();
		assertTrue(list.isEmpty());
		list.add("Line 1");
		list.add(0, null);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void test_Add_ByPosition_WrongPosition_1() {
		MyLinkedList list = new MyLinkedList();
		assertTrue(list.isEmpty());
		list.add(1, "Line 1");
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void test_Add_ByPosition_WrongPosition_2() {
		MyLinkedList list = new MyLinkedList();
		assertTrue(list.isEmpty());
		list.add(-1, "Line 1");
	}
	
	@Test
	public void test_Clear_NonEmptyList() {
		MyLinkedList list = new MyLinkedList();
		list.add("Line 1");
		list.add("Line 2");
		list.add("Line 3");
		list.clear();
		assertTrue(list.isEmpty());
		assertEquals(0, list.size());
	}
	
	@Test
	public void test_Clear_EmptyList() {
		MyLinkedList list = new MyLinkedList();
		list.clear();
		assertTrue(list.isEmpty());
		assertEquals(0, list.size());
	}
	
	@Test
	public void test_Remove_FirstItem() {
		MyLinkedList list = new MyLinkedList();
		list.add("Line 1");
		list.add("Line 2");
		list.add("Line 3");
		list.add("Line 4");
		list.add("Line 5");
		assertEquals("Line 1", list.remove(0));
		assertEquals(4, list.size());
	}
	
	@Test
	public void test_Remove_LastItem() {
		MyLinkedList list = new MyLinkedList();
		list.add("Line 1");
		list.add("Line 2");
		list.add("Line 3");
		list.add("Line 4");
		list.add("Line 5");
		assertEquals("Line 5", list.remove(4));
		assertEquals(4, list.size());
	}
	
	@Test
	public void test_Remove_MiddleItem() {
		MyLinkedList list = new MyLinkedList();
		list.add("Line 1");
		list.add("Line 2");
		list.add("Line 3");
		list.add("Line 4");
		list.add("Line 5");
		assertEquals("Line 3", list.remove(2));
		assertEquals(4, list.size());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void test_Remove_InvalidPosition() {
		MyLinkedList list = new MyLinkedList();
		list.add("Line 1");
		list.add("Line 2");
		list.add("Line 3");
		list.add("Line 4");
		list.add("Line 5");
		assertNull(list.remove(-1));
		assertNull(list.remove(5));
		assertNull(list.remove(6));
	}
}
