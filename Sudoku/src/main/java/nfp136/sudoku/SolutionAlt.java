package nfp136.sudoku;

import java.util.ArrayList;
import java.util.Stack;

public class SolutionAlt {

	public static void findAltSolution(Stack<Grille> grillStack, ArrayList<Grille> solutions){
		
		// recherche d'une solution alternative
		
		
		if(!grillStack.isEmpty()) grillStack.pop();
		int counter = 0;
		
		if(grillStack.isEmpty()) return;
		
		System.err.println("grillStack.peek().estComplet() " + grillStack.peek().estComplet());
		
		
		
			
		//while(!grillStack.peek().estComplet() && !Solver.tempEls.isEmpty()) {
			
			//counter++;
			//if(counter > 100) break;
			
					
			
			try {
				Case caseInterdite = Solver.tempEls.pop();
				//grillStack.pop();
				
				System.err.println("caseInterdite" + caseInterdite);
				
				grillStack.peek().forbiddenElCases.add(caseInterdite);
				
				System.out.println("forbidden: " + grillStack.peek().forbiddenElCases);
				
				Solver.solveOrganically(grillStack.peek());				
				
				if(grillStack.peek().estComplet()) {
					solutions.add(grillStack.peek());
					return;
				}
					
				
				// ** //
				System.out.println(grillStack.size());
				System.err.println(Solver.tempEls.size());
				System.out.println("tempVal: " + Solver.tempEls.peek().tempValue);
				
		
				System.out.println("forbidden: " + grillStack.peek().forbiddenElCases);
				
				
				// ** //
				
				Solver.insertCandidateValue(grillStack);
				Solver.solveOrganically(grillStack.peek());
				
				if(grillStack.peek().estComplet()) {
					solutions.add(grillStack.peek());
					return;
				}
				
				//
				//System.err.println(grillStack.peek());
				//if(!grillStack.isEmpty()) return; // test
				
				
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
				//continue;
				
				
			}
		//}
	}
}
