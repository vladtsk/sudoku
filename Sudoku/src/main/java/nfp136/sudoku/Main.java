package nfp136.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		
		/*int[] rowEx1 = {0, 0, 0, 0, 0, 0, 0, 1, 2};
		int[] rowEx2 = {0, 0, 0, 0, 0, 0, 0, 0, 3};
		int[] rowEx3 = {0, 0, 2, 3, 0, 0, 4, 0, 0};
		int[] rowEx4 = {0, 0, 1, 8, 0, 0, 0, 0, 5};
		int[] rowEx5 = {0, 6, 0, 0, 7, 0, 8, 0, 0};
		int[] rowEx6 = {0, 0, 0, 0, 0, 9, 0, 0, 0};
		int[] rowEx7 = {0, 0, 8, 5, 0, 0, 0, 0, 0};
		int[] rowEx8 = {9, 0, 0, 0, 4, 0, 5, 0, 0};
		int[] rowEx9 = {4, 7, 0, 0, 0, 6, 0, 0, 0};
		*/
		//
		
		int[] rowEx1 = {5, 3, 0, 0, 7, 0, 0, 0, 0};
		int[] rowEx2 = {6, 0, 0, 1, 9, 5, 0, 0, 0};
		int[] rowEx3 = {0, 9, 8, 0, 0, 0, 0, 6, 0};
		int[] rowEx4 = {0, 0, 0, 0, 6, 0, 0, 0, 3};//8
		int[] rowEx5 = {0, 0, 0, 8, 0, 3, 0, 0, 1}; 
		int[] rowEx6 = {0, 0, 0, 0, 2, 0, 0, 0, 6}; //can solve with the 7: 700020006
		int[] rowEx7 = {0, 6, 0, 0, 0, 0, 2, 8, 0}; //961000284
		int[] rowEx8 = {0, 0, 0, 4, 1, 9, 0, 0, 5};
		int[] rowEx9 = {0, 0, 0, 0, 8, 0, 0, 7, 9}; 
		
		
		
		
		/*int[] rowEx1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] rowEx2 = {0, 6, 0, 0, 0, 0, 0, 3, 0};
		int[] rowEx3 = {0, 7, 0, 0, 0, 0, 0, 4, 0};
		int[] rowEx4 = {7, 8, 9, 1, 2, 3, 4, 5, 6};
		int[] rowEx5 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] rowEx6 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] rowEx7 = {4, 5, 6, 7, 8, 9, 1, 2, 3};
		int[] rowEx8 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] rowEx9 = {0, 0, 0, 0, 0, 0, 0, 0, 0};

		*/



		
		int[][] grille1 = {rowEx1, rowEx2, rowEx3, rowEx4, rowEx5, rowEx6, rowEx7, rowEx8, rowEx9};
		
		System.out.println(Arrays.deepToString(grille1));
		Grille grilleObj1 = new Grille(grille1);
		
		Stack<Grille> grillStack = new Stack<>();
		grillStack.push(grilleObj1);

		
		
		
	
	
		Solver.solveOrganically(grillStack);
		
		//Solver.insertCandidateValue(grillStack);
		//Solver.solveOrganically(grillStack);
		
		//System.out.println("tempEls: " + Solver.tempEls);
		//System.out.println(grillStack.peek()); 
		
		try {
		int iterations=0;
		while(!grillStack.peek().estComplet()) {
			Solver.insertCandidateValue(grillStack);
			Solver.solveOrganically(grillStack);
			
			iterations++;
			if(iterations > 100) throw new UnsolvablePuzzleException("Too many iterations.");
			
		}
		
		} catch(Exception e) {
			System.out.println(e.getMessage());
			/*System.out.println("tempEls: " + Solver.tempEls);
			System.out.println(grillStack.peek()); 
			
			grillStack.pop();
			Solver.tempEls.pop();
			System.out.println("tempEls: " + Solver.tempEls);
			System.out.println(grillStack.peek()); 
			
			/*Solver.tempEls.pop();
			Solver.tempEls.pop();
			System.out.println("tempEls: " + Solver.tempEls);
			System.out.println(grillStack.peek()); 
			*/
			
		}
		
		
	
		
		/*
		Solver.solveOrganically(grillStack);
		Solver.insertCandidateValue(grillStack);
		Solver.solveOrganically(grillStack);
		Solver.insertCandidateValue(grillStack);
		Solver.solveOrganically(grillStack);
		Solver.insertCandidateValue(grillStack);
		
		Solver.solveOrganically(grillStack);
		Solver.insertCandidateValue(grillStack);
		Solver.solveOrganically(grillStack);
		Solver.insertCandidateValue(grillStack);
		Solver.solveOrganically(grillStack);
		Solver.insertCandidateValue(grillStack);
		Solver.solveOrganically(grillStack);
		Solver.insertCandidateValue(grillStack);
		Solver.solveOrganically(grillStack);
		Solver.insertCandidateValue(grillStack);
		Solver.solveOrganically(grillStack);
		*/
		 
		 
	} 
	
	
	
}


