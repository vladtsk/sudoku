package nfp136.sudoku;

import java.util.ArrayList;

public class GrilleFillLigne {
		
	public static FillResult fillMinEl(Grille grilleObj1, ArrayList<Integer> indMinList) {
		
		boolean grillUpd = false;
		int indMin = grilleObj1.trouverLigneAvecMinEl(indMinList).index;
		if(indMin == -1) return new FillResult(-1, grillUpd);
		
		
		System.out.println("ligneObj 0 : " + grilleObj1.ligneObjets[indMin]);
		
		Ligne[] ligneObjets = grilleObj1.ligneObjets;
		Colonne[] colonneObjets = grilleObj1.colonneObjets;
		SousCarre[] sousCarreObjets = grilleObj1.sousCarreObjets;
		
		int k = 0;
		
		outerLoop:
		while(k==0) {
			k = 1;
			ArrayList<Integer> missing = ligneObjets[indMin].getElementsManquants();
			System.out.println("missing: " + missing);
			
			 ArrayList<Case> emptySpots = ligneObjets[indMin].getCasesVides();
			 
			 for(int j = 0; j < missing.size(); j++) {
				 System.out.println("El. " + missing.get(j));
				 
				 ArrayList<Boolean> suiviElements = new ArrayList<>(); // liste de variables booléennes pour suivre les colonnes/lignes qui contiennent l'élément 
				 int falseCompteurCol = 0; // compteur de variable "false" (en colonne)
				 //ArrayList<Boolean> suiviElementsSousCarres = new ArrayList<>(); // idem pour les souscarrés
				 
				 
				 GrillFillLigneUtils.analyseEmptySpots(grilleObj1, emptySpots, missing.get(j), suiviElements);
				 falseCompteurCol = GrillFillLigneUtils.calculateFalseEls(suiviElements);
				 
				 System.out.println(suiviElements);
				 System.out.println("nb of false: " + falseCompteurCol);
				 
				 if(missing.size() == 1 && falseCompteurCol == 0) throw new Error("Cannot update element");
				 
				 if(falseCompteurCol == 1) {
					 int index = suiviElements.indexOf(false);
					 System.out.println("index " + index);
					 int column = emptySpots.get(index).colonne;
					 int sousCarre = emptySpots.get(index).sousCarre;
					 System.out.println("Putting the element " + missing.get(j) + " at row " + (indMin+1) + ", col " + column + ", sousCarré " + sousCarre);
					 
					 ligneObjets[indMin].updateLigne(column, missing.get(j));
					 colonneObjets[column-1].updateCol(indMin+1, missing.get(j));
					 sousCarreObjets[sousCarre - 1].updateSousCarre(indMin+1, column,  missing.get(j));
					 
					 k = 0;
					 grillUpd = true;
					 continue outerLoop;
					 
				 } /*else if(falseCompteurCol == 2) {
					 
					 for(int i = 0; i < suiviElements.size(); i++) {
						 if(suiviElements.get(i) == false) {
							 emptySpots.get(i).updateValeursPossibles(missing.get(j));
							 System.out.println("missingEl: " + missing.get(j));
							 System.out.println("emptySpot: " + emptySpots.get(i));
							 System.out.println("valeur possible: " + missing.get(j));
						 }
							 
						 //if(!grilleObj1.casesDeuxEl.contains(emptySpots.get(i))) grilleObj1.updateCasesDeuxEl(emptySpots.get(i));
					 }
					 
					 
					 
				 }*/
			 }
			 
			 /*if(emptySpots.size() > 0) {
				 for(int i = 0; i < emptySpots.size(); i++) {
					 System.out.println("Empty sp. poss val.: " + emptySpots.get(i).valeursPossibles);
				 }
				 
			 
			 }*/
		 
		}
		
		return new FillResult(indMin, grillUpd);
		
	}
	
	public static FillResult fillElTry(Grille grilleObj1) {
		return new FillResult(0, true);
	}
	
	
}
