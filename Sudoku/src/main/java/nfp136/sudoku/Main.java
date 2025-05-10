package nfp136.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		/*
		int[] rowEx1 = {0, 0, 5, 2, 0, 0, 1, 0, 4}; 
		int[] rowEx2 = {1, 3, 4, 0, 0, 6, 0, 8, 9}; 
		int[] rowEx3 = {0, 8, 0, 0, 0, 0, 0, 0, 0};
		int[] rowEx4 = {8, 0, 0, 0, 0, 0, 0, 0, 3};
		int[] rowEx5 = {4, 7, 0, 8, 0, 3, 9, 0, 1}; 
		int[] rowEx6 = {9, 0, 0, 0, 2, 0, 0, 0, 6};
		int[] rowEx7 = {0, 6, 0, 0, 7, 0, 5, 1, 0}; 
		int[] rowEx8 = {0, 0, 0, 4, 1, 2, 0, 0, 5};
		int[] rowEx9 = {0, 0, 0, 6, 8, 0, 0, 9, 0};
		*/
		//
		
		int[] rowEx1 = {5, 3, 0, 0, 7, 0, 0, 0, 0};
		int[] rowEx2 = {6, 0, 0, 1, 9, 5, 0, 0, 0};
		int[] rowEx3 = {0, 9, 8, 0, 0, 0, 0, 6, 0};
		int[] rowEx4 = {8, 0, 0, 0, 6, 0, 0, 0, 3};
		int[] rowEx5 = {0, 0, 0, 8, 0, 3, 0, 0, 1}; 
		int[] rowEx6 = {0, 0, 0, 0, 2, 0, 0, 0, 6}; //can solve with the 7: 700020006
		int[] rowEx7 = {0, 6, 0, 0, 0, 0, 2, 8, 0}; //961000284
		int[] rowEx8 = {0, 0, 0, 4, 1, 9, 0, 0, 5};
		int[] rowEx9 = {0, 0, 0, 0, 8, 0, 0, 7, 9}; 
		
		
		
		/*
		int[] rowEx1 = {5, 3, 4, 6, 7, 8, 9, 1, 2};
		int[] rowEx2 = {6, 7, 0, 1, 9, 5, 3, 4, 0};
		int[] rowEx3 = {0, 9, 8, 0, 0, 0, 0, 6, 0};
		int[] rowEx4 = {8, 0, 0, 0, 6, 0, 0, 0, 3};
		int[] rowEx5 = {4, 0, 0, 8, 0, 3, 0, 0, 1};
		int[] rowEx6 = {7, 0, 0, 0, 2, 0, 0, 0, 6};
		int[] rowEx7 = {0, 6, 0, 0, 0, 0, 2, 8, 0};
		int[] rowEx8 = {0, 0, 0, 4, 1, 9, 0, 0, 5};
		int[] rowEx9 = {0, 0, 0, 0, 8, 0, 0, 7, 9};
*/		



		
		int[][] grille1 = {rowEx1, rowEx2, rowEx3, rowEx4, rowEx5, rowEx6, rowEx7, rowEx8, rowEx9};
		
		System.out.println(Arrays.deepToString(grille1));
		Grille grilleObj1 = new Grille(grille1);
		
		Stack<Grille> grillStack = new Stack<>();
		grillStack.push(grilleObj1);
		
		Solver.solve(grilleObj1);
		
		
		
		
		/*ArrayList<Integer> indMinList = new ArrayList<>();
		
		
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
				res = GrilleFillSousCarre.fillMinEl(grilleObj1, indMinList); //, indMinList
				if(res.grillUpdated) updSousCarre = true;
				if(res.result == -1) break;
				indMinList.add(res.result);
	
			}
			
			indMinList.clear();
			System.out.println("row solver");
			while(indMinList.size() <9) { 
				res = GrilleFillLigne.fillMinEl(grilleObj1, indMinList);
				if(res.grillUpdated) updLigne = true;
				if(res.result == -1) break;
				else indMinList.add(res.result);
			}
			System.out.println(grilleObj1);
			
			System.out.println("column solver");
			indMinList.clear(); 
			while(indMinList.size() <9) { 
				res = GrilleFillColonne.fillMinEl(grilleObj1, indMinList);
				if(res.grillUpdated) updCol = true;
				if(res.result == -1) break;
				else indMinList.add(res.result);
	
			}
			
			
			System.out.println(grilleObj1);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" +updLigne + " " + updCol + " "+ updSousCarre);
			if(!updLigne && !updCol && !updSousCarre) break;
		}
		
		 //System.out.println("Cases à deux élements: " + grilleObj1.casesDeuxEl);
		 //System.out.println("valeursPossibles: " + grilleObj1.casesDeuxEl.get(2).valeursPossibles);
		 
		 
		 
		 
		 MinAllResult indMinRes  = grilleObj1.findMinIndexAll();
		 System.out.println("indMin " + indMinRes.index + ", type: " + indMinRes.type);
		 
		 if((indMinRes.type).equals("ligne")){
			 
			 GrilleFillLigne.fillElTry();
		 }
		 
		 */
		 
		 
	} 
	
	
	
}


