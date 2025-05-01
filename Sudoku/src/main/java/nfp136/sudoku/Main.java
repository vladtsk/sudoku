package nfp136.sudoku;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		
		int[] rowEx1 = {0, 0, 5, 2, 0, 0, 1, 0, 0}; 
		int[] rowEx2 = {1, 3, 4, 0, 0, 6, 0, 8, 9};
		int[] rowEx3 = {0, 8, 0, 7, 0, 9, 0, 0, 0};
		int[] rowEx4 = {8, 0, 0, 0, 6, 0, 0, 0, 3};
		int[] rowEx5 = {4, 7, 0, 8, 0, 3, 9, 0, 1};
		int[] rowEx6 = {9, 0, 0, 0, 2, 0, 0, 0, 6};
		int[] rowEx7 = {0, 6, 0, 0, 0, 0, 5, 1, 0};
		int[] rowEx8 = {0, 0, 0, 4, 1, 9, 0, 0, 5};
		int[] rowEx9 = {0, 0, 0, 0, 8, 0, 0, 9, 7};
		
		
		int[][] grille1 = {rowEx1, rowEx2, rowEx3, rowEx4, rowEx5, rowEx6, rowEx7, rowEx8, rowEx9};
		
		System.out.println(Arrays.deepToString(grille1));
		Grille grilleObj1 = new Grille(grille1);
		
		ArrayList<Integer> indMinList = new ArrayList<>();
		while(!indMinList.contains(-1)) { // indMinList.size() <9
			indMinList.add(GrilleFillLigne.fillMinEl(grilleObj1, indMinList));
			System.out.println(grilleObj1);

		}
		System.out.println("indMinList: "+ indMinList);
		
		ArrayList<Integer> indMinList2 = new ArrayList<>();
		while(!indMinList.contains(-1)) { // indMinList.size() <9
			indMinList.add(GrilleFillColonne.fillMinEl(grilleObj1, indMinList2));
			System.out.println(grilleObj1);

		}
		 
		
	} 
	
	
}


