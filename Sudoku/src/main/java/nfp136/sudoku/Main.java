package nfp136.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		
		
		
		// cannot solve 
		/*
		int[] row1 = {0, 0, 0, 0, 0, 0, 0, 1, 2};
		int[] row2 = {0, 0, 0, 0, 0, 0, 0, 0, 3};
		int[] row3 = {0, 0, 2, 3, 0, 0, 4, 0, 0};
		int[] row4 = {0, 0, 1, 8, 0, 0, 0, 0, 5};
		int[] row5 = {0, 6, 0, 0, 7, 0, 8, 0, 0};
		int[] row6 = {0, 0, 0, 0, 0, 9, 0, 0, 0};
		int[] row7 = {0, 0, 8, 5, 0, 0, 0, 0, 0};
		int[] row8 = {9, 0, 0, 0, 4, 0, 5, 0, 0};
		int[] row9 = {4, 7, 0, 0, 0, 6, 0, 0, 0};
		*/
		//
		/*
		int[] row1 = {5, 0, 0, 0, 7, 0, 0, 0, 0};//53
		int[] row2 = {6, 0, 0, 1, 0, 0, 0, 0, 0};//195
		int[] row3 = {0, 9, 0, 0, 0, 0, 0, 6, 0};//98
		int[] row4 = {0, 0, 0, 0, 6, 0, 0, 0, 3};
		int[] row5 = {0, 0, 0, 8, 0, 3, 0, 0, 1}; 
		int[] row6 = {0, 0, 0, 0, 2, 0, 0, 0, 6}; 
		int[] row7 = {0, 6, 0, 0, 0, 0, 2, 8, 0}; 
		int[] row8 = {0, 0, 0, 4, 0, 9, 0, 0, 5};//419
		int[] row9 = {0, 0, 0, 0, 8, 0, 0, 7, 0}; //79
		*/
		//
		
		/*
		int[] row1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] row2 = {0, 6, 0, 0, 0, 0, 0, 3, 0};
		int[] row3 = {0, 7, 0, 0, 0, 0, 0, 4, 0};
		int[] row4 = {7, 8, 9, 1, 2, 3, 4, 5, 6};
		int[] row5 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] row6 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] row7 = {4, 5, 6, 7, 8, 9, 1, 2, 3};
		int[] row8 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] row9 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		*/

		
		//ok
		/*
		int[] row1 = {2, 0, 0, 3, 0, 0, 0, 0, 0};
		int[] row2 = {8, 0, 4, 0, 6, 2, 0, 0, 3};
		int[] row3 = {0, 1, 3, 8, 0, 0, 2, 0, 0};
		int[] row4 = {0, 0, 0, 0, 2, 0, 3, 9, 0};
		int[] row5 = {5, 0, 7, 0, 0, 0, 6, 2, 1};
		int[] row6 = {0, 3, 2, 0, 0, 6, 0, 0, 0};
		int[] row7 = {0, 2, 0, 0, 0, 9, 1, 4, 0};
		int[] row8 = {6, 0, 1, 2, 5, 0, 8, 0, 9};
		int[] row9 = {0, 0, 0, 0, 0, 1, 0, 0, 2};
		
//		*/
		
		
		// ok
		
		int[] row1 = {0,0,3,0,2,0,6,0,0};
		int[] row2 = {9,0,0,3,0,5,0,0,1};
		int[] row3 = {0,0,1,8,0,6,4,0,0};
		int[] row4 = {0,0,8,1,0,2,9,0,0};
		int[] row5 = {7,0,0,0,0,0,0,0,8};
		int[] row6 = {0,0,6,7,0,8,2,0,0};
		int[] row7 = {0,0,2,6,0,9,5,0,0};
		int[] row8 = {8,0,0,2,0,3,0,0,9};
		int[] row9 = {0,0,5,0,1,0,3,0,0};


		
//


		
		
		int[][] grille1 = {row1, row2, row3, row4, row5, row6, row7, row8, row9};
		
		System.out.println(Arrays.deepToString(grille1));
		Grille grilleObj1 = new Grille(grille1);
		
		ArrayList<Grille> solutions = new ArrayList<>(); 
		
		Stack<Grille> grillStack = new Stack<>();
		grillStack.push(grilleObj1);

		
		
		
	
	
		Solver.solveOrganically(grillStack.peek());
		
		int itr = 0;
		/*while(!grillStack.peek().estComplet() && itr<100) {
			itr++;
		*/
		
		
		
		
			
			try {
				
				
				
				if(!Solver.tempEls.isEmpty() && Solver.tempEls.peek().ligneNextUpd != 0) {
					Case failedCase  = Solver.tempEls.pop();
					
					grillStack.peek().updateGrille(failedCase.ligneNextUpd, failedCase.colNextUpd, failedCase.sousCarreNextUpd, failedCase.tempValue);
					System.err.println("Upd grid, ligne: " + failedCase.ligneNextUpd + "col: " + failedCase.colNextUpd + "value: " + failedCase.tempValue);
					
					
					//Solver.tempEls.pop();
					//Solver.tempEls.peek().ligneNextUpd = 0;
					
				} else if(!Solver.tempEls.isEmpty()) {
					Solver.tempEls.pop();
				}
				
				
				//
				 
				
				/*if(!grillStack.peek().valDeuxCasesPossible.isEmpty() && !grillStack.peek().valDeuxCasesPossible.peek().updatedFirst) {
					
					
					 
					Grille newGrid = GrillUtils.createArrayCopy(grillStack.peek());
					 
					ValPossible val = grillStack.peek().valDeuxCasesPossible.peek();
					val.updatedFirst = true;
					
					newGrid.updateGrille(val.ligne1, val.col1, val.sousCarre1, val.value);
					grillStack.add(newGrid);
					Solver.solveOrganically(grillStack);
					
				} else if(!grillStack.peek().valDeuxCasesPossible.isEmpty()) {
					
					grillStack.pop();
					ValPossible val = grillStack.peek().valDeuxCasesPossible.peek();
					
					if(grillStack.peek().valDeuxCasesPossible.peek().updatedFirst) {
						
						grillStack.peek().updateGrille(val.ligne1, val.col1, val.sousCarre1, val.value);
						
						grillStack.peek().valDeuxCasesPossible.pop();
					}
					
					
					
				}*/
				
				int iterations=0;
				while(!grillStack.peek().estComplet()) {
					
					
					Solver.insertCandidateValue(grillStack);
					Solver.solveOrganically(grillStack.peek());
					
					System.err.println(grillStack.peek());
					
					iterations++;
					if(iterations > 100) throw new UnsolvablePuzzleException("Too many iterations.");
					
				}
				
				
			
			} catch(IndexNotFoundException e){
				System.out.println(e.getMessage());
				e.printStackTrace();
				//break;
			}  	
			catch(UnsolvablePathException e) {
				
				e.printStackTrace();
				
				System.out.println(e.getMessage());
				System.out.println("tempEls: " + Solver.tempEls);
				System.out.println("grid after error: " + grillStack.peek()); 
				
				if(!Solver.tempEls.isEmpty()) {
				
					Case failedCase = Solver.tempEls.peek();
					System.out.println(failedCase.ligne + " " + failedCase.colonne + " tempValue: " + failedCase.tempValue + " valeursInterdites: "+ failedCase.valeursInterdites);
					
					failedCase.updateValeursInterdites(failedCase.tempValue);
					
					System.out.println(failedCase.ligne + " " + failedCase.colonne + " " + failedCase.tempValue);
					
					
					grillStack.pop();
					
		
					
					grillStack.peek().updateForbiddenElCases(failedCase, grillStack);
					
					/*int indexCase = grillStack.peek().forbiddenElCases.indexOf(failedCase);
					
					
					
					if(indexCase == -1) {
						failedCase.tempValue = 0;
						grillStack.peek().forbiddenElCases.add(failedCase);
					} else {
						System.out.println(failedCase + " already exists in forbiddenElCases");
						grillStack.peek().forbiddenElCases.get(indexCase).updateValeursInterdites(failedCase.tempValue);
						grillStack.peek().forbiddenElCases.get(indexCase).tempValue = 0;
					}*/
					
						
					
					System.out.println("forbiddenElCases: " + grillStack.peek().forbiddenElCases);
					
					
					//Solver.tempEls.pop();
					
					System.out.println("temp val: " + Solver.tempEls);
					//continue;
					
					
				
					
				} 
				
				
				
			
				
			} catch(UnsolvablePuzzleException e){
				System.out.println(e.getMessage());
				//break;
				//continue;
			}
			
		//}
		
		//System.out.println("grid stack: " + grillStack);
		
		if(grillStack.peek().estComplet()) {
			solutions.add(grillStack.peek()); 
			System.out.println("Solution 1: " + solutions.get(0));
			System.out.println(Solver.tempEls);
		}
	
		
		
		// recherche d'une solution alternative
		
				
		if(!grillStack.isEmpty()) grillStack.pop();
		int counter = 0;
		
		if(grillStack.isEmpty()) return;
		
		System.err.println("grillStack.peek().estComplet() " + grillStack.peek().estComplet());
		while(!grillStack.peek().estComplet() && !Solver.tempEls.isEmpty()) {
			
			counter++;
			if(counter > 100) break;
			
			System.out.println(grillStack);
			System.out.println(Solver.tempEls);
			System.out.println(Solver.tempEls.peek().tempValue);
			System.out.println(grillStack.peek().forbiddenElCases);
			
			
			try {
				Case caseInterdite = Solver.tempEls.pop();
				//grillStack.pop();
				
				System.err.println("caseInterdite" + caseInterdite);
				
				grillStack.peek().forbiddenElCases.add(caseInterdite);
				Solver.solveOrganically(grillStack.peek());
				if(grillStack.peek().estComplet()) solutions.add(grillStack.peek());
				
				//Solver.insertCandidateValue(grillStack);
				//Solver.solveOrganically(grillStack);
				
				
				int iterations=0;
				while(!grillStack.peek().estComplet()) {
					Solver.insertCandidateValue(grillStack);
					Solver.solveOrganically(grillStack.peek());
					
					iterations++;
					if(iterations > 100) throw new UnsolvablePuzzleException("Too many iterations.");
				}
				
			} catch(UnsolvablePathException e) { 
				
				grillStack.pop();
				if (grillStack.isEmpty()) return;
				continue;
				
				
			}
			
		}
		
			
			if(solutions.isEmpty()) {
				System.err.println("No solution found.");
				} else {
				for (int i = 0; i < solutions.size(); i++) {
				    System.out.println("Solution " + (i + 1) + ": " + solutions.get(i));
				}
			}
			

		
		 
	} 
}


