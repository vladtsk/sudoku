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
		/*this.gr = new int[9][9];
		for (int i = 0; i < 9; i++) {
		    this.gr[i] = Arrays.copyOf(gr[i], 9);
		}*/
		
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
	
	
	public MinResult trouverLigneAvecMinEl(ArrayList<Integer> indMinList ) { 
		return GrilleUtilsSearch.trouverLigneAvecMinEl(ligneObjets, indMinList); 
	}
	
	public MinResult trouverColonneAvecMinEl(ArrayList<Integer> indMinList) { 
		return GrilleUtilsSearch.trouverColonneAvecMinEl(colonneObjets, indMinList); 
	}
	
	public MinResult trouverSousCarreAvecMinEl(ArrayList<Integer> indMinList) { 
		return GrilleUtilsSearch.trouverSousCarreAvecMinEl(sousCarreObjets, indMinList); 
	}
	
	public boolean estComplet() {
		
		boolean estComplet = false;
		int nbEl;
		
		for(int i = 0; i < this.ligneObjets.length; i++) {
			
			nbEl = this.ligneObjets[i].calculerNbElements();
			if(nbEl<9) return false;
			
		}
		estComplet = true;
		
		return estComplet;
	}
	
	public MinAllResult findMinIndexAll() { // trouve l'indice min partout (ligne, colonne, sousCarre) 
		
		
		ArrayList<Integer> indList = new ArrayList<>();
		int indexMin;
		int nbElMin;
		
		/*int indMinLigne = this.trouverLigneAvecMinEl(indList).index;
		int indMinCol = this.trouverColonneAvecMinEl(indList).index;
		int indMinSousCarre = this.trouverSousCarreAvecMinEl(indList).index;*/
		
		
		MinResult indMinLigneRes = this.trouverLigneAvecMinEl(indList);
		MinResult indMinColRes = this.trouverColonneAvecMinEl(indList);
		MinResult indMinSousCarreRes = this.trouverSousCarreAvecMinEl(indList);
		
		indexMin = indMinLigneRes.index;
		nbElMin = indMinLigneRes.nb;
		if((indMinColRes.nb<nbElMin) || (indexMin == -1)) {
			nbElMin = indMinColRes.nb;
			indexMin = indMinColRes.index;
		};
			
		if((indMinSousCarreRes.nb<nbElMin) || (indexMin == -1)) {
			nbElMin = indMinSousCarreRes.nb;
			indexMin = indMinSousCarreRes.index;
		}; 
		
		if(indexMin == indMinLigneRes.index) return new MinAllResult(indexMin, "ligne");
		else if(indexMin == indMinColRes.index) return new MinAllResult(indexMin, "col");
		else return new MinAllResult(indexMin, "sousCarre");
	}
	
	/*public void removeCasesDeuxEl(int row, int column) {
		Case case1 = new Case(row, column); 
		
		int index = casesDeuxEl.indexOf(case1);
		if(index != -1){
			System.out.println("contains, index: " + index);
			casesDeuxEl.remove(index);
		} 
		
	
	}*/	
	
	
		
	@Override
	public String toString() {
		return "grille : " + Arrays.deepToString(gr);
	}
	
}
