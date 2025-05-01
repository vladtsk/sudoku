package nfp136.sudoku;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SousCarreTest {
	
	@Test
	public void testInit() {
		int[] sousCarre1 = {0, 5, 3, 0, 1, 7, 0, 2, 8}; // 4,6, 9  missing
		
		assertArrayEquals(sousCarre1, new SousCarre(sousCarre1).getSousCarre());
	}
	
	@Test
	void testNbEl() {
		int[] sousCarre1 = {0, 5, 3, 0, 1, 7, 0, 2, 8};		
		SousCarre sousCarreObj1 = new SousCarre(sousCarre1);
		int nbEl = sousCarreObj1.calculerNbElements();
		
		assertEquals(6, nbEl); 
	}
	
	@Test
	void testElManquants() {
		int[] sousCarre1 = {0, 5, 3, 0, 0, 7, 0, 2, 8};	
		SousCarre sousCarreObj1 = new SousCarre(sousCarre1);
		ArrayList<Integer> list = new ArrayList<>(List.of(1, 4, 6, 9));
		
		assertEquals(list, sousCarreObj1.getElementsManquants());
	}
	
	@Test 
	void testCasesVides() {
		int[] sousCarre1 = {0, 5, 7, 4, 3, 2, 9, 0, 0};
		
		SousCarre sousCarreObj1 = new SousCarre(sousCarre1);
		sousCarreObj1.numSousCarreCourant = 1;
		
		int num = sousCarreObj1.numSousCarreCourant;
		System.out.println("num1: " + num);
		
		
		Case case1;
		Case case2;
		Case case3;
		
		case1 = new Case(1, 1);
		case2 = new Case(3, 2);
		case3 = new Case(3, 3);
		
		ArrayList<Case> listVides = new ArrayList<>();
		listVides.add(case1);
		listVides.add(case2);
		listVides.add(case3);
		
		assertEquals(listVides,  sousCarreObj1.getCasesVides());
		
	}
	
	@Test 
	void testCasesVide2() {
		int[] sousCarre1 = {5, 4, 0, 8, 0, 2, 3, 1, 6};
		
		SousCarre sousCarreObj1 = new SousCarre(sousCarre1);
		sousCarreObj1.numSousCarreCourant = 5;
		int num = sousCarreObj1.numSousCarreCourant;
		System.out.println("num2: " + num);
		
		Case case1;
		Case case2;
		
		case1 = new Case(4, 6);
		case2 = new Case(5, 5);
		
		ArrayList<Case> listVides = new ArrayList<>();
		listVides.add(case1);
		listVides.add(case2);
		
		assertEquals(listVides,  sousCarreObj1.getCasesVides());
		
	}
	
	@Test 
	void testUpdateCases() {
		int[] sousCarre1 = {1, 5, 0, 4, 3, 2, 9, 0, 0};
		SousCarre sousCarreObj1 = new SousCarre(sousCarre1);
		
		
		Case emptyCase1 = sousCarreObj1.getCasesVides().get(2);
		System.out.println("Empty case: " + emptyCase1);
		
		System.out.println(sousCarreObj1.numSousCarreCourant);
		
		
		sousCarreObj1.updateSousCarre(emptyCase1.ligne, emptyCase1.colonne, 100);
		System.out.println(sousCarreObj1);
	}

}
