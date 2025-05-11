package nfp136.sudoku;

import java.util.ArrayList;
import java.util.Stack;

public class Solver {
	
	static Stack<Case> tempEls = new Stack<>();
	
	public static void solveOrganically(Stack<Grille> stack) {
		Grille grid = stack.peek();
		ArrayList<Integer> indMinList = new ArrayList<>();
		
		
		
		
		FillResult res;
		boolean updLigne;
		boolean updCol;
		boolean updSousCarre;
		
		//boolean update = true;
		
		System.out.println("Starting");
		
		System.out.println(stack.peek());
		
		while(true) {
			updLigne = false;
			updCol = false;
			updSousCarre = false;
		
			while(indMinList.size() <9) { 
				res = GrilleFillSousCarre.fillMinEl(grid, indMinList); //, indMinList
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
			System.out.println(updLigne + " " + updCol + " "+ updSousCarre);
			if(!updLigne && !updCol && !updSousCarre) break;
		}
		
	}
	
	public static void insertCandidateValue(Stack<Grille> stack) {
		Grille grid = stack.peek();
		
		System.out.println("stack top: " + stack.peek());
		
		ArrayList<MinAllResult> suiviIndMin = new ArrayList<>();
		
		MinAllResult indMinRes  = grid.findMinIndexAll();
		
		while(suiviIndMin.contains(indMinRes)) {
			indMinRes  = grid.findMinIndexAll();
		}		
		
		suiviIndMin.add(indMinRes);
		
		if(indMinRes.index == -1) throw new UnsolvableAlternativePathException("No min element found");
		
		 
		 System.out.println("indMin " + indMinRes.index + ", type: " + indMinRes.type);
		 
		 if((indMinRes.type).equals("ligne")){
			 System.out.println("ligne!");
			 GrilleFillLigne.fillElTry(stack, indMinRes.index, tempEls);
			 
		 } else if((indMinRes.type).equals("col")) {
			 System.out.println("col!");
			 GrilleFillColonne.fillElTry(stack, indMinRes.index, tempEls);
			 
		 } else {
			 System.out.println("sousCarre!");
			 GrilleFillSousCarre.fillElTry(stack, indMinRes.index, tempEls);
			 
		 }
	}
}
