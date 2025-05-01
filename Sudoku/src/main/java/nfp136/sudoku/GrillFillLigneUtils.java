package nfp136.sudoku;

import java.util.ArrayList;

public class GrillFillLigneUtils {
	
	
	public static void analyseEmptySpots(Ligne[] ligneObjets, Colonne[] colonneObjets, ArrayList<Case> emptySpots, int missingEl, ArrayList<Boolean> suiviElements) {
		
		
		for(int i = 0; i < emptySpots.size(); i++) {
			 
			 int col = emptySpots.get(i).colonne;
			 int sousCarre = emptySpots.get(i).sousCarre;
			 
			 System.out.println("col " + col); 
			 System.out.println("sousCarre " + sousCarre); 
			 
			 Colonne colObj = colonneObjets[col-1];
			 boolean colContains = colObj.contientElement(missingEl);
			 suiviElements.add(colContains);
			 
			 
			 System.out.println("col obj " + colObj);
			 System.out.println("Col "+ col + " contient el " + missingEl + " : " + colContains);
			 
			 
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
			 
			 System.out.println("sousCarreObj1 contient the missing el: " + sousCarreObj1.contientElement(missingEl));
			 System.out.println("sousCarreObj2 contient the missing el: " + sousCarreObj2.contientElement(missingEl));

			 
			 if(!sousCarreObj1.contientElement(missingEl) && sousCarreObj2.contientElement(missingEl)) {
				 
				 ligneObjets[indMin].updateLigne(col1, missingEl);
				 colonneObjets[col1-1].updateCol(indMin+1, missingEl);
				 sousCarreObjets[sousCarre1 - 1].updateSousCarre(indMin+1, col1,  missingEl);
				 
				 updated = true;
				 
				 
			 }
		return updated;
	}
}
