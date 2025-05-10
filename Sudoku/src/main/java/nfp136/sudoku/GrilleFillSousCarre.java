package nfp136.sudoku;

import java.util.ArrayList;
import java.util.Arrays;

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
				 
				 if(missing.size() == 1 && falseCompteurCol == 0) throw new Error("Cannot update element"); 
				 
				 if(falseCompteurCol == 1) {
					 int index = suiviElements.indexOf(false);
					 System.out.println("index " + index);
					 int ligne = emptySpots.get(index).ligne;
					 int colonne = emptySpots.get(index).colonne;
					 System.out.println("Putting the element " + missing.get(j) + " at row " + ligne + ", col " + colonne + ", sousCarré " + (indMin+1));
					 
					 colonneObjets[colonne - 1].updateCol(ligne, missing.get(j));
					 ligneObjets[ligne-1].updateLigne(colonne, missing.get(j));
					 sousCarreObjets[indMin].updateSousCarre(ligne, colonne,  missing.get(j));
					 System.out.println(ligneObjets[ligne-1]);
					 System.out.println(colonneObjets[colonne-1]);
					 System.out.println(sousCarreObjets[indMin]);
					 k = 0;
					 grillUpd = true;
					 continue outerLoop;
					 
				 } else if(falseCompteurCol == 2) {
					 boolean updated = GrillFillColonneUtils.update2El(suiviElements, emptySpots, grilleObj1, missing.get(j), indMin);
						if(updated) {
							k = 0;
							grillUpd = true;
							continue outerLoop;
						}
						 
					 
				 }
			 }
			 
			 if(emptySpots.size() > 0) System.out.println("Empty sp. poss val.: " + emptySpots.get(0).valeursPossibles);
		 
		}
		
		return new FillResult(indMin, grillUpd);
		//return indMin;
		
	}
}
