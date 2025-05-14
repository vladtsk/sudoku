package nfp136.sudoku;

import java.util.ArrayList;

public class GrillFillUtils {
	
		
	public static void applyEliminationTechnique(Grille grilleObj1, ArrayList<Integer> missing, ArrayList<Case> emptySpots) {
		
		Ligne[] ligneObjets = grilleObj1.ligneObjets;
		Colonne[] colonneObjets = grilleObj1.colonneObjets;
		SousCarre[] sousCarreObjets = grilleObj1.sousCarreObjets;
		
		for(Case spot : emptySpots) {
			 
			 int ligne = spot.ligne;
			 int col = spot.colonne;
			 int sousCarre = spot.sousCarre;
			 
			 Ligne ligneObj = ligneObjets[ligne-1];
			 Colonne colonneObj = colonneObjets[col-1];
			 SousCarre sousCarreObj = sousCarreObjets[sousCarre-1];
			 
			
			 ArrayList<Integer> valExclus = new ArrayList<>();
			 for(int m : missing) {
				 
				 if(ligneObj.contientElement(m) || colonneObj.contientElement(m) || sousCarreObj.contientElement(m)) {
					 valExclus.add(m);
				 }
				 
			 }
			 if(valExclus.size() == (missing.size() - 1)) {
				 
				 int uniqueVal = -1;
				    for (int m : missing) {
				        if (!valExclus.contains(m)) {
				            uniqueVal = m;
				            break;
				        }
				    }
				    
				    grilleObj1.updateGrille(ligne, col, sousCarre, uniqueVal);
				 
			 }
			 
		 }
		
	}
}
