package nfp136.sudoku;

//import static org.junit.jupiter.api.Assertions.assertArrayEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;


public class GrilleTest {
		
	@Test
	void testEstVide1() {
		int[] rowEx1 = {5, 3, 0, 0, 7, 0, 0, 0, 0};
		int[] rowEx2 = {6, 0, 0, 1, 9, 5, 0, 0, 0};
		int[] rowEx3 = {0, 9, 8, 0, 0, 0, 0, 6, 0};
		int[] rowEx4 = {8, 0, 0, 0, 6, 0, 0, 0, 3};
		int[] rowEx5 = {0, 0, 0, 8, 0, 3, 0, 0, 1}; 
		int[] rowEx6 = {0, 0, 0, 0, 2, 0, 0, 0, 6}; 
		int[] rowEx7 = {0, 6, 0, 0, 0, 0, 2, 8, 0}; 
		int[] rowEx8 = {0, 0, 0, 4, 1, 9, 0, 0, 5};
		int[] rowEx9 = {0, 0, 0, 0, 8, 0, 0, 7, 9};
		
		int[][] grille1 = {rowEx1, rowEx2, rowEx3, rowEx4, rowEx5, rowEx6, rowEx7, rowEx8, rowEx9};
		
		Grille grilleObj1 = new Grille(grille1);
		
		assertFalse(grilleObj1.estComplet());
	}
	
	@Test
	void testEstVide2() {
		int[] rowEx1 = {5, 3, 4, 6, 7, 8, 9, 1, 2};
		int[] rowEx2 = {6, 2, 7, 1, 9, 5, 3, 4, 8};
		int[] rowEx3 = {1, 9, 8, 3, 4, 2, 5, 6, 7};
		int[] rowEx4 = {8, 7, 5, 9, 6, 1, 4, 2, 3};
		int[] rowEx5 = {2, 4, 6, 8, 5, 3, 7, 9, 1};
		int[] rowEx6 = {3, 1, 9, 7, 2, 4, 8, 5, 6};
		int[] rowEx7 = {9, 6, 1, 5, 3, 7, 2, 8, 4};
		int[] rowEx8 = {7, 8, 2, 4, 1, 9, 6, 3, 5};
		int[] rowEx9 = {4, 5, 3, 2, 8, 6, 1, 7, 9};

		
		int[][] grille1 = {rowEx1, rowEx2, rowEx3, rowEx4, rowEx5, rowEx6, rowEx7, rowEx8, rowEx9};
		
		Grille grilleObj1 = new Grille(grille1);
		
		assertTrue(grilleObj1.estComplet());
	}
	
	@Test
	void testEstVide3() {
		int[] rowEx1 = {5, 3, 4, 6, 7, 8, 9, 1, 2};
		int[] rowEx2 = {6, 2, 7, 1, 9, 5, 3, 4, 8};
		int[] rowEx3 = {1, 9, 8, 0, 4, 2, 5, 6, 7};
		int[] rowEx4 = {8, 7, 5, 9, 6, 1, 4, 2, 3};
		int[] rowEx5 = {2, 4, 6, 8, 5, 3, 7, 9, 1};
		int[] rowEx6 = {3, 1, 9, 7, 2, 4, 8, 5, 6};
		int[] rowEx7 = {9, 6, 1, 5, 3, 7, 2, 8, 4};
		int[] rowEx8 = {7, 8, 2, 4, 1, 9, 6, 3, 5};
		int[] rowEx9 = {4, 5, 3, 2, 8, 6, 1, 7, 9};

		
		int[][] grille1 = {rowEx1, rowEx2, rowEx3, rowEx4, rowEx5, rowEx6, rowEx7, rowEx8, rowEx9};
		
		Grille grilleObj1 = new Grille(grille1);
		
		assertFalse(grilleObj1.estComplet());
	}
	
	
}
