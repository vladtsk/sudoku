package nfp136.sudoku;

import java.util.ArrayList;
import java.util.Stack;

public class Solution {
	
	public static void findSolutions(Stack<Grille> grillStack, ArrayList<Grille> solutions) {
		
		try {
						
			if(!Solver.tempEls.isEmpty() && Solver.tempEls.peek().ligneNextUpd != 0) {
				Case failedCase  = Solver.tempEls.pop();
				
				grillStack.peek().updateGrille(failedCase.ligneNextUpd, failedCase.colNextUpd, failedCase.sousCarreNextUpd, failedCase.tempValue);
				System.err.println("Upd grid, ligne: " + failedCase.ligneNextUpd + "col: " + failedCase.colNextUpd + "value: " + failedCase.tempValue);
				
			} else if(!Solver.tempEls.isEmpty()) {
				Solver.tempEls.pop();
			}
			
			int iterations=0;
			while(!grillStack.peek().estComplet()) {
				
				
				Solver.insertCandidateValue(grillStack);
				Solver.solveOrganically(grillStack.peek());
				
				iterations++;
				if(iterations > 100) throw new UnsolvablePuzzleException("Too many iterations.");
				
			}
					
		} catch(IndexNotFoundException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}  	
		catch(UnsolvablePathException e) {
			
			e.printStackTrace();
			
			if(!Solver.tempEls.isEmpty()) {
				Case failedCase = Solver.tempEls.peek();
				failedCase.updateValeursInterdites(failedCase.tempValue);
				grillStack.pop();
				grillStack.peek().updateForbiddenElCases(failedCase, grillStack);	
			} 
						
		} catch(UnsolvablePuzzleException e){
			System.out.println(e.getMessage());
				}
		
	if(grillStack.peek().estComplet()) {
		solutions.add(grillStack.peek()); 
	}
	
	// recherche d'une solution alternative
	
	SolutionAlt.findAltSolution(grillStack, solutions);
				
	}

}
