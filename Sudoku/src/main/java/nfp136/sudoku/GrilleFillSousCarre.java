package nfp136.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class GrilleFillSousCarre {
		
	public static FillResult fillMinEl(Grille grilleObj1, ArrayList<Integer> indMinList) {
		
		boolean grillUpd = false;
		int indMin = grilleObj1.trouverSousCarreAvecMinEl(indMinList).index;
		System.out.println("indMin " + indMin);
		if(indMin == -1) return new FillResult(-1, grillUpd);
		
		
		System.out.println("sousCarreObjets [min] : " + grilleObj1.sousCarreObjets[indMin]);
		
		Ligne[] ligneObjets = grilleObj1.ligneObjets;
		Colonne[] colonneObjets = grilleObj1.colonneObjets;
		SousCarre[] sousCarreObjets = grilleObj1.sousCarreObjets;
		
		int k = 0;
		
		outerLoop:
		while(k==0) {
			k = 1;
			ArrayList<Integer> missing = sousCarreObjets[indMin].getElementsManquants();
			System.out.println("missing: " + missing);
			
			 ArrayList<Case> emptySpots = sousCarreObjets[indMin].getCasesVides();
			 System.out.println("emptySpots * " + emptySpots);
			 
			 for(int j = 0; j < missing.size(); j++) {
				 System.out.println("El. " + missing.get(j));
				 
				 ArrayList<Boolean> suiviElements = new ArrayList<>(); // liste de variables booléennes pour suivre les colonnes/lignes qui contiennent l'élément 
				 int falseCompteurCol = 0; // compteur de variable "false" (en colonne)
				 //ArrayList<Boolean> suiviElementsSousCarres = new ArrayList<>(); // idem pour les souscarrés
				 
				 
				 GrillFillSousCarreUtils.analyseEmptySpots(ligneObjets, colonneObjets, emptySpots, missing.get(j), suiviElements);
				 falseCompteurCol = GrillFillSousCarreUtils.calculateFalseEls(suiviElements);
				 
				 System.out.println("suivi" + suiviElements);
				 System.out.println("nb of false: " + falseCompteurCol);
				 
				 if(missing.size() == 1 && falseCompteurCol == 0) throw new UnsolvablePuzzleException("Cannot update element"); 
				 
				 if(falseCompteurCol == 1) {
					 int index = suiviElements.indexOf(false);
					 System.out.println("index " + index);
					 int ligne = emptySpots.get(index).ligne;
					 int colonne = emptySpots.get(index).colonne;
					 System.out.println("Putting the element " + missing.get(j) + " at row " + ligne + ", col " + colonne + ", sousCarré " + (indMin+1));
					 
					 colonneObjets[colonne - 1].updateCol(ligne, missing.get(j));
					 ligneObjets[ligne-1].updateLigne(colonne, missing.get(j));
					 sousCarreObjets[indMin].updateSousCarre(ligne, colonne,  missing.get(j));
					 //System.out.println(ligneObjets[ligne-1]);
					 //System.out.println(colonneObjets[colonne-1]);
					 //System.out.println(sousCarreObjets[indMin]);
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
		//return indMin;
		
	}
	
	
	public static boolean fillElTry(Stack<Grille> grilleStack, int index, Stack<Case> tempEls) {
		Grille grilleObj1 = grilleStack.peek(); 
				
		boolean grillUpd = false;
		Ligne[] ligneObjets = grilleObj1.ligneObjets;
		Colonne[] colonneObjets = grilleObj1.colonneObjets;
		SousCarre[] sousCarreObjets = grilleObj1.sousCarreObjets;
		
		ArrayList<Integer> missing = sousCarreObjets[index].getElementsManquants();
		
		System.out.println("missing: " + missing);
		
		ArrayList<Case> emptySpots = sousCarreObjets[index].getCasesVides();
		
		 //for(int j = 0; j < missing.size(); j++) {
		int j = 0;	 
		System.out.println("El. " + missing.get(j));
			 
			 ArrayList<Boolean> suiviElements = new ArrayList<>(); 
			 int falseCompteurCol = 0; 
			 
			 GrillFillSousCarreUtils.analyseEmptySpots(ligneObjets, colonneObjets, emptySpots, missing.get(j), suiviElements);
			 falseCompteurCol = GrillFillSousCarreUtils.calculateFalseEls(suiviElements);
			 
			 System.out.println("suivi" + suiviElements);
			 System.out.println("nb of false: " + falseCompteurCol);
			 
			 if(missing.size() == 1 && falseCompteurCol == 0) throw new UnsolvablePuzzleException("Cannot update element  in fillElTry of GrillFillSousCarre"); 
			 
			 if(falseCompteurCol != 0) {
				 
				 Grille newGrid = new Grille(grilleObj1.gr);
				 
				 int ind = suiviElements.indexOf(false);
				 
				 int ligne = emptySpots.get(ind).ligne;
				 int colonne = emptySpots.get(ind).colonne;
				 
				 Case newTempCase = new Case(ligne, colonne);
				 newTempCase.tempValue = missing.get(j);
				 tempEls.push(newTempCase);
				 
				 System.out.println("tempEl last: " + tempEls.peek());
				 
				 System.out.println("Putting the element " + missing.get(j) + " at row " + ligne + ", col " + colonne + ", sousCarré " + (index+1));
				 
				 newGrid.colonneObjets[colonne - 1].updateCol(ligne, missing.get(j));
				 newGrid.ligneObjets[ligne-1].updateLigne(colonne, missing.get(j));
				 newGrid.sousCarreObjets[index].updateSousCarre(ligne, colonne,  missing.get(j));
				 
				 
				 System.out.println("pushing newGrid: " + newGrid);
				 grilleStack.push(newGrid);
				 grillUpd = true;
				 //break;
				 
				 
			 }
			 
			 
		 //}
		 
	
		 
		 
		 
		 return grillUpd;
		 
	}
}
