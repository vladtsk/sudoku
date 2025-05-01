package nfp136.sudoku;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ColonneTest {
	
	@Test
	public void testInit() {
		int[] column1 = {4, 5, 3, 8, 0, 1, 0, 2, 7}; // 9 and 6 missing
		
		assertArrayEquals(column1, new Colonne(column1).getColonne());
	}
	
	@Test
	void testNbEl() {
		int[] col1 = {4, 5, 3, 8, 0, 1, 0, 2, 7};
		Colonne col = new Colonne(col1);
		int nbEl = col.calculerNbElements();
		
		assertEquals(7, nbEl); 
	}
	
	@Test
	void testElManquants() {
		int[] col1 = {4, 5, 3, 8, 0, 1, 0, 2, 7};
		Colonne col = new Colonne(col1);
		ArrayList<Integer> list = new ArrayList<>(List.of(6, 9));
		
		assertEquals(list, col.getElementsManquants());
	}
	
	@Test 
	void testCasesVides() {
		int[] col1 = {0, 0, 7, 5, 3, 2, 9, 0, 0};
		Colonne col = new Colonne(col1);
		int num = col.numColonneCourante;
		
		Case case1 = new Case(1, num);
		Case case2 = new Case(2, num);
		Case case3 = new Case(8, num);
		Case case4 = new Case(9, num);
		ArrayList<Case> listVides = new ArrayList<>();
		listVides.add(case1);
		listVides.add(case2);
		listVides.add(case3);
		listVides.add(case4);
		
		assertEquals(listVides,  col.getCasesVides());
	}

}
