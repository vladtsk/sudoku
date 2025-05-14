/*package nfp136.sudoku;

import java.util.Arrays;
import java.util.Stack;

public class SolverUtils {
	
	public static boolean solveByInsertingValue(Stack<Grille> stack) {
		
		Grille grid = stack.peek();
		
		boolean upd = false;
		
		System.out.println(grid);
		System.err.println(grid.valDeuxCasesPossible);
		
		// on va essayer deux possibilité en utilisant valDeuxCasesPossible
		
		boolean[] results = new boolean[] {false, false}; 
		
		Grille newGrid1 = GrillUtils.createArrayCopy(grid);
		//System.out.println(newGrid1.valDeuxCasesPossible);
		
		
		if (grid.valDeuxCasesPossible.isEmpty()) {
		    System.err.println("No values in valDeuxCasesPossible...");
		    return false;
		}
		
		ValPossible val = grid.valDeuxCasesPossible.peek();
		
		
		Grille newGrid2 = GrillUtils.createArrayCopy(grid);
		//System.out.println(newGrid2.valDeuxCasesPossible);
		
		
		try {
			
			// on essaye une possibilité (sur 2) en utilisant le dernier élément de valDeuxCasesPossible
			newGrid1.updateGrille(val.ligne1, val.col1, val.sousCarre1, val.value);
			System.err.println(newGrid1.ligneObjets[val.ligne1-1]);
			
			
			Solver.solveOrganically(newGrid1);
			
			System.err.println(newGrid1);
			results[0] = true;
			
			
			
		} catch(Exception e) {
			
			System.err.println(e.getMessage());
			System.err.println("la première valeur n'est pas valide");
			
		}
		
		
		try {
			
			// on essaye la 2e possibilité  en utilisant le dernier élément de valDeuxCasesPossible
			newGrid2.updateGrille(val.ligne2, val.col2, val.sousCarre2, val.value);
			System.err.println(newGrid2.ligneObjets[val.ligne2-1]);
			
			
			
			Solver.solveOrganically(newGrid2);
			
			results[1] = true;
			
			
			
			
		} catch(Exception e) {
			
			System.err.println(e.getMessage());
			System.err.println("la 2e valeur n'est pas valide");
			
		}
		
		
		System.err.println(newGrid1);
		System.err.println(newGrid2);
		System.out.println(Arrays.toString(results));
		
		if(results[0] && !results[1]) {
			stack.pop();
			newGrid1.valDeuxCasesPossible.pop();
			stack.push(newGrid1);
			upd = true;
			
		} else if(!results[0] && results[1]) {
			stack.pop();
			newGrid2.valDeuxCasesPossible.pop();
			stack.push(newGrid2);
			upd = true;
		} else if(results[0] && results[1]) { //on garde l'un ou l'autre
			newGrid1.valDeuxCasesPossible.pop();
			stack.push(newGrid1);
			Solver.tempEls.add(new Case(val.ligne1, val.col1));
			
			upd = true;
		}
		
		return upd;
	}

}
*/