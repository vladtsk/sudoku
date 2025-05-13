package nfp136.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class GrilleFillColonne {
		
	public static FillResult fillMinEl(Grille grilleObj1, ArrayList<Integer> indMinList) {
		//Grille grilleObj1 = stack.peek();
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
					 
					 /*colonneObjets[indMin].updateCol(ligne, missing.get(j));
					 ligneObjets[ligne-1].updateLigne((indMin+1), missing.get(j));
					 sousCarreObjets[sousCarre - 1].updateSousCarre(ligne, (indMin+1),  missing.get(j));
					 */
					 
					 grilleObj1.updateGrille(ligne, (indMin+1), sousCarre, missing.get(j));
					 
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
			 
			 //if(missing.size() == 1 && falseCompteurCol == 0) throw new UnsolvablePuzzleException("Cannot update element  in fillElTry of GrillFillColonne");
			 if(missing.size() == 1 && falseCompteurCol == 0) {
				 if(!Solver.tempEls.isEmpty()) throw new UnsolvablePathException("Cannot update element. Try another path");
				 else throw new UnsolvablePuzzleException("Cannot update element. No more paths available.");
			 }
			 
			 
			 if(falseCompteurCol == 2) { // avant: !=0
				 //Grille newGrid = new Grille(grilleObj1.gr);
				 
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
				 
				 
				//try the first spot
				 int ligne1 = emptySpots.get(falseIndexes.get(0)).ligne;
				 int sousCarre1 = emptySpots.get(falseIndexes.get(0)).sousCarre;
				 
				 int ligne2 = emptySpots.get(falseIndexes.get(1)).ligne;
				 int sousCarre2 = emptySpots.get(falseIndexes.get(1)).sousCarre;
				 
				 //second option
				 /*Case newTempCase2 = new Case(ligne2, index+1);
				 newTempCase2.tempValue = missing.get(j);
				 tempEls.push(newTempCase2);
				 System.out.println("temp el last: " + Solver.tempEls.peek());*/
				 
				 
				 Case newTempCase1 = new Case(ligne1, index+1);
				 newTempCase1.tempValue = missing.get(j);
				 
				 newTempCase1.ligneNextUpd = ligne2;
				 newTempCase1.colNextUpd = index+1;
				 newTempCase1.sousCarreNextUpd = sousCarre2;
				 
				 Solver.tempEls.push(newTempCase1);
				 System.out.println("temp el last: " + Solver.tempEls.peek());
				 
				 System.out.println("Putting the element " + missing.get(j) + " at row " + ligne1 + ", col " + (index+1) + ", sousCarré " + sousCarre1);
				 
				 /*newGrid.colonneObjets[index].updateCol(ligne, missing.get(j));
				 newGrid.ligneObjets[ligne-1].updateLigne((index+1), missing.get(j));
				 newGrid.sousCarreObjets[sousCarre - 1].updateSousCarre(ligne, (index+1),  missing.get(j));
				 */
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
