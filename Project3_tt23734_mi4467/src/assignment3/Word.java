package assignment3;

public class Word {
	/**
	 * 
	 * @param word, the actual word in the dictionary
	 * @param parent, the adjacent node behind the word, i.e the parent node
	 */
	String	word;
	Word parent;							
	public Word(String word, Word parent){
		this.word=word;
		this.parent=parent;					
	}
}
