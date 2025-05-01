package nfp136.sudoku;

import java.util.ArrayList;

public class GrilleFillColonne {
		
	public static int fillMinEl(Grille grilleObj1, ArrayList<Integer> indMinList) {
		
		
		int indMin = grilleObj1.trouverColonneAvecMinEl(indMinList);
		if(indMin == -1) return -1;
		/*grilleObj1.trouverColonneAvecMinEl();
		grilleObj1.trouverSousCarreAvecMinEl();*/
		
		System.out.println("ligneObj 0 : " + grilleObj1.ligneObjets[indMin]);
		
		Ligne[] ligneObjets = grilleObj1.ligneObjets;
		Colonne[] colonneObjets = grilleObj1.colonneObjets;
		SousCarre[] sousCarreObjets = grilleObj1.sousCarreObjets;
		
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
				 
				 
				 GrillFillColonneUtils.analyseEmptySpots(ligneObjets, colonneObjets, emptySpots, missing.get(j), suiviElements);
				 falseCompteurCol = GrillFillLigneUtils.calculateFalseEls(suiviElements);
				 
				 System.out.println(suiviElements);
				 System.out.println("nb of false: " + falseCompteurCol);
				 
				 if(falseCompteurCol == 1) {
					 int index = suiviElements.indexOf(false);
					 System.out.println("index " + index);
					 int ligne = emptySpots.get(index).ligne;
					 int sousCarre = emptySpots.get(index).sousCarre;
					 System.out.println("Putting the element " + missing.get(j) + " at row " + ligne + ", col " + indMin+1 + ", sousCarré " + sousCarre);
					 
					 colonneObjets[indMin].updateCol(ligne, missing.get(j));
					 ligneObjets[ligne-1].updateLigne(indMin+1, missing.get(j));
					 sousCarreObjets[sousCarre - 1].updateSousCarre(ligne, indMin+1,  missing.get(j));
					 System.out.println(ligneObjets[ligne-1]);
					 System.out.println(colonneObjets[indMin]);
					 System.out.println(sousCarreObjets[sousCarre-1]);
					 k = 0;
					 
					 continue outerLoop;
					 
				 } else if(falseCompteurCol == 2) {
					 
					 boolean updated = GrillFillLigneUtils.update2El(suiviElements, emptySpots, grilleObj1, missing.get(j), indMin);
						if(updated) {
							k = 0;
							continue outerLoop;
						}
						 
					 
				 }
			 }
		 
		}
		
		return indMin;
		
	}
}
