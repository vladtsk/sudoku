package nfp136.sudoku;

import java.util.ArrayList;
import java.util.Arrays;

public class Colonne {
	static int colonneCompteur = 0;
	int numColonneCourante;
	
	private int[] colonne;
	int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	
	ArrayList<Integer> elementsManquants = new ArrayList<>();
	ArrayList<Case> casesVides = new ArrayList<>();

	public Colonne(int[] colonne) {
		this.colonne = colonne;
		colonneCompteur++;
		numColonneCourante = colonneCompteur;
		
		for(int num : nums) {
			boolean found = false;
			for(int el : colonne) {
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
	
	public int calculerNbElements() {
		int nb_el = 0;
		for(int el : colonne) {
			if(el != 0) {
				nb_el++;
				};
		}
		
		return nb_el;
	}
	
	public int[] getColonne() {
		return colonne;
	}
	
	public ArrayList<Integer> getElementsManquants() {
		elementsManquants.clear();
		
		for(int num : nums) {
			boolean found = false;
			for(int el : colonne) {
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
		for(i = 0; i < colonne.length; i++) {
			if(colonne[i] == 0) {
				casesVides.add(new Case(i+1, numColonneCourante));
			}
		}
		System.out.println(casesVides);
		return casesVides;
	}
	
	public boolean contientElement(int el) {
		boolean contient = false;
		for(int element : colonne) {
			if(element == el) contient = true;
		}
		return contient;
	}
	
	public void updateCol(int ligne, int el) {
		colonne[ligne-1] = el;
		System.out.println("Updating colonne " + numColonneCourante + ": " + "with el " + el);
	}
	
	@Override
	public String toString() {
		return "colonne : " + Arrays.toString(colonne);
	}
}
