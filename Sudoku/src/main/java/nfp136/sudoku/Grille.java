package nfp136.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Grille {
	
	int[][] gr;
	Ligne[] ligneObjets;
	Colonne[] colonneObjets;
	SousCarre[] sousCarreObjets;
	
	ArrayList<Case> forbiddenElCases = new ArrayList<>();
	
	Stack<ValPossible> valDeuxCasesPossible = new Stack<>();
	
	
	
	ArrayList<Integer> triedIndListLigne = new ArrayList<>();
	ArrayList<Integer> triedIndListCol = new ArrayList<>();
	ArrayList<Integer> triedIndListSousCarre = new ArrayList<>();
	
	
	public Grille(int[][] gr) {
			
		this.gr = gr;
		/*this.gr = new int[9][];
		for (int i = 0; i < 9; i++) {
		    this.gr[i] = Arrays.copyOf(gr[i], 9);
		}
		*/
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
		
		
		//ArrayList<Integer> indListEmpty = new ArrayList<>();
				
		int indexMin;
		int nbElMin;
		
		if(this.allIndicesTried()) this.resetTriedIndices();
		System.err.println(this.triedIndListLigne);
		
		/*int indMinLigne = this.trouverLigneAvecMinEl(indList).index;
		int indMinCol = this.trouverColonneAvecMinEl(indList).index;
		int indMinSousCarre = this.trouverSousCarreAvecMinEl(indList).index;*/
		
		
		MinResult indMinLigneRes = this.trouverLigneAvecMinEl(this.triedIndListLigne);
		MinResult indMinColRes = this.trouverColonneAvecMinEl(this.triedIndListCol);
		MinResult indMinSousCarreRes = this.trouverSousCarreAvecMinEl(this.triedIndListSousCarre);
		
		
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
		
		if(indexMin == indMinLigneRes.index && (indexMin != -1)) {
			this.triedIndListLigne.add(indexMin);
			return new MinAllResult(indexMin, "ligne");
		} else if(indexMin == indMinColRes.index && (indexMin != -1)) {
			this.triedIndListCol.add(indexMin);
			return new MinAllResult(indexMin, "col");
		} else if(indexMin == indMinSousCarreRes.index && (indexMin != -1)) {
			this.triedIndListSousCarre.add(indexMin);
			return new MinAllResult(indexMin, "sousCarre");
		} else return new MinAllResult(-1, "none");
	}
	
	
	public MinAllResult findCandidateIndex() {
		
		int ind = -1; // l'index de la ligne avec le plus petit nombre d'éléments manquants 
		//int minNb = 100; // le plus petit nombre d'éléments manquants parmi toutes les lignes
		int nb; // le nombre courant d'éléments manquants 
		
		if(this.allIndicesTried()) this.resetTriedIndices();
		
		for(int i=0; i < ligneObjets.length; i++) {
			nb = 9-ligneObjets[i].calculerNbElements();
			if(nb != 0 && nb < 9  && !this.triedIndListLigne.contains(i)) { 
				ind = i;
				break;
				
			}
			}
		if(ind != -1) return new MinAllResult(ind, "ligne");
		
		for(int i=0; i < colonneObjets.length; i++) {
			nb = 9-colonneObjets[i].calculerNbElements();
			if(nb != 0 && nb < 9 && !this.triedIndListCol.contains(i) ) { 
				ind = i;
				break;
				
			}
			}
		if(ind != -1) return new MinAllResult(ind, "col");
		
		
		for(int i=0; i < sousCarreObjets.length; i++) {
			nb = 9-sousCarreObjets[i].calculerNbElements();
			if(nb != 0 && nb < 9 && !this.triedIndListSousCarre.contains(i) ) { 
				ind = i;
				break;
				
			}
			}
		
		if(ind != -1) return new MinAllResult(ind, "souCarre");
		return new MinAllResult(-1, "none");
	}
	
	
	public boolean updateGrille(int ligne, int col, int sousCarre, int el) { // mettre à jour une grille avec un élément 
		boolean updLigne = false;
		boolean updCol = false;
		boolean updSousCarre = false;
		
		
		updLigne = this.ligneObjets[ligne-1].updateLigne(col, el);
		updCol = this.colonneObjets[col-1].updateCol(ligne, el);
		updSousCarre = this.sousCarreObjets[sousCarre - 1].updateSousCarre(ligne, col,  el);
		
		if(updLigne && updCol && updSousCarre) {
			
			this.gr[ligne - 1][col - 1] = el;
			return true;
		}
		return false;
		
	}
	

	
	public boolean allIndicesTried() {
	    // Vérifie si 9 indices pour chaque type ont été essayés
	    return (this.triedIndListLigne.size() >= 9 && this.triedIndListCol.size() >= 9 && this.triedIndListSousCarre.size() >= 9);
	}
	
	public void resetTriedIndices() {
		this.triedIndListLigne.clear();
		this.triedIndListCol.clear();
		this.triedIndListSousCarre.clear();
	}
	
	public void updateForbiddenElCases(Case failedCase, Stack<Grille> grillStack) {
		int indexCase = grillStack.peek().forbiddenElCases.indexOf(failedCase);
		
		if(indexCase == -1) {
			//failedCase.tempValue = 0;
			grillStack.peek().forbiddenElCases.add(failedCase);
		} else {
			System.out.println(failedCase + " already exists in forbiddenElCases");
			grillStack.peek().forbiddenElCases.get(indexCase).updateValeursInterdites(failedCase.tempValue);
			grillStack.peek().forbiddenElCases.get(indexCase).tempValue = 0;
		}
	}
	
	/*
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || this.getClass()!= obj.getClass()) return false;
		
		Case other = (Case) obj;
		return other.colonne == this.colonneObjets && other.ligne == this.ligne;
	}*/
		
	@Override
	public String toString() {
		return "grille : " + Arrays.deepToString(gr);
	}
	
}
