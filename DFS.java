package assignment3;

import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import java.util.Stack;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;

public class DFS{
	
	/**
	 * Constructs wordladder by DFS. Recursive call.
	 * @param input is the starting string
	 * @param output is the ending string
	 * @param dict is the word dictionary
	 * @param stack is the stack
	 * @param visited keeps track of visited elements
	 * @return stack of current map
	 */
	public static Stack<String> getWordLadderDFS(String input, String output, Set<String> dict, Stack<String> stack, ArrayList<String> visited)
	{
		ArrayList<String> adjacentNodes = getAdjacentNodes(input, output, dict);
		
		if(!input.equals(output)){
			for(String node: adjacentNodes){
				if(!stack.contains(node) && !visited.contains(node)){
					visited.add(node);
					stack.push(node);
					Stack<String> out = getWordLadderDFS(node, output, dict, stack, visited);
					if(out.peek().equals(output)){
						return stack;
					}
				}
			}
			stack.pop();
		}
		return stack;
	}
	
	/**
	 * Constructs an adjacency list
	 * 
	 * @param input is the starting string
	 * @param output is the ending string
	 * @param dict is the dictionary
	 * @return adjacency list
	 */
	private static ArrayList<String> getAdjacentNodes(String input, String output, Set<String> dict){
		Iterator it = dict.iterator();
		ArrayList<String> nodes = new ArrayList<String>();
		int diff = getIndexDiff(input, output);

		while(it.hasNext()){
			String k = (String)it.next();

			int inputDiff = getIndexDiff(input, k);
			int outputDiff = getIndexDiff(output, k);
			
			if(inputDiff <= 1 && (outputDiff - 1 <= diff))
				nodes.add(k);

		}

		return nodes;
	}
	
	/**
	 * Returns the number of different characters between 2 strings
	 * 
	 * @param a is the first string
	 * @param b is the second string
	 * @return integer difference
	 */
	private static int getIndexDiff(String a, String b){
		int count = 0;
		for(int i = 0; i < 5; i++){
			if(a.charAt(i) != b.charAt(i))
				count++;
		}
		return count;
	}
	
	/**
	 * Shortens the supplied ladder by eliminating roundabout paths
	 * 
	 * @param input is the input array
	 * @return the output array
	 */
	public static ArrayList<String> shorten(ArrayList<String> input){
		LinkedHashMap<String, Integer> output = new LinkedHashMap<String, Integer>();
		if(input.size() <= 2)
			return input;
		String current = input.get(0);
		while(!current.equals(input.get(input.size() - 1))){
			for(int i = 0; i < input.size(); i++){
				if(getIndexDiff(current, input.get(i)) <= 1)
					output.put(current, i);
			}
			
			current = input.get(output.get(current));
		}
		output.put(input.get(input.size() - 1), input.size() -1);
		return new ArrayList<String>(output.keySet());
	}
}
