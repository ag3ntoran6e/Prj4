package pack1;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * EditorTest class contains unit tests for Editor class.
 * 
 * @author Morrie Cunningham
 *
 * ADD MORE UNIT TESTS TO THIS CLASS
 *
 */
public class EditorTest {

	@Test
	public void testInsertCommand() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i Java is an OO language.");
		ed.processCommand("i Ruby is another OO language.");
		assertEquals("Ruby is another OO language.", ed.getCurrentLine());
		assertEquals("Java is an OO language.", ed.getLine(1));
	}

	@Test 
	public void testRemoveCommand() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i Java is cool.");
		ed.processCommand("i Python is cooler.");
		ed.processCommand("i Ruby is hot.");
		ed.processCommand("i COBOL is old.");
		assertEquals(4, ed.nbrLines());
		ed.processCommand("u 2");
		ed.processCommand("r 2");
		assertEquals(2, ed.nbrLines());
		assertEquals("COBOL is old.", ed.getCurrentLine());
	}

	@Test 
	public void testReverseCommand() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i Java is cool.");
		ed.processCommand("i Python is cooler.");
		ed.processCommand("i Ruby is hot.");
		ed.processCommand("i COBOL is old.");
		assertEquals(4, ed.nbrLines());
		ed.processCommand("rev");
		assertEquals(4, ed.nbrLines());
		assertEquals("COBOL is old.", ed.getLine(1));
		assertEquals("Ruby is hot.", ed.getLine(2));
		assertEquals("Python is cooler.", ed.getLine(3));
		assertEquals("Java is cool.", ed.getLine(4));
	}

	@Test(expected=EditorException.class)
	public void testRemoveException() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i Java is cool.");
		ed.processCommand("i Python is cooler.");
		ed.processCommand("i Ruby is hot.");
		assertEquals(3, ed.nbrLines());
		ed.processCommand("u 1");
		ed.processCommand("r 3"); // this call should throw EditorException
	}
	
	@Test
	public void testMoveUpOne() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i Java is cool.");
		ed.processCommand("i Python is cooler.");
		ed.processCommand("i Ruby is hot.");
		assertEquals("Ruby is hot.", ed.getCurrentLine());
		ed.processCommand("u");
		assertEquals("Python is cooler.", ed.getCurrentLine());
	}
	
	@Test
	public void testMoveDownOne() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i Java is cool.");
		ed.processCommand("i Python is cooler.");
		ed.processCommand("i Ruby is hot.");
		assertEquals("Ruby is hot.", ed.getCurrentLine());
		ed.processCommand("u");
		ed.processCommand("m");
		assertEquals("Ruby is hot.", ed.getCurrentLine());
	}
	
	@Test
	public void testRemoveOneLine() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i Java is cool.");
		ed.processCommand("i Python is cooler.");
		ed.processCommand("i Ruby is hot.");
		ed.processCommand("r");
		assertEquals("Python is cooler.", ed.getCurrentLine());
	}
	
	@Test
	public void testDisplay() throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i Java is cool.");
		ed.processCommand("i Python is cooler.");
		ed.processCommand("i Ruby is hot.");
		ed.processCommand("d");
		assertEquals("Ruby is hot.", ed.getCurrentLine());
		assertEquals(3, ed.nbrLines());
	}

}
