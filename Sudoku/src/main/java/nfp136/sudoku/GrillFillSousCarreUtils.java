package nfp136.sudoku;

import java.util.ArrayList;

public class GrillFillSousCarreUtils {
	
	
	public static void analyseEmptySpots(Grille grilleObj1, ArrayList<Case> emptySpots, int missingEl, ArrayList<Boolean> suiviElements) {
		Ligne[] ligneObjets = grilleObj1.ligneObjets;
		Colonne[] colonneObjets = grilleObj1.colonneObjets;
		//SousCarre[] sousCarreObjets = grilleObj1.sousCarreObjets;
		
		System.out.println("missingEl: " + missingEl);
		System.out.println("emptySpots: " + emptySpots);
		
		for(int i = 0; i < emptySpots.size(); i++) {
			 
			 int ligne = emptySpots.get(i).ligne;
			 int col = emptySpots.get(i).colonne;

			 //ArrayList<Integer> forbiddenVals = emptySpots.get(i).valeursInterdites;
			 //boolean forbiddenContains = forbiddenVals.contains(missingEl);
			 
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
			 
			
			 System.out.println("forbidden contains: " + forbiddenContains);
			 
		
			 Ligne ligneObj = ligneObjets[ligne-1];
			 Colonne colonneObj = colonneObjets[col-1];
			 
			 
			 
			 
			 boolean ligneContains = ligneObj.contientElement(missingEl);
			 boolean colonneContains = colonneObj.contientElement(missingEl);
			 
			 
			 suiviElements.add((ligneContains || colonneContains || forbiddenContains));
			 
			 
			 System.out.println("ligneObj obj " + ligneObj);
			 System.out.println("colonneObj obj " + colonneObj);
			 System.out.println("Ligne "+ ligne + " contient el " + missingEl + " : " + ligneContains);
			 System.out.println("Colonne "+ col + " contient el " + missingEl + " : " + colonneContains);
			
			 
			 
		 }
	}
	
	public static int calculateFalseEls(ArrayList<Boolean> suiviElements) {
		int falseCompteur = 0;
		for(int i = 0; i < suiviElements.size(); i++) {
			if(suiviElements.get(i) == false) falseCompteur++;
		}
		
		return falseCompteur;
	}
	
	
}
