package nfp136.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class GrillUtils {
	
	/*public static Grille createArrayCopy(Grille arr) {
		int[][] gridCopy = new int[9][9]; 
		 
		 for (int i = 0; i < 9; i++) {
			 gridCopy[i] = Arrays.copyOf(arr.gr[i], 9);
		 }
		 
		 
		return new Grille(gridCopy);
	}
	
	*/
	public static Grille createArrayCopy(Grille arr) { //createFullCopy
	    int[][] gridCopy = new int[9][9];
	    for (int i = 0; i < 9; i++) {
	        gridCopy[i] = Arrays.copyOf(arr.gr[i], 9);
	    }

	    Grille copy = new Grille(gridCopy);

	 
	    copy.forbiddenElCases = new ArrayList<>(arr.forbiddenElCases); 
	    copy.valDeuxCasesPossible = (Stack<ValPossible>) arr.valDeuxCasesPossible.clone();

	    
	    copy.ligneObjets = Arrays.copyOf(arr.ligneObjets, arr.ligneObjets.length);
	    copy.colonneObjets = Arrays.copyOf(arr.colonneObjets, arr.colonneObjets.length);
	    copy.sousCarreObjets = Arrays.copyOf(arr.sousCarreObjets, arr.sousCarreObjets.length);

	    return copy;
	}

	
}
