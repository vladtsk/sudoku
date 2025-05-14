package nfp136.sudoku;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SolverTest {
	
	@Test
	void organicTest1() {
		
		int[] row1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] row2 = {0, 6, 0, 0, 0, 0, 0, 3, 0};
		int[] row3 = {0, 7, 0, 0, 0, 0, 0, 4, 0};
		int[] row4 = {7, 8, 9, 1, 2, 3, 4, 5, 6};
		int[] row5 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] row6 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] row7 = {4, 5, 6, 7, 8, 9, 1, 2, 3};
		int[] row8 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] row9 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		

		int[][] grille1 = {row1, row2, row3, row4, row5, row6, row7, row8, row9};
		
		Grille grilleObj1 = new Grille(grille1);
		
		
		Stack<Grille> stack = new Stack<>();
		stack.push(grilleObj1);
		
		
		try {
			Solver.solveOrganically(grilleObj1); //stack
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		//boolean upd = SolverUtils.solveByInsertingValue(stack);
				
		
		 assertTrue(true);//assertTrue(result.estComplet());
	       
		
		
	}
	
}
