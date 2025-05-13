package nfp136.sudoku;

import java.util.ArrayList;
import java.util.Stack;

public class Solver {
	
	static Stack<Case> tempEls = new Stack<>();
	//static ArrayList<Case> forbiddenElCases = new ArrayList<>();
	//static ArrayList<MinAllResult> triedMin = new ArrayList<>(); // des valeurs minimum déjà essayé dans insertCandidateValue
	
	
	
	public static void solveOrganically(Stack<Grille> stack) { 
		Grille grid = stack.peek();
		ArrayList<Integer> indMinList = new ArrayList<>();
		
		
		
		
		FillResult res;
		boolean updLigne;
		boolean updCol;
		boolean updSousCarre;
		
		//boolean update = true;
		
		System.out.println("Starting");
		
		//System.out.println(stack.peek());
		
		//while(!updLigne && !updCol && !updSousCarre) 
		do {
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
			
			//if(!updLigne && !updCol && !updSousCarre) break;
		} while (updLigne || updCol || updSousCarre);
		
	}
	
	
	public static void insertCandidateValue(Stack<Grille> stack) {
		Grille grid = stack.peek();
		
		System.out.println("stack top: " + stack.peek());
		
		
		//ArrayList<MinAllResult> suiviIndMin = new ArrayList<>();
		
		
		boolean updateSuccess = false; //variable pour suivre des mis à jour après fillElTry

		while(!updateSuccess) {
			MinAllResult indMinRes  = grid.findMinIndexAll();
			
			//int count = 0;
			//while(suiviIndMin.contains(indMinRes)) {
				//indMinRes  = grid.findMinIndexAll();
				//count ++;
				//if(count>100) throw new IllegalStateException("Exceeded maximum number of attempts to find the minIndex in insertCandidateValue");
			//}		
			
			//suiviIndMin.add(indMinRes);
			
			
			
			if(indMinRes.index == -1) indMinRes = grid.findCandidateIndex();
			if(indMinRes.index == -1) throw new IndexNotFoundException("No min element found");
			 
			 System.out.println("indMin " + indMinRes.index + ", type: " + indMinRes.type);
			 
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
	
	/*public static void findAlternativeSolution(Stack<Grille> stack) {
		
		System.out.println(stack.peek()); 
		System.out.println(tempEls());
		
	}*/
	
	
}
