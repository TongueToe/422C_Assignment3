/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Mustafa Irfan
 * mi4467
 * 16236
 * Tung To
 * tt23734
 * 16236
 * Slip days used: <0>
 * Git URL:
 * Spring 2017
 */


//package assignment3;
import java.util.*;
import java.io.*;

public class Main {
	
	// static variables and constants only here.

	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout
		}
		initialize();
		
		ArrayList<String> input = parse(kb);
		ArrayList<String> output = getWordLadderDFS(input.get(0), input.get(1));
		for(String out: output)
			System.out.println(out);

		// TODO methods to read in words, output ladder
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		String input = keyboard.nextLine().toUpperCase();
		if(input.equals("/quit"))
			return new ArrayList<String>();
		return new ArrayList<String>(Arrays.asList(input.split(" ")));
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		// Returned list should be ordered start to end.  Include start and end.
		Set<String> dict = makeDictionary();
		ArrayList<String> visited = new ArrayList<String>();
		Stack<String> stack = new Stack<String>();
		stack.push(start);

		ArrayList<String> preprocessed = new ArrayList<String>(DFS.getWordLadderDFS(start, end, dict, stack, visited));
		if(preprocessed.size() == 1){
			preprocessed.add(preprocessed.get(0));
		}
		else if(preprocessed.size() == 2){
			return preprocessed;
		}
		return DFS.shorten(preprocessed);
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		Set<String> dict = makeDictionary();
		LinkedList<String> visited = new LinkedList<String>();
		ArrayList<String> ladder = new ArrayList<String>();
		ladder.add(start);
		return BFS.getWordLadderBFS(start, end, dict, ladder, visited); // replace this line later with real return
	}
    
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	public static void printLadder(ArrayList<String> ladder) {
		
	}
	// TODO
	// Other private static methods here
}
