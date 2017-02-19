import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;

public class DFS{
	public static ArrayList<String> getWordLadderDFS(String input, String output, Set<String> dict){
		ArrayList<String> retList = new ArrayList<String>();
		ArrayList<String> visited = new ArrayList<String>();
		
		

		return retList;
	}
	private static String helperDFS(String input, String output, Set<String> dict, ArrayList<String> visited){
		if(input.equals(output))
			return output;
		else {
			Iterator it = dict.iterator();
			while(it.hasNext()){
				String k = (String)it.next();
				int diffcount = 0;
				for(int j = 0; j < k.length(); j++){
					if(k.charAt(j) != input.charAt(j))
						diffcount++;
				}
				if(diffcount <= 1 && !visited.contains(k)){
					visited.add(k);
					return helperDFS(input, output, dict, visited);
				}
			}
		}

		return null;
	}
	private static ArrayList<String> getNodes(String input, Set<String> dict){
		Iterator it = dict.iterator();
		while(it.hasNext()){
			String k = (String)it.next();
			int diffcount = 0;
			for(int j = 0; j < k.length(); j++){
				if(k.charAt(j) != input.charAt(j))
					diffcount++;
			}
			if(diffcount <= 1 && !visited.contains(k)){
				visited.add(k);
				return helperDFS(input, output, dict, visited);
			}
		}
		
	}
}
