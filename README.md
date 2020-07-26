ITCS 6150 Project 1: Solving 8-puzzle using A* search algorithm

Overview:
In this project we need to solve the 8-puzzle using the A*search algorithm. The 8-puzzle is a puzzle that takes place in a 3x3 square. Each square contains a unique number from 0-8. The 0 square is able to switch places with the squares around it, no other square has this property. The goal is to get from an initial state to a goal state. A* uses two factors to calculate how close a state is to its goal state. First factor is the g-value, this is how many nodes this state is from the initial state. Second factor is the h-value, this is the heuristic value and is dependent on the method you choose to use. For my project I am allowing the user to choose between manhattan distance and number of incorrect nodes.  The g-value and h-value are then added together to create an f-value which is what will be used to calculate the best node.

For using the program the user first chooses which heuristic method they would like to choose. Then after choosing they will be prompted to enter 9 numbers for the initial state. These numbers must be 0-8 and there cannot be any repeats. Next the user will be prompted to select 9 numbers for the goal state. Just like the initial state these must be unique and 0-8.





