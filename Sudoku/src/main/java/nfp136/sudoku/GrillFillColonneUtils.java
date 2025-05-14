package nfp136.sudoku;

import java.util.ArrayList;

public class GrillFillColonneUtils {
	
	
	public static void analyseEmptySpots(Grille grilleObj1, ArrayList<Case> emptySpots, int missingEl, ArrayList<Boolean> suiviElements) {
		
		
		Ligne[] ligneObjets = grilleObj1.ligneObjets;
		SousCarre[] sousCarreObjets = grilleObj1.sousCarreObjets;
		
		for(int i = 0; i < emptySpots.size(); i++) {
			 
			 int ligne = emptySpots.get(i).ligne;
			 int sousCarre = emptySpots.get(i).sousCarre;
			 
			 int col = emptySpots.get(i).colonne;
			 
			 
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
			 
			 
			 //ArrayList<Integer> forbiddenVals = emptySpots.get(i).valeursInterdites;
			 //boolean forbiddenContains = forbiddenVals.contains(missingEl);
			 
			 System.out.println("forbidden contains: " + forbiddenContains);
			 
			 
			 Ligne ligneObj = ligneObjets[ligne-1];
			 boolean ligneContains = ligneObj.contientElement(missingEl);
			 
			 SousCarre sousCarreObj = sousCarreObjets[sousCarre-1];
			 boolean sousCarreContains = sousCarreObj.contientElement(missingEl);
			 
			 suiviElements.add(ligneContains || sousCarreContains || forbiddenContains);
			 
			 
			 System.out.println("ligneObj obj " + ligneObj);
			 System.out.println("Ligne "+ ligne + " contient el " + missingEl + " : " + ligneContains);
			 
			 
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
