package assignment3;

public class Word {
	String	word;
	Word parent;							//this allows us to link back to the input
	boolean beenVisited;				
	
	public Word(String word, Word parent){
		this.word=word;
		this.parent=parent;					//class meant to represent vertex with other properties
		this.beenVisited=false;
	}
	
	
}
