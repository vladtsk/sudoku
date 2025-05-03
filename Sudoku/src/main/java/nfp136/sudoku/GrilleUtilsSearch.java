package nfp136.sudoku;

import java.util.ArrayList;
import java.util.Arrays;

public class GrilleUtilsSearch {
	
	public static MinResult trouverLigneAvecMinEl(Ligne[] ligneObjets, ArrayList<Integer> indMinList) {
		int ind = -1; // l'index de la ligne avec le plus petit nombre d'éléments manquants 
		int minNb = 100; // le plus petit nombre d'éléments manquants parmi toutes les lignes
		int nb; // le nombre courant d'éléments manquants 
		
		for(int i=0; i < ligneObjets.length; i++) {
			nb = 9-ligneObjets[i].calculerNbElements();
			if(nb < minNb && nb != 0 && nb < 9 && !indMinList.contains(i) ) { 
				minNb = nb;
				ind = i;
				
			}
			}
		
		if(ind == -1) return new MinResult(-1, -1);
		
		System.out.println("minNb (ligne): " + minNb + " ind: " + ind);
		System.out.println(Arrays.toString(ligneObjets[ind].getLigne()));
		
		return new MinResult(minNb, ind);
	}
	
	public static MinResult trouverColonneAvecMinEl(Colonne[] colonneObjets, ArrayList<Integer> indMinList) {
		int ind = -1; // l'index de la ligne avec le plus petit nombre d'éléments manquants 
		int minNb = 100; // le plus petit nombre d'éléments manquants parmi toutes les lignes
		int nb; // le nombre courant d'éléments manquants 
		
		for(int i=0; i < colonneObjets.length; i++) {
			nb = 9 - colonneObjets[i].calculerNbElements();
			if(nb < minNb && nb != 0 && nb < 4 ) {
				minNb = nb;
				ind = i;
				
			}
			}
		
		if(ind == -1) return new MinResult(-1, -1);
		
		System.out.println("minNb (colonne): " + minNb + " ind: " + ind);
		System.out.println(Arrays.toString(colonneObjets[ind].getColonne()));
		
		return new MinResult(minNb, ind);
	}
	
	public static MinResult trouverSousCarreAvecMinEl(SousCarre[] sousCarreObjets, ArrayList<Integer> indMinList) {
		int ind = -1; // l'index de la ligne avec le plus petit nombre d'éléments manquants 
		int minNb = 100; // le plus petit nombre d'éléments manquants parmi toutes les lignes
		int nb; // le nombre courant d'éléments manquants 
		
		System.out.println("ind " + ind);
		for(int i=0; i < sousCarreObjets.length; i++) {
			nb = 9-sousCarreObjets[i].calculerNbElements();
			if(nb < minNb && nb != 0 && nb < 4 ) {
				minNb = nb;
				ind = i;
				
			}
			}
		System.out.println("ind (after loop) " + ind);
		if(ind == -1) return new MinResult(-1, -1);
		
		System.out.println("minNb (sousCarré): " + minNb + " ind: " + ind);
		System.out.println(Arrays.toString(sousCarreObjets[ind].getSousCarre()));
		
		return new MinResult(minNb, ind);
	}
	
}
