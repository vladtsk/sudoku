package nfp136.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class GrilleFillColonne {
		
	public static FillResult fillMinEl(Grille grilleObj1, ArrayList<Integer> indMinList) {
		
		boolean grillUpd = false;
		
		int indMin = grilleObj1.trouverColonneAvecMinEl(indMinList).index;
		if(indMin == -1) return new FillResult(-1, grillUpd);
		
		
		System.out.println("colonneObjets 0 : " + grilleObj1.colonneObjets[indMin]);
		
		Ligne[] ligneObjets = grilleObj1.ligneObjets;
		Colonne[] colonneObjets = grilleObj1.colonneObjets;
		//SousCarre[] sousCarreObjets = grilleObj1.sousCarreObjets;
		
		System.out.println("ligneObjets" + Arrays.toString(ligneObjets));
		
		int k = 0;
		
		outerLoop:
		while(k==0) {
			k = 1;
			ArrayList<Integer> missing = colonneObjets[indMin].getElementsManquants();
			System.out.println("missing: " + missing);
			
			 ArrayList<Case> emptySpots = colonneObjets[indMin].getCasesVides();
			 
			 // Technique 1
			 
			 GrillFillUtils.applyEliminationTechnique(grilleObj1, missing, emptySpots);
			 emptySpots = colonneObjets[indMin].getCasesVides();
			 
			 //Technique 2
			 missing = colonneObjets[indMin].getElementsManquants();
			 
			 for(int j = 0; j < missing.size(); j++) {
				 System.out.println("El. " + missing.get(j));
				 
				 ArrayList<Boolean> suiviElements = new ArrayList<>(); // liste de variables booléennes pour suivre les colonnes/lignes qui contiennent l'élément 
				 int falseCompteurCol = 0; // compteur de variable "false" (en colonne)
						 
				 
				 GrillFillColonneUtils.analyseEmptySpots(grilleObj1, emptySpots, missing.get(j), suiviElements);
				 falseCompteurCol = GrillFillColonneUtils.calculateFalseEls(suiviElements);
				 
				 
				 if(missing.size() == 1 && falseCompteurCol == 0) {
					 if(!Solver.tempEls.isEmpty()) throw new UnsolvablePathException("Cannot update element. Try another path");
					 else throw new UnsolvablePuzzleException("Cannot update element. No more paths available.");
				 }
				 
				 if(falseCompteurCol == 1) {
					 int index = suiviElements.indexOf(false);
					 System.out.println("index " + index);
					 int ligne = emptySpots.get(index).ligne;
					 int sousCarre = emptySpots.get(index).sousCarre;
					 System.out.println("Putting the element " + missing.get(j) + " at row " + ligne + ", col " + (indMin+1) + ", sousCarré " + sousCarre);
					 			 
					 grilleObj1.updateGrille(ligne, (indMin+1), sousCarre, missing.get(j));
					 
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
						 
						 int sousCarre1 = emptySpots.get(index1).sousCarre;
						 int sousCarre2 = emptySpots.get(index2).sousCarre;
						 
						 
						 ValPossible val = new ValPossible(missing.get(j), ligne1, (indMin+1), sousCarre1, ligne2, (indMin+1), sousCarre2);
						 grilleObj1.valDeuxCasesPossible.add(val);
					 }
					 
				 }
			 }
		}
		
		return new FillResult(indMin, grillUpd);
		
	}
	
	public static boolean fillElTry(Stack<Grille> grilleStack, int index) {
		Grille grilleObj1 = grilleStack.peek(); 
				
		boolean grillUpd = false;

		Colonne[] colonneObjets = grilleObj1.colonneObjets;
		
		ArrayList<Integer> missing = colonneObjets[index].getElementsManquants();
		
		System.out.println("missing: " + missing);
		
		 ArrayList<Case> emptySpots = colonneObjets[index].getCasesVides();
		 
	
		for(int j = 0; j < missing.size(); j++) {
			 
		 System.out.println("El. " + missing.get(j));
			 
			 ArrayList<Boolean> suiviElements = new ArrayList<>(); 
			 int falseCompteurCol = 0; 
			
			 GrillFillColonneUtils.analyseEmptySpots(grilleObj1, emptySpots, missing.get(j), suiviElements);
			 falseCompteurCol = GrillFillColonneUtils.calculateFalseEls(suiviElements);
			 
			 System.out.println("suivi" + suiviElements);
			 System.out.println("nb of false: " + falseCompteurCol);
			 
			 
			 if(missing.size() == 1 && falseCompteurCol == 0) {
				 if(!Solver.tempEls.isEmpty()) throw new UnsolvablePathException("Cannot update element. Try another path");
				 else throw new UnsolvablePuzzleException("Cannot update element. No more paths available.");
			 }
			 
			 
			 if(falseCompteurCol == 2) { 
				 
				 int[][] gridCopy = new int[9][9]; 
				 
				 for (int i = 0; i < 9; i++) {
					 gridCopy[i] = Arrays.copyOf(grilleObj1.gr[i], 9);
				 }
				 
				 
				 Grille newGrid = new Grille(gridCopy);
				 				 
				 ArrayList<Integer> falseIndexes = new ArrayList<>();
				 for(int i = 0; i < suiviElements.size(); i++) {
					 if(suiviElements.get(i) == false) falseIndexes.add(i);
				 }
				 				 
				//try the first spot
				 int ligne1 = emptySpots.get(falseIndexes.get(0)).ligne;
				 int sousCarre1 = emptySpots.get(falseIndexes.get(0)).sousCarre;
				 
				 int ligne2 = emptySpots.get(falseIndexes.get(1)).ligne;
				 int sousCarre2 = emptySpots.get(falseIndexes.get(1)).sousCarre;
				 			 
				 
				 Case newTempCase1 = new Case(ligne1, index+1);
				 newTempCase1.tempValue = missing.get(j);
				 
				 newTempCase1.ligneNextUpd = ligne2;
				 newTempCase1.colNextUpd = index+1;
				 newTempCase1.sousCarreNextUpd = sousCarre2;
				 
				 Solver.tempEls.push(newTempCase1);
				 System.out.println("temp el last: " + Solver.tempEls.peek());
				 
				 System.out.println("Putting the element " + missing.get(j) + " at row " + ligne1 + ", col " + (index+1) + ", sousCarré " + sousCarre1);
				
				 newGrid.updateGrille(ligne1, (index+1), sousCarre1, missing.get(j));
				 
				 grilleStack.push(newGrid);
				 grillUpd = true;
				 break;
				 
				 
			 }
			 
		 }
				 
		 
		 //return new FillResult(index, grillUpd);
		 return grillUpd;
		 
	}
}
