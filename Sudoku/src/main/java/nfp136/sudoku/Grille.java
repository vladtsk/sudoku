package nfp136.sudoku;

import java.util.ArrayList;
import java.util.Arrays;

public class Grille {
	
	int[][] gr;
	Ligne[] ligneObjets;
	Colonne[] colonneObjets;
	SousCarre[] sousCarreObjets;
	
	public Grille(int[][] gr) {
			
		this.gr = gr;
		
		ligneObjets = new Ligne[]{new Ligne(gr[0]), new Ligne(gr[1]), new Ligne(gr[2]), new Ligne(gr[3]), new Ligne(gr[4]), new Ligne(gr[5]), new Ligne(gr[6]), new Ligne(gr[7]), new Ligne(gr[8])};
		
		colonneObjets = new Colonne[9];
		
		for(int i = 0; i < ligneObjets.length; i++) {
			
			int[] colonne = new int[9];
			
			for(int j = 0; j < ligneObjets.length; j++) {
				colonne[j] = ligneObjets[j].getLigne()[i];
			}
			
			colonneObjets[i] = new Colonne(colonne);
			//System.out.println(Arrays.toString(colonneObjets[i].getColonne()));
			

		}
		
		//System.out.println(Arrays.toString(colonneObjets[1].getColonne()));
		
		sousCarreObjets = new SousCarre[9];
		
		int index=0;
		for(int n = 0; n < 7; n=n+3) {
			for(int i = 0; i < ligneObjets.length; i=i+3) {
				
				int[] sous_carre = new int[9];
				

				int k;
				for(int j = 0 + n; j < 3 + n; j++) {
					k = 0;
					while(k<3) {
						sous_carre[k+3*(j-n)] = ligneObjets[j].getLigne()[i+k];			
						k++;
						
					}
					  
				}
				
				sousCarreObjets[index] = new SousCarre(sous_carre);
				index++;
				
			}
			
			
			
		}
			
		
		
	}
	
	
	public int trouverLigneAvecMinEl(ArrayList<Integer> indMinList) {
		return GrilleUtilsSearch.trouverLigneAvecMinEl(ligneObjets, indMinList);
	}
	
	public int trouverColonneAvecMinEl(ArrayList<Integer> indMinList) {
		return GrilleUtilsSearch.trouverColonneAvecMinEl(colonneObjets, indMinList);
	}
	
	public int trouverSousCarreAvecMinEl(ArrayList<Integer> indMinList) {
		return GrilleUtilsSearch.trouverSousCarreAvecMinEl(sousCarreObjets, indMinList);
	}
		
	
	
	
		
	@Override
	public String toString() {
		return "grille : " + Arrays.deepToString(gr);
	}
	
}
