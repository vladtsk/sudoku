package nfp136.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
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
		
		int[] row1 = {5, 3, 0, 0, 7, 0, 0, 0, 0};
		int[] row2 = {6, 0, 0, 1, 0, 0, 0, 0, 0};//195
		int[] row3 = {0, 9, 8, 0, 0, 0, 0, 6, 0};
		int[] row4 = {0, 0, 0, 0, 6, 0, 0, 0, 3};
		int[] row5 = {0, 0, 0, 8, 0, 3, 0, 0, 1}; 
		int[] row6 = {0, 0, 0, 0, 2, 0, 0, 0, 6}; 
		int[] row7 = {0, 6, 0, 0, 0, 0, 2, 8, 0}; 
		int[] row8 = {0, 0, 0, 4, 0, 9, 0, 0, 5};//419
		int[] row9 = {0, 0, 0, 0, 8, 0, 0, 7, 9}; 
		
		
		
		
		/*int[] rowEx1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] rowEx2 = {0, 6, 0, 0, 0, 0, 0, 3, 0};
		int[] rowEx3 = {0, 7, 0, 0, 0, 0, 0, 4, 0};
		int[] rowEx4 = {7, 8, 9, 1, 2, 3, 4, 5, 6};
		int[] rowEx5 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] rowEx6 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] rowEx7 = {4, 5, 6, 7, 8, 9, 1, 2, 3};
		int[] rowEx8 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] rowEx9 = {0, 0, 0, 0, 0, 0, 0, 0, 0};

		*/



		
		//int[][] grille1 = {rowEx1, rowEx2, rowEx3, rowEx4, rowEx5, rowEx6, rowEx7, rowEx8, rowEx9};
		int[][] grille1 = {row1, row2, row3, row4, row5, row6, row7, row8, row9};
		
		System.out.println(Arrays.deepToString(grille1));
		Grille grilleObj1 = new Grille(grille1);
		
		Stack<Grille> grillStack = new Stack<>();
		grillStack.push(grilleObj1);

		
		
		
	
	
		Solver.solveOrganically(grillStack);
		
		int itr = 0;
		while(!grillStack.peek().estComplet() && itr<100) {
			itr++;
		
		//System.out.println("forb" + grillStack.peek().forbiddenElCases); 
		//System.out.println(grillStack.peek());
		
			
			try {
				
				
				
				if(!Solver.tempEls.isEmpty() && Solver.tempEls.peek().ligneNextUpd != 0) {
					Case c = Solver.tempEls.peek();
					
					grillStack.peek().updateGrille(c.ligneNextUpd, c.colNextUpd, c.sousCarreNextUpd, c.tempValue);
					
					Solver.tempEls.pop();
					//Solver.tempEls.peek().ligneNextUpd = 0;
					
				} else if(!Solver.tempEls.isEmpty()) {
					Solver.tempEls.pop();
				}
				
				
				
				int iterations=0;
				while(!grillStack.peek().estComplet()) {
					Solver.insertCandidateValue(grillStack);
					Solver.solveOrganically(grillStack);
					
					iterations++;
					if(iterations > 100) throw new UnsolvablePuzzleException("Too many iterations.");
					
				}
				
				
			
			} catch(IndexNotFoundException e){
				System.out.println(e.getMessage());
				e.printStackTrace();
				break;
			}  	
			catch(UnsolvablePathException e) {
				
				e.printStackTrace();
				
				System.out.println(e.getMessage());
				System.out.println("tempEls: " + Solver.tempEls);
				System.out.println("grid after error: " + grillStack.peek()); 
				
				if(!Solver.tempEls.isEmpty()) {
				
					Case c = Solver.tempEls.peek();
					System.out.println(c.ligne + " " + c.colonne + " tempValue: " + c.tempValue + " valeursInterdites: "+ c.valeursInterdites);
					
					c.updateValeursInterdites(c.tempValue);
					
					System.out.println(c.ligne + " " + c.colonne + " " + c.tempValue);
					
					
					grillStack.pop();
					int indexCase = grillStack.peek().forbiddenElCases.indexOf(c);
					
					if(indexCase == -1) {
						c.tempValue = 0;
						grillStack.peek().forbiddenElCases.add(c);
					} else {
						System.out.println(c + " already exists in forbiddenElCases");
						grillStack.peek().forbiddenElCases.get(indexCase).updateValeursInterdites(c.tempValue);
						grillStack.peek().forbiddenElCases.get(indexCase).tempValue = 0;
					}
					
						
					
					System.out.println("forbiddenElCases: " + grillStack.peek().forbiddenElCases);
					
					
					
					System.out.println("grid stack size after using pop: " + grillStack.size());
					//Solver.tempEls.pop();
					
					System.out.println("temp val: " + Solver.tempEls);
					continue;
					
					//System.out.println("toUpdNext false? " + Solver.tempEls.peek().toUpdateNext);
					
				
					
				} 
				
				
				
			
				
			} catch(UnsolvablePuzzleException e){
				System.out.println(e.getMessage());
				break;
				//continue;
			}
			
		}
		
		
		
	
		
		 
		 
	} 
}


