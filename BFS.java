package assignment3;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import java.util.Queue;

public class BFS {
		/**
		 * Constructs a BFS word ladder, and determines edge cases such as not in dictionary, or no word Ladder
		 * 
		 * @param input, the start of the ladder
		 * @param output, the bottom of the ladder
		 * @param dict, the dictionary we use to find words to construct the ladder
		 * @param queue, the mechanism for placing our words
		 * @param tracker, to keep track of visited nodes, in order to avoid infinite search
		 * @return Gives an ArrayList that is actually the Word Ladder
		 */
		public static ArrayList<String> getWordLadderBFS(String input, String output, Set<String> dict, Queue<Word> queue, ArrayList<Word> tracker){
			if(input.equals(output)){
				ArrayList<String> ladder = new ArrayList<String>();
				ladder.add(input);
				ladder.add(output);
				return ladder;
				}	
			while(queue.isEmpty()!=true){														
				Word current = queue.poll();
				tracker.add(current);
				if(current.word.equals(output)){
					return constructLadder(tracker, input, output);
				}
				getAdjacentVerticies(current, dict, tracker, queue);	
			}
			ArrayList<String> ladder = new ArrayList<String>();
			ladder.add(input);
			ladder.add(output);
			return ladder;
		}
		/**
		 * Collects all unvisited adjacent nodes and puts them on the queue
		 * 
		 * @param current, a Word object that holds the word we are trying to find adjacent nodes for
		 * @param dict, a dictionary that holds words
		 * @param tracker, a list that keeps track of the visited words
		 * @param queue, the mechanism for placing our words
		 */
		public static void getAdjacentVerticies(Word current, Set<String> dict, ArrayList<Word> tracker, Queue<Word> queue){		//takes vertex input, and collects adjacencies that have been  not been visited
			Iterator dictionaryTraversal = dict.iterator();
			while(dictionaryTraversal.hasNext()!=false){
				String word =(String) dictionaryTraversal.next();
				if(isNeighbor(current.word, word)==true){
					if(hasItBeenVisited(word, tracker)!=true){			//checks to see if adjacency has been visited
						Word pizza = new Word(word, current);
						queue.add(pizza);
						tracker.add(pizza);			                    //marks it as visited
					}
				}
			}
			return;
		}
		/**
		 * Method that decides if dictionary word is adjacent to current node
		 * 
		 * @param vertex, the word we are trying to find a neighbor for
		 * @param word, a word object for the dictionary word
		 * @return a boolean to see if word is adjacent to node
		 */
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
		
		/**
		 * Method that sees if the node is visited
		 * 
		 * @param word, the dictionary word we are trying to see if it has been visited
		 * @param tracker, the list of visited nodes
		 * @return a boolean that tells us if it has been visited
		 */
		public static boolean hasItBeenVisited(String word, ArrayList<Word> tracker){
			for(int i=0; i<tracker.size(); i++){
				if(word.equals(tracker.get(i).word)){														//sees if vertex has been visited, if so, we don't put it in que
					return true;
				}
			}
			return false;
		}
		
		/**
		 * Tracks back and builds the word Ladder
		 * 
		 * @param tracker, the list of visited node, in this case, used to get final word
		 * @param input, the initial word in the ladder
		 * @param output, the final word in the ladder
		 * @return the Word Ladder
		 */
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
