package nfp136.sudoku;

import java.util.ArrayList;
import java.util.Arrays;

public class GrilleFillColonne {
		
	public static FillResult fillMinEl(Grille grilleObj1, ArrayList<Integer> indMinList) {
		boolean grillUpd = false;
		
		int indMin = grilleObj1.trouverColonneAvecMinEl(indMinList).index;
		if(indMin == -1) return new FillResult(-1, grillUpd);
		
		
		System.out.println("colonneObjets 0 : " + grilleObj1.colonneObjets[indMin]);
		
		Ligne[] ligneObjets = grilleObj1.ligneObjets;
		Colonne[] colonneObjets = grilleObj1.colonneObjets;
		SousCarre[] sousCarreObjets = grilleObj1.sousCarreObjets;
		
		System.out.println("ligneObjets" + Arrays.toString(ligneObjets));
		
		int k = 0;
		
		outerLoop:
		while(k==0) {
			k = 1;
			ArrayList<Integer> missing = colonneObjets[indMin].getElementsManquants();
			System.out.println("missing: " + missing);
			
			 ArrayList<Case> emptySpots = colonneObjets[indMin].getCasesVides();
			 
			 for(int j = 0; j < missing.size(); j++) {
				 System.out.println("El. " + missing.get(j));
				 
				 ArrayList<Boolean> suiviElements = new ArrayList<>(); // liste de variables booléennes pour suivre les colonnes/lignes qui contiennent l'élément 
				 int falseCompteurCol = 0; // compteur de variable "false" (en colonne)
				 //ArrayList<Boolean> suiviElementsSousCarres = new ArrayList<>(); // idem pour les souscarrés
				 
				 
				 GrillFillColonneUtils.analyseEmptySpots(grilleObj1, emptySpots, missing.get(j), suiviElements);
				 falseCompteurCol = GrillFillColonneUtils.calculateFalseEls(suiviElements);
				 
				 System.out.println("suivi" + suiviElements);
				 System.out.println("nb of false: " + falseCompteurCol);
				 
				 if(missing.size() == 1 && falseCompteurCol == 0) throw new Error("Cannot update element");
				 
				 if(falseCompteurCol == 1) {
					 int index = suiviElements.indexOf(false);
					 System.out.println("index " + index);
					 int ligne = emptySpots.get(index).ligne;
					 int sousCarre = emptySpots.get(index).sousCarre;
					 System.out.println("Putting the element " + missing.get(j) + " at row " + ligne + ", col " + (indMin+1) + ", sousCarré " + sousCarre);
					 
					 colonneObjets[indMin].updateCol(ligne, missing.get(j));
					 ligneObjets[ligne-1].updateLigne((indMin+1), missing.get(j));
					 sousCarreObjets[sousCarre - 1].updateSousCarre(ligne, (indMin+1),  missing.get(j));
					 System.out.println(ligneObjets[ligne-1]);
					 System.out.println(colonneObjets[indMin]);
					 System.out.println(sousCarreObjets[sousCarre-1]);
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
			 
			 if(emptySpots.size() > 0) System.out.println("Empty sp. 0, poss val.: " + emptySpots.get(0).valeursPossibles);
			 if(emptySpots.size() > 0) System.out.println("Empty sp. 1, poss val.: " + emptySpots.get(1).valeursPossibles);
		 
		}
		
		return new FillResult(indMin, grillUpd);
		
	}
}
