package nfp136.sudoku;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GrillFillLColonneTest {
	
	@Test
	void testMinInd() {
		
		int[] row1 = {0, 3, 6, 0, 0, 0, 0, 1, 2};
		int[] row2 = {0, 0, 0, 0, 0, 0, 0, 0, 3};
		int[] row3 = {0, 0, 2, 3, 6, 0, 4, 0, 0};
		int[] row4 = {0, 0, 1, 8, 0, 0, 6, 0, 5};
		int[] row5 = {0, 6, 0, 0, 7, 0, 8, 0, 0};
		int[] row6 = {0, 0, 0, 6, 0, 9, 3, 0, 0};
		int[] row7 = {6, 1, 8, 5, 3, 7, 2, 4, 9};
		int[] row8 = {9, 2, 3, 1, 4, 8, 5, 6, 7};
		int[] row9 = {4, 7, 5, 2, 9, 6, 1, 3, 8};

		int[][] grille1 = {row1, row2, row3, row4, row5, row6, row7, row8, row9};
		
		Grille grilleObj1 = new Grille(grille1);
		
		
		Stack<Grille> stack = new Stack<>();
		stack.push(grilleObj1);
		
		ArrayList<Integer> indMinList = new ArrayList<>();
		indMinList.add(0);
		indMinList.add(3);
		
		//FillResult res = GrilleFillLigne.fillMinEl(grilleObj1, indMinList);
		int indMin = grilleObj1.trouverColonneAvecMinEl(indMinList).index;
		assertEquals(6, indMin);
	}
	
	@Test
	void testFillEl() {
		
		int[] row1 = {0, 3, 6, 0, 0, 0, 0, 1, 2};//*7
		int[] row2 = {0, 0, 0, 0, 0, 0, 0, 0, 3};
		int[] row3 = {0, 0, 2, 3, 6, 0, 4, 0, 0};
		int[] row4 = {0, 0, 1, 8, 0, 0, 6, 0, 5};
		int[] row5 = {0, 6, 0, 0, 7, 0, 8, 0, 0};
		int[] row6 = {0, 0, 0, 6, 0, 9, 3, 0, 0};
		int[] row7 = {6, 1, 8, 5, 3, 7, 2, 4, 9};
		int[] row8 = {9, 2, 3, 1, 4, 8, 5, 6, 7};
		int[] row9 = {4, 7, 5, 2, 9, 6, 1, 3, 8};
		
		/*int[] row1 = {5, 3, 4, 6, 7, 8, 9, 1, 2}; // missing '4' in position 3
		int[] row2 = {6, 7, 2, 1, 9, 5, 3, 4, 8};
		int[] row3 = {0, 0, 0, 3, 4, 2, 0, 6, 7};
		int[] row4 = {8, 5, 9, 7, 6, 1, 4, 2, 3};
		int[] row5 = {4, 2, 6, 8, 5, 3, 7, 9, 1};
		int[] row6 = {7, 1, 3, 9, 2, 4, 8, 5, 6};
		int[] row7 = {9, 6, 1, 5, 3, 7, 2, 8, 4};
		int[] row8 = {2, 8, 7, 4, 1, 9, 6, 3, 5};
		int[] row9 = {3, 4, 5, 2, 8, 6, 1, 7, 9};
*/
		int[][] grille1 = {row1, row2, row3, row4, row5, row6, row7, row8, row9};
		
		Grille grilleObj1 = new Grille(grille1);
		
		
		Stack<Grille> stack = new Stack<>();
		stack.push(grilleObj1);
		
		ArrayList<Integer> indMinList = new ArrayList<>();
		indMinList.add(0);
		
		FillResult res = GrilleFillColonne.fillMinEl(grilleObj1, indMinList);
		
		System.err.println("res " + res.result);
		System.out.println(grilleObj1.colonneObjets[res.result]);
		System.out.println(grilleObj1.valDeuxCasesPossible);
		
		//assertEquals(2, res.result);
	}
}
