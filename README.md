# E E 422C - Word Ladder

### Project 3 (DFS/BFS) Implementations:

+ getWordLadderBFS() (Breadth-First Search)
+ getWordLadderDFS() (Depth-First Search)
+ parse()
+ printLadder()

### Notes:
+ Why is there a System.exit() call in the example code??

### DFS length-shortening algorithm:
+ Avoid choosing adjacent nodes that point away from ending node
+ After a path is found using DFS, eliminate roundabout paths (ie. a -> b -> c becomes a -> c if a, b, and c are all adjacent nodes.)

