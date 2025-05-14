package nfp136.sudoku;

import java.util.ArrayList;
import java.util.Stack;

public class SolutionAlt {

	public static void findAltSolution(Stack<Grille> grillStack, ArrayList<Grille> solutions){
				
		if(!grillStack.isEmpty()) grillStack.pop();
		
		if(grillStack.isEmpty()) return;
			try {
				Case caseInterdite = Solver.tempEls.pop();
						
				grillStack.peek().forbiddenElCases.add(caseInterdite);	
				Solver.solveOrganically(grillStack.peek());				
				
				if(grillStack.peek().estComplet()) {
					solutions.add(grillStack.peek());
					return;
				}
							
				Solver.insertCandidateValue(grillStack);
				Solver.solveOrganically(grillStack.peek());
				
				if(grillStack.peek().estComplet()) {
					solutions.add(grillStack.peek());
					return;
				}
				
				
				int iterations=0;
				while(!grillStack.peek().estComplet()) {
					Solver.insertCandidateValue(grillStack);
					Solver.solveOrganically(grillStack.peek());
					
					iterations++;
					if(iterations > 100) throw new UnsolvablePuzzleException("Too many iterations.");
				}
				
				if(grillStack.peek().estComplet()) {
					solutions.add(grillStack.peek());
					return;
				}
				
			} catch(UnsolvablePathException e) { 
				
				grillStack.pop();
				if (grillStack.isEmpty()) return;
				
			}
		
	}
}
