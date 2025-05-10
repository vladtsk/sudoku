package nfp136.sudoku;

import java.util.ArrayList;

public class GrillFillSousCarreUtils {
	
	
	public static void analyseEmptySpots(Ligne[] ligneObjets, Colonne[] colonneObjets, ArrayList<Case> emptySpots, int missingEl, ArrayList<Boolean> suiviElements) {
		
		System.out.println("suiviEls: " + suiviElements);
		System.out.println("missingEl: " + missingEl);
		System.out.println("emptySpots: " + emptySpots);
		
		for(int i = 0; i < emptySpots.size(); i++) {
			 
			 int ligne = emptySpots.get(i).ligne;
			 int colonne = emptySpots.get(i).colonne;
			 //int sousCarre = emptySpots.get(i).sousCarre;
			 
			 System.out.println("ligne " + ligne); 
			 System.out.println("colonne " + colonne); 
			 
			 Ligne ligneObj = ligneObjets[ligne-1];
			 Colonne colonneObj = colonneObjets[colonne-1];
			 boolean ligneContains = ligneObj.contientElement(missingEl);
			 boolean colonneContains = colonneObj.contientElement(missingEl);
			 
			 
			 suiviElements.add((ligneContains || colonneContains));
			 
			 
			 System.out.println("ligneObj obj " + ligneObj);
			 System.out.println("colonneObj obj " + colonneObj);
			 System.out.println("Ligne "+ ligne + " contient el " + missingEl + " : " + ligneContains);
			 System.out.println("Colonne "+ colonne + " contient el " + missingEl + " : " + colonneContains);
			 System.out.println("One or the other contains it: " +(ligneContains || colonneContains));
			 
			 
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
			 	 
			 
			 int ligne1 = emptySpots.get(elIndexes.get(0)).ligne;
			 int ligne2 = emptySpots.get(elIndexes.get(1)).ligne;
			 
			 int colonne1 = emptySpots.get(elIndexes.get(0)).colonne;
			 int colonne2 = emptySpots.get(elIndexes.get(1)).colonne;
			 
			 System.out.println("ligne1: " + ligne1 + ", ligne2: " + ligne2);
			 System.out.println("colonne1: " + colonne1 + ", colonne2: " + colonne2);
			 
			 			 
			 Ligne ligneObj1 = ligneObjets[ligne1 - 1];
			 Ligne ligneObj2 = ligneObjets[ligne2 - 1];
			 
			 Colonne colonneObj1 = colonneObjets[colonne1 - 1];
			 Colonne colonneObj2 = colonneObjets[colonne2 - 1];
			 
			 System.out.println("ligneObj1 contient the missing el: " + ligneObj1.contientElement(missingEl));
			 System.out.println("colonneObj1 contient the missing el: " + colonneObj1.contientElement(missingEl));
			 System.out.println("ligneObj2 contient the missing el: " + ligneObj2.contientElement(missingEl));
			 System.out.println("colonneObj2 contient the missing el: " + colonneObj2.contientElement(missingEl));
			 
			 if((!ligneObj1.contientElement(missingEl) && !colonneObj1.contientElement(missingEl)) && ligneObj2.contientElement(missingEl) && colonneObj2.contientElement(missingEl)) {
				 
				 ligneObjets[ligne1-1].updateLigne(colonne1, missingEl);
				 colonneObjets[colonne1-1].updateCol(ligne1, missingEl);
				 sousCarreObjets[indMin].updateSousCarre(ligne1, colonne1,  missingEl);
				 
				 updated = true;
				 
				 
			 } else if((ligneObj1.contientElement(missingEl) && colonneObj1.contientElement(missingEl)) && (!ligneObj2.contientElement(missingEl) && !colonneObj2.contientElement(missingEl))) {
				 System.out.println("putting missing el " + missingEl + " at row " + ligne2 + ", col " + colonne2);
				 colonneObjets[colonne2-1].updateCol(ligne2, missingEl);
				 ligneObjets[ligne2-1].updateLigne(colonne2, missingEl);
				 sousCarreObjets[indMin].updateSousCarre(ligne2, colonne2,  missingEl);
				 
				 updated = true;
				 
			 } else {
				 for(int i = 0; i < suiviElements.size(); i++) {
					 if(suiviElements.get(i) == false) emptySpots.get(i).updateValeursPossibles(missingEl);
					 if(!grilleObj1.casesDeuxEl.contains(emptySpots.get(i))) grilleObj1.updateCasesDeuxEl(emptySpots.get(i));
				 }
			 }
			 
			 
		return updated;
	}
}
