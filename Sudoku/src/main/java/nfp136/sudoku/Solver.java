package nfp136.sudoku;

import java.util.ArrayList;
import java.util.Stack;

public class Solver {
	
	static Stack<Case> tempEls = new Stack<>();
		
	public static void solveOrganically(Grille grid) { //Stack<Grille> stack
	
		ArrayList<Integer> indMinList = new ArrayList<>();
					
		FillResult res;
		boolean updLigne;
		boolean updCol;
		boolean updSousCarre;
		
		System.out.println("Starting");
	
		do {
			updLigne = false;
			updCol = false;
			updSousCarre = false;
					
			indMinList.clear();
			while(indMinList.size() <9) { 
				res = GrilleFillSousCarre.fillMinEl(grid, indMinList); 
				if(res.grillUpdated) updSousCarre = true;
				if(res.result == -1) break;
				indMinList.add(res.result);
	
			}
			
			indMinList.clear();
			System.out.println("row solver");
			while(indMinList.size() <9) { 
				res = GrilleFillLigne.fillMinEl(grid, indMinList);
				if(res.grillUpdated) updLigne = true;
				if(res.result == -1) break;
				else indMinList.add(res.result);
			}
			System.out.println(grid);
			
			System.out.println("column solver");
			indMinList.clear(); 
			while(indMinList.size() <9) { 
				res = GrilleFillColonne.fillMinEl(grid, indMinList);
				if(res.grillUpdated) updCol = true;
				if(res.result == -1) break;
				else indMinList.add(res.result);
	
			}
			
			
			System.out.println(grid);
			
		} while (updLigne || updCol || updSousCarre);
		
	}
	
	
	
	
	
	
	
	
	
	public static void insertCandidateValue(Stack<Grille> stack) {
		Grille grid = stack.peek();
		
		System.out.println("stack top: " + stack.peek());
		
		
		//ArrayList<MinAllResult> suiviIndMin = new ArrayList<>();
		
		
		boolean updateSuccess = false; //variable pour suivre des mis à jour après fillElTry

		while(!updateSuccess) {
			MinAllResult indMinRes  = grid.findMinIndexAll();		
			
			if(indMinRes.index == -1) indMinRes = grid.findCandidateIndex();
			System.out.println("indMin " + indMinRes.index + ", type: " + indMinRes.type);
			
			if(indMinRes.index == -1) throw new IndexNotFoundException("No min element found");
			 
			 
			 
			 switch(indMinRes.type) {
			 case "ligne": 
				 updateSuccess = GrilleFillLigne.fillElTry(stack, indMinRes.index);
				 break;
			 case "col":
				 updateSuccess = GrilleFillColonne.fillElTry(stack, indMinRes.index);
				 break;
			 default: 
				 updateSuccess = GrilleFillSousCarre.fillElTry(stack, indMinRes.index);
				 
			 }
			 
			 
		}
		
	}
	

	
	
}
