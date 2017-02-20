package Assignment3v2;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

public class BFS {
		public static ArrayList<String> getWordLadderBFS(String input, String output, Set<String> dict, Queue<Word> queue, ArrayList<Word> tracker){
			while(queue.isEmpty()!=true){														//Takes the head, checks to see if it's end of ladder, otherwise keeps on traversing
				Word current = queue.poll();
				current.beenVisited=true;
				tracker.add(current);
				if(current.word.equals(output)){
					return constructLadder(tracker, input, output);
				}
				ArrayList<String> adjacencyVertices = getAdjacentVerticies(current.word, dict, tracker);	//gets adjacent string
				addToQueue(adjacencyVertices, queue, current);												//adds strings as Word objects to the Q
			}		
			return null;		
		}
		
		public static ArrayList<String> getAdjacentVerticies(String input, Set<String> dict, ArrayList<Word> tracker){		//takes vertex input, and collects adjacencies that have been  not been visited
			Iterator dictionaryTraversal = dict.iterator();
			ArrayList<String> neighbors = new ArrayList<String>();
			while(dictionaryTraversal.hasNext()!=false){
				String word =(String) dictionaryTraversal.next();
				if(isNeighbor(input, word)==true){
					if(hasItBeenVisited(word, tracker)!=true){			//checks to see if adjacency has been visited
						neighbors.add(word);
					}
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
		
		
		public static void addToQueue(ArrayList<String> adjacencyVertices, Queue<Word> queue, Word current){	
			String s= "";
			ListIterator<String> adjacencyTraversal = adjacencyVertices.listIterator();						//An iterator to travel through neighbor list
			while(adjacencyTraversal.hasNext()!=false){														//turns string into word object, adds it to q
				s = (String) adjacencyTraversal.next();
				Word word = new Word(s, current);
				queue.offer(word);
			}	
			return;																			
		}
		
		public static boolean hasItBeenVisited(String word, ArrayList<Word> tracker){
			for(int i=0; i<tracker.size(); i++){
				if(word.equals(tracker.get(i).word)){														//sees if vertex has been visited, if so, we don't put it in que
					return true;
				}
			}
			return false;
		}
		
		public static ArrayList<String> constructLadder(ArrayList<Word> tracker, String input, String output){
			ArrayList<String> wordLadderTemp = new ArrayList<String>();
			ArrayList<String> wordLadder = new ArrayList<String>();
			Word current = tracker.get(tracker.size()-1);				//creates the word ladder
			while(current!=null){
				wordLadderTemp.add(current.word);
				current =current.parent;			
			}
			for(int i=wordLadderTemp.size()-1; i>=0; i--){
				wordLadder.add(wordLadderTemp.get(i));
			}
			return wordLadder;
			
		}
}
