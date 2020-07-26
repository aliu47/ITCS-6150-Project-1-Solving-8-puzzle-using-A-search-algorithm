import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select heuristic:\n 1.Manhattan Distance\n 2.Number of incorrect nodes");
        int choice = scanner.nextInt();
        List<Integer> a = takeUserInput(scanner, "initial");
        List<Integer> b = takeUserInput(scanner, "goal");
        int[][] init = generateData(a);
        int[][] goal = generateData(b);
        List<Puzzleboard> visited = new ArrayList<>();
        List<Puzzleboard> generated = new ArrayList<>();
        HashSet<String> exists = new HashSet<>();
        exists.add(Arrays.deepToString(init));
        Puzzleboard best = new Puzzleboard(0, 0, 0, init, null);
        visited.add(new Puzzleboard(0, 0, 0, init, null));
        Puzzleboard goalBoard = new Puzzleboard(0, 0, 0, goal, null);
        displayBoard(best, "initial");
        while (!boardsEqual(best.puzzle, goal)) {
            generateNode(best, goal, generated, choice,exists);
            best = generated.get(0);
            visited.add(new Puzzleboard(best.g, best.f, best.h, best.puzzle, best.parent));
            generated.remove(best);
        }
        Puzzleboard current = best;
        ArrayList<Puzzleboard> displayList = new ArrayList<>();
        while (current != null) {
            displayList.add(current);
            current = current.parent;
        }
        for (int i = displayList.size()-1; i >=0 ; i--) {
            displayBoard(displayList.get(i), "Current");

        }
        displayBoard(goalBoard, "Goal");
        int nGenerated = generated.size() + visited.size();
        System.out.println("Nodes Expanded: " + visited.size());
        System.out.println("Nodes Generated: " + nGenerated);


    }

    //This function is used to take in the initial state and the goal state
    private static List<Integer> takeUserInput(Scanner scanner, String state) {
        List<Integer> a = new ArrayList<>();
        System.out.println("Enter 9 digits to define a(n) " + state + " state:");
        for (int i = 0; i < 9; i++) {
            System.out.print("Enter value for " + i + "\n");
            try {
                a.add(scanner.nextInt());
            } catch (Exception e) {
                System.out.print("Please enter a valid number");
                i--;
            }
        }
        return a;

    }

    //This function is used to display the puzzle board
    private static void displayBoard(Puzzleboard best, String state) {
        System.out.println(state + " state");
        printPuzzle(best.puzzle);
        if (state.equals("Current")) {
            System.out.print("G:" + best.getG());
            System.out.print(" H:" + best.getH());
            System.out.print(" F:" + best.getF() + "\n");
        }
    }

    //This function checks if each value in both arrays are equal.
    private static boolean boardsEqual(int[][] b1, int[][] b2) {
        for (int i = 0; i < 3; i++) {
            if (!Arrays.equals(b1[i], b2[i])) {
                return false;
            }
        }

        return true;
    }

    //This function is used to create a 2d array from a given list of integers
    private static int[][] generateData(List<Integer> b) {
        int[][] data = new int[3][3];
        int z = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                data[i][j] = b.get(z);
                z++;
            }
        }


        return data;
    }

    //This function is used to display the puzzle board, G value, H value, and F value
    private static void printPuzzle(int[][] p) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(p[i][j] + " ");
            }
            System.out.print("\n");
        }
//        System.out.print("\n");

    }

    //This function is used to generate the different states that can be reached from the given state.
    //It first generates a board ABOVE, BELOW, LEFT, and RIGHT of the given state if available. Then depending
    //On the user's choice of heuristic it will calculate the h value of the new state.
    private static void generateNode(Puzzleboard best, int[][] goal, List<Puzzleboard> nodes, int choice,HashSet<String> exists) {
        int[][] p = best.puzzle;
        int x = 0, y = 0, hold = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (p[i][j] == 0) {
                    y = i;
                    x = j;
                    break;
                }
            }
        }
        //DOWN
        if (y - 1 > -1) {
            Puzzleboard a = new Puzzleboard();
            a.puzzle = new int[][]{{p[0][0], p[0][1], p[0][2]}, {p[1][0], p[1][1], p[1][2]}, {p[2][0], p[2][1], p[2][2]}};
            hold = a.puzzle[y - 1][x];
            a.puzzle[y][x] = hold;
            a.puzzle[y - 1][x] = 0;
            if (!exists.contains(Arrays.deepToString(a.puzzle))) {
                a.parent = best;
                a.setG(best.g + 1);
                if (choice == 1) {
                    a.setH(mdDistance(goal, a.puzzle));
                } else {
                    a.setH(misplacedNodes(goal, a.puzzle));
                }
                nodes.add(a);
                exists.add(Arrays.deepToString(a.puzzle));
            }

        }
        //UP
        if (y + 1 < 3) {
            Puzzleboard b = new Puzzleboard();
            b.puzzle = new int[][]{{p[0][0], p[0][1], p[0][2]}, {p[1][0], p[1][1], p[1][2]}, {p[2][0], p[2][1], p[2][2]}};
            hold = b.puzzle[y + 1][x];
            b.puzzle[y][x] = hold;
            b.puzzle[y + 1][x] = 0;
            if (!exists.contains(Arrays.deepToString(b.puzzle))) {
                b.parent = best;
                b.setG(best.g + 1);
                if (choice == 1) {
                    b.setH(mdDistance(goal, b.puzzle));
                } else {
                    b.setH(misplacedNodes(goal, b.puzzle));
                }
                exists.add(Arrays.deepToString(b.puzzle));
                nodes.add(b);
            }

        }
        //LEFT
        if (x - 1 > -1) {
            Puzzleboard c = new Puzzleboard();
            c.puzzle = new int[][]{{p[0][0], p[0][1], p[0][2]}, {p[1][0], p[1][1], p[1][2]}, {p[2][0], p[2][1], p[2][2]}};
            hold = c.puzzle[y][x - 1];
            c.puzzle[y][x] = hold;
            c.puzzle[y][x - 1] = 0;
            if (!exists.contains(Arrays.deepToString(c.puzzle))) {
                c.parent = best;
                c.setG(best.g + 1);
                if (choice == 1) {
                    c.setH(mdDistance(goal, c.puzzle));
                } else {
                    c.setH(misplacedNodes(goal, c.puzzle));
                }
                exists.add(Arrays.deepToString(c.puzzle));
                nodes.add(c);
            }

        }
        //RIGHT
        if (x + 1 < 3) {
            Puzzleboard d = new Puzzleboard();
            d.puzzle = new int[][]{{p[0][0], p[0][1], p[0][2]}, {p[1][0], p[1][1], p[1][2]}, {p[2][0], p[2][1], p[2][2]}};
            hold = d.puzzle[y][x + 1];
            d.puzzle[y][x] = hold;
            d.puzzle[y][x + 1] = 0;
            if (!exists.contains(Arrays.deepToString(d.puzzle))) {
                d.parent = best;
                d.setG(best.g + 1);
                if (choice == 1) {
                    d.setH(mdDistance(goal, d.puzzle));
                } else {
                    d.setH(misplacedNodes(goal, d.puzzle));
                }
                exists.add(Arrays.deepToString(d.puzzle));
                nodes.add(d);
            }
        }
        //Order Nodes by f value and h value
        Collections.sort(nodes);
//
    }

    //This function calculates the Manhattan Distance between each value of the test node and goal node
    private static int mdDistance(int[][] goal, int[][] test) {
        int distance = 0;
        HashMap<Integer, Integer> goalMap = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                goalMap.put(goal[i][j], i + j);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (test[i][j] != 0) {
                    distance = distance + Math.abs(i + j - goalMap.get(test[i][j]));
                }
            }
        }
        return distance;
    }

    //This function calculates how many values in the test node are in the incorrect position.
    private static int misplacedNodes(int[][] goal, int[][] test) {
        int result = 9;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (goal[i][j] == test[i][j]) {
                    result--;
                }
            }
        }
        return result;
    }

}
