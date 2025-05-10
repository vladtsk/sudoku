package nfp136.sudoku;

import java.util.ArrayList;

public class Solver {
	
	public static void solve(Grille grid) {
		ArrayList<Integer> indMinList = new ArrayList<>();
		
		
		FillResult res;
		boolean updLigne;
		boolean updCol;
		boolean updSousCarre;
		
		//boolean update = true;
		
		System.out.println("Starting");
		
		
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
		
		 
		 
		 
		 MinAllResult indMinRes  = grid.findMinIndexAll();
		 System.out.println("indMin " + indMinRes.index + ", type: " + indMinRes.type);
		 
		 if((indMinRes.type).equals("ligne")){
			 
			 GrilleFillLigne.fillElTry(grid);
		 }
	}
}
