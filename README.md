Andrew Liu
ITCS 6150 Project 1: Solving 8-puzzle using A* search algorithm

Overview:
In this project we need to solve the 8-puzzle using the A*search algorithm. The 8-puzzle is a puzzle that takes place in a 3x3 square. Each square contains a unique number from 0-8. The 0 square is able to switch places with the squares around it, no other square has this property. The goal is to get from an initial state to a goal state. A* uses two factors to calculate how close a state is to its goal state. First factor is the g-value, this is how many nodes this state is from the initial state. Second factor is the h-value, this is the heuristic value and is dependent on the method you choose to use. For my project I am allowing the user to choose between manhattan distance and number of incorrect nodes.  The g-value and h-value are then added together to create an f-value which is what will be used to calculate the best node.

For using the program the user first chooses which heuristic method they would like to choose. Then after choosing they will be prompted to enter 9 numbers for the initial state. These numbers must be 0-8 and there cannot be any repeats. Next the user will be prompted to select 9 numbers for the goal state. Just like the initial state these must be unique and 0-8.

Classes/Methods/Objects/Variables used:
Variables:
Int choice: This is to decide which heuristic to use
Int[][] Init: This is the initial state of the puzzle
Int[][] goal: This is the goal state of the puzzle
List<PuzzleBoard> visited: This is the list of nodes that have been visited
List<PuzzleBoard> generated: This is the list of nodes that have been generated
Puzzleboard best: This is the Puzzleboard with the lowest f-value.
Hashtable<String> exists: this hashtable contains a string interpretation of every puzzle generated

takeUserInput:
This function is what allows the user to enter in their initial states and the goal states. It returns a list of the integers the user has entered.

displayBoard:
This function is for displaying how the puzzle board looks like to the console. It also displays the g, h, and f value of the board. It takes a Puzzleboard as input, a String to display which state it is and returns void.

boardsEqual:
This function takes two Puzzleboards and checks if each value in the puzzles are the same. If there is a value in the two puzzles that are not equal, it will return false. 
generateData:
This function generates a 2d array based on a given list. 

printPuzzle:
This function is for displaying the puzzle of a PuzzleBoard. It accepts a 2d array and returns void.

generateNode:
This function is used to generate the different possible states of the board. Based on the previous best board it will generate if the 0 is moved left, right, up, or down. It will also calculate the g value and the h value based on the choice the user selected. All nodes are then added to the generated list. Before any node is added though, the function will check if the node has either been generated.
 
mDistance:
This function is used to calculate the manhattan distance of a given 2d array. To do this I added the i and j value of a given 2d array. Then I subtract that from the i and j value of the goal 2d array. Finally I take the absolute value of this result and add it to the result value. Once all values are checked, the result is returned.

misplacedNodes:
This function checks to see which nodes are misplaced. The result starts at 9 and each time a given 2d array and goal array have the same value in the same spot, the result decrements. After all values are checked, the result is returned.

Program Structure:
Puzzleboard Object:
Fields:
Int g: This is the how far this node is away from the initial node
Int h: This is the heuristic value 
Int f: This is the combination of g and f
Int[][] puzzle: This is the puzzleboard represented as a 2d array
Puzzleboard parent: This is to keep track of the parent board.

Methods:
Getters/Setters: self explanatory 
toString: self explanatory 
compareTo: This method is used to compare two Puzzleboards. It first checks to see which board has a lower f value. If the f values are equal, then it will pick the one with the lowest h value.


Searching:
For checking if a puzzle has already been created, the program will check if the hashtable contains the puzzle given. Initially I had checked the Arraylists, but by changing this to use a hashtable, it has increased the program speed substantially.




