package Assignment3v2;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import java.util.Queue;

public class BFS {
		public static ArrayList<String> getWordLadderBFS(String input, String output, Set<String> dict, Queue<Word> queue, ArrayList<Word> tracker){
			while(queue.isEmpty()!=true){															//Takes the head, checks to see if it's end of ladder, otherwise keeps on traversing
				Word current = queue.poll();
				tracker.add(current);
				if(current.word.equals(output)){
					return constructLadder(tracker, input, output);
				}
				getAdjacentVerticies(current, dict, tracker, queue);	//gets adjacent string
			}		
			return null;		
		}
		
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