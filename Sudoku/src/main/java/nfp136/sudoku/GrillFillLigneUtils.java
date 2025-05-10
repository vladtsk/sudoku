package nfp136.sudoku;

import java.util.ArrayList;

public class GrillFillLigneUtils {
	
	
	public static void analyseEmptySpots(Grille grilleObj1, ArrayList<Case> emptySpots, int missingEl, ArrayList<Boolean> suiviElements) {
		
		Colonne[] colonneObjets = grilleObj1.colonneObjets;
		SousCarre[] sousCarreObjets = grilleObj1.sousCarreObjets;
		
		for(int i = 0; i < emptySpots.size(); i++) {
			 
			 int col = emptySpots.get(i).colonne;
			 int sousCarre = emptySpots.get(i).sousCarre;
			 
			 System.out.println("col " + col); 
			 System.out.println("sousCarre " + sousCarre); 
			 
			 Colonne colObj = colonneObjets[col-1];
			 boolean colContains = colObj.contientElement(missingEl);
			 
			 SousCarre sousCarreObj = sousCarreObjets[sousCarre-1];
			 boolean sousCarreContains = sousCarreObj.contientElement(missingEl);
			 
			 suiviElements.add(colContains || sousCarreContains);
			 
			 
			 System.out.println("Col "+ col + " contient el " + missingEl + " : " + colContains);
			 System.out.println("SousCarré "+ sousCarre + " contient el " + missingEl + " : " + sousCarreContains);
			 
			 
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
			 
			 System.out.println("sousCarre1 " + sousCarre1);
			 System.out.println("sousCarre2 " + sousCarre2);
			 
			 int col1 = emptySpots.get(elIndexes.get(0)).colonne;
			 int col2 = emptySpots.get(elIndexes.get(1)).colonne;
			 
			 System.out.println(col2);
			 
			 SousCarre sousCarreObj1 = sousCarreObjets[sousCarre1 - 1];
			 SousCarre sousCarreObj2 = sousCarreObjets[sousCarre2 - 1];
			 
			 System.out.println("sousCarreObj1 contient the missing el (never?): " + sousCarreObj1.contientElement(missingEl));
			 System.out.println("sousCarreObj2 contient the missing el (never?): " + sousCarreObj2.contientElement(missingEl));

			 
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
				 
			 } else {
				 for(int i = 0; i < suiviElements.size(); i++) {
					 if(suiviElements.get(i) == false) {
						 emptySpots.get(i).updateValeursPossibles(missingEl);
						 System.out.println("emptySpot: " + emptySpots.get(i));
						 System.out.println("valeur possible: " + missingEl);
					 }
						 
					 //if(!grilleObj1.casesDeuxEl.contains(emptySpots.get(i))) grilleObj1.updateCasesDeuxEl(emptySpots.get(i));
				 }
				 
				 
				 
			 }
			 
		return updated;
	}
}
