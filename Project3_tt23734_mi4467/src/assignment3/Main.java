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


package assignment3;
import java.util.*;
import java.io.*;

public class Main {
	
	// static variables and constants only here.

	/**
	 * Main method
	 * @param args is the command line arguments
	 */
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
		
		//printLadder(DFS.shorten(output));
		System.out.println(Arrays.toString(getWordLadderDFS("hello", "cells").toArray()));

		// TODO methods to read in words, output ladder
	}
	/**
	 * 	Initialization
	 */
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
		
	
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. Parses 2 strings.
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		String input = keyboard.nextLine().toUpperCase();
		if(input.equals("/quit"))
			return new ArrayList<String>();
		return new ArrayList<String>(Arrays.asList(input.split(" ")));
	}
	
	/**
	 * Constructs wordladder using method in DFS.java
	 * @param start is the starting string
	 * @param end is the ending string
	 * @return the DFS ladder
	 */
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		start = start.toUpperCase();
		end = end.toUpperCase();
		
		// Returned list should be ordered start to end.  Include start and end.
		Set<String> dict = makeDictionary();
		ArrayList<String> visited = new ArrayList<String>();
		Stack<String> stack = new Stack<String>();
		stack.push(start);

		Set<String> dict2 = new HashSet<String>();

		Iterator it = dict.iterator();
		while(it.hasNext()){
			String i = (String)it.next();
			if(i.length() > 5){
				dict2.add(i.split("\r\n")[0]);
				dict2.add(i.split("\r\n")[1]);
			}
			else{
				dict2.add(i);
			}
		}
		
		if(!dict2.contains(start) && dict2.contains(end)){
			ArrayList<String> k = new ArrayList<String>();
	        k.add(start);
			k.add(end);
			return k;	
		}

		ArrayList<String> preprocessed = new ArrayList<String>(DFS.getWordLadderDFS(start, end, dict2, stack, visited));
		if(preprocessed.size() <= 2){
			ArrayList<String> k = new ArrayList<String>();
			k.add(start);
			k.add(end);
			return k;
		}
		return DFS.shorten(preprocessed);
	}
	/**
	 * Constructs wordladder using method in BFS.java
	 * @param start is the starting string
	 * @param end is the ending string
	 * @return the BFS ladder
	 */
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		start = start.toUpperCase();
		end = end.toUpperCase();
    	
		// TODO some code
		Set<String> dict = makeDictionary();				//creates a queue to keep track of visited nodes
		Queue<Word> queue = new LinkedList<Word>();		//creates ladder for 
		ArrayList<Word> visitTracker= new ArrayList<Word>();
		Word first= new Word(start, null);
		queue.offer(first);
		return BFS.getWordLadderBFS(start, end, dict, queue, visitTracker); // replace this line later with real return
	}
    
    /**
     * Constructs dictionary from text file
     * @return dictionary set
     */
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
	
	/**
	 * Prints ladder
	 * @param ladder is the input ladder
	 */
	public static void printLadder(ArrayList<String> ladder) {
		if(ladder.size() == 2)
			System.out.println("no word ladder can be found between " + ladder.get(0).toLowerCase() + " and " + ladder.get(1).toLowerCase());
		else{
			System.out.println("a " + (ladder.size() - 2) + "-rung word ladder exists between " + ladder.get(0).toLowerCase() + " and " + ladder.get(ladder.size() - 1).toLowerCase());	
			for(int i = 0; i < ladder.size(); i++){
				System.out.println(ladder.get(i));
			}
		}
	}
}
