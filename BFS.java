package assignment3;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import java.util.Queue;

public class BFS {
		/**
<<<<<<< HEAD
		 * Constructs a word ladder using BFS algorithm
		 * 
		 * @param input is the starting string
		 * @param output is the ending string
		 * @param dict is the dictionary
		 * @param queue is the queue
		 * @param tracker keeps track of visited words
		 * @return the output ladder
=======
		 * Constructs a BFS word ladder, and determines edge cases such as not in dictionary, or no word Ladder
		 * 
		 * @param input, the start of the ladder
		 * @param output, the bottom of the ladder
		 * @param dict, the dictionary we use to find words to construct the ladder
		 * @param queue, the mechanism for placing our words
		 * @param tracker, to keep track of visited nodes, in order to avoid infinite search
		 * @return Gives an ArrayList that is actually the Word Ladder
>>>>>>> 1c2cd3490c295fc3243da3ff15bb31d6e3b7191a
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
<<<<<<< HEAD
		 * Returns adjacency list of an element
		 * 
		 * @param current is the current word
		 * @param dict is the dictionary
		 * @param tracker is the arraylist containing visited nodes
		 * @param queue is the queue for BFS
=======
		 * Collects all unvisited adjacent nodes and puts them on the queue
		 * 
		 * @param current, a Word object that holds the word we are trying to find adjacent nodes for
		 * @param dict, a dictionary that holds words
		 * @param tracker, a list that keeps track of the visited words
		 * @param queue, the mechanism for placing our words
>>>>>>> 1c2cd3490c295fc3243da3ff15bb31d6e3b7191a
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
<<<<<<< HEAD
		 * Checks to see if 2 words are neighbors.
		 * 
		 * @param vertex is the first word
		 * @param word is the second word
		 * @return whether true or false
=======
		 * Method that decides if dictionary word is adjacent to current node
		 * 
		 * @param vertex, the word we are trying to find a neighbor for
		 * @param word, a word object for the dictionary word
		 * @return a boolean to see if word is adjacent to node
>>>>>>> 1c2cd3490c295fc3243da3ff15bb31d6e3b7191a
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
<<<<<<< HEAD
		 * Checks to see if a word has been visited
		 * 
		 * @param word is the input word
		 * @param tracker is the list of visited elements
		 * @return whether a word has been visited or not
=======
		 * Method that sees if the node is visited
		 * 
		 * @param word, the dictionary word we are trying to see if it has been visited
		 * @param tracker, the list of visited nodes
		 * @return a boolean that tells us if it has been visited
>>>>>>> 1c2cd3490c295fc3243da3ff15bb31d6e3b7191a
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
<<<<<<< HEAD
		 * Constructs ladder
		 * 
		 * @param tracker is the list of visited words
		 * @param input is the input word
		 * @param output is the output word
		 * @return returns ladder
=======
		 * Tracks back and builds the word Ladder
		 * 
		 * @param tracker, the list of visited node, in this case, used to get final word
		 * @param input, the initial word in the ladder
		 * @param output, the final word in the ladder
		 * @return the Word Ladder
>>>>>>> 1c2cd3490c295fc3243da3ff15bb31d6e3b7191a
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
