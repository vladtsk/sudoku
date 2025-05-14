package nfp136.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		
		
		/*
		int[] row1 = {5, 0, 0, 0, 7, 0, 0, 0, 0};
		int[] row2 = {6, 0, 0, 1, 0, 0, 0, 0, 0};
		int[] row3 = {0, 9, 0, 0, 0, 0, 0, 6, 0};
		int[] row4 = {0, 0, 0, 0, 6, 0, 0, 0, 3};
		int[] row5 = {0, 0, 0, 8, 0, 3, 0, 0, 1}; 
		int[] row6 = {0, 0, 0, 0, 2, 0, 0, 0, 6}; 
		int[] row7 = {0, 6, 0, 0, 0, 0, 2, 8, 0}; 
		int[] row8 = {0, 0, 0, 4, 0, 9, 0, 0, 5};
		int[] row9 = {0, 0, 0, 0, 8, 0, 0, 7, 0}; 
		*/
		//
		
		/*
		int[] row1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] row2 = {0, 6, 0, 0, 0, 0, 0, 3, 0};
		int[] row3 = {0, 7, 0, 0, 0, 0, 0, 4, 0};
		int[] row4 = {7, 8, 9, 1, 2, 3, 4, 5, 6};
		int[] row5 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] row6 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] row7 = {4, 5, 6, 7, 8, 9, 1, 2, 3};
		int[] row8 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] row9 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		*/

		
		//ok
		/*
		int[] row1 = {2, 0, 0, 3, 0, 0, 0, 0, 0};
		int[] row2 = {8, 0, 4, 0, 6, 2, 0, 0, 3};
		int[] row3 = {0, 1, 3, 8, 0, 0, 2, 0, 0};
		int[] row4 = {0, 0, 0, 0, 2, 0, 3, 9, 0};
		int[] row5 = {5, 0, 7, 0, 0, 0, 6, 2, 1};
		int[] row6 = {0, 3, 2, 0, 0, 6, 0, 0, 0};
		int[] row7 = {0, 2, 0, 0, 0, 9, 1, 4, 0};
		int[] row8 = {6, 0, 1, 2, 5, 0, 8, 0, 9};
		int[] row9 = {0, 0, 0, 0, 0, 1, 0, 0, 2};
		*/
//		
		
		
		// ok
		
		int[] row1 = {0,0,3,0,2,0,6,0,0};
		int[] row2 = {9,0,0,3,0,5,0,0,1};
		int[] row3 = {0,0,1,8,0,6,4,0,0};
		int[] row4 = {0,0,8,1,0,2,9,0,0};
		int[] row5 = {7,0,0,0,0,0,0,0,8};
		int[] row6 = {0,0,6,7,0,8,2,0,0};
		int[] row7 = {0,0,2,6,0,9,5,0,0};
		int[] row8 = {8,0,0,2,0,3,0,0,9};
		int[] row9 = {0,0,5,0,1,0,3,0,0};


		int[][] grille1 = {row1, row2, row3, row4, row5, row6, row7, row8, row9};
		
		System.out.println(Arrays.deepToString(grille1));
		Grille grilleObj1 = new Grille(grille1);
		
		ArrayList<Grille> solutions = new ArrayList<>(); 
		
		Stack<Grille> grillStack = new Stack<>();
		grillStack.push(grilleObj1);

		
		Solution.findSolutions(grillStack, solutions);
		
		
		// vérifier que les solutions sont différentes
		if(solutions.size() == 2) {
			if(Arrays.deepEquals(solutions.get(0).gr, solutions.get(1).gr)) {
				solutions.remove(1);
			}
		}
		
		if(solutions.isEmpty()) {
			System.err.println("No solution found.");
			} else {
				for (int i = 0; i < solutions.size(); i++) {
				    System.out.println("Solution " + (i + 1) + ": " + solutions.get(i));
				}
			}
			

		
		 
	} 
}

