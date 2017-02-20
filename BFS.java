package assignment3;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

public class BFS {
		public static ArrayList<String> getWordLadderBFS(String input, String output, Set<String> dict, ArrayList<String> ladder, LinkedList<String> visited){
			ArrayList<String> adjacencyVertices = new ArrayList<String>();
			while(ladder.contains(output)!=true){	//essentially gets neighbors of most recent rung, then decides to add a rung
				adjacencyVertices = getAdjacentVerticies(ladder.get(ladder.size()-1), dict);	//get an array that has all the neighbors of a node, last rung essentially
				addRung(ladder, adjacencyVertices, visited, dict, output);			//continues to add rungs until list finally gets word ladder
			}		
			return ladder;		
		}
		
		public static ArrayList<String> getAdjacentVerticies(String input, Set<String> dict){		//takes vertex input, 
			Iterator dictionaryTraversal = dict.iterator();
			ArrayList<String> neighbors = new ArrayList<String>();
			while(dictionaryTraversal.hasNext()!=false){
				String word =(String) dictionaryTraversal.next();
				if(isNeighbor(input, word)==true){
					neighbors.add(word);
					//System.out.println(word);
				}
			}
			return neighbors;
		}
		
		public static boolean isNeighbor(String vertex, String word){		//takes a node, and some other word, and sees if they are connected
			int difChars=0;
			for(int i=0; i<vertex.length(); i++){
				if(vertex.charAt(i)!=word.charAt(i)){
					difChars++;
				}
			}
			if(difChars!=1){
				return false;
			}
			return true;			
		}
		
		public static int diffString(String vertex, String word){		//counts number of letters different in two strings
			int difChars=0;
			for(int i=0; i<vertex.length(); i++){
				if(vertex.charAt(i)!=word.charAt(i)){
					difChars++;
				}
			}
			return difChars;			
		}
		
		public static void addRung(ArrayList<String> ladder, ArrayList<String> adjacencyVertices, LinkedList<String> visited, Set<String> dict, String output){
			int difference = 999999999;
			int difChars = 0;
			int index =0;
		
			ListIterator<String> adjacencyTraversal = adjacencyVertices.listIterator();						//An iterator to travel through neighbor list
			while(adjacencyTraversal.hasNext()!=false){										//finds neighbor that is closest to output
				difChars = diffString((String) adjacencyTraversal.next(), output);
				if(difChars<difference){
					difference=difChars;
					index = adjacencyTraversal.nextIndex()-1;								//keep index of rung closest to output
				}
			}	
			ladder.add((String) adjacencyVertices.get(index));
			visited.add((String) adjacencyVertices.get(index));
			return;																			//pushes word closes to output to ladder, and marks the vertex as visited
		}
}
