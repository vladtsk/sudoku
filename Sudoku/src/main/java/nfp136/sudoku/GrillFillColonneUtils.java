package nfp136.sudoku;

import java.util.ArrayList;

public class GrillFillColonneUtils {
	
	
	public static void analyseEmptySpots(Grille grilleObj1, ArrayList<Case> emptySpots, int missingEl, ArrayList<Boolean> suiviElements) {
		
		
		Ligne[] ligneObjets = grilleObj1.ligneObjets;
		SousCarre[] sousCarreObjets = grilleObj1.sousCarreObjets;
		
		for(int i = 0; i < emptySpots.size(); i++) {
			 
			 int ligne = emptySpots.get(i).ligne;
			 int sousCarre = emptySpots.get(i).sousCarre;
			 
			 ArrayList<Integer> forbiddenVals = emptySpots.get(i).valeursInterdites;
			 boolean forbiddenContains = forbiddenVals.contains(missingEl);
			 
			 System.out.println("forbiddenVals: " + forbiddenVals); 
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
	
	/*public static boolean update2El(ArrayList<Boolean> suiviElements,  ArrayList<Case> emptySpots, Grille grilleObj1, int missingEl,  int indMin) {
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
			 
			 System.out.println("sousCarre1 " + sousCarre1);
			 System.out.println("sousCarre2 " + sousCarre2);
			 
			 int ligne1 = emptySpots.get(elIndexes.get(0)).ligne;
			 int ligne2 = emptySpots.get(elIndexes.get(1)).ligne;
			 
			 System.out.println("ligne2 " + ligne2);
			 
			 SousCarre sousCarreObj1 = sousCarreObjets[sousCarre1 - 1];
			 SousCarre sousCarreObj2 = sousCarreObjets[sousCarre2 - 1];
			 
			 System.out.println("sousCarreObj1 contient the missing el: " + sousCarreObj1.contientElement(missingEl));
			 System.out.println("sousCarreObj2 contient the missing el: " + sousCarreObj2.contientElement(missingEl));

			 
			 if(!sousCarreObj1.contientElement(missingEl) && sousCarreObj2.contientElement(missingEl)) {
				 
				 colonneObjets[indMin].updateCol(ligne1, missingEl);
				 ligneObjets[ligne1-1].updateLigne((indMin+1), missingEl);
				 sousCarreObjets[sousCarre1 - 1].updateSousCarre(ligne1, (indMin+1),  missingEl);
				 
				 updated = true;
				 
				 
			 } else if(sousCarreObj1.contientElement(missingEl) && !sousCarreObj2.contientElement(missingEl)) {
				 System.out.println("putting missing el " + missingEl + " at row " + ligne2 + ", col " + (indMin+1));
				 colonneObjets[indMin].updateCol(ligne2, missingEl);
				 ligneObjets[ligne2-1].updateLigne((indMin+1), missingEl);
				 sousCarreObjets[sousCarre2 - 1].updateSousCarre(ligne2, (indMin+1),  missingEl);
				 
				 updated = true;
				 
			 } else {
				 for(int i = 0; i < suiviElements.size(); i++) {
					 if(suiviElements.get(i) == false) emptySpots.get(i).updateValeursPossibles(missingEl);
					 if(!grilleObj1.casesDeuxEl.contains(emptySpots.get(i))) grilleObj1.updateCasesDeuxEl(emptySpots.get(i));
				 }
			 }
			 
			 
		return updated;
	}*/
}
