package nfp136.sudoku;

import java.util.ArrayList;
import java.util.Arrays;

public class SousCarre {
	static int sousCarreCompteur = 0;
	int numSousCarreCourant;
	
	private int[] sousCarre;
	int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	
	ArrayList<Integer> elementsManquants = new ArrayList<>();
	ArrayList<Case> casesVides = new ArrayList<>();
	
	Grille parentGrille;

	public SousCarre(int[] sousCarre, Grille parentGrille) {
		this.sousCarre = sousCarre;
		this.parentGrille = parentGrille;
		
		sousCarreCompteur++;
		numSousCarreCourant = sousCarreCompteur;
		
		for(int num : nums) {
			boolean found = false;
			for(int el : sousCarre) {
				if(el == num) {
					found = true;
					break;
				}
			}
			if(found == false) {
				elementsManquants.add(num);
			}
			
		} 
	}
	
	public SousCarre(int[] sousCarre) {
		this(sousCarre, null);
	}
	
	public int calculerNbElements() {
		int nb_el = 0;
		for(int el : sousCarre) {
			if(el != 0) {
				nb_el++;
				};
		}
		
		return nb_el;
	}
	
	public int[] getSousCarre() {
		return sousCarre;
	}
	
	public ArrayList<Integer> getElementsManquants() {
		elementsManquants.clear();
		
		for(int num : nums) {
			boolean found = false;
			for(int el : sousCarre) {
				if(el == num) {
					found = true;
					break;
				}
			}
			if(found == false) {
				elementsManquants.add(num);
			}
			
		} 
		
		return elementsManquants;
	}
	
	public ArrayList<Case> getCasesVides() {
		casesVides.clear();
		int i;
		int row = 0;
		int column = 0;
		
		for(i = 0; i < sousCarre.length; i++) {
			if(sousCarre[i] == 0) {
				
					if(i<3) { 
						if(numSousCarreCourant >= 1 && numSousCarreCourant <=3) {
							row = 1;
						} else if(numSousCarreCourant >= 4 && numSousCarreCourant <=6) {
							row = 4;
						} else {
							row = 7;
						}
						//numSousCarreCourant=1, 2, 3 => row=1
						//numSousCarreCourant=4, 5, 6 => row=4
						//numSousCarreCourant=7, 8, 9 => row=7
						//row = numSousCarreCourant / 3 + 3;
						if(numSousCarreCourant %3 == 1) {
							column = i+1;
						} else if(numSousCarreCourant %3 ==2) {
							column = i+4;
						} else {
							column = i+7;
						}
						
					} else if(i>=3 && i<6) {
						if(numSousCarreCourant >= 1 && numSousCarreCourant <=3) {
							row = 2;
						} else if(numSousCarreCourant >= 4 && numSousCarreCourant <=6) {
							row = 5;
						} else {
							row = 8;
						}
						//numSousCarreCourant=1, 2, 3 => row=2
						//numSousCarreCourant=4, 5, 6 => row=5
						//numSousCarreCourant=7, 8, 9 => row=8
						if(numSousCarreCourant %3 ==1) {
							column = i-2;
						} else if(numSousCarreCourant %3 ==2) {
							column = i+1;
						} else {
							column = i+4;
						}
						
					} else if(i>=6 && i<9) {
						if(numSousCarreCourant >= 1 && numSousCarreCourant <=3) {
							row = 3;
						} else if(numSousCarreCourant >= 4 && numSousCarreCourant <=6) {
							row = 6;
						} else {
							row = 9;
						}
						//numSousCarreCourant=1, 2, 3 => row=3
						//numSousCarreCourant=4, 5, 6 => row=6
						//numSousCarreCourant=7, 8, 9 => row=9
						if(numSousCarreCourant %3 ==1) {
							column = i-5;
						} else if(numSousCarreCourant %3 ==2) {
							column = i-2;
						} else {
							column = i+1;
						}
					}
				System.out.println("row " + row + ", column:" + column);
				casesVides.add(new Case(row, column));
			}
		}
		System.out.println(casesVides);
		return casesVides;
	}
	
	public boolean contientElement(int el) {
		boolean contient = false;
		for(int element : sousCarre) {
			if(element == el) contient = true;
		}
		return contient;
	}
	
	public void updateSousCarre(int ligne, int col, int el) {
		int index;
		if(ligne == numSousCarreCourant-2) {
			index = col - 6;
		} else if(ligne == numSousCarreCourant-1) {
			index = col - 3;
		} else if(ligne == numSousCarreCourant) {
			index = col;
		} else if(ligne == numSousCarreCourant+1) {
			index = col + 3;
		} else {                   // if(ligne == numSousCarreCourant+2)
			index = col + 6;
		}
				
		sousCarre[index-1] = el;
		System.out.println("Updating sousCarré " + numSousCarreCourant + ": " + "with el " + el);
		
		parentGrille.removeCasesDeuxEl(ligne, col);
	}
	
	@Override
	public String toString() {
		return "sousCarré : " + Arrays.toString(sousCarre);
	}
	
	
		
}
