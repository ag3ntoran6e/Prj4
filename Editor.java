package pack1;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Editor class implements a simple line-oriented editor.
 * 
 * @author Morrie Cunningham
 *
 */
public class Editor implements IEditor {

	private MyLinkedList list;
	private String[] input;
	private ArrayList<String> history;

	// add other fields as needed

	public Editor() {
		list = new MyLinkedList();
		input = new String[0];
		history = new ArrayList<String>();
	}

	@Override
	public void processCommand(String command) throws EditorException {
		// COMPLETE THIS METHOD
		command = command.trim();
		String[] splitInput = command.split(" ");
		splitInput[0] = splitInput[0].toLowerCase();

		// no input
		if (splitInput.length == 0 || command.equals("") || 
				command.equals(" ")) {
		}

		// only 1 element found
		else if (splitInput.length == 1) {

			String cmd = splitInput[0];
			switch (cmd) {
			case "m":
				history.add(command);
				moveDownLines(1);
				break;

			case "u":
				history.add(command);
				moveUpLines(1);
				break;

			case "r":
				history.add(command);
				removeCurrentLines(1);
				break;

			case "d":
				list.display(1, nbrLines());
				break;

			case "c":
				history.add(command);
				Scanner inputc = new Scanner(System.in);
				System.out.println("Are you sure you want to clear? "
						+ "(y/n)");
				String sc = inputc.nextLine().toLowerCase();

				if(sc.equals("y") || sc.equals("yes"))
					list.clear();

				break;

			case "h":
				String help = null;

				try{
					help = new String(Files.readAllBytes(Paths.get("help.txt")));
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println(help);
				break;

			case "x":
				Scanner inputx = new Scanner(System.in);
				System.out.println("Are you sure you want to exit? "
						+ "(y/n)");
				String sx = inputx.nextLine().toLowerCase();

				if(sx.equals("y") || sx.equals("yes")) {
					System.out.println("GOODBYE!");
					System.exit(0);
				}
				break;

			case "rev":

				history.add(command);

				if (splitInput.length != 1) {

					String e = "UNDO command takes no arguments";
					throw new EditorException(e);
				} else {

					list.reverseList();
				}

				break;


			default:
				throw new EditorException("Command not recognized");

			}
		}

		// 2 or more elements found
		else if(splitInput.length >= 2) {

			String cmd = splitInput[0];
			switch(cmd) {
			case "b":
				history.add(command);

				list.add(getCurrentLineNbr() - 1, 
						command.substring(2));
				break;

			case "i":
				history.add(command);

				list.add(getCurrentLineNbr(), command.substring(2));
				break;

			case "s":
				// if too many arguments passed
				if(splitInput.length != 2) {
					String e = "S command only takes 1 argument." + 
							"Args passed: " + (splitInput.length-1);
					throw new EditorException(e);
				}

				// if correct number of arguments passed
				else {
					saveFile(splitInput[1]);
				}

			case "l":

				// clear history since loaded file could have been modified
				history = new ArrayList<String>();

				// too many args passed
				if (splitInput.length != 2) {
					String e = "L command only takes 1 arg. " +
							"Args passed: " + (splitInput.length - 1);
					throw new EditorException(e);
				}

				// command and arg passed
				else if (splitInput.length == 2) {

					// the file has content
					if (nbrLines() > 0) {
						Scanner inputl = new Scanner(System.in);
						System.out.println("Are you sure you want to load " +
								"file and overwrite the current working file?" +
								" (y/n)");
						String sl = inputl.nextLine().toLowerCase();

						if (sl.equals("y") || sl.equals("yes") ||
								sl.equals("true")) {
							loadFile(splitInput[1]);
						}
					}

					// the file is empty
					else {
						loadFile(splitInput[1]);
					}
					break;
				}

				break;

			case "e":
				history.add(command);

				list.add(command.substring(2));
				break;

			case "m":
				history.add(command);

				// if too many args passed
				if (splitInput.length > 2) {
					String e = "M command only takes 0 or 1 arg. " +
							"Args passed: " + (splitInput.length - 1);
					throw new EditorException(e);
				}

				// if command and one arg passed
				else {

					// if arg is a valid integer
					if (isInt(splitInput[1])) {
						int param = Integer.parseInt(splitInput[1]);
						moveDownLines(param);
					}

					// if arg is invalid
					else {
						String e = "M command can only take a " +
								"parameter of type int";
						throw new EditorException(e);
					}
				}
				break;

			case "u":
				history.add(command);

				// if too many arguments are passed
				if(splitInput.length > 2) {
					String e = "u command only takes 0 or 1 argument. "
							+ "Args passed: " + (splitInput.length - 1);
					throw new EditorException(e);
				}

				// if command and only one argument passed
				else {

					// if argument is a valid integer
					if(isInt(splitInput[1])) {
						int param = Integer.parseInt(splitInput[1]);
						moveUpLines(param);
					}

					// if argument is invalid
					else {
						String e = "u command can only take a " + 
								"parameter of type int";
						throw new EditorException(e);
					}
				}
				break;

			case "r":
				history.add(command);

				// too many arguments passed
				if(splitInput.length > 2) {
					String e = "r command only takes 0 or 1 argument." 
							+ "Args passed: " + (splitInput.length - 1);
					throw new EditorException(e);
				}

				// if command and argument are passed
				else if(splitInput.length == 2) {

					// if the command arg is a valid integer
					if (isInt(splitInput[1])) {
						int param = Integer.parseInt(splitInput[1]);
						removeCurrentLines(param);
					}

					// if argument is not valid
					else {
						String e = "R command can only take a " +
								"parameter of type int";
						throw new EditorException(e);
					}
				}
				break;

			case "d":

				// too many args passed
				if (splitInput.length != 3) {
					String e = "D command only takes 0 or 2 arg. " +
							"Args passed: " + (splitInput.length - 1);
					throw new EditorException(e);
				}

				// command and two args passed
				else {

					// args are valid integers
					if (isInt(splitInput[1]) && isInt(splitInput[2])) {
						list.display(Integer.parseInt(splitInput[1]),
								Integer.parseInt(splitInput[2]));
					}

					// arg is invalid
					else {
						String e = "C command takes two args of type int";
						throw new EditorException(e);
					}
				}
				break;    

			default:
				throw new EditorException("Command not valid");
			}


		}
	}

	/**************************************************
	 *
	 **************************************************/
	private boolean isInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	private void moveDownLines(int nbr) {
		for(int i = 0; i < nbr; i++) {
			if(list.getCurrent().getNext() != null)
				list.setCurrent(list.getCurrent().getNext());
		}
	}

	private void moveUpLines(int nbr) {

		for(int i = 0; i < nbr; i++) {
			Node temp = list.getFront();

			while(temp.getNext() != list.getCurrent()) {
				temp = temp.getNext();

			}

			if(temp != null)
				list.setCurrent(temp);

		}
	}

	private void removeCurrentLines(int nbr) throws EditorException {

		int currentLine = getCurrentLineNbr();

		for(int i = 0; i < nbr; i++) {
			if(currentLine - 1 < list.size())
				list.remove(currentLine - 1);
			else 
				throw new EditorException(null);

		}
	}

	//	private void undo() throws EditorException {
	//		if(history.size() > 0) {
	//			list.clear();
	//			ArrayList<String> temp = history;
	//			history = new ArrayList<String>();
	//			temp.remove(temp.size() - 1);
	//			
	//			for(String s : temp) {
	//				try{
	//					processCommand(s);
	//				} catch (EditorException e) {
	//					// do nothing
	//				}
	//			}
	//			history = temp;
	//		} else {
	//			throw new EditorException("No history to undo");
	//		}
	//	}

	@Override
	public int nbrLines() {
		return list.size();
	}

	@Override
	public String getLine(int lineNbr) throws EditorException {
		return list.get(lineNbr - 1);
	}

	@Override
	public String getCurrentLine() {
		return list.getCurrent().getData();
	}

	@Override
	public int getCurrentLineNbr() {
		if(nbrLines() > 0) 
			return list.get(list.getCurrent().getData()) + 1;
		else 
			return 0;	
	}

	/**************************************************
	 *
	 **************************************************/
	public void saveFile(String file) throws EditorException {

		if (nbrLines() > 0) {
			list.add(" ");
		} else {
			list.add(" ");
		}
		try {
			PrintWriter out = new PrintWriter(file);
			for (int i = 0; i < nbrLines(); i++) {
				out.println(list.get(i));
			}
			out.close();
		} catch (IOException e) {
			throw new EditorException("saving file - " + e.getMessage());
		}


	}

	/**************************************************
	 *
	 **************************************************/
	public void loadFile(String file) throws EditorException {
		String doc = "";
		try {
			doc = new String(Files.readAllBytes(Paths.get(file)));
		} catch (IOException e) {
			throw new EditorException(e.getMessage());
		}
		String[] parsed = doc.split("\n");
		list.clear();
		for (String s : parsed) {
			list.add(s);
		}
		moveUpLines(nbrLines() - 1);
	}



	// add other public/private methods as needed

	public static void main(String[] args) throws EditorException {
		Editor ed = new Editor();
		ed.processCommand("i one");
		ed.processCommand("i two");
		ed.processCommand("i three");
	}

}
