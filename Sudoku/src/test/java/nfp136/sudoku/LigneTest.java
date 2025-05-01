package nfp136.sudoku;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class LigneTest {
		
	@Test
	void testInit() {
		int[] row1 = {7, 0, 5, 3, 2, 9, 0, 0, 6};
		Ligne ligne = new Ligne(row1);
		assertArrayEquals(row1, ligne.getLigne());
	}
	
	@Test
	void testNbEl() {
		int[] row1 = {7, 0, 5, 3, 2, 9, 0, 0, 6};
		Ligne ligne = new Ligne(row1);
		int nbEl = ligne.calculerNbElements();
		
		assertEquals(6, nbEl); 
	}
	
	@Test
	void testElManquants() {
		int[] row1 = {7, 0, 5, 3, 2, 9, 0, 0, 6};
		Ligne ligne = new Ligne(row1);
		ArrayList<Integer> list = new ArrayList<>(List.of(1, 4, 8));
		
		assertEquals(list, ligne.getElementsManquants());
	}
	
	@Test 
	void testCasesVides() {
		int[] row1 = {0, 0, 7, 5, 3, 2, 9, 0, 0};
		Ligne ligne = new Ligne(row1);
		int num = ligne.numLigneCourante;
		
		Case case1 = new Case(num, 1);
		Case case2 = new Case(num, 2);
		Case case3 = new Case(num, 8);
		Case case4 = new Case(num, 9);
		ArrayList<Case> listVides = new ArrayList<>();
		listVides.add(case1);
		listVides.add(case2);
		listVides.add(case3);
		listVides.add(case4);
		
		assertEquals(listVides, ligne.getCasesVides());
	}
}
