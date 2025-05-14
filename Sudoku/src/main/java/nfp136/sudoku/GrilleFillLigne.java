package nfp136.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class GrilleFillLigne {
		
	public static FillResult fillMinEl(Grille grilleObj1, ArrayList<Integer> indMinList) {
		//Grille grilleObj1 = grilleStack.peek();
		boolean grillUpd = false;
		int indMin = grilleObj1.trouverLigneAvecMinEl(indMinList).index;
		if(indMin == -1) return new FillResult(-1, grillUpd);
		
		
		System.out.println("ligneObj 0 : " + grilleObj1.ligneObjets[indMin]);
		
		Ligne[] ligneObjets = grilleObj1.ligneObjets;
		//Colonne[] colonneObjets = grilleObj1.colonneObjets;
		//SousCarre[] sousCarreObjets = grilleObj1.sousCarreObjets;
		
		int k = 0;
		
		outerLoop:
		while(k==0) {
			k = 1;
			ArrayList<Integer> missing = ligneObjets[indMin].getElementsManquants();
			System.err.println("missing: " + missing);
			
			 ArrayList<Case> emptySpots = ligneObjets[indMin].getCasesVides();
			 
			 
			 // Technique 1
			 
			 GrillFillUtils.applyEliminationTechnique(grilleObj1, missing, emptySpots);
			 
			 
			 //Technique 2
			 
			 missing = ligneObjets[indMin].getElementsManquants();
			 emptySpots = ligneObjets[indMin].getCasesVides();
			 System.err.println("missing: " + missing);
			 
			 for(int j = 0; j < missing.size(); j++) {
				 System.out.println("El. " + missing.get(j));
				 
				 ArrayList<Boolean> suiviElements = new ArrayList<>(); // liste de variables booléennes pour suivre les colonnes/lignes qui contiennent l'élément 
				 int falseCompteurCol = 0; // compteur de variable "false" 
				 //ArrayList<Boolean> suiviElementsSousCarres = new ArrayList<>(); // idem pour les souscarrés
				 
				 
				 GrillFillLigneUtils.analyseEmptySpots(grilleObj1, emptySpots, missing.get(j), suiviElements);
				 falseCompteurCol = GrillFillLigneUtils.calculateFalseEls(suiviElements);
				 
				 System.out.println(suiviElements);
				 System.out.println("nb of false: " + falseCompteurCol);
				 
				 if(missing.size() == 1 && falseCompteurCol == 0) {
					 if(!Solver.tempEls.isEmpty()) throw new UnsolvablePathException("Cannot update element. Try another path");
					 else throw new UnsolvablePuzzleException("Cannot update element. No more paths available.");
				 }
					 
				 
				 if(falseCompteurCol == 1) {
					 int index = suiviElements.indexOf(false);
					 System.out.println("index " + index);
					 int column = emptySpots.get(index).colonne;
					 int sousCarre = emptySpots.get(index).sousCarre;
					 System.out.println("Putting the element " + missing.get(j) + " at row " + (indMin+1) + ", col " + column + ", sousCarré " + sousCarre);
					 
					 /*ligneObjets[indMin].updateLigne(column, missing.get(j));
					 colonneObjets[column-1].updateCol(indMin+1, missing.get(j));
					 sousCarreObjets[sousCarre - 1].updateSousCarre(indMin+1, column,  missing.get(j));
					 */
					 grilleObj1.updateGrille((indMin+1), column, sousCarre, missing.get(j));
					 
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
						 int column1 = emptySpots.get(index1).colonne;
						 int column2 = emptySpots.get(index2).colonne;
						 
						 int sousCarre1 = emptySpots.get(index1).sousCarre;
						 int sousCarre2 = emptySpots.get(index2).sousCarre;
						 
						 
						 ValPossible val = new ValPossible(missing.get(j), (indMin+1), column1, sousCarre1, (indMin+1), column2, sousCarre2);
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
		Ligne[] ligneObjets = grilleObj1.ligneObjets;
		
		ArrayList<Integer> missing = ligneObjets[index].getElementsManquants();
		
		System.out.println("missing: " + missing);
		
		 ArrayList<Case> emptySpots = ligneObjets[index].getCasesVides();
		 
		 for(int j = 0; j < missing.size(); j++) {
		//int j =0;	 
		 System.out.println("El. " + missing.get(j));
			 
			 ArrayList<Boolean> suiviElements = new ArrayList<>(); 
			 int falseCompteurCol = 0; 
			 
			 			 
			 GrillFillLigneUtils.analyseEmptySpots(grilleObj1, emptySpots, missing.get(j), suiviElements);
			 
			 // for each empty spot we add true/ false
			 
			 falseCompteurCol = GrillFillLigneUtils.calculateFalseEls(suiviElements);
			 
			 System.out.println("nb of false: " + falseCompteurCol);
			 
			 //if(missing.size() == 1 && falseCompteurCol == 0) throw new UnsolvablePuzzleException("Cannot update element in fillElTry of GrillFillLigne");
			 if(missing.size() == 1 && falseCompteurCol == 0) {
				 if(!Solver.tempEls.isEmpty()) throw new UnsolvablePathException("Cannot update element. Try another path");
				 else throw new UnsolvablePuzzleException("Cannot update element. No more paths available.");
			 }
			 
			 // if(falseCompteurCol != 0) {
			 if(falseCompteurCol == 2) {
				 System.out.println("2 faux els");
				 
				 int[][] gridCopy = new int[9][9]; 
				 
				 for (int i = 0; i < 9; i++) {
					 gridCopy[i] = Arrays.copyOf(grilleObj1.gr[i], 9);
				 }
				 
				 
				 Grille newGrid = new Grille(gridCopy);
				 
				 //int ind = suiviElements.indexOf(false);
				 ArrayList<Integer> falseIndexes = new ArrayList<>();
				 for(int i = 0; i < suiviElements.size(); i++) {
					 if(suiviElements.get(i) == false) falseIndexes.add(i);
				 }
				 
				
				 int column1 = emptySpots.get(falseIndexes.get(0)).colonne;
				 int sousCarre1 = emptySpots.get(falseIndexes.get(0)).sousCarre;
				 
				 int column2 = emptySpots.get(falseIndexes.get(1)).colonne; // second false spot column
				 int sousCarre2 = emptySpots.get(falseIndexes.get(1)).sousCarre;
				 
				
				 
				
				 Case newTempCase1 = new Case(index+1, column1);
				 newTempCase1.tempValue = missing.get(j);
				 
				 newTempCase1.ligneNextUpd = index+1;
				 newTempCase1.colNextUpd = column2;
				 newTempCase1.sousCarreNextUpd = sousCarre2;
				 
				 Solver.tempEls.push(newTempCase1);
				 System.out.println("temp el last: " + Solver.tempEls.peek());

				 
				 System.out.println("Putting the element " + missing.get(j) + " at row " + (index+1) + ", col " + column1 + ", sousCarré " + sousCarre1);
				 
				 
				 newGrid.updateGrille((index+1), column1, sousCarre1, missing.get(j));
				 
				 
				 System.out.println("pushing newGrid: " + newGrid);
				 System.out.println("old: " + grilleObj1);
				 grilleStack.push(newGrid);
				 grillUpd = true;
				 break;
				 
			 }
			 
			 
			 
		 
		 }
		
		 return grillUpd;
	}
	
	//public static boolean fillElTry(Stack<Grille> grilleStack, int index) {
		
	//}
	
		
}
