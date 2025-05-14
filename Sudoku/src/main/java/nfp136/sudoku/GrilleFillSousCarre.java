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
		
		//Ligne[] ligneObjets = grilleObj1.ligneObjets;
		//Colonne[] colonneObjets = grilleObj1.colonneObjets;
		SousCarre[] sousCarreObjets = grilleObj1.sousCarreObjets;
		
		int k = 0;
		
		outerLoop:
		while(k==0) {
			k = 1;
			ArrayList<Integer> missing = sousCarreObjets[indMin].getElementsManquants();
			System.out.println("missing: " + missing);
			
			 ArrayList<Case> emptySpots = sousCarreObjets[indMin].getCasesVides();
			 System.out.println("emptySpots * " + emptySpots);
			 
			 // Technique 1
			 
			 GrillFillUtils.applyEliminationTechnique(grilleObj1, missing, emptySpots);
			 
			 
			 //Technique 2
			 
			 missing = sousCarreObjets[indMin].getElementsManquants();
				System.out.println("missing: " + missing);
				
			emptySpots = sousCarreObjets[indMin].getCasesVides();
				 System.out.println("emptySpots * " + emptySpots);
			 
			 //
			 
			 for(int j = 0; j < missing.size(); j++) {
				 System.out.println("El. " + missing.get(j));
				 
				 ArrayList<Boolean> suiviElements = new ArrayList<>(); // liste de variables booléennes pour suivre les colonnes/lignes qui contiennent l'élément 
				 int falseCompteurCol = 0; // compteur de variable "false" (en colonne)
				 //ArrayList<Boolean> suiviElementsSousCarres = new ArrayList<>(); // idem pour les souscarrés
				 
				 
				 GrillFillSousCarreUtils.analyseEmptySpots(grilleObj1, emptySpots, missing.get(j), suiviElements);
				 falseCompteurCol = GrillFillSousCarreUtils.calculateFalseEls(suiviElements);
				 
				 System.out.println("suivi" + suiviElements);
				 System.out.println("nb of false: " + falseCompteurCol);
				 
				 if(missing.size() == 1 && falseCompteurCol == 0) {
					 if(!Solver.tempEls.isEmpty()) throw new UnsolvablePathException("Cannot update element. Try another path");
					 else throw new UnsolvablePuzzleException("Cannot update element. No more paths available.");
				 }
				 
				 if(falseCompteurCol == 1) {
					 int index = suiviElements.indexOf(false);
					 System.out.println("index " + index);
					 int ligne = emptySpots.get(index).ligne;
					 int colonne = emptySpots.get(index).colonne;
					 System.out.println("Putting the element " + missing.get(j) + " at row " + ligne + ", col " + colonne + ", sousCarré " + (indMin+1));
					 
					 
					 grilleObj1.updateGrille(ligne, colonne, (indMin+1), missing.get(j));
					 
					 
					 k = 0;
					 grillUpd = true;
					 continue outerLoop;
					 
				 } else if(falseCompteurCol == 2) {
					 
					 int index1 = suiviElements.indexOf(false);
					 int index2 = -1;
					 
					 					 
					 for(int i = 0; i < suiviElements.size(); i++) {
						 if(suiviElements.get(i) == false && i != index1) {
							 index2 = i;
							 break;
							 } 
						 
					 }
					 
					 if(index2 != -1) {
						 int ligne1 = emptySpots.get(index1).ligne;
						 int ligne2 = emptySpots.get(index2).ligne;
						 
						 int colonne1 = emptySpots.get(index1).colonne;
						 int colonne2 = emptySpots.get(index2).colonne;
						 
						 
						 ValPossible val = new ValPossible(missing.get(j), ligne1, colonne1, (indMin+1), ligne2, colonne2, (indMin+1));
						 grilleObj1.valDeuxCasesPossible.add(val);
					 }
					 
				 }
			 
			 }
			 
		
		 
		}
		
		return new FillResult(indMin, grillUpd);
		//return indMin;
		
	}
	
	
	public static boolean fillElTry(Stack<Grille> grilleStack, int index) {
		Grille grilleObj1 = grilleStack.peek(); 
				
		boolean grillUpd = false;
		//Ligne[] ligneObjets = grilleObj1.ligneObjets;
		//Colonne[] colonneObjets = grilleObj1.colonneObjets;
		SousCarre[] sousCarreObjets = grilleObj1.sousCarreObjets;
		
		ArrayList<Integer> missing = sousCarreObjets[index].getElementsManquants();
		
		System.out.println("missing: " + missing);
		
		ArrayList<Case> emptySpots = sousCarreObjets[index].getCasesVides();
		
		for(int j = 0; j < missing.size(); j++) {
		 
		System.out.println("El. " + missing.get(j));
			 
			 ArrayList<Boolean> suiviElements = new ArrayList<>(); 
			 int falseCompteurCol = 0; 
			 
			 GrillFillSousCarreUtils.analyseEmptySpots(grilleObj1, emptySpots, missing.get(j), suiviElements);
			 falseCompteurCol = GrillFillSousCarreUtils.calculateFalseEls(suiviElements);
			 
			 System.out.println("suivi" + suiviElements);
			 System.out.println("nb of false: " + falseCompteurCol);
			 
			 //if(missing.size() == 1 && falseCompteurCol == 0) throw new UnsolvablePuzzleException("Cannot update element  in fillElTry of GrillFillSousCarre"); 
			 if(missing.size() == 1 && falseCompteurCol == 0) {
				 if(!Solver.tempEls.isEmpty()) throw new UnsolvablePathException("Cannot update element. Try another path");
				 else throw new UnsolvablePuzzleException("Cannot update element. No more paths available.");
			 }
			 
			 if(falseCompteurCol == 2) {
				 
				 //Grille newGrid = new Grille(grilleObj1.gr);
				 
				 int[][] gridCopy = new int[9][9]; 
				 
				 for (int i = 0; i < 9; i++) {
					 gridCopy[i] = Arrays.copyOf(grilleObj1.gr[i], 9);
				 }
				 
				 
				 Grille newGrid = new Grille(gridCopy);
				 
				 ArrayList<Integer> falseIndexes = new ArrayList<>();
				 for(int i = 0; i < suiviElements.size(); i++) {
					 if(suiviElements.get(i) == false) falseIndexes.add(i);
				 }
				 
				 int ligne1 = emptySpots.get(falseIndexes.get(0)).ligne;
				 int colonne1 = emptySpots.get(falseIndexes.get(0)).colonne;
				 
				 int ligne2 = emptySpots.get(falseIndexes.get(1)).ligne;
				 int colonne2 = emptySpots.get(falseIndexes.get(1)).colonne;
				 int sousCarre2 = emptySpots.get(falseIndexes.get(1)).sousCarre;
				 
				 
				 /*Case newTempCase2 = new Case(ligne2, colonne2);
				 newTempCase2.tempValue = missing.get(j);
				 tempEls.push(newTempCase2);
				 System.out.println("tempEl last: " + Solver.tempEls.peek());*/
				 
				 Case newTempCase1 = new Case(ligne1, colonne1);
				 newTempCase1.tempValue = missing.get(j);
				 
				 newTempCase1.ligneNextUpd = ligne2;
				 newTempCase1.colNextUpd = colonne2;
				 newTempCase1.sousCarreNextUpd = sousCarre2;
				 
				 Solver.tempEls.push(newTempCase1);
				 System.out.println("tempEl last: " + Solver.tempEls.peek());
				 
				 System.out.println("Putting the element " + missing.get(j) + " at row " + ligne1 + ", col " + colonne1 + ", sousCarré " + (index+1));
				 
				 /*newGrid.colonneObjets[colonne - 1].updateCol(ligne, missing.get(j));
				 newGrid.ligneObjets[ligne-1].updateLigne(colonne, missing.get(j));
				 newGrid.sousCarreObjets[index].updateSousCarre(ligne, colonne,  missing.get(j));
				 */
				 
				 newGrid.updateGrille(ligne1, colonne1, (index+1), missing.get(j));
				 
				 System.out.println("pushing newGrid: " + newGrid);
				 grilleStack.push(newGrid);
				 grillUpd = true;
				 break;
				 
				 
			 }
			 
			 
		 }
		 
	
		 
		 
		 
		 return grillUpd;
		 
	}
}
