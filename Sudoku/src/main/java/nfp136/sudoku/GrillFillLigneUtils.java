package nfp136.sudoku;

import java.util.ArrayList;

public class GrillFillLigneUtils {
	
	
	public static void analyseEmptySpots(Grille grilleObj1, ArrayList<Case> emptySpots, int missingEl, ArrayList<Boolean> suiviElements) {
		
		Colonne[] colonneObjets = grilleObj1.colonneObjets;
		SousCarre[] sousCarreObjets = grilleObj1.sousCarreObjets;
		
		for(int i = 0; i < emptySpots.size(); i++) {
			 
			 int col = emptySpots.get(i).colonne;
			 int sousCarre = emptySpots.get(i).sousCarre;
			 
			 int ligne = emptySpots.get(i).ligne;
			 System.out.println("ligne: " + ligne);
			 
			 Case c = new Case(ligne, col);
			 
			 boolean containsCase = grilleObj1.forbiddenElCases.contains(c);
			 boolean forbiddenContains = false;
			 
			 if(containsCase) {
				 System.out.println("forbiddenElCases contains case: " + c);
				 int index = grilleObj1.forbiddenElCases.indexOf(c);
				 ArrayList<Integer> forbiddenVals = grilleObj1.forbiddenElCases.get(index).valeursInterdites;
				 if(forbiddenVals.contains(missingEl)) forbiddenContains = true;
				 
				 System.out.println("forbiddenVals: " + forbiddenVals); 
			 }
			 
			 Colonne colObj = colonneObjets[col-1];
			 boolean colContains = colObj.contientElement(missingEl);
			 
			 SousCarre sousCarreObj = sousCarreObjets[sousCarre-1];
			 boolean sousCarreContains = sousCarreObj.contientElement(missingEl);
			 
			 suiviElements.add(colContains || sousCarreContains || forbiddenContains);
			 
		 }
	}
	
	public static int calculateFalseEls(ArrayList<Boolean> suiviElements) {
		int falseCompteur = 0;
		for(int i = 0; i < suiviElements.size(); i++) {
			if(suiviElements.get(i) == false) falseCompteur++;
		}
		
		return falseCompteur;
	}
	
	public static boolean update2El(ArrayList<Boolean> suiviElements,  ArrayList<Case> emptySpots, Grille grilleObj1, int missingEl,  int indMin) {
		Ligne[] ligneObjets = grilleObj1.ligneObjets;
		Colonne[] colonneObjets = grilleObj1.colonneObjets;
		SousCarre[] sousCarreObjets = grilleObj1.sousCarreObjets;
		
		boolean updated = false;
		
		System.out.println("2 elements faux");
		 
		 ArrayList<Integer> elIndexes = new ArrayList<>(); 
		 
		 
		 for(int i = 0; i < suiviElements.size(); i++) {
			 if(suiviElements.get(i) == false) {
				 elIndexes.add(i);
				 			 
			 }
		 }
		 
		 System.out.println("elIndexes " + elIndexes);
		 System.out.println("emptySpots " + emptySpots);
			 
			 int sousCarre1 = emptySpots.get(elIndexes.get(0)).sousCarre;
			 int sousCarre2 = emptySpots.get(elIndexes.get(1)).sousCarre;
			 
			 int col1 = emptySpots.get(elIndexes.get(0)).colonne;
			 int col2 = emptySpots.get(elIndexes.get(1)).colonne;
			 			 
			 SousCarre sousCarreObj1 = sousCarreObjets[sousCarre1 - 1];
			 SousCarre sousCarreObj2 = sousCarreObjets[sousCarre2 - 1];
			
			 
			 if(!sousCarreObj1.contientElement(missingEl) && sousCarreObj2.contientElement(missingEl)) {
				 
				 ligneObjets[indMin].updateLigne(col1, missingEl);
				 colonneObjets[col1-1].updateCol((indMin+1), missingEl);
				 sousCarreObjets[sousCarre1 - 1].updateSousCarre((indMin+1), col1,  missingEl);
				 
				 updated = true;
				 
				 
			 } else if(sousCarreObj1.contientElement(missingEl) && !sousCarreObj2.contientElement(missingEl)) {
				 System.out.println("putting missing el " + missingEl + " at row " + (indMin+1) + ", col " + col2);
				 
				 ligneObjets[indMin].updateLigne(col2, missingEl);
				 colonneObjets[col2-1].updateCol((indMin+1), missingEl);
				 sousCarreObjets[sousCarre2 - 1].updateSousCarre((indMin+1), col2,  missingEl);
				 
				 updated = true;
				 
			 } 
		return updated;
	}
}
