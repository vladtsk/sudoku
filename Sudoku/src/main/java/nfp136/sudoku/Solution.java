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
			
			
			//

			
			
			int iterations=0;
			while(!grillStack.peek().estComplet()) {
				
				
				Solver.insertCandidateValue(grillStack);
				Solver.solveOrganically(grillStack.peek());
				
				System.err.println(grillStack.peek());
				
				iterations++;
				if(iterations > 100) throw new UnsolvablePuzzleException("Too many iterations.");
				
			}
			
			//if(grillStack.peek().estComplet()) solutions.add(grillStack.peek());
			
		
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
		//System.out.println("Solution 1: " + solutions.get(0));
		//System.out.println(Solver.tempEls);
	}

	
	
	// recherche d'une solution alternative
	
	SolutionAlt.findAltSolution(grillStack, solutions);
		
	/*
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
		
	}*/
		
	}

}
